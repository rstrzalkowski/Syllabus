import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError, Observable} from "rxjs";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html'
})
export class UserProfileComponent implements OnInit {

  user$: Observable<User> | undefined


  constructor(private userService: UserService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      const userId = params.get('id')!.toString();

      this.user$ = this.userService.getUser(userId)
        .pipe(catchError(err => {
          this.router.navigate(['/404'])
          throw err
        }))
    })
  }

}
