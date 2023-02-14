import {Component, Input, OnInit} from '@angular/core';
import {Activity} from "../../model/activity";
import {Observable} from "rxjs";
import {GradeService} from "../../services/grade.service";
import {GradesOfActivity} from "../../model/grades-of-activity";

@Component({
  selector: 'app-grade-students',
  templateUrl: './grade-students.component.html'
})
export class GradeStudentsComponent implements OnInit {

  @Input() activity$: Observable<Activity> | undefined

  activity: Activity | undefined
  grades: GradesOfActivity[] | undefined

  loading = false

  subscription: any

  constructor(private gradeService: GradeService) {
  }

  ngOnInit(): void {
    this.activity$?.subscribe((value) => {
      this.subscription?.unsubscribe()
      this.loading = true
      this.activity = value
      this.subscription = this.gradeService.getGradesOfActivity(this.activity?.activityId).subscribe((result) => {
        this.grades = result
        this.loading = false
      })
    })
  }

  isLoading() {
    return this.loading;
  }
}
