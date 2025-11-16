import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from '../../../../environments/environment.development';
import { LoginService } from '../../../core/services/api/login/login';


@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  router = inject(Router);
  loginService = inject(LoginService);

  loginForm: FormGroup = new FormGroup({
    role: new FormControl(""),
    username : new FormControl(""),
    password : new FormControl("")
  });


  onLogin() {
    const formValue = this.loginForm.value;
    const selectedRole = this.loginForm.get('role')?.value;

    this.loginService.onLogin(formValue).subscribe({
      next: (response:any) => {
        
        if(response && response.token){
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role);
          localStorage.setItem('userId', response.userId);

          if(response.role === 'CLIENT' && 
            this.loginForm.value.role === 'client'
          ){
            this.router.navigateByUrl("/dashboard/client");
          }
          else if(response.role === 'AGENT' && 
            this.loginForm.value.role === 'agent'
          ){
          this.router.navigateByUrl("/dashboard/agent");
          }
          else if(response.role !== selectedRole.toUpperCase()){
            alert(`${selectedRole} not Found. Please select the correct role or register.`);
            localStorage.removeItem('token');
            localStorage.removeItem('role');
            localStorage.removeItem('userId');
          }
        }
        else{
          alert("Invalid response from server.");
        }
      },
      error: (error) => {
        alert("Invalid Credentials. Please try again.");
      }
    });
  }
}
