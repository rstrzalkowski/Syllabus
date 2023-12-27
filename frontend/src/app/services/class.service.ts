import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Class, ClassPage} from "../model/class";

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  constructor(private http: HttpClient) {
  }

  getAllActiveClasses() {
    return this.http.get<ClassPage>(`${environment.apiUrl}/classes?sort=level`)
  }

  getClassById(id: string | undefined) {
    return this.http.get<Class>(`${environment.apiUrl}/classes/${id}`)
  }

  getActiveClasses(page: number | undefined) {
    return this.http.get<ClassPage>(`${environment.apiUrl}/classes?page=${page}&size=6&sort=level`)
  }

  getArchivedClasses(page: number | undefined) {
    return this.http.get<ClassPage>(`${environment.apiUrl}/classes/archived?page=${page}&size=6&sort=level`)
  }

  createClass(shortName: string | undefined, fullName: string | undefined, level: number | undefined, teacherId: number | undefined) {
    return this.http.post(`${environment.apiUrl}/classes`, {
      shortName: shortName,
      fullName: fullName,
      level: level,
      teacherId: teacherId
    }, {observe: "response"})
  }

  updateClass(classId: string | undefined, shortName: string | undefined, fullName: string | undefined, level: string | undefined, teacherId: string | undefined) {
    return this.http.put(`${environment.apiUrl}/classes`, {
      id: classId,
      shortName: shortName,
      fullName: fullName,
      level: level,
      teacherId: teacherId
    }, {observe: "response"})
  }

  archiveClass(levelId: string | undefined) {
    return this.http.delete(`${environment.apiUrl}/classes/${levelId}`)
  }
}
