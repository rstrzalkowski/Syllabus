import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {

  user: User | undefined

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getLoggedInUser().subscribe((result) => {
      this.user = result
    })
  }
}
