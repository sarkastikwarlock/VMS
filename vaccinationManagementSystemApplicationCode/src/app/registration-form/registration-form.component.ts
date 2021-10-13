import { DatePipe } from '@angular/common';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm, SelectControlValueAccessor } from '@angular/forms';
import { Router } from '@angular/router';
import { iif } from 'rxjs';
import { Doctors } from '../interface/doctors';
import { Patients } from '../interface/patients';
import { VaccinationCentres } from '../interface/vaccinationCentres';
import { DoctorsService } from '../service/doctors.service';
import { PatientsService } from '../service/patients.service';
import { VaccinationCentresService } from '../service/vaccination-centres.service';

@Component({
  selector: 'registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {
  public registerAsA: any=null;
  public selectedValue!: string;
  public registerList:  any = ["Patient", "Doctor", "Vaccination Centre"];
  

  public doctor = new Doctors();
  public centre = new VaccinationCentres();
  public patient = new Patients;

  public fullname!:any;
  public email!: any;
  public password!: any;
  public id!:string;
  public dob!: any;
  public phone!: any;
  public reTypePassword!: any;

  public nameValidationFailed!: any;
  public emailValidationFailed!: any;
  public passwordValidationFailed!: any;
  public retypeValidationFailed!: any;
  public idValidationFailed!: any;
  public dobValidationFailed!: any;
  public phoneValidationFailed!: any;
  public dateValidationFailed!: any;

  public docFieldValidationFailed!: any;
  public docAccValidationFailed!: any;

  public cAddressValidationFailed!: any;

  public nameIsFilled!:any;
  public emailIsFilled!:any;
  public passwordIsFilled!:any;
  public retypeIsFilled!:any;
  public idIsFilled!:any;
  public dobIsFilled!:any;
  public phoneIsFilled!:any;

  public docFieldIsFilled!: any;
  public docAccIsFilled!: any;

  public cNameIsFilled!:any;
  public cEmailIsFilled!:any;
  public cAddressIsFilled!:any;
  public cPhoneIsFilled!:any;
  public cOpeningIsFilled!:any;
  public cClosingIsFilled!:any;
  public cVaccIsFilled!:any;

  public isMatch:any = false;
  
  public doctorField: any = null;
  public doctorAccreditation!: any;
  public isAGp!:any;

  public centreName!: any;
  public centreEmail!: any;
  public centreAddress!: any;
  public centrePhone!: any

  public centreOpening!: any;
  public centreClosing!: any;

  public checkModerna: boolean = false;
  public checkPfizer: boolean = false;
  public checkComirnaty: boolean = false;

  public isAPatient: boolean=true;
  public isADoctor: boolean=false;
  public isACentre: boolean=false;

  public timeDiff!: any;

  public isClicked!: any;

  public medicalField: any =["Surgery", "Pediatrics", "Radiology", "Internal Medicine", "Neurology", "Cardiology", "Dermatology", "Public Health", "Preventive Healthcare", "Family Medicine","Others"];
  

  public maxDate: any = new Date().toJSON().split('T')[0];
  
  constructor(private doctorsService: DoctorsService, private patientsService: PatientsService, private centresService: VaccinationCentresService,
    private router: Router, private datePipe: DatePipe ) { }

  ngOnInit(): void {
  }

  getRegisterType(){
    this.isClicked=false;
    if(this.registerAsA=="Vaccination Centre"){this.isACentre = true;this.isAPatient = false;this.isADoctor = false;}
    if(this.registerAsA=="Doctor"){this.isADoctor = true;this.isAPatient = false;this.isACentre = false;}
    if(this.registerAsA=="Patient"){this.isAPatient = true;this.isADoctor = false;this.isACentre = false;}
  }

  public submitByType(){
    this.isClicked=true;
    if(this.isACentre){this.isCentreExist();}
    if(this.isADoctor){this.isDoctorExist();}
    if(this.isAPatient){this.isPatientExist();}
  }

  public isPatientExist():void{
    if(this.checkNameIsFilled(this.fullname)&&this.checkEmailIsFilled(this.email)&&this.checkPasswordIsFilled(this.password)&&this.checkRetypeIsFilled(this.reTypePassword)&&this.checkIdIsFilled(this.id)&&this.checkDobIsFilled(this.dob)&&this.checkPhoneIsFilled(this.phone)
    &&this.fullnameValidation(this.fullname)&&this.emailValidation(this.email)&&this.passwordValidation(this.password)&&this.isPasswordMatching(this.password, this.reTypePassword)&&this.idValidation(this.id)&&this.ageValidation(this.dob)&&this.phoneValidation(this.phone)){
      let temp = this.id;
      this.id = temp.toUpperCase();
      this.patientsService.getPatientsById(this.id).subscribe(
        (response: Patients)=>{
          alert("Patient has already exist"); 
        },
        (error: HttpErrorResponse)=>{
          this.addPatient();
        }
      );
    }else{
      alert("Please fill in the form.");
    }
  }

  public isDoctorExist():void{
    console.log(this.doctorAccreditation);
    if(this.checkNameIsFilled(this.fullname)&&this.checkEmailIsFilled(this.email)&&this.checkRetypeIsFilled(this.reTypePassword)&&this.checkPasswordIsFilled(this.password)&&this.checkIdIsFilled(this.id)&&this.checkDobIsFilled(this.dob)&&this.checkPhoneIsFilled(this.phone)&&this.checkDocAccIsFilled(this.doctorAccreditation)&&this.checkDocFieldIsFilled(this.doctorField)&&
      this.fullnameValidation(this.fullname)&&this.emailValidation(this.email)&&this.passwordValidation(this.password)&&this.isPasswordMatching(this.password, this.reTypePassword)&&this.idValidation(this.id)&&this.phoneValidation(this.phone)&& this.accreditationValidation(this.doctorAccreditation)){
    let temp = this.id;
    this.id = temp.toUpperCase();
    this.doctorsService.getDoctorsById(this.id).subscribe(
      (response: Doctors)=>{
        alert("Doctor has already exist"); 
      },
      (error: HttpErrorResponse)=>{
        this.addDoctor();
      }
    );
  }else{
    alert("Please fill in the form.");
    }
  }

  public isCentreExist():void{
    if(this.centreName!=undefined||this.centreName!=''||this.centreName!=null){
    this.centresService.getCentresByName(this.centreName).subscribe(
      (response: VaccinationCentres)=>{
        alert("Vaccination Centre has already exist");
      },
      (error: HttpErrorResponse)=>{
        this.addCentre();
      }
    );
  }else{
    alert("Please fill in the form.");
  }
  }

  public addCentre():void{

    this.centre.centreName = this.centreName;
    this.centre.centreEmail = this.centreEmail;
    this.centre.centreAddress = this.centreAddress;
    this.centre.centrePhone = this.centrePhone;
    this.centre.centreOpening = this.centreOpening;
    this.centre.centreClosing = this.centreClosing;

    if(this.checkCNameIsFilled(this.centreName)&&this.checkCEmailIsFilled(this.centreEmail)&&this.checkCAddressIsFilled(this.centreAddress)&&this.checkCPhoneIsFilled(this.centrePhone)&&this.checkCOpeningIsFilled(this.centreOpening)&&this.checkCClosingIsFilled(this.centreClosing)&&this.checkCVaccIsFilled(this.centreEmail.centreVaccine)&&
      this.emailValidation(this.centreEmail)&&this.phoneValidation(this.centrePhone)&&this.addressValidation(this.centreAddress)){

    this.centresService.addCentres(this.centre).subscribe(
      (response: VaccinationCentres)=>{
        alert("Registration successful. Return to login page");
        this.router.navigate(['']);
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
        this.isClicked=false;

        alert("Registration failed. Please reload page.");

      }
    );
    }else{
      this.isClicked=false;
    }
  }

  public addPatient():void{
    this.patient.patientFullname = this.fullname;
    this.patient.patientEmail = this.email;
    this.patient.patientPassword = this.password;
    let temp: string = this.id;
    this.patient.patientId = temp?.toUpperCase();
    this.patient.patientDOB = this.dob;
    this.patient.patientPhone = this.phone;

    
      this.patientsService.addPatients(this.patient).subscribe(
        (response: Patients)=>{
          alert("Registration successful. Return to login page");
          this.isClicked=false;
          this.router.navigate(['']);
        },
        (error: HttpErrorResponse)=>{
          console.log(error.message);
          this.isClicked=false;
          alert("Registration failed! Please reload page.");
        }
      ); 
  }

  public addDoctor():void{
    this.doctor.doctorFullname = this.fullname;
    this.doctor.doctorEmail = this.email;
    this.doctor.doctorPassword = this.password;
    let temp = this.id;
    this.doctor.doctorId = temp?.toUpperCase();
    this.doctor.doctorDOB = this.dob;
    this.doctor.doctorPhone = this.phone;
    this.doctor.doctorField = this.doctorField;
    this.doctor.doctorAccreditation = this.doctorAccreditation;
    this.doctor.isAGp = this.isAGp;

    
      this.doctorsService.addDoctors(this.doctor).subscribe(
        (response: Doctors)=>{
          alert("Registration successful. Return to login page.");
          this.isClicked=false;
          this.router.navigate(['']);
        },
        (error: HttpErrorResponse)=>{
          console.log(error.message);
          this.isClicked=false;

          alert("Registration failed. Please reload page.");
        }
      );
  }

  public populateVacc(){
    let temp:string = this.centre.centreVaccine;
    console.log("in pop, before "+temp);
    
    if(this.checkModerna){  
      if(temp===null||temp===''||temp===undefined){
        console.log("In check moderna");
        this.centre.centreVaccine = 'Moderna';
      }else{
        if(!temp?.includes('Moderna')){
          this.centre.centreVaccine = temp.concat('/Moderna');
        }
      }
    }else {
      if(temp?.includes('Moderna')){
        this.centre.centreVaccine = temp.replace('Moderna','');
      }
      if(temp?.includes('Moderna/')){
        this.centre.centreVaccine = temp.replace('Moderna/','');
      }
      if(temp?.includes('/Moderna/')){
        this.centre.centreVaccine = temp.replace('/Moderna/','');
      }
      if(temp?.includes('/Moderna')){
        this.centre.centreVaccine = temp.replace('/Moderna','');
      }
    }
    if(this.checkPfizer){
      if(temp===null||temp===''||temp===undefined){
        this.centre.centreVaccine = 'Pfizer';
      }else{
        if(!temp?.includes('Pfizer')){
          this.centre.centreVaccine = temp.concat('/Pfizer');
        }
      }
    }else {
      if(temp?.includes('Pfizer')){
        this.centre.centreVaccine = temp.replace('Pfizer','');
      }
      if(temp?.includes('Pfizer/')){
        this.centre.centreVaccine = temp.replace('Pfizer/','');
      }
      if(temp?.includes('/Pfizer/')){
        this.centre.centreVaccine = temp.replace('/Pfizer/','');
      }
      if(temp?.includes('/Pfizer')){
        this.centre.centreVaccine = temp.replace('/Pfizer','');
      }
    }
    if(this.checkComirnaty){
      if(temp===null||temp===''||temp===undefined){
        this.centre.centreVaccine = 'Comirnaty';
      }else{
        if(!temp?.includes('Moderna')){
          this.centre.centreVaccine = temp.concat('/Comirnaty');
        }
      }
    }else {
      if(temp?.includes('Comirnaty')){
        this.centre.centreVaccine = temp.replace('Comirnaty','');
      }
      if(temp?.includes('Comirnaty/')){
        this.centre.centreVaccine = temp.replace('Comirnaty/','');
      }
      if(temp?.includes('/Comirnaty/')){
        this.centre.centreVaccine = temp.replace('/Comirnaty/','');
      }
      if(temp?.includes('/Comirnaty')){
        this.centre.centreVaccine = temp.replace('/Comirnaty','');
      }
    }
    console.log("in pop, after"+this.centreName?.centreVaccine);
  }



    

  public isPasswordMatching(password?: string, reTypePassword?: string): boolean{
    if(password === reTypePassword){
      this.isMatch = true;
      return true;
    }else{
      this.isMatch = false;
      return false;
    }
  }

  public fullnameValidation(fullname?: any):boolean{
    console.log("In name validation");
    if(fullname?.match(/^[a-zA-Z ]+$/)){
      this.nameValidationFailed = false;
      return true;
    }else{
      this.nameValidationFailed=true
      return false;
    }
  }

  public emailValidation(email?: any): boolean{
    console.log("In email validation");
    if(email?.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
      this.emailValidationFailed=false;
      return true;
    }else{
      this.emailValidationFailed=true;
      return false;
    }
  }

  public passwordValidation(password?: any): boolean{
    if(password?.match(/^([a-zA-Z0-9!@#$%^&*])(?=.{8,})/)){
      this.passwordValidationFailed=false;
      return true;
    }else{
      this.passwordValidationFailed=true;
      return false;
    }
  }

  public idValidation(id?: any): boolean{
    if(id?.toUpperCase().match(/^[STFGstfg]\d{7}[a-zA-Z]$/)){
      this.idValidationFailed=false;
      return true;
    }else{
      this.idValidationFailed=true;
      return false;
    }
  }

  public phoneValidation(phone?: any): boolean{
    if(phone?.match(/^[896]\d{7}$/)){
      this.phoneValidationFailed=false;
      return true;
    }else{
      this.phoneValidationFailed=true;
      return false;
    }
  }

  public ageValidation(age?: any): boolean{
    let dob = new Date(Date.parse(age));
    let dobYear = dob.getFullYear();
    this.timeDiff = new Date().getFullYear()-dobYear;
    if(this.timeDiff>=12){
      return true;
    }else{
      return false;
    }
  }

  public ageValidationForInput(age?: any){
    let dob = new Date(Date.parse(age));
    let dobYear = dob.getFullYear();
    this.timeDiff = new Date().getFullYear()-dobYear;
  }

  public accreditationValidation(accreditation?: any):boolean{
    if(accreditation?.match(/^[a-zA-Z0-9]{2}-[a-zA-Z0-9]{7}$/)){
      this.docAccValidationFailed=false;
      return true;
    }else{
      this.docAccValidationFailed=true;
      return false;
    }
  }

  public addressValidation(address?: any):boolean{
    if(address?.match(/^([a-zA-Z0-9!@#$%^&\*])(?=.{5,})/)){
      this.cAddressValidationFailed=false;
      return true;
    }else{
      this.cAddressValidationFailed=true;
      return false;
    }
  }
  
  public checkNameIsFilled(name?:any):boolean{
    console.log("In name is filled validation");
    if(name!=null||name!=''||name!=undefined){
      this.nameIsFilled=true;
      return true;
    }else{
      this.nameIsFilled=false;
      return false;
    }
  }
  
  public checkEmailIsFilled(email?:any):boolean{
    if(email!=null||email!=''||email!=undefined){
      this.emailIsFilled=true;
      return true;
    }else{
      this.emailIsFilled=false;
      return false;
    }
  }
  
  public checkPasswordIsFilled(password?:any):boolean{
    if(password!=null||password!=''||password!=undefined){
      this.passwordIsFilled=true;
      return true;
    }else{
      this.passwordIsFilled=false;
      return false;
    }
  }
  
  public checkRetypeIsFilled(retype?:any):boolean{
    if(retype!=null||retype!=''||retype!=undefined){
      this.retypeIsFilled=true;
      return true;
    }else{
      this.retypeIsFilled=false;
      return false;
    }
  }
  
  public checkIdIsFilled(id?:any):boolean{
    if(id!=null||id!=''||id!=undefined){
      this.idIsFilled=true;
      return true;
    }else{
      this.idIsFilled=false;
      return false;
    }
  }
  
  public checkDobIsFilled(dob?:any):boolean{
    if(dob!=null||dob!=''||dob!=undefined){
      this.dobIsFilled=true;
      return true;
    }else{
      this.dobIsFilled=false;
      return false;
    }
  }
  
  public checkPhoneIsFilled(phone?:any):boolean{
    if(phone!=null||phone!=''||phone!=undefined){
      this.phoneIsFilled=true;
      return true;
    }else{
      this.phoneIsFilled=false;
      return false;
    }
  }

  public checkDocFieldIsFilled(docField?:any): boolean{
    if(docField!=null||docField!=''||docField!=undefined){
      this.docFieldIsFilled=true;
      return true;
    }else{
      this.docFieldIsFilled=false;
      return false;
    }
  }

  public checkDocAccIsFilled(docAcc?:any): boolean{
    if(docAcc!=null||docAcc!=''||docAcc!=undefined){
      this.docAccIsFilled=true;
      return true;
    }else{
      this.docAccIsFilled=false;
      return false;
    }
  }
  public checkCNameIsFilled(cName?:any): boolean{
    if(cName!=null||cName!=''||cName!=undefined){
      this.cNameIsFilled=true;
      return true;
    }else{
      this.cNameIsFilled=false;
      return false;
    }
  }
  public checkCEmailIsFilled(cEmail?:any): boolean{
    if(cEmail!=null||cEmail!=''||cEmail!=undefined){
      this.cEmailIsFilled=true;
      return true;
    }else{
      this.cEmailIsFilled=false;
      return false;
    }
  }
  public checkCAddressIsFilled(cAddress?:any): boolean{
    if(cAddress!=null||cAddress!=''||cAddress!=undefined){
      this.cAddressIsFilled=true;
      return true;
    }else{
      this.cAddressIsFilled=false;
      return false;
    }
  }
  public checkCPhoneIsFilled(cPhone?:any): boolean{
    if(cPhone!=null||cPhone!=''||cPhone!=undefined){
      this.cPhoneIsFilled=true;
      return true;
    }else{
      this.cPhoneIsFilled=false;
      return false;
    }
  }
  public checkCOpeningIsFilled(cOpening?:any): boolean{
    if(cOpening!=null||cOpening!=''||cOpening!=undefined){
      this.cOpeningIsFilled=true;
      return true;
    }else{
      this.cOpeningIsFilled=false;
      return false;
    }
  }
  public checkCClosingIsFilled(cClosing?:any): boolean{
    if(cClosing!=null||cClosing!=''||cClosing!=undefined){
      this.cClosingIsFilled=true;
      return true;
    }else{
      this.cClosingIsFilled=false;
      return false;
    }
  }
  public checkCVaccIsFilled(cVacc?:any): boolean{
    if(cVacc!=null||cVacc!=''||cVacc!=undefined){
      this.cVaccIsFilled=true;
      return true;
    }else{
      this.cVaccIsFilled=false;
      return false;
    }
  }


  public cancelRegistration(){
    this.router.navigate(['']);
  }
}
