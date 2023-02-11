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
    return this.http.get<ActivityPage>(`${environment.apiUrl}/realisations/${realisationId}/activities?size=1`)
  }
}
