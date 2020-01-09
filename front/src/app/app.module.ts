import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MenuComponent } from './menu/menu.component';
import { LayoutModule } from '@angular/cdk/layout';
import { StoreModule } from '@ngrx/store';
import { reducers, metaReducers } from './reducers';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EntityDataModule, DefaultDataServiceConfig } from '@ngrx/data';
import { entityConfig, defaultDataServiceConfig } from './entity-metadata';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { EffectsModule } from '@ngrx/effects';
import { MaterialModule } from './material/material.module';
import { HttpErrorInterceptor } from './core/http-interceptors/http-error.interceptor';
import { UserListComponent } from './user/components/user-list/user-list.component';
import { UserCreateComponent } from './user/components/user-create/user-create.component';
import { UserDeleteComponent } from './user/components/user-delete/user-delete.component';
import { UserUpdateComponent } from './user/components/user-update/user-update.component';
import { ItemCreateComponent } from './item/components/item-create/item-create.component';
import { ItemListComponent } from './item/components/item-list/item-list.component';
import { ItemDeleteComponent } from './item/components/item-delete/item-delete.component';
import { ItemUpdateComponent } from './item/components/item-update/item-update.component';
import { AppErrorHandler } from './core/error-handler/app-error-handler.service';
import { OrderComponent } from './order/components/cart/order.component';
import { OrderCloseComponent } from './order/components/order-close/order-close.component';
import { SharedService } from './order/components/shared-data-service';
import { OrderListComponent } from './order/components/order-list/order-list.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ItemCreateComponent,
    ItemListComponent,
    ItemDeleteComponent,
    ItemUpdateComponent,
    ItemListComponent,
    UserListComponent,
    UserCreateComponent,
    UserDeleteComponent,
    UserUpdateComponent,
    OrderComponent,
    OrderCloseComponent,
    OrderListComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MaterialModule,

    StoreModule.forRoot(reducers, {
      metaReducers,
      runtimeChecks: {
        strictStateImmutability: true,
        strictActionImmutability: true
      }
    }),
    EffectsModule.forRoot([]),

    EntityDataModule.forRoot(entityConfig)
  ],
  providers: [
    { provide: ErrorHandler, useClass: AppErrorHandler },
    { provide: DefaultDataServiceConfig, useValue: defaultDataServiceConfig },
    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true },
    { provide: SharedService, useClass: SharedService }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
