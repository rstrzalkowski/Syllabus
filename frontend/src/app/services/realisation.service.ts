import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {RealisedSubject} from "../model/realised.subject";
import {RealisationInfo} from "../model/realisation.info";

@Injectable({
  providedIn: 'root'
})
export class RealisationService {

  constructor(private http: HttpClient) {
  }

  getActiveRealisations() {
    return this.http.get(`${environment.apiUrl}/realisations`)
  }

  getRealisedSubjects() {
    return this.http.get<RealisedSubject[]>(`${environment.apiUrl}/realisations/me`)
  }

  getRealisationInfo(id: number) {
    return this.http.get<RealisationInfo>(`${environment.apiUrl}/realisations/${id}`)
  }
}
