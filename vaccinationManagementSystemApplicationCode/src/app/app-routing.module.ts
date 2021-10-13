import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { DoctorPageComponent } from './doctor-page/doctor-page.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { PatientPageComponent } from './patient-page/patient-page.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
  {path:'', component: LoginPageComponent},
  {path:'doctorsPage/:userId', component: DoctorPageComponent},
  {path:'adminsPage/:userId', component: AdminPageComponent},
  {path:'patientsPage/:userId', component: PatientPageComponent},
  {path:'register', component: RegistrationFormComponent},
];


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]

})
export class AppRoutingModule { }
