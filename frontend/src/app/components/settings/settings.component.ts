import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {

  oldPassword = "";
  newPassword = "";
  repeatPassword = "";
  alertTimeout: number = 3000;

  constructor(private userService: UserService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    let timeout = localStorage.getItem("alertTimeout")
    if (timeout) {
      this.alertTimeout = JSON.parse(timeout)
    } else {
      this.alertTimeout = 3000
    }
  }

  changePassword() {
    if (!(this.newPassword === this.repeatPassword)) {
      this.alertService.showAlert("warning", "Passwords don't match.")
      return;
    }
    this.userService.changePassword(this.oldPassword, this.newPassword).subscribe((result) => {
      this.alertService.showAlert("success", "Password changed successfully!")
      this.clearChangePasswordForm()
    }, error => {
      this.alertService.showAlert("danger", "Password doesn't meet strength requirements.")
    })
  }

  clearChangePasswordForm() {
    this.oldPassword = ""
    this.newPassword = ""
    this.repeatPassword = ""
  }

  saveAlertTimeout() {
    localStorage.setItem("alertTimeout", JSON.stringify(this.alertTimeout))
    this.alertService.showAlert("success", "Alert timeout saved successfully!")
  }

  get user() {
    return this.userService.user
  }
}
