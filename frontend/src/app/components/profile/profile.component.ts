import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
})
export class ProfileComponent implements OnInit {

  user: User | undefined

  aboutChange = false
  newAbout = ""

  constructor(private userService: UserService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.userService.getLoggedInUser().subscribe((result) => {
      this.user = result
      this.newAbout = this.user.description
    })
  }

  saveAbout() {
    this.userService.updateAbout(this.newAbout).subscribe((result) => {
      this.aboutChange = false
      this.ngOnInit()
      this.alertService.showAlert("success", "Description updated successfully")
    }, error => {
      this.alertService.showAlert("danger", "Error during updating description.")
    })
  }

  cancelChangingAbout() {
    this.newAbout = this.user?.description
    this.aboutChange = false
  }
}
