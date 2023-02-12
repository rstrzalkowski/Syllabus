import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-create-activity',
  templateUrl: './create-activity.component.html'
})
export class CreateActivityComponent implements OnInit {

  @Input() realisationId: number | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor() {
  }

  ngOnInit(): void {
  }

  closeModal() {
    this.close.emit()
  }
}
