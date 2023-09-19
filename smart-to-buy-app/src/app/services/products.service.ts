import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../models/product";
import {ProductCreateRequest} from "../models/request/product-create-request.interface";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  url = 'http://localhost:8080/api/products'

  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.url}`)
  }

  save(request: ProductCreateRequest){
    return this.http.post(this.url, request);
  }

}
