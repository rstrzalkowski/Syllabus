import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {TokenPage} from "../../../../model/token";
import {SubjectService} from "../../../../services/subject.service";
import {AlertService} from "../../../../services/alert.service";
import {UserService} from "../../../../services/user.service";
import {AuthService} from "../../../../services/auth.service";

@Component({
  selector: 'app-codes',
  templateUrl: './codes.component.html'
})
export class CodesComponent implements OnInit {

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination


  //Data
  codes$: Observable<TokenPage> | undefined
  //end data

  //Filter
  userType: string = "STUDENT"
  //end filter

  //Modals
  deleteModalOpen: boolean = false

  //end modals


  constructor(private subjectService: SubjectService,
              private alertService: AlertService,
              private userService: UserService,
              public authService: AuthService) {
  }

  ngOnInit(): void {
    this.pageNumber$.subscribe(() => {
      this.getFilteredTokens()
    })
  }

  getFilteredTokens() {
    switch (this.userType) {
      case "STUDENT": {
        this.codes$ = this.userService.getStudentTokens(this.pageNumber$.value)
        break
      }
      case "TEACHER": {
        this.codes$ = this.userService.getTeacherTokens(this.pageNumber$.value)
        break
      }
      case "OFFICE": {
        this.codes$ = this.userService.getOfficeTokens(this.pageNumber$.value)
        break
      }
      case "DIRECTOR": {
        this.codes$ = this.userService.getDirectorTokens(this.pageNumber$.value)
        break
      }
      default: {
        this.codes$ = this.codes$ = this.userService.getStudentTokens(this.pageNumber$.value)
      }
    }
  }

  showStudentTokens() {
    this.userType = "STUDENT"
    this.getFilteredTokens()
  }

  showTeacherTokens() {
    this.userType = "TEACHER"
    this.getFilteredTokens()
  }

  showOfficeTokens() {
    this.userType = "OFFICE"
    this.getFilteredTokens()
  }

  showDirectorTokens() {
    this.userType = "DIRECTOR"
    this.getFilteredTokens()
  }
  
  previousPage() {
    if (this.pageNumber$.value > 0) {
      this.pageNumber$.next(this.pageNumber$.value - 1)
    }
  }

  nextPage() {
    this.pageNumber$.next(this.pageNumber$.value + 1)
  }
}
