import {Component, OnInit} from '@angular/core';
import {RealisedSubject} from "../../model/realised.subject";
import {AuthService} from "../../services/auth.service";
import {RealisationService} from "../../services/realisation.service";
import {BehaviorSubject, Observable} from "rxjs";
import {ActivityPage} from "../../model/activity";
import {ActivityService} from "../../services/activity.service";
import {GradePage} from "../../model/grade";
import {GradeService} from "../../services/grade.service";
import {PostService} from "../../services/post.service";
import {PostPage} from "../../model/post";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  //Pagination
  activityPageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  gradePageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  postPageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination

  today = new Date()

  url = 'https://thumbsnap.com/s/mTmZ4rQj.jpg'


  monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ];

  days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

  subjects$: Observable<RealisedSubject[]> | undefined
  activities$: Observable<ActivityPage> | undefined
  grades$: Observable<GradePage> | undefined
  posts$: Observable<PostPage> | undefined

  constructor(public authService: AuthService,
              private realisationService: RealisationService,
              private activityService: ActivityService,
              private gradeService: GradeService,
              private postService: PostService) {
  }

  ngOnInit(): void {
    if (this.authService.getRole() == "STUDENT" || this.authService.getRole() == "TEACHER") {
      this.subjects$ = this.realisationService.getRealisedSubjects()

      this.activityPageNumber$.subscribe(() => {
        this.activities$ = this.activityService.getAllIncomingActivities(this.activityPageNumber$.value)
      })

      this.gradePageNumber$.subscribe(() => {
        this.grades$ = this.gradeService.getRecentGrades(this.gradePageNumber$.value)
      })

      this.postPageNumber$.subscribe(() => {
        this.posts$ = this.postService.getRecentPosts(this.postPageNumber$.value)
      })
    }
  }

  previousActivityPage() {
    if (this.activityPageNumber$.value > 0) {
      this.activityPageNumber$.next(this.activityPageNumber$.value - 1)
    }
  }

  nextActivityPage() {
    this.activityPageNumber$.next(this.activityPageNumber$.value + 1)
  }

  previousGradePage() {
    if (this.gradePageNumber$.value > 0) {
      this.gradePageNumber$.next(this.gradePageNumber$.value - 1)
    }
  }

  nextGradePage() {
    this.gradePageNumber$.next(this.gradePageNumber$.value + 1)
  }

  previousPostPage() {
    if (this.postPageNumber$.value > 0) {
      this.postPageNumber$.next(this.postPageNumber$.value - 1)
    }
  }

  nextPostPage() {
    this.postPageNumber$.next(this.postPageNumber$.value + 1)
  }
}
