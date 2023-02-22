import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AverageGrade} from "../model/average.grade";
import {GradePage} from "../model/grade";
import {GradesOfActivity} from "../model/grades-of-activity";

@Injectable({
  providedIn: 'root'
})
export class GradeService {

  constructor(private http: HttpClient) {
  }

  getAverageGrade(realisationId: number | undefined) {
    return this.http.get<AverageGrade>(`${environment.apiUrl}/realisations/${realisationId}/average`)
  }

  getGradesOfRealisation(realisationId: number | undefined, page: number) {
    return this.http.get<GradePage>(`${environment.apiUrl}/realisations/${realisationId}/grades?size=6&page=${page}&sort=date`)
  }

  getGradesOfActivity(activityId: number | undefined) {
    return this.http.get<GradesOfActivity[]>(`${environment.apiUrl}/activities/${activityId}/grades`)
  }

  getRecentGrades(page: number | undefined) {
    return this.http.get<GradePage>(`${environment.apiUrl}/grades/own?size=3&page=${page}&sort=createdAt,desc`)
  }

  updateGrade(activityId: number | undefined, studentId: number | undefined, value: number | undefined, comment: string | undefined) {
    return this.http.post(`${environment.apiUrl}/grades`, {
      value: value,
      activityId: activityId,
      studentId: studentId,
      comment: comment
    })
  }
}
