
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpEvent, HttpRequest} from '@angular/common/http';
import { Session } from '../models/session.model';
import { Datapoint } from '../models/datapoint.model';
import { environment } from '../../environments/environment';



@Injectable()
export class DatasessionsService {

  constructor(private http:HttpClient) { }

  private sessionUrl= environment.server_url +  'sessions/api';

  public getSessions() {
    return this.http.get<Session[]>(this.sessionUrl).pipe(map((sessions) => {
      sessions.forEach(session => {
        session.startTime = new Date(session.startTime)
        session.endTime = new Date(session.endTime);
      });
      return sessions;
    }));
  }

  public getSessionById(id: number) {
    return this.http.get<Datapoint[]>(this.sessionUrl + "/" + id);
  }

  public getSessionByIdAsJSON(id: number) {
    return this.http.get(this.sessionUrl + "/" + id);
  }

  public getSessionAsCSV(id: number) {
    const url = this.sessionUrl + '/' + id + '/download';
    const request = new HttpRequest('GET',
     url,
      {responseType: 'blob', reportProgress: true}); 

    return this.http.request(request);

  }

  public getSessionBySession(session: Session) {
    return this.http.get(this.sessionUrl + "/" + session.sessionId);
  }

  public deleteSession(session: Session) {
    this.http.post(this.sessionUrl + "/delete/" + session.sessionId, "").subscribe();
  }

}
