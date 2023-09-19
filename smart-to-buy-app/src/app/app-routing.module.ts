import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";
import {ProductsComponent} from "./components/product/products/products.component";
import {CreateProduct} from "./components/product/create-product/create-product.component";
import {CartComponent} from "./components/cart/cart.component";

const routes: Routes = [
  {path: "home", component: DashboardComponent},
  {path: "products", component: ProductsComponent},
  {path: "register", component: RegisterComponent},
  {path: "login", component: LoginComponent},
  {path: "create-product", component: CreateProduct},
  {path: 'cart-items', component: CartComponent },
  {path: "", redirectTo: "home", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
