import {Component, Input, OnInit} from '@angular/core';
import {PostPage} from "../../../model/post";
import {PostService} from "../../../services/post.service";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html'
})
export class PostsComponent implements OnInit {

  @Input() realisationId: number | undefined;

  postPage: PostPage | undefined

  constructor(private postService: PostService) {
  }

  ngOnInit(): void {
    this.postService.getRealisationPosts(this.realisationId).subscribe((result) => {
      this.postPage = result
    })
  }
}
