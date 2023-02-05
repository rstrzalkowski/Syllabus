import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {RealisationService} from "../../services/realisation.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = "";
  password = "";

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  public login() {
    this.authService.login(this.username, this.password).subscribe((result) => {
      console.log(result.status)
    })
  }

}
