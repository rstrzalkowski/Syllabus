import {Component, OnInit} from '@angular/core';
import {RealisationInfo} from "../../model/realisation.info";
import {ActivatedRoute, Router} from "@angular/router";
import {RealisationService} from "../../services/realisation.service";
import {ActivityService} from "../../services/activity.service";
import {ActivityPage} from "../../model/activity";

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

  public realisationId: number | undefined;

  //Data from API
  realisationInfo: RealisationInfo | undefined
  activities: ActivityPage | undefined
  //end data


  //Page number
  pageNumber: number = 0
  //


  //Loading indicators
  activitiesLoading = false
  realisationLoading = false;
  //end loading


  //Subscriptions
  realisationSubscription: any;
  activitiesSubscription: any;

  //end subscriptions


  constructor(private activatedRoute: ActivatedRoute,
              private realisationService: RealisationService,
              private activityService: ActivityService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.clearSubscriptions()
      let realisationIdString = params.get('id')!.toString()

      this.realisationId = Number(realisationIdString)

      this.getRealisationInfo(this.realisationId)
      this.getActivities(this.realisationId)
    })
  }

  clearSubscriptions() {
    this.realisationSubscription?.unsubscribe()
    this.activitiesSubscription?.unsubscribe()
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


  getActivities(realisationId: number) {
    this.activitiesLoading = true
    this.activitiesSubscription = this.activityService.getRealisationActivities(realisationId, this.pageNumber).subscribe((result) => {
      this.activities = result
      this.activitiesLoading = false
    })
  }

  isLoading() {
    return this.realisationLoading || this.activitiesLoading
  }

  nextPage() {
    if (!this.activities?.last) {
      this.pageNumber++
      this.getActivities(this.realisationId!)
    }
  }

  previousPage() {
    if (this.pageNumber > 0) {
      this.pageNumber--
      this.getActivities(this.realisationId!)
    }
  }
}
