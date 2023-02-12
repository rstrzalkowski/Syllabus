import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivityPage} from "../../../model/activity";

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

  @Input() realisationId: number | undefined;

  @Input() activityPage: ActivityPage | undefined

  @Output() nextPage: EventEmitter<any> = new EventEmitter();
  @Output() previousPage: EventEmitter<any> = new EventEmitter();


  constructor() {
  }

  ngOnInit(): void {
  }

  previous() {
    this.previousPage.emit()
  }

  next() {
    this.nextPage.emit()
  }
}
