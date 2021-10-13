import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Admins } from '../interface/admins';

@Injectable({
  providedIn: 'root'
})
export class AdminsService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(public http: HttpClient) { }

  public getAdmins(): Observable<Admins[]>{
    return this.http.get<Admins[]>(`${this.apiServerUrl}/Admins/all`);
  } 

  public getAdminsById(adminId: string): Observable<Admins>{
    return this.http.get<Admins>(`${this.apiServerUrl}/Admins/findById/${adminId}`);
  }

  public getAdminsByIdAndPassword(admin: Admins): Observable<Admins>{
    return this.http.post<Admins>(`${this.apiServerUrl}/Admins/findByIdAndPassword`, admin);
  }

  public addAdmins(admin: Admins): Observable<Admins>{
    return this.http.post<Admins>(`${this.apiServerUrl}/Admins/add`, admin);
  }
  
  public updateAdmins(admin: Admins): Observable<Admins>{
    return this.http.put<Admins>(`${this.apiServerUrl}/Admins/update`, admin);
  }

  public deleteAdmins(adminId: string): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/Admins/delete/${adminId}`);
  }
}
