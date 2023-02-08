import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
})
export class ProfileComponent implements OnInit {

  user: User | undefined

  aboutChange = false
  newAbout = ""

  constructor(private userService: UserService) {
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
    }, error => {
      alert("Nie udalo sie zmienic opisu")
    })
  }

  cancelChangingAbout() {
    this.newAbout = this.user?.description
    this.aboutChange = false
  }
}
