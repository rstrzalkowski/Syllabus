import {Component, HostListener, OnInit} from '@angular/core';
import {RealisationInfo} from "../../model/realisation.info";
import {ActivatedRoute, Router} from "@angular/router";
import {RealisationService} from "../../services/realisation.service";
import {ActivityService} from "../../services/activity.service";
import {Activity, ActivityPage} from "../../model/activity";
import {AlertService} from "../../services/alert.service";
import {BehaviorSubject} from "rxjs";

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


  //Delete activity
  deleteModalOpened = false
  activityIdToBeDeleted: number | undefined
  //end delete


  //Edit activity
  editModalOpened = false
  editedActivity: Activity | undefined

  //end edit


  //Grading
  chosenActivity: Activity | undefined
  chosenActivity$: BehaviorSubject<Activity> | undefined

  //end grading


  constructor(private activatedRoute: ActivatedRoute,
              private realisationService: RealisationService,
              private activityService: ActivityService,
              private alertService: AlertService,
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

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.editModalOpened = false
      this.deleteModalOpened = false
    }
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

  refreshActivities() {
    this.pageNumber = 0
    this.activitiesLoading = true
    this.activitiesSubscription = this.activityService.getRealisationActivities(this.realisationId, this.pageNumber).subscribe((result) => {
      this.activities = result
      this.chosenActivity = undefined
      this.activitiesLoading = false
      this.editModalOpened = false
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

  deleteActivity() {
    if (this.activityIdToBeDeleted) {
      this.activityService.deleteActivity(this.activityIdToBeDeleted).subscribe((result) => {
        this.ngOnInit()
        this.alertService.showAlert('success', 'Activity has been deleted.')
        this.deleteModalOpened = false
        if (this.chosenActivity?.activityId === this.activityIdToBeDeleted) {
          this.chosenActivity = undefined
        }
      }, error => {
        this.alertService.showAlert('danger', 'Something went wrong during deleting activity. Try again later.')
      })
    }
  }

  showDeleteModal(activityId: number) {
    this.activityIdToBeDeleted = activityId
    this.deleteModalOpened = true
  }

  showEditModal(activity: Activity) {
    this.editedActivity = activity
    this.editModalOpened = true
  }

  showStudentList(activity: Activity) {
    this.chosenActivity = activity
    if (!this.chosenActivity$) {
      this.chosenActivity$ = new BehaviorSubject<Activity>(this.chosenActivity)
    } else {
      this.chosenActivity$.next(this.chosenActivity)
    }
  }
}
