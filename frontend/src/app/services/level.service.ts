import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {LevelPage} from "../model/level";

@Injectable({
  providedIn: 'root'
})
export class LevelService {

  constructor(private http: HttpClient) {
  }

  getActiveLevels(page: number | undefined) {
    return this.http.get<LevelPage>(`${environment.apiUrl}/levels?page=${page}&size=6&sort=value`)
  }

  getAllActiveLevels() {
    return this.http.get<LevelPage>(`${environment.apiUrl}/levels?sort=value`)
  }

  getArchivedLevels(page: number | undefined) {
    return this.http.get<LevelPage>(`${environment.apiUrl}/levels/archived?page=${page}&size=6&sort=value`)
  }

  createLevel(value: number | undefined) {
    return this.http.post(`${environment.apiUrl}/levels`, {level: value}, {observe: "response"})
  }

  updateLevel(levelId: number | undefined, value: number | undefined) {
    return this.http.put(`${environment.apiUrl}/levels/${levelId}`, {level: value})
  }

  archiveLevel(levelId: number | undefined) {
    return this.http.delete(`${environment.apiUrl}/levels/${levelId}`)
  }
}
