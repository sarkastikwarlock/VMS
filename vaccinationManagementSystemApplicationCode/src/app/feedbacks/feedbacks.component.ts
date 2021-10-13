import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Feedbacks } from '../interface/feedbacks';
import { FeedbacksService } from '../service/feedbacks.service';

@Component({
  selector: 'feedbacks',
  templateUrl: './feedbacks.component.html',
  styleUrls: ['./feedbacks.component.css']
})
export class FeedbacksComponent implements OnInit {
  public feedback!: Feedbacks[];

  constructor(private feedbacksService: FeedbacksService) { }

  ngOnInit(): void {
  }

  public getFeedbacks(): void{
    this.feedbacksService.getFeedbacks().subscribe(
      (response: Feedbacks[])=>{
        this.feedback = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

}
