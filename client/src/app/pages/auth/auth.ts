import { Component, inject } from '@angular/core';
import { Login } from './login/login';
import { Signup } from './signup/signup';
import { Router, RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [Login, Signup, RouterOutlet, RouterModule, CommonModule],
  templateUrl: './auth.html',
  styleUrls: ['./auth.css']
})
export class Auth {

  router = inject(Router);
  constructor() {
    if(localStorage.getItem('token')){
      this.router.navigateByUrl("/dashboard/client") ;
    }
  }

  selectedTab: 'login' | 'register' = 'login';
  selectTab(tab: 'login' | 'register') {
    this.selectedTab = tab;


  }
}
