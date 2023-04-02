import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {Category} from "../../../models/category";
import {CategoryService} from "../../../services/category.service";

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
    private _categoryService: CategoryService
  ) {
    this.form = this._formDefinition;
    this.categories$ = this._categoryService.getCategories();
  }

  ngOnInit(): void {
    // this.form = this._formDefinition;
    // this.categories$ = this._categoryService.getCategories();
  }

  onSubmit(){
    let name = this.form.get('name')?.value
    let category = this.form.get('category')?.value
    console.log(this.form)

  }
  //Todo Implement create form

  private get _formDefinition(){
    return this._formBuilder.group({
      name: new FormControl(null, Validators.required),
      category: new FormControl(null, Validators.required)
    });
  }
}
