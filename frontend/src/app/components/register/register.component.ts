import {Component, OnInit} from '@angular/core';
import {ThemeService} from "../../services/theme.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
})
export class RegisterComponent implements OnInit {

  constructor(public themeService: ThemeService) {
  }

  ngOnInit(): void {
  }

}
