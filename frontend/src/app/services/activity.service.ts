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

  getRealisationActivities(realisationId: number | undefined) {
    return this.http.get<ActivityPage>(`${environment.apiUrl}/realisations/${realisationId}/activities?size=4&sort=date`)
  }

  getIncomingActivities(realisationId: number | undefined, page: number) {
    return this.http.get<ActivityPage>(`${environment.apiUrl}/realisations/${realisationId}/activities/incoming?size=3&page=${page}&sort=date`)
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
}
