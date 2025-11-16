import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout',
  imports: [RouterOutlet],
  templateUrl: './layout.html',
  styleUrl: './layout.css'
})
export class Layout {
  
  router = inject(Router);
  currentYear = new Date().getFullYear();

  constructor() {
    if(!localStorage.getItem('token')){
      this.router.navigate(['/auth']);
    }
  }

  OnLogoff(){
    localStorage.removeItem('token');
    this.router.navigate(['/auth']);

  }

  onProfileClick(){
    const userType = localStorage.getItem('role');
    
    if(userType === 'CLIENT'){
      this.router.navigate(['/profile/client']);
    } else if(userType === 'AGENT'){
      this.router.navigate(['/profile/agent']);
    } else {
      this.router.navigate(['']);
    }
  }
}