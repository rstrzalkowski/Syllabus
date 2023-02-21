import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-profile',
  templateUrl: './own-profile.component.html',
})
export class OwnProfileComponent implements OnInit {

  aboutChange = false
  newAbout = ""
  changeImageModalOpen = false

  constructor(private userService: UserService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    if (this.userService.user == undefined) {
      this.userService.getLoggedInUserObservable().subscribe((result) => {
        this.userService.user = result
        this.newAbout = this.userService.user.description
      })
    } else {
      this.newAbout = this.userService.user.description
    }
  }

  refreshUserData() {
    this.userService.getLoggedInUserObservable().subscribe((result) => {
      this.userService.user = result
      this.newAbout = this.userService.user.description
    })
  }

  saveAbout() {
    this.userService.updateAbout(this.newAbout).subscribe((result) => {
      this.aboutChange = false
      this.userService.getLoggedInUser()
      this.alertService.showAlert("success", "Description updated successfully")
    }, error => {
      this.alertService.showAlert("danger", "Error during updating description.")
    })
  }

  cancelChangingAbout() {
    this.newAbout = this.user?.description
    this.aboutChange = false
  }

  get user() {
    return this.userService.user
  }
}
