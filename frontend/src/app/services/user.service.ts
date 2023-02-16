import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {User, UserPage} from "../model/user";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public user: User | undefined

  constructor(private http: HttpClient,
              private authService: AuthService) {
    if (this.authService.authenticated.value) {
      this.getLoggedInUser()
    } else {
      this.authService.authenticated.subscribe((authenticated) => {
        if (authenticated) {
          this.getLoggedInUser()
        } else {
          this.user = undefined
        }
      })
    }
  }

  getAllActiveTeachers() {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/teachers`)
  }

  getAllNotSupervisingActiveTeachers() {
    return this.http.get<User[]>(`${environment.apiUrl}/users/teachers/free?sort=lastName,firstName`)
  }

  getLoggedInUserObservable() {
    return this.http.get<User>(`${environment.apiUrl}/users/me`)
  }

  getLoggedInUser() {
    this.http.get<User>(`${environment.apiUrl}/users/me`).subscribe((result) => {
      this.user = result;
    })
  }

  updateAbout(newAbout: string) {
    return this.http.put(`${environment.apiUrl}/users/me/description`, {description: newAbout}, {observe: "response"})
  }

  changePassword(oldPassword: string, newPassword: string) {
    return this.http.put(`${environment.apiUrl}/users/me/password`, {
      oldPassword: oldPassword,
      newPassword: newPassword
    }, {observe: "response"})
  }
}
