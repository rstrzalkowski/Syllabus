import {Component, EventEmitter, HostListener, OnInit, Output} from '@angular/core';
import {AlertService} from "../../../../services/alert.service";
import {ClassService} from "../../../../services/class.service";
import {LevelPage} from "../../../../model/level";
import {User} from "../../../../model/user";
import {Observable} from "rxjs";
import {LevelService} from "../../../../services/level.service";
import {UserService} from "../../../../services/user.service";

@Component({
  selector: 'app-create-class',
  templateUrl: './create-class.component.html'
})
export class CreateClassComponent implements OnInit {

  //Data
  shortName: string = ""
  fullName: string = ""
  levelId: number | undefined
  teacherId: number | undefined
  levels$: Observable<LevelPage> = this.levelService.getAllActiveLevels()
  teachers$: Observable<User[]> = this.userService.getAllNotSupervisingActiveTeachers()
  //end data


  //Loading
  loading = false
  //end loading

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private alertService: AlertService,
              private classService: ClassService,
              private levelService: LevelService,
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
    if (this.shortName === '' || this.fullName === '' || this.levelId === undefined || this.teacherId === undefined) {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }

    this.loading = true
    this.classService.createClass(this.shortName, this.fullName, this.levelId, this.teacherId).subscribe((result) => {
      this.alertService.showAlert('success', 'Class has been successfully created!')
      this.success.emit()
    }, error => {
      if (error.status === 409) {
        this.alertService.showAlert('danger', 'Class with same short name and level already exists.')
      } else {
        this.alertService.showAlert('danger', 'Something went wrong during creating a class. Make sure form is valid')
      }
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
