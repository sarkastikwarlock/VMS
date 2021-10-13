import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Patients } from '../interface/patients';

@Injectable({
  providedIn: 'root'
})
export class PatientsService {
  private apiServerUrl =environment.apiBaseUrl;

  constructor(public http: HttpClient) { }

  public getPatients(): Observable<Patients[]>{
    return this.http.get<Patients[]>(`${this.apiServerUrl}/Patients/all`);
  } 

  public getPatientsById(patientId: string): Observable<Patients>{
    return this.http.get<Patients>(`${this.apiServerUrl}/Patients/findById/${patientId}`);
  }

  public getPatientsByEmail(patientEmail: string): Observable<Patients>{
    return this.http.get<Patients>(`${this.apiServerUrl}/Patients/findByEmail/${patientEmail}`);
  }

  public getPatientsByIdAndPassword(patient: Patients): Observable<Patients>{
    return this.http.post<Patients>(`${this.apiServerUrl}/Patients/findByIdAndPassword`, patient);
  }

  public addPatients(patient: Patients): Observable<Patients>{
    return this.http.post<Patients>(`${this.apiServerUrl}/Patients/add`, patient);
  }
  
  public updatePatients(patient: Patients): Observable<Patients>{
    return this.http.put<Patients>(`${this.apiServerUrl}/Patients/update`, patient);
  }

  public deletePatients(patientId: string): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/Patients/delete/${patientId}`);
  }
}
