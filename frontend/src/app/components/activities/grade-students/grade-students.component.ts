import {Component, HostListener, Input, OnInit} from '@angular/core';
import {Activity} from "../../../model/activity";
import {Observable} from "rxjs";
import {GradeService} from "../../../services/grade.service";
import {GradesOfActivity} from "../../../model/grades-of-activity";
import {AlertService} from "../../../services/alert.service";

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

  //edit grade
  editModalOpened: boolean = false
  currentGrade: number | undefined
  currentComment: string | undefined
  studentId: number | undefined
  studentFirstName: string | undefined
  studentLastName: string | undefined
  studentPersonalId: string | undefined

  //end edit grade

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeGradeModal()
    }
  }

  constructor(private gradeService: GradeService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.activity$?.subscribe((value) => {
      this.subscription?.unsubscribe()
      this.loading = true
      this.activity = value
      this.getGrades()
    })
  }

  getGrades() {
    this.subscription = this.gradeService.getGradesOfActivity(this.activity?.activityId).subscribe((result) => {
      this.grades = result
      this.loading = false
    })
  }

  getFilteredGrades(): GradesOfActivity[] | undefined {
    return this.grades?.filter((grade) => {
      let conditionUngraded = true
      if (this.onlyUngraded) {
        conditionUngraded = grade.grade === null
      }
      let conditionFilter =
        grade.studentFirstName.toLowerCase().includes(this.filter.toLowerCase()) ||
        grade.studentLastName.toLowerCase().includes(this.filter.toLowerCase()) ||
        grade.studentPersonalId.includes(this.filter);
      return conditionFilter && conditionUngraded
    })
  }

  isLoading() {
    return this.loading;
  }

  openGradeModal(grade: GradesOfActivity) {
    this.studentId = grade.studentId
    this.currentGrade = grade.grade
    this.currentComment = grade.comment
    this.studentFirstName = grade.studentFirstName
    this.studentLastName = grade.studentLastName
    this.studentPersonalId = grade.studentPersonalId
    this.editModalOpened = true
  }

  closeGradeModal() {
    this.studentId = undefined
    this.currentGrade = undefined
    this.currentComment = undefined
    this.studentFirstName = undefined
    this.studentLastName = undefined
    this.studentPersonalId = undefined
    this.editModalOpened = false
  }

  submit() {
    this.gradeService.updateGrade(this.activity?.activityId, this.studentId, this.currentGrade, this.currentComment).subscribe((result) => {
      this.alertService.showAlert("success", "Grade has been updated!")
      this.loading = true
      this.getGrades()
      this.closeGradeModal()
    }, error => {
      this.alertService.showAlert("danger", "Error during grading. Make sure form is valid!")
      this.loading = false
    })
  }
}
