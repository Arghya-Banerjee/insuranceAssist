import { CommonModule } from '@angular/common';
import { inject, Injectable } from '@angular/core';
import { Component } from '@angular/core';
import { Client } from '../../../../../core/services/api/client/client';
import { Policy } from '../../../../../core/services/api/policy/policy';

@Component({
  selector: 'app-client-overview',
  imports: [CommonModule],
  standalone: true,
  templateUrl: './client-overview.html',
  styleUrl: './client-overview.css'
})
export class ClientOverview {
  
  private clientService = inject(Client);
  private policyService = inject(Policy);
  private clientId: any = localStorage.getItem('userId');
  clientDetails: any;
  policyDetails: any;
  policyTypeDetails: any;

  ngOnInit(){
    this.onGetProfileDetails();
    this.onGetPolicyDetails();
    this.onGetPolicyTypeDetails();
  }

  onGetProfileDetails(){
    this.clientService.onGetProfileDetails(this.clientId).subscribe({
      next: (result: any)  => {
        this.clientDetails = result;
      }
    });
  }

  onGetPolicyDetails(){
    this.policyService.onGetPolicyDetails(this.clientId).subscribe({
      next: (result: any) => {
        this.policyDetails = result;
      }
    });
  }

  onGetPolicyTypeDetails(){
    this.policyService.onGetPolicyTypeDetails(this.clientId).subscribe({
      next: (result: any) => {
        this.policyTypeDetails = result;
      }
    });
  }

}
