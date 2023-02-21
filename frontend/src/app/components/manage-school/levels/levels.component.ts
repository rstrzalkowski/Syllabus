import {Component, HostListener, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {AlertService} from "../../../services/alert.service";
import {Level, LevelPage} from "../../../model/level";
import {LevelService} from "../../../services/level.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-levels',
  templateUrl: './levels.component.html'
})
export class LevelsComponent implements OnInit {

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination


  //Data
  levels$: Observable<LevelPage> | undefined
  //end data


  //Filter
  showArchived: boolean = false
  //end filter


  //Modals
  deleteModalOpen: boolean = false
  createLevelModalOpen: boolean = false
  //end modals


  //Data passed to children
  levelIdToBeArchived: number | undefined

  //end data


  constructor(private levelService: LevelService,
              private alertService: AlertService,
              public authService: AuthService) {
  }

  ngOnInit(): void {
    this.pageNumber$.subscribe(() => {
      this.getFilteredLevels()
    })
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.deleteModalOpen = false
    }
  }

  getFilteredLevels() {
    if (!this.showArchived) {
      this.levels$ = this.levelService.getActiveLevels(this.pageNumber$.value)
    } else {
      this.levels$ = this.levelService.getArchivedLevels(this.pageNumber$.value)
    }
  }

  showActiveLevels() {
    if (this.showArchived) {
      this.showArchived = false
      this.getFilteredLevels()
    }
  }

  showArchivedLevels() {
    if (!this.showArchived) {
      this.showArchived = true
      this.getFilteredLevels()
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

  deleteLevel() {
    if (this.levelIdToBeArchived) {
      this.levelService.archiveLevel(this.levelIdToBeArchived).subscribe((result) => {
        this.alertService.showAlert("success", "Level has been successfully archived.")
        this.getFilteredLevels()
        this.deleteModalOpen = false
      }, error => {
        this.alertService.showAlert("danger", "There was a problem during archiving level. Try again later.")
      })
    }
  }

  showDeleteModal(level: Level) {
    if (level.activeSchoolClasses > 0) {
      this.alertService.showAlert('warning', "You can't archive this level because there are active school classes of this level")
      return
    }
    this.levelIdToBeArchived = level.id
    this.deleteModalOpen = true
  }
}
