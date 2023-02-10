import {Component, Input, OnInit} from '@angular/core';
import {PostPage} from "../../../model/post";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html'
})
export class PostsComponent implements OnInit {

  @Input() realisationId: number | undefined;

  @Input() postPage: PostPage | undefined

  constructor() {
  }

  ngOnInit(): void {
  }
}
