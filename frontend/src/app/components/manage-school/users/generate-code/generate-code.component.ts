import {Component, EventEmitter, HostListener, OnInit, Output} from '@angular/core';
import {AlertService} from "../../../../services/alert.service";
import {SubjectService} from "../../../../services/subject.service";
import {AuthService} from "../../../../services/auth.service";
import {Observable} from "rxjs";
import {ClassPage} from "../../../../model/class";
import {ClassService} from "../../../../services/class.service";
import {UserService} from "../../../../services/user.service";

@Component({
  selector: 'app-generate-code',
  templateUrl: './generate-code.component.html'
})
export class GenerateCodeComponent implements OnInit {

  //Data
  number: number = 1
  role: string = ""
  classId: number | undefined = -1
  classes$: Observable<ClassPage> = this.classService.getAllActiveClasses()
  //end data


  //Loading
  loading = false
  //end loading

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private alertService: AlertService,
              private subjectService: SubjectService,
              public authService: AuthService,
              private classService: ClassService,
              private userService: UserService) {
  }

  ngOnInit(): void {
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  submit() {
    if (this.role == "") {
      this.alertService.showAlert('warning', 'Please select role.')
      return
    }

    if (this.number < 1 || this.number > 30) {
      this.alertService.showAlert('warning', 'Number of codes must be between 1 and 30')
      return
    }

    if (this.classId == -1) {
      this.classId = undefined
    }

    this.loading = true
    this.userService.generateCodes(this.number, this.role, this.classId).subscribe((result) => {
      this.alertService.showAlert('success', 'Codes have been generated! You can see them in "Generated codes" section')
      this.success.emit()
    }, error => {
      this.alertService.showAlert('danger', 'Something went wrong during generating codes. Make sure form is valid')
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }

}
