import {Component, OnInit} from '@angular/core';
import {RealisationService} from "../../services/realisation.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RealisationInfo} from "../../model/realisation.info";
import {GradeService} from "../../services/grade.service";
import {PostService} from "../../services/post.service";
import {ActivityService} from "../../services/activity.service";
import {PostPage} from "../../model/post";
import {ActivityPage} from "../../model/activity";

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

  //Loading indicators
  realisationLoading = false;
  postsLoading = false;
  activitiesLoading = false;
  gradeLoading = false;
  //end loading

  //Subscriptions
  realisationSubscription: any;
  gradeSubscription: any;
  postsSubscription: any;
  activitiesSubscription: any;

  //end subscriptions

  constructor(private realisationService: RealisationService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private gradeService: GradeService,
              private postService: PostService,
              private activityService: ActivityService) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.clearSubscriptions()
      let realisationIdString = params.get('id')!.toString();

      this.realisationId = Number(realisationIdString)

      this.getAverageGrade();
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
    this.postsSubscription = this.postService.getRealisationPosts(this.realisationId).subscribe((result) => {
      this.postPage = result
      this.postsLoading = false
    })
  }

  getActivities() {
    this.activitiesLoading = true
    this.postsSubscription = this.activityService.getRealisationActivities(this.realisationId).subscribe((result) => {
      this.activityPage = result
      this.activitiesLoading = false
    })
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
}
