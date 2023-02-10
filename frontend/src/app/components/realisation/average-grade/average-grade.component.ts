import {Component, Input, OnInit} from '@angular/core';
import {GradeService} from "../../../services/grade.service";

@Component({
  selector: 'app-grade',
  templateUrl: './average-grade.component.html'
})
export class AverageGradeComponent implements OnInit {


  @Input() realisationId: number | undefined;

  grade: number | undefined

  constructor(private gradeService: GradeService) {
  }

  ngOnInit(): void {
    this.gradeService.getAverageGrade(this.realisationId).subscribe((result) => {
      this.grade = result.average
    })
  }
}
