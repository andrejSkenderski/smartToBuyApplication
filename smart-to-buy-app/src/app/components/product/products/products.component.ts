import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {ProductsService} from "../../../services/products.service";
import {Product} from "../../../models/product";
import { FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  form: FormGroup;
  products$: Observable<Product[]> | undefined;

  constructor(private _productService: ProductsService,
              private _formBuilder: FormBuilder) {
    this.form = this._formDefinition;
  }

  ngOnInit(): void {
    this.products$ = this._productService.getProducts()
  }


  onSubmit() {
    console.log('productID', this.form.get('products'));
  }

  private get _formDefinition() {
    return this._formBuilder.group({
      products: new FormControl(null)
    });
  }
}
