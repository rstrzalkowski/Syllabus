import {Component, OnInit} from '@angular/core';
import {ThemeService} from "../../services/theme.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {AlertService} from "../../services/alert.service";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
})
export class RegisterComponent implements OnInit {


  //Data
  email = ""
  password = ""
  repeatPassword = ""
  firstName = ""
  lastName = ""
  personalId = ""
  token = ""
  //end data

  loading = false

  constructor(private authService: AuthService,
              private router: Router,
              public themeService: ThemeService,
              private alertService: AlertService,
              private userService: UserService) {
  }

  ngOnInit(): void {
  }

  register() {
    if (this.email == "" || this.password == "" || this.firstName == "" || this.lastName == "" || this.personalId == "" || this.token == "") {
      this.alertService.showAlert("danger", "Please fill all fields.")
      return
    }

    if (this.password.length < 8 || this.password.length > 20) {
      this.alertService.showAlert("danger", "Password must be between 8 and 20 characters.")
      return
    }

    if (this.repeatPassword != this.password) {
      this.alertService.showAlert("danger", "Repeated password doesn't match password")
      return
    }

    this.loading = true
    this.authService.register(this.email, this.password, this.firstName, this.lastName, this.personalId, this.token).subscribe((result) => {
      this.router.navigate(['/login'])
      this.alertService.showAlert("success", "You have successfully registered! You can log in now.")
      this.loading = false

    }, error => {
      this.loading = false
      if (error.status === 406) {
        this.alertService.showAlert("danger", "Invalid registration token.")
      } else if (error.status === 409) {
        this.alertService.showAlert("danger", "There is another account registered on this email.")
      } else {
        this.alertService.showAlert("danger", "Provided data is invalid. Make sure form is properly filled.")
      }
    })
  }
}
