import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {Category} from "../../../models/category";
import {CategoryService} from "../../../services/category.service";
import {ProductsService} from "../../../services/products.service";
import {ProductCreateRequest} from "../../../models/request/product-create-request.interface";
import {Router} from "@angular/router";

@Component({
  selector: 'create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.scss']
})
export class CreateProduct implements OnInit {
  form: FormGroup;
  categories$: Observable<Category[]>;

  constructor(
    private _formBuilder: FormBuilder,
    private _categoryService: CategoryService,
    private _productService: ProductsService,
    private router: Router
  ) {
    this.form = this._formDefinition;
    this.categories$ = this._categoryService.getCategories();
  }

  ngOnInit(): void {
    // this.form = this._formDefinition;
    // this.categories$ = this._categoryService.getCategories();
  }

  onSubmit() {
    this._productService.save(this.form.getRawValue() as ProductCreateRequest).subscribe({
      next: value => this.router.navigate(['/home'])
    })

  }

  private get _formDefinition() {
    return this._formBuilder.group({
      name: new FormControl(null, Validators.required),
      category: new FormControl(null, Validators.required),
      price: new FormControl(null, Validators.required),
      description: new FormControl(null),
      image: new FormControl(null)
    });
  }
}
