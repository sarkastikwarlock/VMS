import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Patients } from '../interface/patients';
import { VaccinationCentres } from '../interface/vaccinationCentres';
import { PatientsService } from '../service/patients.service';
import { VaccinationCentresService } from '../service/vaccination-centres.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';
import { ClickOutsideDirectiveDirective } from '../click-outside-directive.directive';

@Component({
  selector: 'patient-page',
  templateUrl: './patient-page.component.html',
  styleUrls: ['./patient-page.component.css']
})
export class PatientPageComponent implements OnInit {

  public viewMode: string = 'Information';

  public patient!: any;
  public centres!: VaccinationCentres[];
  public data!: any;

  public reTypePassword: any= '';
  public timeDiff!: any;

  public sessionName!:any;
  public sessionId!: any;
  public sessionEmail!:any;
  public sessionPhone!: any;
  public sessionDOB!:any;

  public nameValidationFailed!: any;
  public emailValidationFailed!: any;
  public passwordValidationFailed!: any;
  public retypeValidationFailed!: any;
  public idValidationFailed!: any;
  public dobValidationFailed!: any;
  public phoneValidationFailed!: any;
  public dateValidationFailed!: any;


  public nameIsFilled!:any;
  public emailIsFilled!:any;
  public passwordIsFilled!:any;
  public retypeIsFilled!:any;
  public idIsFilled!:any;
  public dobIsFilled!:any;
  public phoneIsFilled!:any;

  public objectData!: any;

  public firstCentrePatient!: any;
  public secondCentrePatient!: any;

  public tempFirstTime!:any;
  public tempSecondTime!: any;

  public minDateFirst: any = this.addDays(new Date().toJSON().split('T')[0],1);
  public minDateSecond!: any;

  public maxDate: any = new Date().toJSON().split('T')[0];

  public firstSchedule!: any;
  public secondSchedule!: any;

  public dateDiff!: any;

  
  constructor(private patientsService: PatientsService, private centresService: VaccinationCentresService,
    private modalService: NgbModal, private route: ActivatedRoute, private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.data = this.route.snapshot.params['userId'];
    this.getCentres();
    this.getPatientsById(this.data);
  }

