import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Doctors } from '../interface/doctors';
import { Patients } from '../interface/patients';
import { VaccinationCentres } from '../interface/vaccinationCentres';
import { DoctorsService } from '../service/doctors.service';
import { PatientsService } from '../service/patients.service';
import { VaccinationCentresService } from '../service/vaccination-centres.service';

@Component({
  selector: 'doctor-page',
  templateUrl: './doctor-page.component.html',
  styleUrls: ['./doctor-page.component.css']
})
export class DoctorPageComponent implements OnInit {
  public viewMode: string = 'Doctor';
  public innerDoctorViewMode: string = 'Information';
  public innerPatientViewMode: string = 'Information';
  public doctor!: Doctors;
  public patient!: Patients;
  public centres!: VaccinationCentres[];

  public objectData!:any;

  public reTypeDoctorPassword!:any;
  public reTypePatientPassword!:any;

  public firstSchedule!:any;
  public secondSchedule!:any;

  public data!: any;
  public medicalField: any =['Surgery', 'Pediatrics', 'Radiology', 'Internal Medicine', 'Neurology', 'Cardiology', 'Dermatology', 'Public Health', 'Preventive Healthcare', 'Family Medicine','Others'];


  public firstCentreDoctor!: any;
  public secondCentreDoctor!: any;
  public thirdCentreDoctor!: any;

  public firstCentrePatient!: any;
  public secondCentrePatient!: any;
  public isAlsoPatient: boolean = false;

  public firstTimeStart!: any;
  public firstTimeEnd!: any;

  public firstDate!: any;
  public secondDate!: any;
  public thirdDate!: any;

  public secondTimeStart!: any;
  public secondTimeEnd!: any;

  public thirdTimeStart!: any;
  public thirdTimeEnd!: any;

  constructor(private doctorsService: DoctorsService, private patientsService: PatientsService, 
    private centresService: VaccinationCentresService, private modalService: NgbModal,
    private route: ActivatedRoute, private router: Router, private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.data = this.route.snapshot.params['userId'];
    this.getDoctorsById(this.data);
    this.getPatientsById(this.data);
    this.getCentres();
  }

  public getPatientsById(patientId: string):void{
    this.patientsService.getPatientsById(patientId).subscribe(
      (response: Patients)=>{
        this.patient = response;
        this.isAlsoPatient = true;
        this.firstCentrePatient = this.patient.patientFirstCentre;
        this.secondCentrePatient = this.patient.patientSecondCentre;

        this.firstSchedule = this.patient.patientFirstDate+ " @ "+this.datePipe.transform(this.patient.patientFirstTime, 'HH:mm');
        this.secondSchedule = this.patient.patientSecondDate+" @ "+this.datePipe.transform(this.patient.patientSecondTime, 'HH:mm');

      },
      (error: HttpErrorResponse)=>{
        //alert(error.message);
        this.isAlsoPatient = false;
      }
    );
  }

