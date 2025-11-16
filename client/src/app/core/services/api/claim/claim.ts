import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class Claim {

  private http = inject(HttpClient);

  onGetClaimHistory(clientId: string) : any {
    return this.http.get(`${environment.apiUrl}/private/claim/get/client/${clientId}`)
  }

  onCreateClaim(claimDetails: any) : any {
    return this.http.post(`${environment.apiUrl}/private/claim/create`, claimDetails)
  }


}
