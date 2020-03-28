import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable} from 'rxjs';
import { map } from 'rxjs/operators';

import { Customer } from './models/customer-model';
import { HttpRequestsService } from './http-requests.service';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<Customer>;
  public currentUser: Observable<Customer>;
  public customer: Customer;
  public authenticated: boolean;
  
  constructor(private httpService:HttpRequestsService) {
    this.currentUserSubject = new BehaviorSubject<Customer>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
   }

  public login(username:string, password:string){
      const headers={'Content-Type': 'text/plain'}
      const body = {"username" : username,"password" : password};

      return this.httpService.sendPostRequest('https://localhost:8443/login', body, headers).pipe(map(data =>{
        this.customer = data.body;
        localStorage.setItem('currentUser', JSON.stringify(this.customer));
        localStorage.setItem('token', data.headers.get('Authorization'));
        return this.customer;
      }))
      }

      logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('currentUser');
        localStorage.removeItem('token');
        this.currentUserSubject.next(null);
    }


}