import {Component, OnInit} from '@angular/core';
import {RealisationService} from "../../services/realisation.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RealisationInfo} from "../../model/realisation.info";
import {GradeService} from "../../services/grade.service";
import {PostService} from "../../services/post.service";
import {PostPage} from "../../model/post";

@Component({
  selector: 'app-realisation',
  templateUrl: './realisation.component.html'
})
export class RealisationComponent implements OnInit {

  realisationId: number | undefined
  realisationInfo: RealisationInfo | undefined
  realisationLoading = false;
  postsLoading = false;
  gradeLoading = false;
  realisationSubscription: any;
  gradeSubscription: any;
  postsSubscription: any;


  grade: number | undefined;
  postPage: PostPage | undefined;


  constructor(private realisationService: RealisationService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private gradeService: GradeService,
              private postService: PostService) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.clearSubscriptions()
      let realisationIdString = params.get('id')!.toString();

      this.realisationId = Number(realisationIdString)

      this.getAverageGrade();
      this.getPosts();
      this.getRealisationInfo(this.realisationId);
    })
  }

  clearSubscriptions() {
    this.realisationSubscription?.unsubscribe()
    this.postsSubscription?.unsubscribe()
    this.gradeSubscription?.unsubscribe()
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

  getRealisationInfo(realisationId: number) {
    this.realisationLoading = true
    this.realisationSubscription = this.realisationService.getRealisationInfo(realisationId).subscribe((result) => {
      this.realisationInfo = result
      this.realisationLoading = false;
    }, error => {
      this.router.navigate(['/forbidden'])
    })
  }

  isLoading() {
    return this.realisationLoading || this.postsLoading || this.gradeLoading
  }
}
