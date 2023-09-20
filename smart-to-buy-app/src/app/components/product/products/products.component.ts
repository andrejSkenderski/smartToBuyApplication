import {Component, OnInit} from '@angular/core';
import {ProductsService} from "../../../services/products.service";
import {Product} from "../../../models/product";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {CartItemService} from "../../../services/cart-item.service";
import {User} from "../../../models/user";
import {UserService} from "../../../services/user.service";
import {SuggestedProductsService} from "../../../services/suggested-products.service";

@Component({
  selector: 'products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  isLoggedIn: boolean = false
  loggedInUser: User | null = null

  form: FormGroup;
  products: Product[] | undefined;
  suggestedProducts: Product[] | undefined;

  constructor(private _productService: ProductsService,
              private _formBuilder: FormBuilder,
              private cartItemService: CartItemService,
              private userService: UserService,
              private _suggestedProductService: SuggestedProductsService
  ) {
    this.form = this._formDefinition;
  }

  ngOnInit(): void {
    this.userService.getUser().subscribe({
      next: data => {
        if(data.user) {
          this.loggedInUser = data.user
          this.isLoggedIn = true
        }
        else {
          this.isLoggedIn = false
        }
      }
    })

    this._productService.getProducts().subscribe({
        next: value => this.products = value
      }
    )

    this._suggestedProductService.getSuggestedProducts().subscribe({
        next: value => this.suggestedProducts = value
      }
    )
  }


  onSubmit() {
    console.log('productID', this.form.get('products'));
  }

  addToCart(productId: number) {
    this.cartItemService.addToCart(productId).subscribe({
      next: () => {
        // Handle success, you may want to display a message or update the cart icon
        console.log(`Product with ID ${productId} added to cart.`);
      },
      error: (error) => {
        // Handle error, display an error message if needed
        console.error(`Error adding product to cart: ${error}`);
      }
    });
  }

  private get _formDefinition() {
    return this._formBuilder.group({
      products: new FormControl(null)
    });
  }
}
