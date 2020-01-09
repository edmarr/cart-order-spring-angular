import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { User } from '../../user.model';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../user.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  public basicForm: FormGroup;
  itens$: Observable<User[]>;

  constructor(private activeRoute: ActivatedRoute, private location: Location, private fb: FormBuilder, private userService: UserService) {
    this.itens$ = userService.entities$;
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.basicForm = this.fb.group({
      name:  new FormControl('', [Validators.required, Validators.max(50)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      id: []
    })

    this.getItemById();

  }

  private getItemById() {
    let id: number = this.activeRoute.snapshot.params['id'];

    this.userService.getByKey(id).subscribe(res => {
      this.basicForm.patchValue(res as User);
    });
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.basicForm.controls[controlName].hasError(errorName);
  }
  public onCancel = () => {
    this.location.back();
  }

  public update= (basicFormValue) => {
    console.log(basicFormValue);

    if (this.basicForm.valid) {
      this.userService.update(basicFormValue).subscribe(result => {
        this.location.back();
      });
    }
  }

}

