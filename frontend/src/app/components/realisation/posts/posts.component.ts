import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PostPage} from "../../../model/post";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html'
})
export class PostsComponent implements OnInit {

  @Input() realisationId: number | undefined;

  @Input() postPage: PostPage | undefined

  @Output() nextPage: EventEmitter<any> = new EventEmitter();
  @Output() previousPage: EventEmitter<any> = new EventEmitter();

  constructor() {
  }

  ngOnInit(): void {
  }

  next() {
    this.nextPage.emit()
  }

  previous() {
    this.previousPage.emit()
  }
}
