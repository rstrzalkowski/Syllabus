import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AlertService {


  alert = new Subject<AlertInfo>();

  constructor() {
  }

  showAlert(type: string, message: string) {
    const alertInfo: AlertInfo = {
      message: message,
      type: type
    }
    this.alert.next(alertInfo)
  }
}

export interface AlertInfo {
  type: string
  message: string
}


