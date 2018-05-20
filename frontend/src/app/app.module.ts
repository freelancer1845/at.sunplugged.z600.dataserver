import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ControlContainer, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MatTableModule, MatSortModule, MatFormFieldModule, MatDatepickerModule, MatInputModule, MatButtonModule } from '@angular/material';
import { MAT_MOMENT_DATE_FORMATS, MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';

import { TableModule } from 'primeng/table';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import {ContextMenuModule, ContextMenu} from 'primeng/contextmenu';
import {MenuItem} from 'primeng/api';
import {DialogModule} from 'primeng/dialog';
import {CheckboxModule} from 'primeng/checkbox';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ConfirmationService} from 'primeng/api';
import {ProgressBarModule} from 'primeng/progressbar';
import {TabMenuModule} from 'primeng/tabmenu';

import { saveAs} from 'file-saver';

import { AppComponent } from './app.component';
import { DatasessionsService } from './datasession/datasessions.service';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { DatasessionViewPrimeComponent } from './datasession-view-prime/datasession-view-prime.component';
import { TargetViewPrimeComponent } from './target-view-prime/target-view-prime.component';
import { TargetServiceService } from './target-view-prime/target-service.service';


const routes: Routes = [
  { path: 'datasessions', component: DatasessionViewPrimeComponent },
  { path: "targets", component: TargetViewPrimeComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    DatasessionViewPrimeComponent,
    TargetViewPrimeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatSortModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatInputModule,
    MatButtonModule,
    RouterModule.forRoot(routes),
    TableModule,
    CalendarModule,
    InputTextModule,
    ButtonModule,
    ContextMenuModule,
    DialogModule,
    CheckboxModule,
    ReactiveFormsModule,
    ConfirmDialogModule,
    ProgressBarModule,
    TabMenuModule
  ],
  providers: [DatasessionsService,
    TargetServiceService,
    DatePipe,
    ConfirmationService,
    { provide: MAT_DATE_LOCALE, useValue: 'de-GE' },
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
