import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {AlertService} from "../../../../services/alert.service";
import {SubjectService} from "../../../../services/subject.service";
import {Subject} from "../../../../model/subject";

@Component({
  selector: 'app-edit-subject',
  templateUrl: './edit-subject.component.html'
})
export class EditSubjectComponent implements OnInit {

  //Data
  name: string = ""
  abbreviation: string = ""
  //end data


  //Loading
  loading = false
  //end loading

  @Input() subject: Subject | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private alertService: AlertService,
              private subjectService: SubjectService) {
  }

  ngOnInit(): void {
    this.name = this.subject?.name || ""
    this.abbreviation = this.subject?.abbreviation || ""
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  submit() {
    if (this.name === '' || this.abbreviation === '') {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }

    this.loading = true
    this.subjectService.updateSubject(this.subject?.id, this.name, this.abbreviation).subscribe((result) => {
      this.alertService.showAlert('success', 'Subject has been successfully updated!')
      this.success.emit()
    }, error => {
      this.alertService.showAlert('danger', 'Something went wrong during updating a subject. Make sure form is valid')
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
