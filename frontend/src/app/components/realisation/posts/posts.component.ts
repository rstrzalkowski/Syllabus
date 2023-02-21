import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {Post, PostPage} from "../../../model/post";
import {UserService} from "../../../services/user.service";
import {PostService} from "../../../services/post.service";
import {AlertService} from "../../../services/alert.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html'
})
export class PostsComponent implements OnInit {

  @Input() realisationId: number | undefined;

  @Input() postPage: PostPage | undefined

  @Output() nextPage: EventEmitter<any> = new EventEmitter();
  @Output() previousPage: EventEmitter<any> = new EventEmitter();
  @Output() deleteSuccess: EventEmitter<any> = new EventEmitter();
  @Output() updateSuccess: EventEmitter<any> = new EventEmitter();

  //Delete post
  deleteModalOpened = false
  postIdToBeDeleted: number | undefined
  //end delete


  //Edit post
  editModalOpened = false
  editedPost: Post | undefined

  //end edit

  constructor(public userService: UserService,
              private postService: PostService,
              private alertService: AlertService,
              public authService: AuthService) {
  }

  ngOnInit(): void {
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.editModalOpened = false
      this.deleteModalOpened = false
    }
  }

  showDeleteModal(postId: number) {
    this.postIdToBeDeleted = postId
    this.deleteModalOpened = true
  }

  showEditModal(post: Post) {
    this.editedPost = post
    this.editModalOpened = true
  }

  deletePost() {
    if (this.postIdToBeDeleted) {
      this.postService.deletePost(this.postIdToBeDeleted).subscribe((result) => {
        this.alertService.showAlert('success', 'Post has been deleted.')
        this.deleteSuccess.emit()
        this.deleteModalOpened = false
      }, error => {
        this.alertService.showAlert('danger', 'Something went wrong during deleting post. Try again later.')
      })
    }
  }

  updateSuccessful() {
    this.updateSuccess.emit()
    this.editModalOpened = false
  }

  next() {
    this.nextPage.emit()
  }

  previous() {
    this.previousPage.emit()
  }
}
