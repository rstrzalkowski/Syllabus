import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {Observable} from "rxjs";
import {UserPage} from "../../../../model/user";
import {AlertService} from "../../../../services/alert.service";
import {LevelService} from "../../../../services/level.service";
import {UserService} from "../../../../services/user.service";
import {RealisationInfo} from "../../../../model/realisation.info";
import {RealisationService} from "../../../../services/realisation.service";

@Component({
  selector: 'app-edit-realisation',
  templateUrl: './edit-realisation.component.html'
})
export class EditRealisationComponent implements OnInit {

  //Data
  year: number = new Date().getFullYear()
  teacherId: number | undefined

  teachers$: Observable<UserPage> = this.userService.getAllActiveTeachers()
  //end data


  //Loading
  loading = false
  //end loading

  @Input() realisation: RealisationInfo | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private alertService: AlertService,
              private realisationService: RealisationService,
              private levelService: LevelService,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.year = this.realisation?.year || new Date().getFullYear()
    this.teacherId = this.realisation?.teacherId
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  submit() {
    if (this.year <= 0 || this.teacherId === undefined) {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }

    this.loading = true
    this.realisationService.updateRealisation(this.realisation?.id, this.teacherId, this.year).subscribe((result) => {
      this.alertService.showAlert('success', 'Realisation has been successfully updated!')
      this.success.emit()
    }, error => {
      this.alertService.showAlert('danger', 'Something went wrong during updating a class. Make sure form is valid')
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
