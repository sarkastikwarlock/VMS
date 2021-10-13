import { Component, OnInit } from '@angular/core';
import { DoctorsService } from '../service/doctors.service';
import { Doctors } from '../interface/doctors';
import { HttpErrorResponse } from '@angular/common/http';
import { AdminsService } from '../service/admins.service';
import { PatientsService } from '../service/patients.service';
import { Patients } from '../interface/patients';
import { Admins } from '../interface/admins';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  public doctor = new Doctors();
  public patient = new Patients();
  public admin = new Admins();
  public errorMsg: any;
  public userId!: any;
  public userPassword!: any;
  public a!: Admins;

  public isAPatient!: any;
  public isADoctor!: any;
  public isAAdmin!: any;

  public objectData!: any;
  public emailFilled!: any;
  public emailToSend!: any;

  constructor(private doctorsService: DoctorsService, private adminsService: AdminsService, private patientsService: PatientsService,
    private modalService: NgbModal, private router: Router) { }

  ngOnInit(): void {
  }

  public getAdmins():void{
    this.admin.adminId = this.userId;
    this.admin.adminPassword = this.userPassword;

    this.adminsService.getAdminsByIdAndPassword(this.admin).subscribe(
      (response: Admins)=>{
        console.log("Data received");
        this.router.navigate(['adminsPage', this.admin.adminId], { skipLocationChange: true });
      },
      (error: HttpErrorResponse)=>{
        this.getDoctors();
      }
    );
  }

  public getDoctors():void{
    this.doctor.doctorId = this.userId;
    this.doctor.doctorPassword = this.userPassword;

    this.doctorsService.getDoctorsByIdAndPassword(this.doctor).subscribe(
      (response: Doctors)=>{
        console.log("Data received");
        this.router.navigate(['doctorsPage', this.doctor.doctorId], { skipLocationChange: true });
      },
      (error: HttpErrorResponse)=>{
        this.getPatients();
      }
    );
  }

  public getPatients():void{
    let temp = this.userId;
    this.patient.patientId = temp.toUpperCase();
    this.patient.patientPassword = this.userPassword;
    this.patientsService.getPatientsByIdAndPassword(this.patient).subscribe(
      (response: Patients)=>{
        this.router.navigate(['patientsPage', this.patient.patientId], { skipLocationChange: true });
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
        alert("User not found. You may have entered an incorrect NRIC/Password. Please try again.");
      }
    );
  }

  public ifEmailExist(object: any, content: any): void{
    if(object!=null){
      this.patientsService.getPatientsByEmail(this.emailToSend).subscribe(
        (response: Patients)=>{
          this.sendEmail(null, content);
        },
        (error:HttpErrorResponse)=>{
          this.doctorsService.getDoctorsByEmail(this.emailToSend).subscribe(
            (response: Doctors)=>{
              this.sendEmail(null, content);
            },
            (error: HttpErrorResponse)=>{
              this.alertModal();
            }
          );
        }
      );
    }else{
      this.alertModal();
    }
    
  }

  public loginUser():void{
    this.getAdmins();
  }

  public register():void{
    this.router.navigate(['register']);
  }

  public onOpenModal(object:any, content: any):void{
    this.objectData = object;
    this.modalService.open(content);
  }

  public onCloseModal():void{
    this.modalService.dismissAll();
  }

  public sendEmail(object: any, content: any){
    this.onOpenModal(null, content);
  }

  public alertModal(){
    alert("Email does not exist. Please re-enter the email that you have registered with.");
  }
}
