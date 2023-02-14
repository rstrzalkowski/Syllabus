import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-manage-school-sidebar',
  templateUrl: './manage-school-sidebar.component.html'
})
export class ManageSchoolSidebarComponent implements OnInit {


  @Output() hideSidebar: EventEmitter<any> = new EventEmitter<any>()

  constructor() {
  }

  ngOnInit(): void {
  }

  hideSidebarIfOverlapping() {
    this.hideSidebar.emit()
  }
}
