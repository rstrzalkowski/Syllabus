import {Component} from '@angular/core';
import {AlertService} from "../../../services/alert.service";
import {ThemeService} from "../../../services/theme.service";

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html'
})
export class AlertComponent {

  success = false;
  warning = false;
  danger = false;
  info = false;
  message = "";
  lastCallback: any

  constructor(private alertService: AlertService,
              public themeService: ThemeService) {
    this.alertService.alert.subscribe((event) => {
      this.resetAlerts()
      this.message = event.message
      switch (event.type) {
        case "warning": {
          this.warning = true
          break
        }
        case "info": {
          this.info = true
          break
        }
        case "danger": {
          this.danger = true
          break
        }
        case "success": {
          this.success = true
          break
        }
      }
      let timeoutString = localStorage.getItem("alertTimeout")
      if (!timeoutString) {
        timeoutString = "3000"
      }
      let timeout = JSON.parse(timeoutString)
      if (timeout != 0) {
        let callback = setTimeout(() => {
          this.resetAlerts()
        }, timeout)
        clearTimeout(this.lastCallback)
        this.lastCallback = callback
      }
    })
  }

  resetAlerts() {
    this.success = false
    this.info = false
    this.danger = false
    this.warning = false
  }
}
