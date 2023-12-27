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

  getRealisationPosts(realisationId: string | undefined, page: number) {
    return this.http.get<PostPage>(`${environment.apiUrl}/realisations/${realisationId}/posts?size=3&page=${page}&sort=createdAt,desc`)
  }

  getRecentPosts(page: number | undefined) {
    return this.http.get<PostPage>(`${environment.apiUrl}/posts/recent?size=1&page=${page}&sort=createdAt,desc`)
  }

  createPost(title: string, content: string, realisationId: string | undefined) {
    return this.http.post(`${environment.apiUrl}/posts`, {
      title: title,
      content: content,
      realisationId: realisationId
    }, {observe: "response"})
  }

  updatePost(title: string, content: string, postId: string | undefined) {
    return this.http.put(`${environment.apiUrl}/posts`, {
      id: postId,
      title: title,
      content: content,
    }, {observe: "response"})
  }

  deletePost(postId: string) {
    return this.http.delete(`${environment.apiUrl}/posts/${postId}`)
  }
}
