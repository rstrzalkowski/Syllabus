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

  filter: string = ""
  onlyUngraded: boolean = false

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

  getFilteredGrades(): GradesOfActivity[] | undefined {
    return this.grades?.filter((grade) => {
      let conditionUngraded = true
      let conditionFilter
      if (this.onlyUngraded) {
        conditionUngraded = grade.grade === null
      }
      conditionFilter = grade.studentFirstName.includes(this.filter) || grade.studentLastName.includes(this.filter) || grade.studentPersonalId.includes(this.filter);
      return conditionFilter && conditionUngraded
    })
  }

  isLoading() {
    return this.loading;
  }
}
