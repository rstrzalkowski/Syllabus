import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {Class} from "../../../../model/class";
import {Observable} from "rxjs";
import {UserPage} from "../../../../model/user";
import {UserService} from "../../../../services/user.service";
import {AlertService} from "../../../../services/alert.service";
import {ClassService} from "../../../../services/class.service";

@Component({
  selector: 'app-edit-students',
  templateUrl: './edit-students.component.html'
})
export class EditStudentsComponent implements OnInit {

  studentIdToBeAdded: number | undefined
  studentIdToBeUnassigned: number | undefined

  loading = false

  deleteModalOpen: boolean = false

  @Input() class: Class | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() successUnassign: EventEmitter<any> = new EventEmitter()
  @Output() successAssign: EventEmitter<any> = new EventEmitter()

  unassignedStudents$: Observable<UserPage> = this.userService.getUnassignedStudents()

  constructor(private userService: UserService,
              private classService: ClassService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.loading = true
    this.classService.getClassById(this.class?.id).subscribe((result) => {
      this.class = result
      this.loading = false
    })
  }

  unassign() {
    if (this.studentIdToBeUnassigned) {
      this.userService.unassignStudent(this.studentIdToBeUnassigned).subscribe((result) => {
        this.alertService.showAlert("success", "Student has been successfully unassigned.")
        this.refreshUnassignedStudents()
        this.ngOnInit()
        this.successUnassign.emit()
        this.deleteModalOpen = false
      }, error => {
        this.alertService.showAlert("danger", "There was a problem during unassigning. Try again later.")
      })
    }
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.deleteModalOpen = false
    }
  }

  assign() {
    if (this.studentIdToBeAdded) {
      this.userService.assignStudent(this.studentIdToBeAdded, this.class?.id).subscribe((result) => {
        this.alertService.showAlert("success", "Student has been successfully added to class.")
        this.refreshUnassignedStudents()
        this.ngOnInit()
        this.successAssign.emit()
      }, error => {
        this.alertService.showAlert("danger", "There was a problem assigning student to class. Try again later.")
      })
    } else {
      this.alertService.showAlert("warning", "Choose a student to add.")
    }
  }

  refreshUnassignedStudents() {
    this.unassignedStudents$ = this.userService.getUnassignedStudents()
  }

  showDeleteModal(id: number) {
    this.studentIdToBeUnassigned = id
    this.deleteModalOpen = true
  }
}
