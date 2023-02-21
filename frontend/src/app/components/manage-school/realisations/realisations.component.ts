import {Component, HostListener, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {AlertService} from "../../../services/alert.service";
import {RealisationInfo, RealisationInfoPage} from "../../../model/realisation.info";
import {RealisationService} from "../../../services/realisation.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-realisations',
  templateUrl: './realisations.component.html'
})
export class RealisationsComponent implements OnInit {

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination


  //Data
  realisations$: Observable<RealisationInfoPage> | undefined
  //end data

  //Filter
  showArchived: boolean = false
  //end filter

  //Modals
  deleteModalOpen: boolean = false
  createRealisationModalOpen: boolean = false
  editRealisationModalOpen: boolean = false
  //end modals


  //Data passed to children
  realisationIdToBeArchived: number | undefined
  editedRealisation: RealisationInfo | undefined

  //end data


  constructor(private realisationService: RealisationService,
              private alertService: AlertService,
              public authService: AuthService) {
  }

  ngOnInit(): void {
    this.pageNumber$.subscribe(() => {
      this.getFilteredRealisations()
    })
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.deleteModalOpen = false
    }
  }

  getFilteredRealisations() {
    if (!this.showArchived) {
      this.realisations$ = this.realisationService.getActiveRealisations(this.pageNumber$.value)
    } else {
      this.realisations$ = this.realisationService.getArchivedRealisations(this.pageNumber$.value)
    }
  }

  showActiveRealisations() {
    if (this.showArchived) {
      this.showArchived = false
      this.getFilteredRealisations()
    }
  }

  showArchivedRealisations() {
    if (!this.showArchived) {
      this.showArchived = true
      this.getFilteredRealisations()
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

  deleteRealisation() {
    if (this.realisationIdToBeArchived) {
      this.realisationService.archiveRealisation(this.realisationIdToBeArchived).subscribe((result) => {
        this.alertService.showAlert("success", "Realisation has been successfully archived.")
        this.getFilteredRealisations()
        this.deleteModalOpen = false
      }, error => {
        this.alertService.showAlert("danger", "There was a problem during archiving realisation. Try again later.")
      })
    }
  }

  showDeleteModal(id: number) {
    this.realisationIdToBeArchived = id
    this.deleteModalOpen = true
  }

  showEditModal(realisation: RealisationInfo) {
    this.editedRealisation = realisation
    this.editRealisationModalOpen = true
  }
}
