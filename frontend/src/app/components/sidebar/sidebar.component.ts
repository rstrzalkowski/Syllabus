import {Component, HostListener, OnInit} from '@angular/core';
import {ThemeService} from "../../services/theme.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
})
export class SidebarComponent implements OnInit {

  hidden = true;
  defaultTouch = {x: 0, y: 0, time: 0};

  constructor(public themeService: ThemeService,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    if (window.innerWidth > 1024) {
      this.hidden = false;
    }
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

  logout() {
    this.authService.logout()
    this.router.navigate(['/login'])
  }

  hide() {
    this.hidden = true
  }

  show() {
    this.hidden = false
  }
}
