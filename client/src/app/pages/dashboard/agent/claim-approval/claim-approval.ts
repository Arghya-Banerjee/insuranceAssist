import { HttpClient, HttpRequest } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { OnInit } from '@angular/core';
import { environment } from '../../../../../environments/environment.development';
import { CommonModule, DatePipe } from '@angular/common';
import { ClaimManagement } from '../../../../core/services/api/AgentDashBoard/claim-management';
import { FormsModule } from '@angular/forms';
import { DocumentService } from '../../../../core/services/api/document/document';

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

@Component({
  selector: 'app-claim-approval',
  standalone: true, 
  imports: [DatePipe,CommonModule, FormsModule],       
  templateUrl: './claim-approval.html',
  styleUrls: ['./claim-approval.css']
})


export class ClaimApproval implements OnInit{

  Claims:any[] = []

  private service = inject(ClaimManagement)
  private agentId:any
  private DocumentService = inject(DocumentService);

  ngOnInit(): void {
  this.agentId = localStorage.getItem('userId')
  this.service.getClaimsByAgentId(this.agentId).subscribe({
      next: (claims: Claim[]) => {
        this.Claims = claims;
        this.Claims = this.Claims.filter((claim) => {
          return (claim.status == "CREATED" || claim.status == "IN REVIEW");
      })
        console.log("Claims loaded:", this.Claims);
      },
      error: (err) => {
        console.error("Failed to load claims:", err);
      }
    });

  }

  ConfirmAction(claim:Claim,status:string){

    console.log(this.Claims)
    this.service.updateClaimStatus(claim,status)
  }

  onDownloadDocument(claimId: string) {
    this.DocumentService.onDocumentDownload(claimId).subscribe({
        next: (response: any) => {

            console.log(response[0].data);
            const byteCharacters = atob(response[0].data);
            const byteNumbers = new Array(byteCharacters.length);

            for (let i = 0; i < byteCharacters.length; i++) {
              byteNumbers[i] = byteCharacters.charCodeAt(i);
            }

            const byteArray = new Uint8Array(byteNumbers);

            const blob = new Blob([byteArray], { type: response[0].type });
            console.log(blob);
            const url = window.URL.createObjectURL(blob);
            console.log(url);
            window.open(url);

            const a = document.createElement('a');
            a.href = url;
            a.download = `claim_${claimId}_document.pdf`;
            document.body.appendChild(a);
            a.click();
            // window.URL.revokeObjectURL(url);
            // document.body.removeChild(a);
            // console.log(response);
        },
        error: (err: any) => {
            console.error('Document download failed:', err);  
            alert('Failed to download document. Please try again later.');
        }
    });
  }

  
}

