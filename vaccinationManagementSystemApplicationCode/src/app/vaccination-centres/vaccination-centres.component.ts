import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { VaccinationCentres } from '../interface/vaccinationCentres';
import { VaccinationCentresService } from '../service/vaccination-centres.service';

@Component({
  selector: 'vaccination-centres',
  templateUrl: './vaccination-centres.component.html',
  styleUrls: ['./vaccination-centres.component.css']
})
export class VaccinationCentresComponent implements OnInit {
  public centres!: VaccinationCentres[]

  constructor(private centresService: VaccinationCentresService) { }

  ngOnInit(): void {
    this.getCentres();
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

}
