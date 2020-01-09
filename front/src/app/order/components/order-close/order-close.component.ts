import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { SharedService } from '../shared-data-service';
import { Cart } from '../../cart.model';
import { ItemCartService } from '../../item-cart.service';
import { NotificationService } from 'src/app/core/notifications/notification.service';

@Component({
  selector: 'app-order-close',
  templateUrl: './order-close.component.html',
  styleUrls: ['./order-close.component.css']
})
export class OrderCloseComponent implements OnInit {

  displayedColumns: string[] = ['name', 'valueItem', 'quantity'];
  cart:Cart;
  transactions =[];

  constructor(private shared: SharedService, private itemCartService:ItemCartService, private notificationService:NotificationService, private location:Location) { }

  ngOnInit() {
  
    this.cart = this.shared.value as Cart;
    this.cart.itens.sort((one,two) => (one.item.name > two.item.name ? 1:-1));
    this.transactions = this.cart.itens;
  }


  getTotalCost() {
    return this.transactions.map(t => t.quantity * t.item.valueItem).reduce((acc, value) => acc + value, 0);
  }

  public checkout =  ()  =>{
    
    console.log(this.cart);
    this.itemCartService.add(this.cart).subscribe(result => {
      this.notificationService.success ('The order was created.');
      this.location.back();
    });
  }
}
