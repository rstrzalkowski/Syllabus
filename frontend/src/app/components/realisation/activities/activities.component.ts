import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

  @Input() realisationId: number | undefined;

  constructor() {
  }

  ngOnInit(): void {
  }

}
