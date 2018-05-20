import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Target } from '../models/target.model';
import { environment } from '../../environments/environment';

@Injectable()
export class TargetServiceService {



  
  private url = environment.server_url + 'targets/api';

  constructor(private http: HttpClient) { }

  public getTargets() {
    return this.http.get<Target[]>(this.url);
  }

  public updateTarget(target: Target) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post(this.url + "/update", target, {headers: headers});
  }

  public newTarget() {
    return this.http.get<Target>(this.url + "/new/" + "dummy");
  }

  public deleteTarget(target: Target) {
    return this.http.post(this.url + "/delete", target);
  }

  public addWork(target: Target, work: number) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post(this.url + "/addWork", {"name": target.name, "work": work}, {headers: headers});
  }
}
