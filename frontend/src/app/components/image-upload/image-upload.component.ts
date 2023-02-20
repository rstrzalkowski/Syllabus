import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SubjectService} from "../../services/subject.service";
import {Subject} from "../../model/subject";
import {AlertService} from "../../services/alert.service";

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
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private subjectService: SubjectService,
              private alertService: AlertService) {
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
    if (this.selectedFile === undefined) {
      this.alertService.showAlert("warning", "Choose image first.")
      return
    }

    this.loading = true
    this.subjectService.updateSubjectImage(this.subject?.id, this.selectedFile.file).subscribe((result) => {
      this.alertService.showAlert("success", "Subject image has been changed.")
      this.success.emit()
      this.loading = false
    }, error => {
      this.alertService.showAlert("danger", "There was an error during changing subject image. Try again later.")
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
