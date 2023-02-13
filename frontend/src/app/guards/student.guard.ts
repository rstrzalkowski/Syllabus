import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from "../services/auth.service";
import {UserService} from "../services/user.service";

@Injectable({
  providedIn: 'root'
})
export class StudentGuard implements CanActivate {

  constructor(
    private router: Router,
    private authService: AuthService,
    private userService: UserService) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    // if (!this.userService.user) {
    //   this.userService.getLoggedInUser()
    //   while (this.userService.user === undefined) {
    //   }
    // }
    //
    // if (this.userService.user?.role !== 'STUDENT') {
    //   this.router.navigate(['forbidden'])
    //   return false
    // }
    return true
  }
}
