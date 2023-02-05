import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(username: string, password: string) {
    console.log(username)
    console.log(password)
    return this.http.post(`${environment.apiUrl}/authorize`, {username, password}, {observe: "response"})
  }
}
