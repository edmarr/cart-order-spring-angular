import { Component, OnInit } from '@angular/core';
import { Cart } from '../../cart.model';
import { ItemCartService } from '../../item-cart.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  displayedColumns: string[] = ['name' , 'total'];
  dataSource;
  constructor(private readonly itemCartService:ItemCartService) { }

  ngOnInit() {
    this.itemCartService.getAll().subscribe(res => {
      this.dataSource = res;
    });
  }

  public getTotalCost = (cart:Cart) => {
    console.log(cart);
    return  cart.itens.map(t => t.quantity * t.item.valueItem).reduce((acc, value) => acc + value, 0);;
  }


}
