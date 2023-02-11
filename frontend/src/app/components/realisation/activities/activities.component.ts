import {Component, Input, OnInit} from '@angular/core';
import {ActivityPage} from "../../../model/activity";

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

  @Input() realisationId: number | undefined;

  @Input() activityPage: ActivityPage | undefined


  constructor() {
  }

  ngOnInit(): void {
  }

}
