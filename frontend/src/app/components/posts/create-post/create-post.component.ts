import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {PostService} from "../../../services/post.service";
import {AlertService} from "../../../services/alert.service";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html'
})
export class CreatePostComponent implements OnInit {

  //Data
  title: string = ""
  content: string = ""
  //end data


  //Loading
  loading = false
  //end loading

  @Input() realisationId: number | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private postService: PostService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  submit() {
    if (this.title === '' || this.content === '') {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }

    this.loading = true
    this.postService.createPost(this.title, this.content, this.realisationId).subscribe((result) => {
      this.alertService.showAlert('success', 'Post has been successfully created!')
      this.success.emit()
    }, error => {
      this.alertService.showAlert('danger', 'Something went wrong during creating a post. Make sure form is valid')
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
