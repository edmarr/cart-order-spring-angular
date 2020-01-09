import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Observable } from 'rxjs';
import { NotificationService } from 'src/app/core/notifications/notification.service';
import { Item } from 'src/app/item/item.model';
import { ItemService } from 'src/app/item/item.service';
import { User } from 'src/app/user/user.model';
import { UserService } from 'src/app/user/user.service';
import { Cart } from '../../cart.model';
import { ItemCart } from '../../item-cart.model';
import { ItemCartService } from '../../item-cart.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SharedService } from '../shared-data-service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  public displayedColumns = ['name', 'valueItem', 'add', 'delete'];
  public dataSource = new MatTableDataSource();

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  public basicForm: FormGroup;
  users: Observable<User[]>;
  public user;
  cart = <Cart>{};

  constructor(private readonly notificationService: NotificationService, private readonly userService: UserService, private readonly itemService: ItemService, private shared: SharedService,private router: Router ) { }

  ngOnInit() {
    this.users = this.userService.getAll();
    this.getItens();
  }



  getItens() {
    this.itemService.getAll().subscribe((res) => {
      this.dataSource.data = res;

    });
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

  public addItem = (item: Item) => {


    if (this.cart.user == null) {
      this.cart.user = this.user;
    }

    if (this.cart.itens == null || this.cart.itens.length === 0) {
      this.cart.itens = [];
      this.addNewItem(item);
    } else {

      let updateItem = this.cart.itens.find(i => i.item.id == item.id);
      if (updateItem) {
        let index = this.cart.itens.indexOf(updateItem);
        updateItem.quantity = updateItem.quantity + 1;
        this.cart.itens[index] = updateItem;
      } else {
        this.addNewItem(item);
      }

    }
    console.log(this.cart);
  }

  public deleteItem = (item: Item) => {
    if (this.cart.itens == null || this.cart.itens.length === 0) {
      this.notificationService.warn("The cart is empty!");
    }
    let keyItem = this.cart.itens.find(i => i.item == item);
    const index = this.cart.itens.indexOf(keyItem, 0);
    if (index > -1) {
      this.cart.itens.splice(index, 1);
    } else {
      this.notificationService.warn("No item ordered");
    }
  }

  private addNewItem(item: Item) {
    let itemCart = <ItemCart>{};
    itemCart.item = item;
    itemCart.quantity = 1;
    this.cart.itens.push(itemCart);
  }
  public redirectToBuy = (id: string) => {
    console.log(this.shared);
    this.shared.value = this.cart;
    let url: string = `orders/buy`;
    this.router.navigate([url]);
  }


}
