import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PostService} from "../../../services/post.service";
import {AlertService} from "../../../services/alert.service";
import {Post} from "../../../model/post";

@Component({
  selector: 'app-edit-post',
  templateUrl: './edit-post.component.html'
})
export class EditPostComponent implements OnInit {

  //Data
  title: string = ""
  content: string = ""
  //end data


  //Loading
  loading = false
  //end loading

  @Input() post: Post | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private postService: PostService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.title = this.post?.title || ""
    this.content = this.post?.content || ""
  }

  submit() {
    if (this.title === '' || this.content === '') {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }

    this.loading = true
    this.postService.updatePost(this.title, this.content, this.post?.postId).subscribe((result) => {
      this.alertService.showAlert('success', 'Post has been successfully updated!')
      this.success.emit()
    }, error => {
      this.alertService.showAlert('danger', 'Something went wrong during updating a post. Try again later.')
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