  public getDoctorsById(doctorId: string): void{
    this.doctorsService.getDoctorsById(doctorId).subscribe(
      (response: Doctors)=>{
        this.doctor = response;
        
        this.firstCentreDoctor = this.doctor.doctorFirstCentre;
        this.doctor.doctorSecondCentre?this.secondCentreDoctor = this.doctor.doctorSecondCentre: this.doctor.doctorSecondCentre;
        this.doctor.doctorThirdCentre?this.thirdCentreDoctor = this.doctor.doctorThirdCentre: this.doctor.doctorThirdCentre;
        
        this.firstDate = this.doctor.doctorFirstDate;
        this.secondDate = this.doctor.doctorSecondDate;
        this.thirdDate = this.doctor.doctorThirdDate;
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

  public onOpenModal(object:any, content: any):void{
    this.objectData = object;
    this.modalService.open(content);
  }

  public onCloseModal():void{
    this.modalService.dismissAll();
  }

  public updateDoctor(): void{

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
  

  public updatePatient(): void{
    if(this.objectData.patientFullname==null||this.objectData.patientEmail==null||this.objectData.patientPassword==null
      ||this.reTypePatientPassword==null||this.objectData.patientId==null||this.objectData.patientDOB==null
      ||this.objectData.patientPhone==null){
        alert("Update failed.Please refresh page.");
        this.ngOnInit();  
      }else{
        if(this.fullnameValidation(this.objectData.patientFullname)&&this.emailValidation(this.objectData.patientEmail)&&this.passwordValidation(this.objectData.patientPassword)&&
    this.isPasswordMatching(this.objectData.patientPassword, this.reTypePatientPassword)&&this.idValidation(this.objectData.patientId)&&this.ageValidation(this.objectData.patientDOB)&&this.phoneValidation(this.objectData.patientPhone)){
          this.patientsService.updatePatients(this.objectData).subscribe(
            (response: Patients)=>{
              alert("Patient has been updated.");
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


  public updatePatientSchedule():void{
    this.objectData.patientFirstDate = this.firstSchedule.substring(0,10);
    this.objectData.patientFirstTime = this.firstSchedule.substring(13, this.firstSchedule.length);
    this.objectData.patientSecondDate = this.secondSchedule.substring(0,10);
    this.objectData.patientSecondTime = this.secondSchedule.substring(13, this.secondSchedule.length);
    
    this.patientsService.updatePatients(this.objectData).subscribe(
      (response: Patients)=>{
        console.log(response);
        alert("Schedule have been updated.");
        this.onCloseModal();
        this.router.navigate(['doctorsPage', this.data]);
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
        alert("Update failed. Please refresh page.");
      }
    );
  }

  public updateSchedule():void{
    this.onPopulate();
    
    this.doctorsService.updateDoctors(this.objectData).subscribe(
      (response: Doctors)=>{
        console.log(response);
        alert("Schedule have been updated.");
        this.onCloseModal();
        this.router.navigate(['doctorsPage', this.data]);
      },
      (error: HttpErrorResponse)=>{
        console.log(error.message);
        alert("Update unsuccessful. Please refresh page.");
      }
    );
  }

  public onPopulate(){



    this.firstCentreDoctor === undefined?this.objectData.doctorFirstCentre = null:this.objectData.doctorFirstCentre = this.firstCentreDoctor.centreName;
    this.secondCentreDoctor === undefined?this.objectData.doctorSecondCentre = null:this.objectData.doctorSecondCentre = this.secondCentreDoctor.centreName;
    this.thirdCentreDoctor === undefined?this.objectData.doctorThirdCentre = null:this.objectData.doctorThirdCentre = this.thirdCentreDoctor.centreName;
    
    (this.firstTimeStart || this.firstTimeEnd) === undefined?this.objectData.doctorFirstTime = null:this.objectData.doctorFirstTime = this.firstTimeStart +" - "+ this.firstTimeEnd;
    (this.secondTimeStart || this.secondTimeEnd) === undefined?this.objectData.doctorSecondTime = null:this.objectData.doctorSecondTime = this.secondTimeStart+" - "+this.secondTimeEnd;
    (this.thirdTimeStart || this.thirdTimeEnd) === undefined?this.objectData.doctorThirdTime = null:this.objectData.doctorThirdTime = this.thirdTimeStart+" - "+this.thirdTimeEnd;

    console.log(this.firstTimeEnd);

  }

  public isPasswordMatching(password: string, reTypePassword: string): boolean{
    if(password === reTypePassword){
      return true;
    }else{
      alert("Password does not match!")
      return false;
    }
  }

  public fullnameValidation(fullname: any):boolean{
    if(fullname.match(/^[a-zA-Z ]+$/)){
      return true;
    }else{
      alert("Fullname can't contain any symbol!");
      return false;
    }
  }

  public emailValidation(email: any): boolean{
    if(email.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
      return true;
    }else{
      alert("Please enter a valid email address.\nEg. john.doe@gmail.com");
      return false;
    }
  }

  public passwordValidation(password: any): boolean{
    if(password.match(/^([a-zA-Z0-9!@#$%^&*])(?=.{8,})/)){
      return true;
    }else{
      alert("Password must be a least 8 characters long!");
      return false;
    }
  }

  public idValidation(id: any): boolean{
    if(id.toUpperCase().match(/^[STFG]\d{7}[A-Z]$/)){
      return true;
    }else{
      alert("Invalide NRIC! NRIC should start with either S,T,F,G contains 7 digits \nin between and ends with an alphabet.");
      return false;
    }
  }

  public phoneValidation(phone: any): boolean{
    if(phone.match(/^[896]\d{7}$/)){
      return true;
    }else{
      alert("Phone number should be 8 digits long \nand start with either 8, 9 or 6 (for landline).");
      return false;
    }
  }

  public ageValidation(age: any): boolean{
    let dob = new Date(Date.parse(age));
    let dobYear = dob.getFullYear();
    let timeDiff: any = new Date().getFullYear()-dobYear;
    if(timeDiff>=12){
      return true;
    }else{
      alert("You have to be at least 12 years old to register.")
      return false;
    }

  }

  public accreditationValidation(accreditation: any):boolean{
    if(accreditation.match(/^[a-zA-Z0-9]{2}-[a-zA-Z0-9]{7}$/)){
      return true;
    }else{
      alert("Medical Accreditation should consist of only alphanumeric characters.\nEg.aZ-9877uI64H");
      return false;
    }
  }

}
