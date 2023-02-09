import {Component, OnInit} from '@angular/core';
import {RealisationService} from "../../services/realisation.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RealisationInfo} from "../../model/realisation.info";

@Component({
  selector: 'app-realisation',
  templateUrl: './realisation.component.html'
})
export class RealisationComponent implements OnInit {

  realisationInfo: RealisationInfo | undefined

  constructor(private realisationService: RealisationService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      let realisationIdString = params.get('id')!.toString();
      let realisationId
      try {
        realisationId = Number(realisationIdString)
        this.realisationService.getRealisationInfo(realisationId).subscribe((result) => {
          this.realisationInfo = result
        })
      } catch (err) {
        this.router.navigate([''])
      }
    })
  }
}
