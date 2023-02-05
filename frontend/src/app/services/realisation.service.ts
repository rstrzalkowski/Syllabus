import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RealisationService {

  constructor(private http: HttpClient) { }

  public getActiveRealisations() {
    return this.http.get(`${environment.apiUrl}/realisations`)
  }
}
