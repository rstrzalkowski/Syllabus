import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getLoggedInUser() {
    return this.http.get<User>(`${environment.apiUrl}/users/me`)
  }

  updateAbout(newAbout: string) {
    return this.http.put(`${environment.apiUrl}/users/me`, {description: newAbout}, {observe: "response"})
  }
}
