import { Component, OnInit } from '@angular/core';
import { User } from '../../user.model';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { NotificationService } from 'src/app/core/notifications/notification.service';
import { UserService } from '../../user.service';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {
  public basicForm: FormGroup;
  itens$: Observable<User[]>;


  constructor(private readonly notificationService: NotificationService, private location: Location, private fb: FormBuilder,private userService: UserService) {
    this.itens$ = userService.entities$;
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.basicForm = this.fb.group({
      name:  new FormControl('', [Validators.required, Validators.max(50)]),
      email: new FormControl('', [Validators.required, Validators.email]),
    })

  }


  public hasError = (controlName: string, errorName: string) => {
    return this.basicForm.controls[controlName].hasError(errorName);
  }

  public onCancel = () => {
    this.location.back();
  }

  public create = (basicFormValue) => {
    this.userService.add(basicFormValue).subscribe(result => {
      this.notificationService.success ('The user was created.');
      this.location.back();
    });
  }



}
