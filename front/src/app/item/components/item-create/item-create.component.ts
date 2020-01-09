import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Location } from '@angular/common';
import { Item } from '../../item.model';
import { Observable } from 'rxjs';
import { NotificationService } from 'src/app/core/notifications/notification.service';
import { ItemService } from '../../item.service';

@Component({
  selector: 'app-item-create',
  templateUrl: './item-create.component.html',
  styleUrls: ['./item-create.component.css']
})
export class ItemCreateComponent implements OnInit {
  public basicForm: FormGroup;
  itens$: Observable<Item[]>;


  constructor(private readonly notificationService: NotificationService, private location: Location, private fb: FormBuilder,private itemService: ItemService) {
    this.itens$ = itemService.entities$;
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.basicForm = this.fb.group({
      name:  new FormControl('', [Validators.required, Validators.max(30)]),
      valueItem: [null, Validators.required],
    })

  }


  public hasError = (controlName: string, errorName: string) => {
    return this.basicForm.controls[controlName].hasError(errorName);
  }

  public onCancel = () => {
    this.location.back();
  }

  public createItem = (basicFormValue) => {
    this.itemService.add(basicFormValue).subscribe(result => {
      this.notificationService.success ('Item was saved');
      this.location.back();
    });
  }


}
