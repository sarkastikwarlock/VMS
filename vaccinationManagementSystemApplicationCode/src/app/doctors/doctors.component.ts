import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Doctors } from '../interface/doctors';
import { DoctorsService } from '../service/doctors.service';

@Component({
  selector: 'doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {
  public doctors!: Doctors[];

  constructor(private doctorsService: DoctorsService) { 
  }

  ngOnInit(): void {
    this.getDoctors();
  }
  
  public getDoctors(): void{
    this.doctorsService.getDoctors().subscribe(
      (response: Doctors[]) =>{
        this.doctors = response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    )
  }

}
