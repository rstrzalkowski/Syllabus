import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {User, UserPage} from "../model/user";
import {AuthService} from "./auth.service";
import {TokenPage} from "../model/token";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public user: User | undefined

  constructor(private http: HttpClient, private authService: AuthService) {
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

  generateCodes(amount: number, role: string, schoolClassId: number | undefined) {
    if (schoolClassId) {
      return this.http.post(`${environment.apiUrl}/users/tokens`, {
        amount: amount,
        role: role,
        schoolClassId: schoolClassId
      })
    } else {
      return this.http.post(`${environment.apiUrl}/users/tokens`, {
        amount: amount,
        role: role,
      })
    }
  }

  getAllActiveTeachers() {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/teachers?sort=lastName,firstName`)
  }

  getActiveUsers(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users?size=10&page=${page}&sort=lastName,firstName`)
  }

  getArchivedUsers(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/archived?size=10&page=${page}&sort=lastName,firstName`)
  }

  getActiveStudents(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/students?size=10&page=${page}&sort=lastName,firstName`)
  }

  getActiveTeachers(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/teachers?size=10&page=${page}&sort=lastName,firstName`)
  }

  getActiveOffices(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/offices?size=10&page=${page}&sort=lastName,firstName`)
  }

  getActiveDirectors(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/directors?size=10&page=${page}&sort=lastName,firstName`)
  }

  getArchivedStudents(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/students/archived?size=10&page=${page}&sort=lastName,firstName`)
  }

  getArchivedTeachers(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/teachers/archived?size=10&page=${page}&sort=lastName,firstName`)
  }

  getArchivedOffices(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/offices/archived?size=10&page=${page}&sort=lastName,firstName`)
  }

  getArchivedDirectors(page: number | undefined) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/directors/archived?size=10&page=${page}&sort=lastName,firstName`)
  }

  getAllNotSupervisingActiveTeachers() {
    return this.http.get<User[]>(`${environment.apiUrl}/users/teachers/free?sort=lastName,firstName`)
  }

  getStudentTokens(page: number | undefined) {
    return this.http.get<TokenPage>(`${environment.apiUrl}/users/tokens/students?size=10&page=${page}&sort=createdAt,desc`)
  }

  getTeacherTokens(page: number | undefined) {
    return this.http.get<TokenPage>(`${environment.apiUrl}/users/tokens/teachers?size=10&page=${page}&sort=createdAt,desc`)
  }

  getOfficeTokens(page: number | undefined) {
    return this.http.get<TokenPage>(`${environment.apiUrl}/users/tokens/offices?size=10&page=${page}&sort=createdAt,desc`)
  }

  getDirectorTokens(page: number | undefined) {
    return this.http.get<TokenPage>(`${environment.apiUrl}/users/tokens/directors?size=10&page=${page}&sort=createdAt,desc`)
  }

  getLoggedInUserObservable() {
    return this.http.get<User>(`${environment.apiUrl}/users/me`)
  }

  getLoggedInUser() {
    this.http.get<User>(`${environment.apiUrl}/users/me`).subscribe((result) => {
      this.user = result;
    })
  }

  getUser(userId: number | undefined) {
    return this.http.get<User>(`${environment.apiUrl}/users/${userId}`)
  }

  searchUsers(keyword: string) {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/search?keyword=${keyword}&size=5`)
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

  getUnassignedStudents() {
    return this.http.get<UserPage>(`${environment.apiUrl}/users/unassigned?sort=lastName,firstName`)
  }

  archiveUser(userId: number | undefined) {
    return this.http.delete(`${environment.apiUrl}/users/${userId}`)
  }

  unassignStudent(userId: number | undefined) {
    return this.http.put(`${environment.apiUrl}/users/${userId}/unassign`, {}, {observe: "response"})
  }

  assignStudent(userId: number | undefined, classId: number | undefined) {
    return this.http.put(`${environment.apiUrl}/users/${userId}/assign`, {classId: classId}, {observe: "response"})
  }

  updateProfileImage(image: any) {
    const formData = new FormData();
    formData.append("image", image)
    return this.http.put(`${environment.apiUrl}/users/me/image`, formData, {observe: "response"})
  }
}
