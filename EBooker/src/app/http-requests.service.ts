import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestsService {

  constructor(private http:HttpClient) { }

  public sendPostRequest(url: string, body: any, headers: any ): Observable<any> {
    return this.http.post(url, body, {headers, observe: "response"});
  }
}
