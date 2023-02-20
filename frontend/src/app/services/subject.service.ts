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

  getAllActiveSubjects() {
    return this.http.get<SubjectPage>(`${environment.apiUrl}/subjects?sort=createdAt,desc`)
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

  createSubject(name: string | undefined, abbreviation: string | undefined) {
    return this.http.post(`${environment.apiUrl}/subjects`, {
      name: name,
      abbreviation: abbreviation,
    }, {observe: "response"})
  }

  updateSubjectImage(subjectId: number | undefined, image: any) {
    const formData = new FormData();
    formData.append("image", image)
    return this.http.put(`${environment.apiUrl}/subjects/${subjectId}/image`, formData, {observe: "response"})
  }

  updateSubject(subjectId: number | undefined, name: string | undefined, abbreviation: string | undefined) {
    return this.http.put(`${environment.apiUrl}/subjects/${subjectId}`, {
      name: name,
      abbreviation: abbreviation
    }, {observe: "response"})
  }
}
