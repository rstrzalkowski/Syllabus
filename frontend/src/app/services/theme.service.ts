import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  public lightTheme = true;

  constructor() {
    this.lightTheme = JSON.parse(localStorage.getItem("lightTheme") || 'true')
  }

  switchTheme() {
    this.lightTheme = !this.lightTheme
    localStorage.setItem("lightTheme", JSON.stringify(this.lightTheme));
  }
}
