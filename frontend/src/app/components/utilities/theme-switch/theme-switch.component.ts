import {Component, OnInit} from '@angular/core';
import {ThemeService} from "../../../services/theme.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './theme-switch.component.html',
})
export class ThemeSwitchComponent implements OnInit {

  constructor(public themeService: ThemeService) {
  }

  ngOnInit(): void {
  }

  switchTheme() {
    this.themeService.switchTheme()
  }
}
