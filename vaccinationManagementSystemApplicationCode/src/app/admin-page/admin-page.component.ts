import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Doctors } from '../interface/doctors';
import { Feedbacks } from '../interface/feedbacks';
import { Patients } from '../interface/patients';
import { VaccinationCentres } from '../interface/vaccinationCentres';
import { DoctorsService } from '../service/doctors.service';
import { FeedbacksService } from '../service/feedbacks.service';
import { PatientsService } from '../service/patients.service';
import { VaccinationCentresService } from '../service/vaccination-centres.service';

@Component({
  selector: 'admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {
  public viewMode: string = 'Patients';
  public doctors!: Doctors[]; 
  public patients!: Patients[];
  public centres!: VaccinationCentres[];
  public feedbacks!: Feedbacks[];

  public patientData = new Patients();
  public doctorData = new Doctors();
  public centreData = new VaccinationCentres();
  public feedbackData = new Feedbacks();

  public checkModerna: boolean = false;
  public checkPfizer: boolean = false;
  public checkComirnaty: boolean = false;

  public reTypeDoctorPassword:any='';
  public reTypePatientPassword:any='';

  public reTypeDoctorAddPassword:any='';
  public reTypePatientAddPassword:any='';

  public maxDate: any = new Date().toJSON().split('T')[0];

  public firstTimeStart!:any;
  public firstTimeEnd!:any;
  public secondTimeStart!:any;
  public secondTimeEnd!:any;
  public thirdTimeStart!:any;
  public thirdTimeEnd!:any;

  public nameValidationFailed!: any;
  public emailValidationFailed!: any;
  public passwordValidationFailed!: any;
  public retypeValidationFailed!: any;
  public idValidationFailed!: any;
  public dobValidationFailed!: any;
  public phoneValidationFailed!: any;
  public dateValidationFailed: any=false;

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

  public timeDiff!: any;
  public dateDiff!: any;

  public isClicked!: any;

  public field!: any;

  public firstCentre!: any;
  public dateToday = new Date();

  public medicalField: any =['Surgery', 'Pediatrics', 'Radiology', 'Internal Medicine', 'Neurology', 'Cardiology', 'Dermatology', 'Public Health', 'Preventive Healthcare', 'Family Medicine','Others'];

  @Input('objectData') 
  public objectData!: any;

  public data!: any;
  public id!: any;

  public minDateFirst: any = this.addDays(new Date().toJSON().split('T')[0],1);
  public minDateSecond!: any;


  constructor(private doctorsService: DoctorsService, private patientsService: PatientsService,
    private centresService: VaccinationCentresService, private feedbacksService: FeedbacksService, 
    private modalService: NgbModal, private route: ActivatedRoute, private router: Router, private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.data = this.route.snapshot.params['userId'];
    this.getDoctors();
    this.getPatients();
    this.getCentres();
    this.getFeedbacks();
  }

  public getDoctors(): void{
    this.doctorsService.getDoctors().subscribe(
      (response: Doctors[])=>{
        this.doctors = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  public getPatients(): void{
    this.patientsService.getPatients().subscribe(
      (response: Patients[])=>{
        this.patients = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
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

  public getFeedbacks(): void{
    this.feedbacksService.getFeedbacks().subscribe(
      (response: Feedbacks[])=>{
        this.feedbacks = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  public onOpenModal(object:any, content: any):void{
      this.objectData = object;
    
      let temp: any = undefined;
      this.patientData.patientFullname=temp;
      this.patientData.patientEmail=temp;
      this.patientData.patientPassword = temp;
      this.reTypePatientAddPassword = temp;
      this.patientData.patientId = temp;
      this.patientData.patientDOB = temp;
      this.patientData.patientPhone = temp;
      
      if(this.objectData?.centreVaccine != null){
        let temp: string = this.objectData.centreVaccine;
        if(temp.includes('Comirnaty')){
          this.checkComirnaty=true;
        }
        if(temp.includes('Moderna')){
          this.checkModerna=true;
        }
        if(temp.includes('Pfizer')){
          this.checkPfizer=true;
        }
      }

      this.modalService.open(content);
  }

  public onCloseModal():void{  
    this.modalService.dismissAll();
    this.reTypePatientAddPassword='';
    this.reTypePatientPassword='';
    this.ngOnInit();

  }

  public onDelete():void{
    if(this.objectData.patientId){
      this.deletePatient();
    }
    if(this.objectData.doctorId){
      this.deleteDoctor();
    }
    if(this.objectData.centreId){
      this.deleteCentre();
    }
    if(this.objectData.feedbackId){
      this.deleteFeedback();
    }
  }

  public deletePatient():void{

    this.patientsService.deletePatients(this.objectData.patientId).subscribe(
      (response: void)=>{
        alert("Patient has been deleted.");
        this.onCloseModal();
      },
      (error:HttpErrorResponse)=>{
        console.log(error.message);
        alert("Patient cant be deleted. Please refresh page.");
        this.onCloseModal();
      }
    );
  }

  public deleteDoctor():void{
    this.doctorsService.deleteDoctors(this.objectData.doctorId).subscribe(
      (response: void)=>{
        alert("Doctor has been deleted");
        this.onCloseModal();
        this.ngOnInit();
      },
      (error:HttpErrorResponse)=>{
        console.log(error.message);
        alert("Doctor cant be deleted. Please refresh page.");
        this.onCloseModal();
        this.ngOnInit();
      }
    );
  }

  public deleteCentre():void{
    this.centresService.deleteCentres(this.objectData.centreId).subscribe(
      (response: void)=>{
        alert("Vaccination centre has been deleted");
        this.onCloseModal();
        this.ngOnInit();
      },
      (error:HttpErrorResponse)=>{
        console.log(error.message);
        alert("Vaccination centre cant be deleted. Please refresh page.");
        this.onCloseModal();
        this.ngOnInit();
      }
    );
  }

  public deleteFeedback():void{
    this.feedbacksService.deleteFeedback(this.objectData.feedbackId).subscribe(
      (response: void)=>{
        alert("Feedback has been deleted");
        this.onCloseModal();
        this.ngOnInit();
      },
      (error:HttpErrorResponse)=>{
        console.log(error.message);
        alert("Feedback cant be deleted. Please refresh page.");
        this.onCloseModal();
        this.ngOnInit();
      }
    );
  }

  public isPatientExist():void{
    this.isClicked=true;
    console.log(this.patientData);
    if(this.checkNameIsFilled(this.patientData.patientFullname)&&this.checkEmailIsFilled(this.patientData.patientEmail)&&this.checkPasswordIsFilled(this.patientData.patientPassword)&&this.checkRetypeIsFilled(this.reTypePatientAddPassword)&&this.checkIdIsFilled(this.patientData.patientId)&&this.checkDobIsFilled(this.patientData.patientDOB)&&this.checkPhoneIsFilled(this.patientData.patientPhone)
    &&this.fullnameValidation(this.patientData.patientFullname)&&this.emailValidation(this.patientData.patientEmail)&&this.passwordValidation(this.patientData.patientPassword)&&this.isPasswordMatching(this.patientData.patientPassword, this.reTypePatientAddPassword)&&this.idValidation(this.patientData.patientId)&&this.ageValidation(this.patientData.patientDOB)&&this.phoneValidation(this.patientData.patientPhone)){
      let temp = this.patientData.patientId;
      this.patientData.patientId = temp.toUpperCase();
      if(this.isQualifiedForVaccine()){
        this.patientsService.getPatientsById(this.patientData.patientId).subscribe(
          (response: Patients)=>{
            
            alert("Patient has already exist. Please enter a patient with a different Nric."); 
            this.isClicked=false;
          },
          (error: HttpErrorResponse)=>{
            
            this.addPatient();
            this.isClicked=false;
          }
        );
      }
    }else{
      this.isClicked=false;
      alert("Please check the registration form.");
    }   
  }

  public isDoctorExist():void{
    this.doctorsService.getDoctorsById(this.doctorData.doctorId).subscribe(
      (response: Doctors)=>{
        alert("Doctor has already exist.\nPlease enter a doctor with a different Nric."); 
      },
      (error: HttpErrorResponse)=>{
        this.addDoctor();
      }
    );
  }

  public isCentreExist():void{
    this.centresService.getCentresByName(this.centreData.centreName).subscribe(
      (response: VaccinationCentres)=>{
        alert("Vaccination Centre has already exist.\nPlease enter a Vaccination Centre with a different name");
      },
      (error: HttpErrorResponse)=>{
        this.addCentre();
      }
    );
  }

  public addPatient(){
    this.patientsService.addPatients(this.patientData).subscribe(
      (response: Patients)=>{
        alert("Patient has been added.");

        this.onCloseModal();

        
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
        
        alert("Registration failed.Please refresh page.");

    
        this.onCloseModal();
        
      }
    );
  }

  public addDoctor(){

    
    if(this.doctorData.doctorFullname==null||this.doctorData.doctorEmail==null||this.doctorData.doctorPassword==null
      ||this.reTypeDoctorAddPassword==null||this.doctorData.doctorId==null||this.doctorData.doctorDOB==null
      ||this.doctorData.doctorPhone==null||this.doctorData.doctorField==null||this.doctorData.doctorAccreditation==null){
        alert("Registration failed.Please refresh page.");
        this.ngOnInit();  
      }else{
        let temp = this.doctorData.doctorId;
    this.doctorData.doctorId = temp.toUpperCase();
        if(this.fullnameValidation(this.doctorData.doctorFullname)&&this.emailValidation(this.doctorData.doctorEmail)&&this.passwordValidation(this.doctorData.doctorPassword)&&
    this.isPasswordMatching(this.doctorData.doctorPassword, this.reTypeDoctorAddPassword)&&this.idValidation(this.doctorData.doctorId)&&this.phoneValidation(this.doctorData.doctorPhone)&&this.accreditationValidation(this.doctorData.doctorAccreditation)){
          this.doctorsService.addDoctors(this.doctorData).subscribe(
            (response: Doctors)=>{
              alert("Doctor has been added.");
              this.onCloseModal();
              this.ngOnInit();
            },
            (error: HttpErrorResponse)=>{
              console.log(error.message);
              alert("Registration failed.Please refresh page.");
              this.onCloseModal();
              this.ngOnInit();
            }
          );
        }else{
          alert("Registration failed.Please refresh page.");
        }
      }   
  }

  public addCentre(){
    if(this.checkModerna){
      if(this.centreData.centreVaccine==null){
        this.centreData.centreVaccine = 'Moderna';
      }else{
        let temp = this.centreData.centreVaccine;
        this.centreData.centreVaccine = temp.concat('/Moderna');
      }
    }
    if(this.checkPfizer){
      if(this.centreData.centreVaccine==null){
        this.centreData.centreVaccine = 'Pfizer';
      }else{
        let temp = this.centreData.centreVaccine;
        this.centreData.centreVaccine = temp.concat('/Pfizer');
      }
    }
    if(this.checkComirnaty){
      if(this.centreData.centreVaccine==null){
        this.centreData.centreVaccine = 'Comirnaty';
      }else{
        let temp = this.centreData.centreVaccine;
        this.centreData.centreVaccine = temp.concat('/Comirnaty');
      }
    }

    if(this.centreData.centreName==null||this.centreData.centreAddress==null||this.centreData.centreEmail==null
    ||this.centreData.centrePhone==null||this.centreData.centreOpening==null||this.centreData.centreClosing==null
    ||this.centreData.centreVaccine==null){
      alert("Registration failed. Please refresh page.");
      this.onCloseModal();
      this.ngOnInit();  
    }else{
      if(this.emailValidation(this.centreData.centreEmail)&&this.phoneValidation(this.centreData.centrePhone)&&this.addressValidation(this.centreData.centreAddress)){
      this.centresService.addCentres(this.centreData).subscribe(
        (response: VaccinationCentres)=>{
          alert("Vaccination centre has been added.");
          this.onCloseModal();
          this.ngOnInit();
        },
        (error: HttpErrorResponse)=>{
          console.log(error.message);
          alert("Registration failed. Please refresh page.");
          this.onCloseModal();
          this.ngOnInit();
        }
      );
      }else{
        alert("Registration failed. Please refresh page.")
      }
    }
  }

  public addFeedback(){
    let time = new Date();
     
    this.feedbackData.feedbackTime = this.datePipe.transform(time, "dd/MM/yyyy")!.toString();

    if(this.feedbackData.feedbackType!=null||this.feedbackData.feedbackRatings!=null){
      this.feedbacksService.addFeedbacks(this.feedbackData).subscribe(
        (response: Feedbacks)=>{
          alert("Feedback has been added.");
          this.onCloseModal();
          this.ngOnInit();
        },
        (error: HttpErrorResponse)=>{
          console.log(error.message);
          alert("Feedback cant be added.Please refresh page");
          this.onCloseModal();
          this.ngOnInit();
        }
      );
    }else{
        alert("Feedback cant be added.Please refresh page");
        this.onCloseModal();
        this.ngOnInit();
    }
  }

  public updatePatient(){
    this.isClicked=true;
    console.log(this.objectData.patientFirstCentre);
    if(this.checkNameIsFilled(this.objectData.patientFullname)&&this.checkEmailIsFilled(this.objectData.patientEmail)&&this.checkPasswordIsFilled(this.objectData.patientPassword)&&this.checkRetypeIsFilled(this.reTypePatientPassword)&&this.checkIdIsFilled(this.objectData.patientId)&&this.checkDobIsFilled(this.objectData.patientDOB)&&this.checkPhoneIsFilled(this.objectData.patientPhone)
    &&this.fullnameValidation(this.objectData.patientFullname)&&this.emailValidation(this.objectData.patientEmail)&&this.passwordValidation(this.objectData.patientPassword)&&this.isPasswordMatching(this.objectData.patientPassword, this.reTypePatientPassword)&&this.idValidation(this.objectData.patientId)&&this.ageValidation(this.objectData.patientDOB)&&this.phoneValidation(this.objectData.patientPhone)){
      if(this.objectData.patientFirstCentre!=null&&this.objectData.patientSecondCentre!=null){
        if(this.dateValidation(this.objectData.patientFirstDate, this.objectData.patientSecondDate)){
          console.log(1);
          this.patientsService.updatePatients(this.objectData).subscribe(
            (response: Patients)=>{
              this.onCloseModal();
              alert("Patient has been updated.");
              this.isClicked=false;
            },
            (error: HttpErrorResponse)=>{
              console.log(error.message);    
              this.onCloseModal(); 
              alert("Update failed.Please refresh page.");
              this.isClicked=false;
            }
          );
        }else{ 
          console.log(2);
          alert("Update failed.Please refresh page.");
          this.isClicked=false;
        }
      }else{
        console.log(3);
        this.patientsService.updatePatients(this.objectData).subscribe(
          (response: Patients)=>{
            this.onCloseModal();
            alert("Patient has been updated.");
            this.isClicked=false;
          },
          (error: HttpErrorResponse)=>{
            console.log(error.message);
            this.onCloseModal(); 
            alert("Update failed.Please refresh page.");
            this.isClicked=false;
          }
        );
      }
    }else{ 
      console.log(4);
      alert("Update failed.Please refresh page.");
      this.isClicked=false;
    }
  }

  public updateDoctor(){
    if(this.objectData.doctorFullname==null||this.objectData.doctorEmail==null||this.objectData.doctorPassword==null
      ||this.reTypeDoctorPassword==null||this.objectData.doctorId==null||this.objectData.doctorDOB==null
      ||this.objectData.doctorPhone==null||this.objectData.doctorField==null||this.objectData.doctorAccreditation==null){
        alert("Update failed.Please refresh page.");
        this.ngOnInit();  
      }else{
        if(this.fullnameValidation(this.objectData.doctorFullname)&&this.emailValidation(this.objectData.doctorEmail)&&this.passwordValidation(this.objectData.doctorPassword)&&
    this.isPasswordMatching(this.objectData.doctorPassword, this.reTypeDoctorPassword)&&this.idValidation(this.objectData.doctorId)&&this.phoneValidation(this.objectData.doctorPhone)&&this.accreditationValidation(this.objectData.doctorAccreditation)){
          this.doctorsService.updateDoctors(this.objectData).subscribe(
            (response: Doctors)=>{
              alert("Doctor has been updated.");
              this.onCloseModal();
              this.ngOnInit();
            },
            (error: HttpErrorResponse)=>{
              console.log(error.message);
              alert("Update failed.Please refresh page.");
              this.onCloseModal();
              this.ngOnInit();
            }
          );
        }else{
          alert("Update failed.Please refresh page.");
        }
      } 
  }

  public updateFeedback(){
    this.feedbacksService.updateFeedback(this.objectData).subscribe(
      (response: Feedbacks)=>{
        alert("Feedback has been updated");
        this.onCloseModal();
        this.ngOnInit();
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
        this.onCloseModal();
        this.ngOnInit();
      }
    );
  }

  public updateCentre(){
    if(this.emailValidation(this.objectData.centreEmail)&&this.phoneValidation(this.objectData.centrePhone)&&this.addressValidation(this.objectData.centreAddress)){
      this.vaccineCheckBox(this.objectData);
      this.centresService.updateCentres(this.objectData).subscribe(
      (response: VaccinationCentres)=>{
        alert("Vaccination centre has been updated");
        this.onCloseModal();
        this.ngOnInit();
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
        alert("Update failed. Please refresh page.");
        this.onCloseModal();
        this.ngOnInit();
      }
    );
    }else{
      alert("Update failed. Please refresh page.");
    }
  }

  public toggleQualified(doctor: Doctors){
    doctor.isQualified=!doctor.isQualified;
    this.doctorsService.updateDoctors(doctor).subscribe(
      (response: Doctors)=>{
        console.log("Qualified toggle activated");
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
      }
    );
  }

  public toggleFirstDose(patient: Patients){
    patient.firstDoseIsDone=!patient.firstDoseIsDone;
    this.patientsService.updatePatients(patient).subscribe(
      (response: Patients)=>{
        console.log("Qualified toggle activated");
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
      }
    );
  }

  public toggleSecondDose(patient: Patients){
    patient.secondDoseIsDone=!patient.secondDoseIsDone;
    this.patientsService.updatePatients(patient).subscribe(
      (response: Patients)=>{
        console.log("Qualified toggle activated");
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
      }
    );
  }

  public isQualifiedForVaccine(): boolean{
    let dob = new Date(Date.parse(this.patientData.patientDOB));
    let dobYear = dob.getFullYear();
    let timeDiff: any = new Date().getFullYear()-dobYear;

    return timeDiff<12?false:true;
  }

  public toggleCentreQualified(centre: VaccinationCentres){
    centre.isQualified=!centre.isQualified;
    this.centresService.updateCentres(centre).subscribe(
      (response: VaccinationCentres)=>{
        console.log("Qualified Centre toggle activated");
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
      }
    );
  }

  public ratingsSelect(event: any): void{
    this.feedbackData.feedbackRatings = event.target.value;
  }

  public typeSelect(event: any): void{
    this.feedbackData.feedbackType = event.target.value;
  }

  public addDays(date: string, days: number) {
    let result = new Date(date);
    result.setDate(result.getDate() + days);
    let ans = this.datePipe.transform(result, 'yyyy-MM-dd');
    return ans;
  }

  public vaccineCheckBox(data?: any){
    let temp:string = data.centreVaccine;
    if(this.checkModerna){
      if(temp=='undefined'||temp==''){
        data.centreVaccine = 'Moderna';
      }else{
        if(!temp.includes('Moderna')){
        data.centreVaccine = temp.concat('/Moderna');
        }
      }
    }else {
      if(temp.includes('Moderna')){
        data.centreVaccine = temp.replace('Moderna','');
      }
      if(temp.includes('Moderna/')){
        data.centreVaccine = temp.replace('Moderna/','');
      }
      if(temp.includes('/Moderna/')){
        data.centreVaccine = temp.replace('/Moderna/','');
      }
      if(temp.includes('/Moderna')){
        data.centreVaccine = temp.replace('/Moderna','');
      }
    }
    if(this.checkPfizer){
      if(temp=='undefined'||temp==''){
        data.centreVaccine = 'Pfizer';
      }else{
        if(!temp.includes('Pfizer')){
          data.centreVaccine = temp.concat('/Pfizer');
        }
      }
    }else {
      if(temp.includes('Pfizer')){
        data.centreVaccine = temp.replace('Pfizer','');
      }
      if(temp.includes('Pfizer/')){
        data.centreVaccine = temp.replace('Pfizer/','');
      }
      if(temp.includes('/Pfizer/')){
        data.centreVaccine = temp.replace('/Pfizer/','');
      }
      if(temp.includes('/Pfizer')){
        data.centreVaccine = temp.replace('/Pfizer','');
      }
    }
    if(this.checkComirnaty){
      if(temp=='undefined'||temp==''){
        data.centreVaccine = 'Comirnaty';
      }else{
        if(!temp.includes('Moderna')){
          data.centreVaccine = temp.concat('/Comirnaty');
        }
      }
    }else {
      if(temp.includes('Comirnaty')){
        data.centreVaccine = temp.replace('Comirnaty','');
      }
      if(temp.includes('Comirnaty/')){
        data.centreVaccine = temp.replace('Comirnaty/','');
      }
      if(temp.includes('/Comirnaty/')){
        data.centreVaccine = temp.replace('/Comirnaty/','');
      }
      if(temp.includes('/Comirnaty')){
        data.centreVaccine = temp.replace('/Comirnaty','');
      }
    }

  }

  onChange():void{
    this.minDateSecond = this.addDays(this.objectData.patientFirstDate,30);
  }

  public isPasswordMatching(password?: string, reTypePassword?: string): boolean{
    if(password === reTypePassword){
      return true;
    }else{
      return false;
    }
  }

  public fullnameValidation(fullname?: any):boolean{
    if(fullname?.match(/^[a-zA-Z \']+$/)){
      this.nameValidationFailed = false;
      return true;
    }else{
      this.nameValidationFailed=true
      return false;
    }
  }

  public emailValidation(email?: any): boolean{
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
  
  public locationEqual(secondCentre: any, firstCentre: any){
    if(secondCentre = firstCentre){
      return true;
    }else{
      return false;
    }
  }

  public dateValidation(date1?: any, date2?: any): boolean{
    let d1 = new Date(Date.parse(date1));
    let d2 = new Date(Date.parse(date2));
  
    let dateBook1 = d1.getTime();
    let dateBook2 = d2.getTime();
    this.dateDiff = (dateBook2-dateBook1)/ (1000 * 3600 * 24);
    if(this.dateDiff>=30){
      this.dateValidationFailed = false;
      return true;
    }else{
      this.dateValidationFailed = true;
      return false;
    }
  }
}
