import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {AlertService} from "../../../services/alert.service";
import {ActivityService} from "../../../services/activity.service";

@Component({
  selector: 'app-create-activity',
  templateUrl: './create-activity.component.html'
})
export class CreateActivityComponent implements OnInit {

  //Data
  name: string = ""
  description: string = ""
  weight: number = 1
  date = new Date().toISOString().slice(0, 10)
  //end data


  //Loading
  loading = false
  //end loading

  @Input() realisationId: number | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private alertService: AlertService,
              private activityService: ActivityService) {
  }

  ngOnInit(): void {
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  submit() {
    if (this.name === '' || this.weight === 0) {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }

    this.loading = true
    this.activityService.createActivity(this.name, this.description, this.weight, new Date(this.date), this.realisationId).subscribe((result) => {
      this.alertService.showAlert('success', 'Activity has been successfully created! Now you can grade students of it.')
      this.success.emit()
    }, error => {
      this.alertService.showAlert('danger', 'Something went wrong during creating a activity. Make sure form is valid')
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
