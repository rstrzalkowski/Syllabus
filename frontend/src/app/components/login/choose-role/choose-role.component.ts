import {Component, EventEmitter, HostListener, OnInit, Output} from '@angular/core';
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-choose-role',
  templateUrl: './choose-role.component.html'
})
export class ChooseRoleComponent implements OnInit {
  //Loading
  loading = false
  //end loading

  roles: string[] = []

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<string> = new EventEmitter()

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
    const jwt = this.authService.getJwtFromStorage();
    let decodedJWT = this.authService.decodeJWT(jwt!);

    this.roles = decodedJWT.realm_access.roles;
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  loginAs(role: string) {
    this.success.emit(role);
  }

  closeModal() {
    this.close.emit()
  }
}
