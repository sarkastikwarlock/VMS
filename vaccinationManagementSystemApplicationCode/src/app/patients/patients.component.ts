import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Patients } from '../interface/patients';
import { PatientsService } from '../service/patients.service';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
  public patient!: Patients[];

  constructor(private patientsService: PatientsService) { }

  ngOnInit(): void {
  }

  public getPatients():void{
    this.patientsService.getPatients().subscribe(
      (response: Patients[])=>{
        this.patient=response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }
}
