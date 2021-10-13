import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { VaccinationCentres } from '../interface/vaccinationCentres';

@Injectable({
  providedIn: 'root'
})
export class VaccinationCentresService {
  private apiServerUrl=environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getCentres(): Observable<VaccinationCentres[]>{
    return this.http.get<VaccinationCentres[]>(`${this.apiServerUrl}/VaccinationCentres/all`);
  }

  public getCentresByName(centreName: string): Observable<VaccinationCentres>{
    return this.http.get<VaccinationCentres>(`${this.apiServerUrl}/VaccinationCentres/findByName/${centreName}`);
  }

  public addCentres(centre: VaccinationCentres): Observable<VaccinationCentres>{
    return this.http.post<VaccinationCentres>(`${this.apiServerUrl}/VaccinationCentres/add`, centre);
  }

  public updateCentres(centre: VaccinationCentres): Observable<VaccinationCentres>{
    return this.http.put<VaccinationCentres>(`${this.apiServerUrl}/VaccinationCentres/update`, centre);
  }

  public deleteCentres(centreId: string): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/VaccinationCentres/delete/${centreId}`);
  }
}
