import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Feedbacks } from '../interface/feedbacks';
import { FeedbacksService } from '../service/feedbacks.service';

@Component({
  selector: 'logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  public user!: any;
  public feedbackData = new Feedbacks();

  constructor(private route: ActivatedRoute, private router: Router, private modalService: NgbModal, private feedbacksService: FeedbacksService,
    private datePipe: DatePipe) { }

  ngOnInit(): void {
  }

  public logout(){
    this.router.navigate(['']);
    alert("You have logged out!");
  }

  public onCloseModal():void{
    this.modalService.dismissAll();
  }

  public onOpenModal(content: any):void{
    this.modalService.open(content);
  }

  public addFeedback(){
    let time = new Date();
     
    this.feedbackData.feedbackTime = this.datePipe.transform(time, "dd/MM/yyyy")!.toString();

    this.feedbacksService.addFeedbacks(this.feedbackData).subscribe(
      (response: Feedbacks)=>{
        alert("Feedback has been added");
        this.onCloseModal();
      },
      (error:HttpErrorResponse)=>{
        console.log(error.message);
        this.onCloseModal();
      }
    );
  }

  public typeSelect(event: any){
    this.feedbackData.feedbackType = event.target.value;
    console.log(this.feedbackData);
  }

  public ratingsSelect(event: any){
    this.feedbackData.feedbackRatings = event.target.value;
  }

}
