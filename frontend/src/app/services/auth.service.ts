import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {BehaviorSubject} from "rxjs";
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authenticated = new BehaviorSubject<boolean>(false)

  constructor(private http: HttpClient) {
  }

  public login(username: string, password: string) {
    return this.http.post(`${environment.apiUrl}/authorize`, {username, password}, {observe: "response"})
  }

  setLoggedInUser(result: any) {
    localStorage.setItem("jwt", result.body.token)
    this.authenticated.next(true)
  }

  getJwtFromStorage() {
    return localStorage.getItem("jwt");
  }

  getRole() {
    let decoded = this.decodeJWT(this.getJwtFromStorage() || "");
    return decoded.role;
  }

  decodeJWT(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }
}
