import { Component, OnInit } from '@angular/core';
import {CartItem} from "../../models/cart-item";
import {CartItemService} from "../../services/cart-item.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart',
  templateUrl: 'cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  cartItems: CartItem | undefined;
  totalCartPrice: number = 0;

  constructor(private cartItemService: CartItemService,
              private router: Router) {}

  ngOnInit(): void {
    this.getCartItems();
  }

  getCartItems(): void {
    this.cartItemService.getCartItems().subscribe((cartItems) => {
      this.cartItems = cartItems;
      this.totalCartPrice = cartItems.products.reduce(
        (total, product) => total + product.price,
        0
      );
    });
  }

  removeFromCart(productId: number): void {
    this.cartItemService.removeFromCart(productId).subscribe(() => {
      // After successfully removing the item, update the cartItems array
      this.getCartItems();
      window.location.reload();
    });
  }

  purchaseCartItems(): void {
    this.cartItemService.purchaseCartItems().subscribe(() => {
      this.getCartItems();
      this.router.navigate(['/home']);
    });
  }

}
