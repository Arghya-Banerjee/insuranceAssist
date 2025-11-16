import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class Policy {

  private http = inject(HttpClient);

  onGetPolicyDetails(clientId: string){
    return this.http.get(`${environment.apiUrl}/private/policy/get/${clientId}`)
  }

  onGetPolicyTypeDetails(clientId: string){
    return this.http.get(`${environment.apiUrl}/private/policy/get/policyType/${clientId}`)
  }
  
}
