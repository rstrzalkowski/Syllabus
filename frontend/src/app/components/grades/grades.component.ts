import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RealisationService} from "../../services/realisation.service";
import {GradeService} from "../../services/grade.service";
import {RealisationInfo} from "../../model/realisation.info";
import {GradePage} from "../../model/grade";

@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html'
})
export class GradesComponent implements OnInit {

  public realisationId: number | undefined;

  //Data from API
  realisationInfo: RealisationInfo | undefined
  grades: GradePage | undefined
  //end data


  //Page number
  pageNumber: number = 0
  //


  //Loading indicators
  gradesLoading = false
  realisationLoading = false;
  //end loading


  //Subscriptions
  realisationSubscription: any;
  gradesSubscription: any;

  //end subscriptions


  constructor(private activatedRoute: ActivatedRoute,
              private realisationService: RealisationService,
              private gradeService: GradeService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.clearSubscriptions()
      let realisationIdString = params.get('id')!.toString()

      this.realisationId = Number(realisationIdString)

      this.getRealisationInfo(this.realisationId)
      this.getGrades(this.realisationId)
    })
  }

  clearSubscriptions() {
    this.realisationSubscription?.unsubscribe()
    this.gradesSubscription?.unsubscribe()
  }

  getRealisationInfo(realisationId: number) {
    this.realisationLoading = true
    this.realisationSubscription = this.realisationService.getRealisationInfo(realisationId).subscribe((result) => {
      this.realisationInfo = result
      this.realisationLoading = false
    }, error => {
      this.router.navigate(['/forbidden'])
    })
  }


  getGrades(realisationId: number) {
    this.gradesLoading = true
    this.gradesSubscription = this.gradeService.getGradesOfRealisation(realisationId, this.pageNumber).subscribe((result) => {
      this.grades = result
      this.gradesLoading = false
    })
  }

  isLoading() {
    return this.realisationLoading || this.gradesLoading
  }

  nextPage() {
    if (!this.grades?.last) {
      this.pageNumber++
      this.getGrades(this.realisationId!)
    }
  }

  previousPage() {
    if (this.pageNumber > 0) {
      this.pageNumber--
      this.getGrades(this.realisationId!)
    }
  }
}
