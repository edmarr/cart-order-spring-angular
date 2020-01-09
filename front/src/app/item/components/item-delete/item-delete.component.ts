import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material';
import { Item } from '../../item.model';
import { ItemService } from '../../item.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-item-delete',
  templateUrl: './item-delete.component.html',
  styleUrls: ['./item-delete.component.css']
})
export class ItemDeleteComponent implements OnInit {
  loading$: Observable<boolean>;
  itens$: Observable<Item[]>;
  public item: Item;

  constructor(private location: Location, private dialog: MatDialog,
    private activeRoute: ActivatedRoute, private itemService: ItemService ) {

      this.itens$ = itemService.entities$;
      this.loading$ = itemService.loading$;

  }

  ngOnInit() {
    this.getItemById();
  }
  private getItemById() {
    let id: number = this.activeRoute.snapshot.params['id'];

    this.itemService.getByKey(id).subscribe(res => {
      this.item = res as Item;
    });
  }

  public delete(){
    this.itemService.delete(this.item.id).subscribe(res => {
      this.location.back();
    });
  }

  public onCancel = () => {
    this.location.back();
  }


}
