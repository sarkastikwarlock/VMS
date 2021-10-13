import { DatePipe } from '@angular/common';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Patients } from '../interface/patients';
import { VaccinationCentres } from '../interface/vaccinationCentres';
import { PatientsService } from '../service/patients.service';
import { VaccinationCentresService } from '../service/vaccination-centres.service';


@Component({
  selector: 'book-vaccine-slot',
  templateUrl: './book-vaccine-slot.component.html',
  styleUrls: ['./book-vaccine-slot.component.css']
})
export class BookVaccineSlotComponent implements OnInit {
  @Input('inputData')
  public inputData!: Patients;

  public firstCentre: any=null;
  public secondCentre: any=null;

  public opening!: any;
  public closing!: any;

  public vaccineSlots: Array<any>=[];
  public vaccineSlots2: Array<any>=[];

  public centres!: VaccinationCentres[];

  public vaccineSlotTime: Array<any>=[];
  public vaccineSlotTime2: Array<any>=[];

  public isRadioSelected: boolean=false;
  public isRadioSelected2: boolean=false;

  public dateDiff!: any;

  public firstDate!: any;
  public secondDate!: any;

  public dateToday: any = new Date().toJSON().split('T')[0];
  public minDateFirst: any = this.addDays(new Date().toJSON().split('T')[0],1);
  public minDateSecond!: any;

  public firstSlot!: any;
  public secondSlot!: any;

  public test!: any;

  public show: boolean = false;
  public show2: boolean = false;

  public dateValidationFailed!: boolean;

  public radioSlots!: any;

  constructor(private centreService: VaccinationCentresService, private patientsService: PatientsService,
     private datePipe: DatePipe, private route: Router ) { }

  ngOnInit(): void {
    this.getCentres();
    //this.onSubmit();
  }

  getCentres(): void{
    this.centreService.getCentres().subscribe(
      (response: VaccinationCentres[])=>{
        this.centres = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );

  }

  onChange():void{
    this.vaccineSlotTime = [];
    this.secondCentre = this.firstCentre;
    
    this.minDateSecond = this.addDays(this.firstDate,30);
    this.secondDate=this.minDateSecond;
    this.isRadioSelected=false;
    if(this.show==false){
      setTimeout(()=>
      this.show=true);
    }
    if(this.firstDate!=undefined&&this.secondDate!=undefined){
      this.dateValidation(this.firstDate, this.secondDate);
    }
  }

  onChange2():void{
    this.vaccineSlotTime2 = [];
    

    this.isRadioSelected2=false;
    if(this.show2==false){
      setTimeout(()=>
      this.show2=true); 
    }
    if(this.firstDate!=undefined&&this.secondDate!=undefined){
      this.dateValidation(this.firstDate, this.secondDate);
    }
  }

  getVaccinationCentreSlots():void{
    //calculating the hours the centre operates based on the opening/closing hours
    if(this.firstDate!=undefined){
    this.vaccineSlots = [];
      
    let hourOpening = this.firstCentre.centreOpening.split(':');
    let hourClosing = this.firstCentre.centreClosing.split(':');
    let hoursOperate = hourClosing[0]-hourOpening[0];

    this.vaccineSlots.push(Number(hourOpening[0])+':00');
    for(let i = 1; i<hoursOperate; i++){
      this.vaccineSlots.push(Number(hourOpening[0])+i+':00');
    }
    this.vaccineSlots.push(Number(hourClosing[0])+':00');

    this.vaccineSlots.forEach(element => {
      this.vaccineSlotTime.push(this.stringToTime(element));
    });
  }
  }

  getVaccinationCentreSlots2(selectedCentre: any):void{
    //calculating the hours the centre operates based on the opening/closing hours
    if(this.secondDate!=undefined&&this.secondCentre!=undefined){
    this.vaccineSlots2 = [];
    let hourOpening = selectedCentre.centreOpening.split(':');
    let hourClosing = selectedCentre.centreClosing.split(':');
    let hoursOperate = hourClosing[0]-hourOpening[0];

    this.vaccineSlots2.push(Number(hourOpening[0])+':00');
    for(let i = 1; i<hoursOperate; i++){
      this.vaccineSlots2.push(Number(hourOpening[0])+i+':00');
    }
    this.vaccineSlots2.push(Number(hourClosing[0])+':00');

    this.vaccineSlots2.forEach(element => {
      this.vaccineSlotTime2.push(this.stringToTime(element));
    });
  }
  }

  onPopulate(){
    this.inputData.patientFirstCentre = this.firstCentre.centreName;
    this.inputData.patientSecondCentre = this.secondCentre.centreName;
    this.inputData.patientFirstDate = this.firstDate.toString();
    this.inputData.patientSecondDate = this.secondDate.toString();
    this.inputData.patientVaccinationType = this.firstCentre.vaccineType;
  }

  radioSelect(event: any){
    let temp:any = event.target.value;
    this.inputData.patientFirstTime = this.datePipe.transform(temp, 'HH:mm')!.toString();
    this.isRadioSelected=true;
  }

  radioSelect2(event: any){
    let temp:any = event.target.value;
    this.inputData.patientSecondTime = this.datePipe.transform(temp, 'HH:mm')!.toString();
    this.isRadioSelected2=true;
  }

  stringToTime(stringTime: any){
    let timeString = stringTime.split(':');
    return new Date().setHours(timeString[0], timeString[1]);
  }

  onSubmit(){
    this.onPopulate();
    if(!this.isRadioSelected==true){
      let temp: any = null;
      this.inputData.patientFirstTime = temp;
    }
    if(!this.isRadioSelected2==true){
      let temp: any = null;
      this.inputData.patientSecondTime = temp;
    }
    
    if(this.dateValidation(this.inputData.patientFirstDate, this.inputData.patientSecondDate)){
    this.patientsService.updatePatients(this.inputData).subscribe(
      (respons: Patients)=>{
        alert("Vaccination schedule have been updated.");
        console.log(this.inputData);
        this.route.navigate(['patientsPage', this.inputData.patientId]);
      },
      (error: HttpErrorResponse)=>{
        alert("Failed to book slots. Please refresh page.");
        console.log(this.inputData);
      }
    );
  }else{
    alert("Failed to book slots. Please refresh page.");
  }
  }

  public addDays(date: string, days: number) {
    let result = new Date(date);
    result.setDate(result.getDate() + days);
    let ans = this.datePipe.transform(result, 'yyyy-MM-dd');
    return ans;
  }


  public isDifferent(): boolean{
    if(this.firstCentre?.centreName!=this.secondCentre?.centreName){
      return true;
    }else{return false;}
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
  
}