  public getPatientsById(patientId: string):void{
    this.patientsService.getPatientsById(patientId).subscribe(
      (response: Patients)=>{
        this.patient = response;
        this.sessionName = this.patient.patientFullname;
        this.sessionId = this.patient.patientId;
        this.sessionEmail = this.patient.patientEmail;
        this.sessionPhone = this.patient.patientPhone;
        this.sessionDOB = this.patient.patientDOB;

        this.firstCentrePatient = this.patient.patientFirstCentre;
        this.secondCentrePatient = this.patient.patientSecondCentre;

        this.tempFirstTime=this.patient.patientFirstTime;
        this.tempSecondTime=this.patient.patientSecondTime;

        this.firstSchedule = this.patient.patientFirstDate+ " @ "+this.patient.patientFirstTime;
        this.secondSchedule = this.patient.patientSecondDate+" @ "+this.patient.patientSecondTime;

      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  public updatePatient(): void{
    if(this.fullnameValidation(this.objectData.patientFullname)&&this.emailValidation(this.objectData.patientEmail)&&this.passwordValidation(this.objectData.patientPassword)&&this.isPasswordMatching(this.objectData.patientPassword, this.reTypePassword)
    &&this.idValidation(this.objectData.patientId)&&this.phoneValidation(this.objectData.patientPhone)&& (this.objectData.patientPassword!=null||this.objectData.patientPassword=='')
    &&this.checkNameIsFilled(this.objectData.patientFullname)&&this.checkEmailIsFilled(this.objectData.patientEmail)&&this.checkPasswordIsFilled(this.objectData.patientPassword)&&this.checkRetypeIsFilled(this.reTypePassword)&&this.checkIdIsFilled(this.objectData.patientId)&&this.checkDobIsFilled(this.objectData.patientDOB)&&this.checkPhoneIsFilled(this.objectData.patientPhone)){
      this.patientsService.updatePatients(this.objectData).subscribe(
        (response: Patients)=>{
          console.log(response);
          alert("Patient information have been updated.");
          this.onCloseModal();
        },
        (error: HttpErrorResponse)=>{
          console.log(error.message);
          alert("Update failed. Please refresh page.");
          this.onCloseModal();
        }
      );
    }else{
      alert("Update failed. Please refresh page.");
    }
  }

  public updatePatientSchedule(): void{
    if(this.dateValidation(this.patient.patientFirstDate,this.patient.patientSecondDate)){
    this.patientsService.updatePatients(this.patient).subscribe(
      (response: Patients)=>{
        console.log(response);
        alert("Patient schedule have been updated.");
        this.onCloseModal();
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
        alert("Update failed. Please refresh page.");
        this.onCloseModal();
      }
    );
    }else{
      alert("Update failed. Please refresh page.");
    }
}

  public getCentres(): void{
    this.centresService.getCentres().subscribe(
      (response: VaccinationCentres[])=>{
        this.centres = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  public onOpenModal(object:any, content: any):void{
    this.objectData = object;
    this.modalService.open(content);
  }

public onCloseModal():void{
  this.modalService.dismissAll();
  this.reTypePassword='';
  this.ngOnInit();
}

public addDays(date: string, days: number) {
  let result = new Date(date);
  result.setDate(result.getDate() + days);
  let ans = this.datePipe.transform(result, 'yyyy-MM-dd');
  return ans;
}

public ageValidationForInput(age?: any){
  let dob = new Date(Date.parse(age));
  let dobYear = dob.getFullYear();
  this.timeDiff = new Date().getFullYear()-dobYear;
}

public isPasswordMatching(password?: string, reTypePassword?: string): boolean{
  if(password === reTypePassword){
    this.retypeValidationFailed = false;
    return true;
  }else{
    this.retypeValidationFailed = true;
    return false;
  }
}

public fullnameValidation(fullname?: any):boolean{
  if(fullname?.match(/^[a-zA-Z ]+$/)){
    this.nameValidationFailed = false;
    return true;
  }else{
    this.nameValidationFailed = true;
    return false;
  }
}

public emailValidation(email?: any): boolean{
  if(email?.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
    this.emailValidationFailed = false;
    return true;
  }else{
    this.emailValidationFailed = true;
    return false;
  }
}

public passwordValidation(password?: any): boolean{
  if(password?.match(/^([a-zA-Z0-9!@#$%^&*])(?=.{8,})/)){
    this.passwordValidationFailed = false;
    return true;
  }else{
    this.passwordValidationFailed = true;
    return false;
  }
}

public idValidation(id?: any): boolean{
  if(id?.toUpperCase().match(/^[STFGstfg]\d{7}[a-zA-Z]$/)){
    this.idValidationFailed = false;
    return true;
  }else{
    this.idValidationFailed = true;
    return false;
  }
}

public phoneValidation(phone?: any): boolean{
  if(phone?.match(/^[896]\d{7}$/)){
    return true;
  }else{
    this.phoneValidationFailed = true;
    return false;
  }
}

public ageValidation(age?: any): boolean{
  let dob = new Date(Date.parse(age));
  let dobYear = dob.getFullYear();
  this.timeDiff = new Date().getFullYear()-dobYear;
  if(this.timeDiff>=12){
    this.dobValidationFailed = false;
    return true;
  }else{
    this.dobValidationFailed = true;
    return false;
  }
}

public checkNameIsFilled(name?:any):boolean{
  if(name!=null||name!=''){
    this.nameIsFilled=true;
    return true;
  }else{
    this.nameIsFilled=false;
    return false;
  }
}

public checkEmailIsFilled(email?:any):boolean{
  if(email!=null||email!=''){
    this.emailIsFilled=true;
    return true;
  }else{
    this.emailIsFilled=false;
    return false;
  }
}

public checkPasswordIsFilled(password?:any):boolean{
  if(password!=null||password!=''){
    this.passwordIsFilled=true;
    return true;
  }else{
    this.passwordIsFilled=false;
    return false;
  }
}

public checkRetypeIsFilled(retype?:any):boolean{
  if(retype!=null||retype!=''){
    this.retypeIsFilled=true;
    return true;
  }else{
    this.retypeIsFilled=false;
    return false;
  }
}

public checkIdIsFilled(id?:any):boolean{
  if(id!=null||id!=''){
    this.idIsFilled=true;
    return true;
  }else{
    this.idIsFilled=false;
    return false;
  }
}

public checkDobIsFilled(dob?:any):boolean{
  if(dob!=null||dob!=''){
    this.dobIsFilled=true;
    return true;
  }else{
    this.dobIsFilled=false;
    return false;
  }
}

public checkPhoneIsFilled(phone?:any):boolean{
  if(phone!=null||phone!=''){
    this.phoneIsFilled=true;
    return true;
  }else{
    this.phoneIsFilled=false;
    return false;
  }
}

public dateValidation(date1?: any, date2?: any): boolean{
  let d1 = new Date(Date.parse(date1));
  let d2 = new Date(Date.parse(date2));

  let dateBook1 = d1.getTime();
  let dateBook2 = d2.getTime();
  this.dateDiff = (dateBook2-dateBook1)/ (1000 * 3600 * 24);
  console.log(this.dateDiff);
  if(this.dateDiff>=30){
    this.dateValidationFailed = false;
    return true;
  }else{
    this.dateValidationFailed = true;
    return false;
  }
}

public outsideClicked(){
  this.onCloseModal();
  return true;
}

public locationEqual(){
  this.patient.patientSecondCentre = this.patient.patientFirstCentre;
}

}
