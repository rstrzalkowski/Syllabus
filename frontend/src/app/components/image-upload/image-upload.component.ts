import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SubjectService} from "../../services/subject.service";
import {Subject} from "../../model/subject";
import {AlertService} from "../../services/alert.service";
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";

class ImageSnippet {
  constructor(public src: string, public file: File) {
  }
}

@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html'
})
export class ImageUploadComponent implements OnInit {

  loading = false

  selectedFile: ImageSnippet | undefined

  @Input() subject: Subject | undefined
  @Input() user: User | undefined

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() successSubject: EventEmitter<any> = new EventEmitter()
  @Output() successUser: EventEmitter<any> = new EventEmitter()

  constructor(private subjectService: SubjectService,
              private alertService: AlertService,
              private userService: UserService) {
  }

  ngOnInit(): void {
  }

  processFile(imageInput: any) {
    const file: File = imageInput.files[0];
    const reader = new FileReader();

    reader.addEventListener('load', (event: any) => {

      this.selectedFile = new ImageSnippet(event.target.result, file);
    });

    reader.readAsDataURL(file);
  }

  save() {
    if (this.subject) {
      this.saveSubjectImage()
    } else if (this.user) {
      this.saveProfileImage()
    }
  }

  saveSubjectImage() {
    if (this.selectedFile === undefined) {
      this.alertService.showAlert("warning", "Choose image first.")
      return
    }

    this.loading = true
    this.subjectService.updateSubjectImage(this.subject?.id, this.selectedFile.file).subscribe((result) => {
      this.alertService.showAlert("success", "Subject image has been changed.")
      this.successSubject.emit()
      this.loading = false
    }, error => {
      this.alertService.showAlert("danger", "There was an error during changing subject image. Try again later.")
      this.loading = false
    })
  }

  saveProfileImage() {
    if (this.selectedFile === undefined) {
      this.alertService.showAlert("warning", "Choose image first.")
      return
    }

    this.loading = true
    this.userService.updateProfileImage(this.selectedFile.file).subscribe((result) => {
      this.alertService.showAlert("success", "Profile image has been changed.")
      this.successUser.emit()
      this.loading = false
    }, error => {
      this.alertService.showAlert("danger", "There was an error during changing profile image. Try again later.")
      this.loading = false
    })
  }


  closeModal() {
    this.close.emit()
  }
}
