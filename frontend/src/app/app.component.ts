import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { TabMenuModule } from 'primeng/tabmenu';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  items: MenuItem[];
  title = 'app';

  ngOnInit() {
    this.items = [
      {label: 'Datasessions',  icon: 'fa-database', routerLink: ['/datasessions']},
      {label: 'Targets',  icon: 'fa-info', routerLink: ['/targets']}
    ];
  }
}
