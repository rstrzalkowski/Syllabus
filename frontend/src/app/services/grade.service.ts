import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AverageGrade} from "../model/average.grade";

@Injectable({
  providedIn: 'root'
})
export class GradeService {

  constructor(private http: HttpClient) {
  }

  getAverageGrade(realisationId: number | undefined) {
    return this.http.get<AverageGrade>(`${environment.apiUrl}/realisations/${realisationId}/average`)
  }
}
