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

  getRealisationPosts(realisationId: number | undefined, page: number) {
    return this.http.get<PostPage>(`${environment.apiUrl}/realisations/${realisationId}/posts?size=3&page=${page}&sort=createdAt,desc`)
  }

  createPost(title: string, content: string, realisationId: number | undefined) {
    return this.http.post<PostPage>(`${environment.apiUrl}/posts`, {
      title: title,
      content: content,
      realisationId: realisationId
    }, {observe: "response"})
  }
}
