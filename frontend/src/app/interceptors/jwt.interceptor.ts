import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {catchError, Observable} from 'rxjs';
import {AuthService} from "../services/auth.service";
import {environment} from "../../environments/environment";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.url.startsWith(environment.apiUrl) && this.authService.authenticated.value) {
      const cloned = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.authService.getJwtFromStorage() || ""}`
        }
      })
      return next.handle(cloned).pipe(catchError(err => {
        if (err.status === 401) {
          this.authService.jwtExpired()
        }
        throw err
      }));
    }
    return next.handle(request);
  }
}
