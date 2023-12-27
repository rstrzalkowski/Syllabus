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

  email = "";
  password = "";
  loading = false;
  chooseRoleModalOpened = false;

  constructor(private authService: AuthService,
              private router: Router,
              public themeService: ThemeService,
              private alertService: AlertService,
              private userService: UserService) {
  }

  ngOnInit(): void {
  }

  login() {
    this.authService.login(this.email, this.password).subscribe((result) => {
      this.loading = true
      this.authService.saveJWT(result)

      const roles = this.getAppRolesFromJwt();
      if (roles.length > 1) {
        this.chooseRoleModalOpened = true;
      } else {
        this.roleHasBeenChosen(roles[0] || "")
      }

    }, error => {
      this.alertService.showAlert("danger", "Wrong credentials")
      this.loading = false
    })
  }

  getAppRolesFromJwt(): string[] {
    const decodedJWT = this.authService.decodeJWT(this.authService.getJwtFromStorage()!)
    let roles: string[] = []
    decodedJWT.realm_access.roles.forEach((role: string) => {
      if (["STUDENT", "TEACHER", "OFFICE", "DIRECTOR", "ADMIN"].includes(role)) {
        roles.push(role)
      }
    })
    return roles;
  }

  roleHasBeenChosen(role: string) {
    localStorage.setItem("role", role)
    this.chooseRoleModalOpened = false;
    this.userService.getLoggedInUserObservable().subscribe((result) => {
      this.userService.user = result
      this.router.navigate(['/dashboard'])
    })
  }

  closeRoleModal() {
    this.loading = false
    this.chooseRoleModalOpened = false;
  }

  loginAs(email: string) {
    this.email = email
    this.password = "P@ssw0rd"
    this.login()
  }
}
