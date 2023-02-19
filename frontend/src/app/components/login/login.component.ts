import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {ThemeService} from "../../services/theme.service";
import {AlertService} from "../../services/alert.service";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  username = "";
  password = "";
  loading = false;

  constructor(private authService: AuthService,
              private router: Router,
              public themeService: ThemeService,
              private alertService: AlertService,
              private userService: UserService) {
  }

  ngOnInit(): void {
  }

  login() {
    this.authService.login(this.username, this.password).subscribe((result) => {
      this.loading = true
      this.authService.saveJWT(result)
      this.userService.getLoggedInUserObservable().subscribe((result) => {
        this.userService.user = result
        this.router.navigate(['/dashboard'])
      })
    }, error => {
      this.alertService.showAlert("danger", "Wrong credentials")
      this.loading = false
    })
  }
}
