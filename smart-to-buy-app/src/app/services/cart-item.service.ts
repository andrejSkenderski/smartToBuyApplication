import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CartItem} from "../models/cart-item";

@Injectable({
  providedIn: 'root',
})
export class CartItemService {
  private baseUrl = '/api/cart';

  constructor(private http: HttpClient) {}

  addToCart(productId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/add/${productId}`, {});
  }

  removeFromCart(productId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/remove/${productId}`);
  }

  purchaseCartItems(): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/purchase`, {});
  }

    getCartItems(): Observable<CartItem> {
        return this.http.get<CartItem>(`${this.baseUrl}/items`);
    }
}
