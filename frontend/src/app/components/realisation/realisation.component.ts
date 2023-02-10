import {Component, OnInit} from '@angular/core';
import {RealisationService} from "../../services/realisation.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RealisationInfo} from "../../model/realisation.info";

@Component({
  selector: 'app-realisation',
  templateUrl: './realisation.component.html'
})
export class RealisationComponent implements OnInit {

  realisationId: number | undefined
  realisationInfo: RealisationInfo | undefined
  loading = false;


  constructor(private realisationService: RealisationService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.loading = true;
      this.realisationInfo = undefined
      let realisationIdString = params.get('id')!.toString();

      this.realisationId = Number(realisationIdString)
      this.realisationService.getRealisationInfo(this.realisationId).subscribe((result) => {
        this.realisationInfo = result
        this.loading = false;
      }, error => {
        this.router.navigate(['/forbidden'])
      })
    })
  }
}
