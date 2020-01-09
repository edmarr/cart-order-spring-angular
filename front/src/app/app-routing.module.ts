import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemCreateComponent } from './item/components/item-create/item-create.component';
import { ItemListComponent } from './item/components/item-list/item-list.component';
import { ItemDeleteComponent } from './item/components/item-delete/item-delete.component';
import { ItemUpdateComponent } from './item/components/item-update/item-update.component';
import { UserListComponent } from './user/components/user-list/user-list.component';
import { UserCreateComponent } from './user/components/user-create/user-create.component';
import { UserDeleteComponent } from './user/components/user-delete/user-delete.component';
import { UserUpdateComponent } from './user/components/user-update/user-update.component';
import { OrderComponent } from './order/components/cart/order.component';
import { OrderCloseComponent } from './order/components/order-close/order-close.component';
import { OrderListComponent } from './order/components/order-list/order-list.component';


const routes: Routes = [
  { path: 'users/list', component: UserListComponent },
  { path: 'users/create', component: UserCreateComponent },
  { path: 'users/delete/:id', component: UserDeleteComponent },
  { path: 'users/update/:id', component: UserUpdateComponent },
  { path: 'itens/list', component: ItemListComponent },
  { path: 'itens/create', component: ItemCreateComponent },
  { path: 'itens/delete/:id', component: ItemDeleteComponent },
  { path: 'itens/update/:id', component: ItemUpdateComponent },
  { path: 'orders/manager', component: OrderComponent },
  { path: 'orders/buy', component: OrderCloseComponent },
  { path: 'orders/list', component: OrderListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
