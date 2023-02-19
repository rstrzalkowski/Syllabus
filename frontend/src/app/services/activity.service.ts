import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {ActivityPage} from "../model/activity";

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient) {
  }

  getRealisationActivities(realisationId: number | undefined, page: number) {
    return this.http.get<ActivityPage>(`${environment.apiUrl}/realisations/${realisationId}/activities?size=5&page=${page}&sort=createdAt`)
  }

  getIncomingActivitiesByRealisation(realisationId: number | undefined, page: number) {
    return this.http.get<ActivityPage>(`${environment.apiUrl}/realisations/${realisationId}/activities/incoming?size=3&page=${page}&sort=createdAt`)
  }

  getAllIncomingActivities(page: number) {
    return this.http.get<ActivityPage>(`${environment.apiUrl}/activities/incoming?size=3&page=${page}&sort=date`)
  }

  createActivity(name: string, description: string, weight: number, date: any, realisationId: number | undefined) {
    return this.http.post(`${environment.apiUrl}/activities`, {
      name: name,
      description: description,
      weight: weight,
      date: date,
      realisationId: realisationId
    }, {observe: "response"})
  }

  updateActivity(name: string, description: string, weight: number, date: any, activityId: number | undefined,) {
    return this.http.put(`${environment.apiUrl}/activities/${activityId}`, {
      name: name,
      description: description,
      weight: weight,
      date: date
    }, {observe: "response"})
  }

  deleteActivity(activityId: number) {
    return this.http.delete(`${environment.apiUrl}/activities/${activityId}`)
  }
}
