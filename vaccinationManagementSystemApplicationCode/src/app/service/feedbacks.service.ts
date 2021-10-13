import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Feedbacks } from '../interface/feedbacks';

@Injectable({
  providedIn: 'root'
})
export class FeedbacksService {
  private apiServerUrl=environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getFeedbacks(): Observable<Feedbacks[]>{
    return this.http.get<Feedbacks[]>(`${this.apiServerUrl}/Feedbacks/all`);
  }

  public addFeedbacks(feedback: Feedbacks): Observable<Feedbacks>{
    return this.http.post<Feedbacks>(`${this.apiServerUrl}/Feedbacks/add`, feedback);
  }

  public updateFeedback(feedback: Feedbacks): Observable<Feedbacks>{
    return this.http.put<Feedbacks>(`${this.apiServerUrl}/Feedbacks/update`, feedback); 
  }

  public deleteFeedback(feedbackId: string): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/Feedbacks/delete/${feedbackId}`);
  }

}
