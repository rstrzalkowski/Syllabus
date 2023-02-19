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
    return this.http.get<ClassPage>(`${environment.apiUrl}/classes?sort=level.value,createdAt`)
  }

  getClassById(id: number | undefined) {
    return this.http.get<Class>(`${environment.apiUrl}/classes/${id}`)
  }

  getActiveClasses(page: number | undefined) {
    return this.http.get<ClassPage>(`${environment.apiUrl}/classes?page=${page}&size=6&sort=level.value,createdAt`)
  }

  getArchivedClasses(page: number | undefined) {
    return this.http.get<ClassPage>(`${environment.apiUrl}/classes/archived?page=${page}&size=6&sort=level.value,createdAt`)
  }

  createClass(shortName: string | undefined, fullName: string | undefined, levelId: number | undefined, teacherId: number | undefined) {
    return this.http.post(`${environment.apiUrl}/classes`, {
      shortName: shortName,
      fullName: fullName,
      levelId: levelId,
      teacherId: teacherId
    }, {observe: "response"})
  }

  updateClass(classId: number | undefined, shortName: string | undefined, fullName: string | undefined, levelId: number | undefined, teacherId: number | undefined) {
    return this.http.put(`${environment.apiUrl}/classes/${classId}`, {
      shortName: shortName,
      fullName: fullName,
      levelId: levelId,
      teacherId: teacherId
    }, {observe: "response"})
  }

  archiveClass(levelId: number | undefined) {
    return this.http.delete(`${environment.apiUrl}/classes/${levelId}`)
  }
}
