import {Component, EventEmitter, HostListener, OnInit, Output} from '@angular/core';
import {AlertService} from "../../../../services/alert.service";
import {LevelService} from "../../../../services/level.service";

@Component({
  selector: 'app-create-level',
  templateUrl: './create-level.component.html'
})
export class CreateLevelComponent implements OnInit {

  //Data
  value: number | undefined
  //end data


  //Loading
  loading = false
  //end loading

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private alertService: AlertService,
              private levelService: LevelService) {
  }

  ngOnInit(): void {
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  submit() {
    if (this.value === undefined) {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }

    this.loading = true
    this.levelService.createLevel(this.value).subscribe((result) => {
      this.alertService.showAlert('success', 'Level has been successfully created!')
      this.success.emit()
    }, error => {
      if (error.status === 409) {
        this.alertService.showAlert('danger', 'This level already exists.')
      } else {
        this.alertService.showAlert('danger', 'Something went wrong during creating new level. Try again later.')
      }
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
