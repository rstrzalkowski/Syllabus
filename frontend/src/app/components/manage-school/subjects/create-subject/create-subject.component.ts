import {Component, EventEmitter, HostListener, OnInit, Output} from '@angular/core';
import {AlertService} from "../../../../services/alert.service";
import {SubjectService} from "../../../../services/subject.service";

@Component({
  selector: 'app-create-subject',
  templateUrl: './create-subject.component.html'
})
export class CreateSubjectComponent implements OnInit {

  //Data
  name: string = ""
  abbreviation: string = ""
  //end data


  //Loading
  loading = false
  //end loading

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private alertService: AlertService,
              private subjectService: SubjectService) {
  }

  ngOnInit(): void {
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
    this.subjectService.createSubject(this.name, this.abbreviation).subscribe((result) => {
      this.alertService.showAlert('success', 'Subject has been successfully created!')
      this.success.emit()
    }, error => {
      this.alertService.showAlert('danger', 'Something went wrong during creating a subject. Make sure form is valid')
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
