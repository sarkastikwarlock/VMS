import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { DoctorsComponent } from './doctors/doctors.component';
import { DoctorsService } from './service/doctors.service';
import { FeedbacksService } from './service/feedbacks.service';
import { VaccinationCentresService } from './service/vaccination-centres.service';
import { PatientsService } from './service/patients.service';
import { LoginPageComponent } from './login-page/login-page.component';
import { FeedbacksComponent } from './feedbacks/feedbacks.component';
import { PatientsComponent } from './patients/patients.component';
import { VaccinationCentresComponent } from './vaccination-centres/vaccination-centres.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BookVaccineSlotComponent } from './book-vaccine-slot/book-vaccine-slot.component';
import { DatePipe } from '@angular/common';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DoctorPageComponent } from './doctor-page/doctor-page.component';
import { PatientPageComponent } from './patient-page/patient-page.component';
import { FeedbackPageComponent } from './feedback-page/feedback-page.component';
import { AppRoutingModule } from './app-routing.module';
import { AdminsService } from './service/admins.service';
import { SortPipe } from './sort.pipe';
import { LogoutComponent } from './logout/logout.component';
import { ClickOutsideDirectiveDirective } from './click-outside-directive.directive';

@NgModule({
  declarations: [
    AppComponent,
    DoctorsComponent,
    LoginPageComponent,
    FeedbacksComponent,
    PatientsComponent,
    VaccinationCentresComponent,
    RegistrationFormComponent,
    BookVaccineSlotComponent,
    AdminPageComponent,
    DoctorPageComponent,
    PatientPageComponent,
    FeedbackPageComponent,
    SortPipe,
    LogoutComponent,
    ClickOutsideDirectiveDirective,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule
    ],
  providers: [
    DoctorsService,
    FeedbacksService,
    VaccinationCentresService,
    PatientsService,
    AdminsService,
    DatePipe,
    NgbModule
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
