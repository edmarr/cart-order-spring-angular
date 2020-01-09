import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { Item } from '../../item.model';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ItemService } from '../../item.service';

@Component({
  selector: 'app-item-update',
  templateUrl: './item-update.component.html',
  styleUrls: ['./item-update.component.css']
})
export class ItemUpdateComponent implements OnInit {

  public basicForm: FormGroup;
  itens$: Observable<Item[]>;
  public item: Item;

  constructor(private activeRoute: ActivatedRoute, private location: Location, private fb: FormBuilder, private itemService: ItemService) {
    this.itens$ = itemService.entities$;
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.basicForm = this.fb.group({
      name: new FormControl('', [Validators.required, Validators.max(30)]),
      valueItem: [null, Validators.required],
      id: []
    })

    this.getItemById();

  }

  private getItemById() {
    let id: number = this.activeRoute.snapshot.params['id'];

    this.itemService.getByKey(id).subscribe(res => {
      this.item = res as Item;
      this.basicForm.patchValue(this.item);
    });
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.basicForm.controls[controlName].hasError(errorName);
  }
  public onCancel = () => {
    this.location.back();
  }

  public updateItem = (basicFormValue) => {
    console.log(basicFormValue);

    if (this.basicForm.valid) {
      this.itemService.update(basicFormValue).subscribe(result => {
        this.location.back();
      });
    }
  }

}
