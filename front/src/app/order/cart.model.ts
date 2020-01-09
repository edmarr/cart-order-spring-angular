import { User } from '../user/user.model';
import { ItemCart } from './item-cart.model';

export interface Cart {
  id: number;
  user: User;
  itens: ItemCart[];
}
