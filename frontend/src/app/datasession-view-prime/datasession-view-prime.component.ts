import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DatasessionsService } from '../datasession/datasessions.service';
import { Session } from '../models/session.model';
import { Table } from 'primeng/table';
import { Calendar } from 'primeng/calendar';
import { MenuItem, ConfirmationService } from 'primeng/api';

import { Datapoint } from '../models/datapoint.model';
import { saveAs } from 'file-saver';
import { HttpEvent, HttpEventType } from '@angular/common/http';
import { ProgressBar } from 'primeng/progressbar';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-datasession-view-prime',
  templateUrl: './datasession-view-prime.component.html',
  styleUrls: ['./datasession-view-prime.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DatasessionViewPrimeComponent implements OnInit {



  sessions: Session[];
  startDate: Date;
  endDate: Date;


  constructor(private dataService: DatasessionsService, private datePipe: DatePipe, private confirmationService: ConfirmationService) { }

  onStartDateSelected(date: Date, dt: Table) {
    this.startDate = date;
    dt.filter(this.startDate, 'startTime', 'gt');

  }

  onEndDateSelected(date: Date, dt: Table) {
    this.endDate = date;
    dt.filter(this.endDate, 'endTime', 'lt');

  }

  onClearFilterClick(startCalender: Calendar, endCalender: Calendar, dt: Table) {
    startCalender.writeValue(0);
    endCalender.writeValue(0);


    setTimeout(() => {
      this.onStartDateSelected(new Date(0), dt);
      setTimeout(() => {
        this.onEndDateSelected(undefined, dt);
      }, 500);
    }, 250);

  }


 

  downloadSession(session: Session, progressBar: ProgressBar) {

    progressBar.showValue = true;
    progressBar.style = { 'display': 'block', 'height': '20px' };
    progressBar.unit = "mb";

    const sessionId = session.sessionId;

    this.dataService.getSessionAsCSV(sessionId).subscribe((event: HttpEvent<any>) => {
      switch (event.type) {
        case HttpEventType.DownloadProgress:
          progressBar.value = event.loaded / 1000000.0;
          break;
        case HttpEventType.Response:
          saveAs(event.body, 'z600-' + this.datePipe.transform(session.startTime, 'yyyy-MM-dd') + '.csv');
          progressBar.value = 100.0;
          progressBar.unit = '%';
          setTimeout(() => {
            progressBar.style = { 'display': 'none' };
            progressBar.value = 0.0;
          }, 2000);
          break;
      }

    });
  }

  deleteSession(session: Session) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this session? Can not be undone!',
      accept: () => {
        this.dataService.deleteSession(session);
        this.sessions = this.sessions.filter(ses => ses === session);
      }
    });

  }

  ngOnInit() {
    this.dataService.getSessions().subscribe(data => this.sessions = data);
  }

}
