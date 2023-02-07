import {Component, OnInit} from '@angular/core';
import {ThemeService} from "../../services/theme.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
})
export class SidebarComponent implements OnInit {

  hidden = true;

  constructor(public themeService: ThemeService,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    if (window.innerWidth > 1024) {
      this.hidden = false;
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
