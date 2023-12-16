import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-grade',
  templateUrl: './average-grade.component.html'
})
export class AverageGradeComponent implements OnInit {


  @Input() realisationId: string | undefined;
  @Input() grade: number | undefined;


  constructor() {
  }

  ngOnInit(): void {
  }
}
