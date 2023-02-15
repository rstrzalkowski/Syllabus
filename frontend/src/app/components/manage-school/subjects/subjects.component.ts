import {Component, OnInit} from '@angular/core';
import {SubjectService} from "../../../services/subject.service";
import {BehaviorSubject, Observable} from "rxjs";
import {SubjectPage} from "../../../model/subject";
import {AlertService} from "../../../services/alert.service";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html'
})
export class SubjectsComponent implements OnInit {

  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  subjects$: Observable<SubjectPage> | undefined
  showArchived: boolean = false
  deleteModalOpened: boolean = false
  subjectIdToBeArchived: number | undefined


  constructor(private subjectService: SubjectService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.pageNumber$.subscribe(() => {
      this.getFilteredSubjects()
    })
  }

  getFilteredSubjects() {
    if (!this.showArchived) {
      this.subjects$ = this.subjectService.getActiveSubjects(this.pageNumber$.value)
    } else {
      this.subjects$ = this.subjectService.getArchivedSubjects(this.pageNumber$.value)
    }
  }

  showActiveSubjects() {
    if (this.showArchived) {
      this.showArchived = false
      this.getFilteredSubjects()
    }
  }

  showArchivedSubjects() {
    if (!this.showArchived) {
      this.showArchived = true
      this.getFilteredSubjects()
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

  deleteSubject() {
    if (this.subjectIdToBeArchived) {
      this.subjectService.archiveSubject(this.subjectIdToBeArchived).subscribe((result) => {
        this.alertService.showAlert("success", "Subject has been successfully archived.")
        this.getFilteredSubjects()
        this.deleteModalOpened = false
      }, error => {
        this.alertService.showAlert("danger", "There was a problem during archiving subject. Try again later.")
      })
    }
  }

  showDeleteModal(id: number) {
    this.subjectIdToBeArchived = id
    this.deleteModalOpened = true
  }
}
