import {Component, HostListener, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {AlertService} from "../../../services/alert.service";
import {Class, ClassPage} from "../../../model/class";
import {ClassService} from "../../../services/class.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html'
})
export class ClassesComponent implements OnInit {

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination


  //Data
  classes$: Observable<ClassPage> | undefined

  //end data

  //Filter
  showArchived: boolean = false
  //end filter

  //Modals
  deleteModalOpen: boolean = false
  createClassModalOpen: boolean = false
  editClassModalOpen: boolean = false
  manageStudentWindow: boolean = false
  //end modals


  //Data passed to children
  classIdToBeArchived: number | undefined
  editedClass: Class | undefined

  //end data


  constructor(private classService: ClassService,
              private alertService: AlertService,
              public authService: AuthService) {
  }

  ngOnInit(): void {
    this.pageNumber$.subscribe(() => {
      this.getFilteredClasses()
    })
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.createClassModalOpen = false
      this.editClassModalOpen = false
      this.deleteModalOpen = false
    }
  }

  getFilteredClasses() {
    if (!this.showArchived) {
      this.classes$ = this.classService.getActiveClasses(this.pageNumber$.value)
    } else {
      this.classes$ = this.classService.getArchivedClasses(this.pageNumber$.value)
    }
  }

  showActiveClasses() {
    if (this.showArchived) {
      this.showArchived = false
      this.getFilteredClasses()
    }
  }

  showArchivedClasses() {
    if (!this.showArchived) {
      this.showArchived = true
      this.getFilteredClasses()
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

  deleteClass() {
    if (this.classIdToBeArchived) {
      this.classService.archiveClass(this.classIdToBeArchived).subscribe((result) => {
        this.alertService.showAlert("success", "Class has been successfully archived.")
        this.getFilteredClasses()
        this.deleteModalOpen = false
      }, error => {
        this.alertService.showAlert("danger", "There was a problem during archiving class. Try again later.")
      })
    }
  }

  showDeleteModal(id: number) {
    this.classIdToBeArchived = id
    this.deleteModalOpen = true
  }

  showEditModal(schoolClass: Class) {
    this.editedClass = schoolClass
    this.editClassModalOpen = true
  }

  showManageStudentsWindow(schoolClass: Class) {
    this.editedClass = schoolClass
    this.manageStudentWindow = true
  }

}
