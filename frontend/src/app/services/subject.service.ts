import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {SubjectPage} from "../model/subject";

@Injectable({
  providedIn: 'root'
})
export class SubjectService {


  constructor(private http: HttpClient) {
  }

  getActiveSubjects(page: number) {
    return this.http.get<SubjectPage>(`${environment.apiUrl}/subjects?size=6&page=${page}&sort=createdAt,desc`)
  }

  getArchivedSubjects(page: number) {
    return this.http.get<SubjectPage>(`${environment.apiUrl}/subjects/archived?size=6&page=${page}&sort=createdAt,desc`)
  }

  archiveSubject(subjectId: number | undefined) {
    return this.http.delete(`${environment.apiUrl}/subjects/${subjectId}`)
  }
}
