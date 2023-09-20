import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../models/product";

@Injectable({
  providedIn: 'root'
})
export class SuggestedProductsService {
  url = 'http://localhost:8080/api/suggested-products'

  constructor(private http: HttpClient) {
  }

  getSuggestedProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.url}`)
  }

}
