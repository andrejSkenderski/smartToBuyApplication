import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../models/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  url = 'http://localhost:8080/api/product-category'

  constructor(private _http: HttpClient) {
  }

  getCategories(): Observable<Category[]> {
    return this._http.get<Category[]>(`${this.url}`);
  }
}
