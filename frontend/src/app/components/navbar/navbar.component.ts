import {Component, OnInit} from '@angular/core';
import {ThemeService} from "../../services/theme.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public themeService: ThemeService) {
  }

  ngOnInit(): void {
  }

  switchTheme() {
    this.themeService.switchTheme()
  }
}
