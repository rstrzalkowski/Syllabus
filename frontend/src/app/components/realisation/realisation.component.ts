import {Component, OnInit} from '@angular/core';
import {RealisationService} from "../../services/realisation.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RealisationInfo} from "../../model/realisation.info";
import {GradeService} from "../../services/grade.service";
import {PostService} from "../../services/post.service";
import {ActivityService} from "../../services/activity.service";
import {PostPage} from "../../model/post";
import {ActivityPage} from "../../model/activity";
import {AuthService} from "../../services/auth.service";
import {Title} from "@angular/platform-browser";
import {ThemeService} from "../../services/theme.service";

@Component({
  selector: 'app-realisation',
  templateUrl: './realisation.component.html'
})
export class RealisationComponent implements OnInit {

  realisationId: number | undefined

  //Data from API
  realisationInfo: RealisationInfo | undefined
  grade: number | undefined;
  postPage: PostPage | undefined;
  activityPage: ActivityPage | undefined;
  //end data

  //Page numbers
  postPageNumber: number = 0
  activityPageNumber: number = 0
  //end page numbers

  //Loading indicators
  realisationLoading = false
  postsLoading = false
  postPageLoading = false
  activitiesLoading = false
  activityPageLoading = false
  gradeLoading = false
  //end loading

  //Subscriptions
  realisationSubscription: any
  gradeSubscription: any
  postsSubscription: any
  activitiesSubscription: any
  //end subscriptions

  //Modals
  createActivityOpened = false
  createPostOpened = false
  editActivityOpened = false

  //end modals

  constructor(private realisationService: RealisationService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private gradeService: GradeService,
              private postService: PostService,
              private activityService: ActivityService,
              public authService: AuthService,
              private titleService: Title,
              public themeService: ThemeService) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.clearSubscriptions()
      let realisationIdString = params.get('id')!.toString();

      this.realisationId = Number(realisationIdString)

      if (this.authService.getRole() === 'STUDENT') {
        this.getAverageGrade();
      }
      this.getPosts();
      this.getActivities();
      this.getRealisationInfo(this.realisationId);
    })
  }

  isLoading() {
    return this.realisationLoading || this.postsLoading || this.gradeLoading || this.activitiesLoading
  }

  clearSubscriptions() {
    this.realisationSubscription?.unsubscribe()
    this.postsSubscription?.unsubscribe()
    this.gradeSubscription?.unsubscribe()
    this.activitiesSubscription?.unsubscribe()
  }

  getAverageGrade() {
    this.gradeLoading = true
    this.gradeSubscription = this.gradeService.getAverageGrade(this.realisationId).subscribe((result) => {
      this.grade = result.average
      this.gradeLoading = false
    })
  }

  getPosts() {
    this.postsLoading = true
    this.postsSubscription = this.postService.getRealisationPosts(this.realisationId, this.postPageNumber).subscribe((result) => {
      this.postPage = result
      this.postsLoading = false
    })
  }

  getActivities() {
    this.activitiesLoading = true
    this.postsSubscription = this.activityService.getIncomingActivitiesByRealisation(this.realisationId, this.activityPageNumber).subscribe((result) => {
      this.activityPage = result
      this.activitiesLoading = false
    })
  }

  getRealisationInfo(realisationId: number) {
    this.realisationLoading = true
    this.realisationSubscription = this.realisationService.getRealisationInfo(realisationId).subscribe((result) => {
      this.realisationInfo = result
      this.realisationLoading = false
      if (this.authService.getRole() === 'STUDENT') {
        this.titleService.setTitle(this.realisationInfo.subjectName + ' - Syllabus');
      } else {
        this.titleService.setTitle(this.realisationInfo.schoolClassName + " " + this.realisationInfo.subjectAbbreviation + ' - Syllabus');
      }
    }, error => {
      this.router.navigate(['/forbidden'])
    })
  }

  refreshPosts() {
    this.postPageNumber = 0
    this.postPageLoading = true
    this.postService.getRealisationPosts(this.realisationId, this.postPageNumber).subscribe((result) => {
      this.postPage = result
      this.postPageLoading = false
      this.createPostOpened = false
    })
  }

  refreshActivities() {
    this.activityPageNumber = 0
    this.activityPageLoading = true
    this.activityService.getIncomingActivitiesByRealisation(this.realisationId, this.postPageNumber).subscribe((result) => {
      this.activityPage = result
      this.activityPageLoading = false
      this.createActivityOpened = false
    })
  }

  getNextPostPage() {
    if (!this.postPage?.last) {
      this.postPageLoading = true
      this.postPageNumber++
      this.postService.getRealisationPosts(this.realisationId, this.postPageNumber).subscribe((result) => {
        this.postPage = result
        this.postPageLoading = false
      })
    }
  }

  getPreviousPostPage() {
    if (this.postPageNumber > 0) {
      this.postPageLoading = true
      this.postPageNumber--
      this.postService.getRealisationPosts(this.realisationId, this.postPageNumber).subscribe((result) => {
        this.postPage = result
        this.postPageLoading = false
      })
    }
  }

  getNextActivityPage() {
    if (!this.activityPage?.last) {
      this.activityPageLoading = true
      this.activityPageNumber++
      this.activityService.getIncomingActivitiesByRealisation(this.realisationId, this.activityPageNumber).subscribe((result) => {
        this.activityPage = result
        this.activityPageLoading = false
      })
    }
  }

  getPreviousActivityPage() {
    if (this.activityPageNumber > 0) {
      this.activityPageLoading = true
      this.activityPageNumber--
      this.activityService.getIncomingActivitiesByRealisation(this.realisationId, this.activityPageNumber).subscribe((result) => {
        this.activityPage = result
        this.activityPageLoading = false
      })
    }
  }
}
