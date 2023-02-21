import {Component, HostListener, OnInit} from '@angular/core';
import {SubjectService} from "../../../services/subject.service";
import {BehaviorSubject, Observable} from "rxjs";
import {Subject, SubjectPage} from "../../../model/subject";
import {AlertService} from "../../../services/alert.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html'
})
export class SubjectsComponent implements OnInit {

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination


  //Data
  subjects$: Observable<SubjectPage> | undefined
  //end data

  //Filter
  showArchived: boolean = false
  //end filter

  //Modals
  deleteModalOpen: boolean = false
  createSubjectModalOpen: boolean = false
  editSubjectModalOpen: boolean = false
  editImageModalOpen: boolean = false
  //end modals


  //Data passed to children
  subjectIdToBeArchived: number | undefined
  editedSubject: Subject | undefined
  editedImageSubject: Subject | undefined

  //end data


  constructor(private subjectService: SubjectService,
              private alertService: AlertService,
              public authService: AuthService) {
  }

  ngOnInit(): void {
    this.pageNumber$.subscribe(() => {
      this.getFilteredSubjects()
    })
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.deleteModalOpen = false
      this.editImageModalOpen = false
      this.createSubjectModalOpen = false
      this.editImageModalOpen = false
    }
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
        this.deleteModalOpen = false
      }, error => {
        this.alertService.showAlert("danger", "There was a problem during archiving subject. Try again later.")
      })
    }
  }

  showDeleteModal(id: number) {
    this.subjectIdToBeArchived = id
    this.deleteModalOpen = true
  }

  showEditModal(subject: Subject) {
    this.editedSubject = subject
    this.editSubjectModalOpen = true
  }

  showEditImageModal(subject: Subject) {
    this.editedImageSubject = subject
    this.editImageModalOpen = true
  }
}
