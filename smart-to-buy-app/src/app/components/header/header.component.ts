import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{

  isLoggedIn: boolean = false
  loggedInUser: User | null = null

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService) { }

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
  }

  logout() {
    this.authService.logout()
    this.isLoggedIn = false
    this.router.navigateByUrl("/")
  }

}
