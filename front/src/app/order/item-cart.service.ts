import { Injectable } from '@angular/core';
import {
  EntityCollectionServiceBase,
  EntityCollectionServiceElementsFactory
} from '@ngrx/data';
import { Cart } from './cart.model';

@Injectable({ providedIn: 'root' })
export class ItemCartService extends EntityCollectionServiceBase<Cart> {
  constructor(serviceElementsFactory: EntityCollectionServiceElementsFactory) {
    super('Carts', serviceElementsFactory);
  }

}