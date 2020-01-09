import { Injectable } from '@angular/core';
import {
  EntityCollectionServiceBase,
  EntityCollectionServiceElementsFactory
} from '@ngrx/data';
import { Item } from './item.model';

@Injectable({ providedIn: 'root' })
export class ItemService extends EntityCollectionServiceBase<Item> {
  constructor(serviceElementsFactory: EntityCollectionServiceElementsFactory) {
    super('Itens', serviceElementsFactory);
  }

}