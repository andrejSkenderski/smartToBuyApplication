import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {ProductsService} from "../../../services/products.service";
import {Product} from "../../../models/product";
import {Form, FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  form: FormGroup;
  products$: Observable<Product[]> | undefined;

  constructor(private _productService: ProductsService) {
    this.form = this.formDefinition();
  }

  ngOnInit(): void {
    this.products$ = this._productService.getProducts()
  }

  formDefinition() {
    return this.form = new FormGroup({
        products: new FormControl('')
      }
    );
  }

  onSubmit() {
    console.log(this.form.controls)
  }

}
