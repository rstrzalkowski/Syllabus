import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
})
export class ProfileComponent implements OnInit {

  user: User | undefined

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getLoggedInUser().subscribe((result) => {
      this.user = result
      console.log(this.user)
    })
  }

}
