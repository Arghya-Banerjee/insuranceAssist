import { Injectable,inject } from '@angular/core';
import { HttpClient,HttpRequest } from '@angular/common/http';
import { environment } from '../../../../../environments/environment.development';
import { Observable } from 'rxjs';

type Claim = {
  claimId: string;           // UUID as string
  policyId: string;          // UUID as string
  openDate: string;          // ISO 8601 format (e.g., "2025-09-16T18:03:00")
  procedureNotes: string;
  claimType: string;
  status: string;
  claimAmount: number; 
  updatedAt:string;      
};


type Agent =  {
  username: string;
  name: string;
  gender: string;
  dob: string; // ISO date string (e.g., "1990-01-01")
  address: string;
  email: string;
  phone: number;
}


@Injectable({
  providedIn: 'root'
})
export class ClaimManagement {
  private http = inject(HttpClient)
  private apiUrl = environment.apiUrl
  private Claims!:[] 
  

  getAgentDetails(agentID:string|null):Observable<Agent>{
    const url = `${this.apiUrl}/private/profile/get/${agentID}`;
    return this.http.get<Agent>(url);
  }

  getClaimsByAgentId(agentId: string | null): Observable<Claim[]> {
    const url = `${this.apiUrl}/private/claim/get/agent/${agentId}`;
    return this.http.get<Claim[]>(url);
  }
  
  updateClaimStatus(claim: Claim, updatedStatus: string) {
    if (updatedStatus === '2') {
      claim.status = "IN REVIEW";
    } else if (updatedStatus === '3') {
      claim.status = "APPROVED";
    } else if (updatedStatus === '4') {
      claim.status = "REJECTED";
    }
    claim.claimType==="PRE"
      
    // const url = `${environment.apiUrl}/private/claim/update/${claim.claimId}/${updatedStatus}`;
    const url = (claim.claimType==="PRE")?`${environment.apiUrl}/private/claim/update/${claim.claimId}/${updatedStatus}/${claim.claimAmount}`:`${environment.apiUrl}/private/claim/update/${claim.claimId}/${updatedStatus}`;
    console.log(url);
    this.http.put(url, {}).subscribe();
  }
  
}
