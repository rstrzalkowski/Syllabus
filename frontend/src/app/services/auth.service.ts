import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {BehaviorSubject} from "rxjs";
import jwt_decode from "jwt-decode";
import {Router} from "@angular/router";
import {AlertService} from "./alert.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authenticated = new BehaviorSubject<boolean>(false)

  constructor(private http: HttpClient,
              private router: Router,
              private alertService: AlertService) {
    let jwt = this.getJwtFromStorage()
    if (jwt == undefined) {
      return
    }
    let decodedJWT = this.decodeJWT(jwt)
    if (decodedJWT == null) {
      return
    }
    this.authenticated.next(true)
  }

  login(username: string, password: string) {
    return this.http.post(`${environment.apiUrl}/authorize`, {username, password}, {observe: "response"})
  }

  register(email: string, password: string, firstName: string, lastName: string, personalId: string, registrationToken: string) {
    return this.http.post(`${environment.apiUrl}/register`, {
      email: email,
      password: password,
      firstName: firstName,
      lastName: lastName,
      personalId: personalId,
      registrationToken: registrationToken
    }, {observe: "response"})
  }

  logout() {
    this.clearJwt()
    this.authenticated.next(false)
  }

  jwtExpired() {
    this.clearJwt()
    this.authenticated.next(false)
    this.router.navigate(['/login'])
    this.alertService.showAlert("info", "Your session has expired. Please log in again.")
  }

  saveJWT(result: any) {
    localStorage.setItem("jwt", result.body.token)
    this.authenticated.next(true)
  }

  getJwtFromStorage() {
    return localStorage.getItem("jwt")
  }

  clearJwt() {
    localStorage.removeItem("jwt")
  }

  getRole() {
    let decoded = this.decodeJWT(this.getJwtFromStorage() || "")
    return decoded.role
  }

  decodeJWT(token: string): any {
    try {
      return jwt_decode(token)
    } catch (Error) {
      return null
    }
  }

  canArchive() {
    return this.getRole() == 'DIRECTOR' || this.getRole() == 'ADMIN'
  }
}
