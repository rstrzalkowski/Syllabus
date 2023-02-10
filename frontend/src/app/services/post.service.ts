import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {PostPage} from "../model/post";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {
  }

  getRealisationPosts(realisationId: number | undefined) {
    return this.http.get<PostPage>(`${environment.apiUrl}/realisations/${realisationId}/posts?size=4`)
  }
}
