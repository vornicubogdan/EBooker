import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { ErrorHandlerService } from '../error-handler.service';
import { Customer } from '../models/customer-model';

@Component({
  selector: 'app-login',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  username: string;
  password: string;
  message: any

  constructor(private authService:AuthenticationService, private errorService:ErrorHandlerService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.authService.login(this.username, this.password).subscribe(
      loggedCustomer =>{
            this.navigate(loggedCustomer.roles);
      }, error => {
        this.errorService.handleError(error);
      }
  );
}

private navigate(roles){
  if(roles.match("ADMIN")){
    this.router.navigate(['/admin']);
  } else if (roles.match("CUSTOMER")){
    this.router.navigate(['/customer']);
  }else{
    this.router.navigate(['/login']);
  }
}
}