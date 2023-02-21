import {Component, HostListener, OnInit} from '@angular/core';
import {ThemeService} from "../../services/theme.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {RealisationService} from "../../services/realisation.service";
import {RealisedSubject} from "../../model/realised.subject";
import {Observable} from "rxjs";
import {UserPage} from "../../model/user";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {

  sidebarHidden = true;
  subjectsHidden = false;
  defaultTouch = {x: 0, y: 0, time: 0};
  logoutModal = false;
  searchInputHidden = true;
  searchResultsHidden = true;
  searchInput = "";


  foundUsers$: Observable<UserPage> | undefined


  subjects: RealisedSubject[] = []


  constructor(public themeService: ThemeService,
              public authService: AuthService,
              public userService: UserService,
              private realisationService: RealisationService,
              private router: Router) {
  }

  ngOnInit(): void {
    if (this.router.url == '/') {
      this.router.navigate(['dashboard'])
    }

    if (window.innerWidth > 1023) {
      this.sidebarHidden = false;
    }

    if (this.authService.getRole() == "STUDENT" || this.authService.getRole() == "TEACHER")
      this.realisationService.getRealisedSubjects().subscribe((result) => {
        this.subjects = result;
      })
  }

  @HostListener('touchstart', ['$event'])
  @HostListener('touchend', ['$event'])
  handleTouch(event: { touches: any[]; changedTouches: any[]; type: string; timeStamp: number; }) {
    let touch = event.touches[0] || event.changedTouches[0];
    // check the events
    if (event.type === 'touchstart') {
      this.defaultTouch.x = touch.pageX;
      this.defaultTouch.time = event.timeStamp;
    } else if (event.type === 'touchend') {
      let deltaX = touch.pageX - this.defaultTouch.x;
      let deltaTime = event.timeStamp - this.defaultTouch.time;

      // simulate a swipe -> less than 500 ms and more than 60 px
      if (deltaTime < 500) {
        // touch movement lasted less than 500 ms
        if (Math.abs(deltaX) > 60) {
          // delta x is at least 60 pixels
          if (deltaX > 0) {
            this.show();
          } else {
            this.hide();
          }
        }
      }
    }
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.logoutModal = false
    }
  }

  logout() {
    this.authService.logout()
    this.router.navigate(['/login'])
  }

  hide() {
    this.sidebarHidden = true
  }

  hideSidebarIfOverlapping() {
    if (window.innerWidth < 1024) {
      this.sidebarHidden = true;
    }
  }

  show() {
    this.sidebarHidden = false
  }

  get user() {
    return this.userService.user
  }

  switchSearchInput() {
    this.searchInputHidden = !this.searchInputHidden
  }

  search() {
    if (this.searchInput === '') {
      this.foundUsers$ = undefined
    } else {
      this.foundUsers$ = this.userService.searchUsers(this.searchInput)
    }
  }

  showResults() {
    setTimeout(() => {
      this.searchResultsHidden = false
    }, 100)
  }

  hideResults() {
    setTimeout(() => {
      this.searchResultsHidden = true
    }, 100)
  }
}
