import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationComponent } from './authentication/authentication.component';
import { FormsModule} from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { AuthenticationService } from './authentication.service';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { FullCalendarModule } from '../../node_modules/@fullcalendar/angular'; // for FullCalendar!


@NgModule({
  declarations: [
    AppComponent,
    AuthenticationComponent,
    HomeComponent,
    CustomerHomeComponent,
    AdminHomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FullCalendarModule
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
