import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Doctors } from '../interface/doctors';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DoctorsService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getDoctors(): Observable<Doctors[]>{
    return this.http.get<Doctors[]>(`${this.apiServerUrl}/Doctors/all`);
  }

  public getDoctorsById(doctorId: string): Observable<Doctors>{
    return this.http.get<Doctors>(`${this.apiServerUrl}/Doctors/findById/${doctorId}`);
  }

  public getDoctorsByEmail(doctorEmail: string): Observable<Doctors>{
    return this.http.get<Doctors>(`${this.apiServerUrl}/Doctors/findByEmail/${doctorEmail}`);
  }

  public getDoctorsByIdAndPassword(doctor: Doctors): Observable<Doctors>{
    return this.http.post<Doctors>(`${this.apiServerUrl}/Doctors/findByIdAndPassword`, doctor);
  }

  public addDoctors(doctor: Doctors): Observable<Doctors>{
    return this.http.post<Doctors>(`${this.apiServerUrl}/Doctors/add`, doctor);
  }

  public updateDoctors(doctor: Doctors): Observable<Doctors>{
    return this.http.put<Doctors>(`${this.apiServerUrl}/Doctors/update`, doctor);
  }

  public deleteDoctors(doctorId: String): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/Doctors/delete/${doctorId}`);
  }
}
