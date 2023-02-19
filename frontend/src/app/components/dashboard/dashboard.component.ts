import {Component, OnInit} from '@angular/core';
import {RealisedSubject} from "../../model/realised.subject";
import {AuthService} from "../../services/auth.service";
import {RealisationService} from "../../services/realisation.service";
import {BehaviorSubject, Observable} from "rxjs";
import {ActivityPage} from "../../model/activity";
import {ActivityService} from "../../services/activity.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination

  today = new Date()

  monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ];

  days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

  subjects$: Observable<RealisedSubject[]> | undefined
  activities$: Observable<ActivityPage> | undefined

  constructor(public authService: AuthService,
              private realisationService: RealisationService,
              private activityService: ActivityService) {
  }

  ngOnInit(): void {
    if (this.authService.getRole() == "STUDENT" || this.authService.getRole() == "TEACHER") {
      this.subjects$ = this.realisationService.getRealisedSubjects()
      this.activities$ = this.activityService.getAllIncomingActivities(this.pageNumber$.value)
      this.pageNumber$.subscribe(() => {
        this.activities$ = this.activityService.getAllIncomingActivities(this.pageNumber$.value)
      })
    }
  }

  previousPage() {
    if (this.pageNumber$.value > 0) {
      this.pageNumber$.next(this.pageNumber$.value - 1)
    }
  }

  nextPage() {
    this.pageNumber$.next(this.pageNumber$.value + 1)
  }
}
