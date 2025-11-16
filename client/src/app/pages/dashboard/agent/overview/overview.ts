import { Component, inject, OnInit, Pipe } from '@angular/core';
import { environment } from '../../../../../environments/environment.development';
import { ClaimManagement } from '../../../../core/services/api/AgentDashBoard/claim-management';
import { CommonModule, DatePipe } from '@angular/common';
import { UrlSegment } from '@angular/router';

type Agent = {
  username: string;
  name: string;
  gender: string;
  dob: string;
  address: string;
  email: string;
  phone: number;
}

type Claim = {
  claimId: string;
  policyId: string;
  openDate: string;
  procedureNotes: string;
  claimType: string;
  status: string;
  claimAmount: number;
  updatedAt: string;
};



@Component({
  selector: 'app-overview',
  imports: [DatePipe, CommonModule],
  templateUrl: './overview.html',
  styleUrl: './overview.css'
})
export class Overview implements OnInit {

  private profileService: ClaimManagement = inject(ClaimManagement);
  private userId!: string | null;
  agentDetails!: Agent;
  totalClients!: Number;
  Claims!: Claim[];
  activePoliciesNo!: Number;
  activeClients!: Number;
  totalAmountClaimPassed!: Number;
  latestClaims!: Claim[]

  ngOnInit(): void {
    this.userId = localStorage.getItem('userId');
    this.profileService.getAgentDetails(this.userId).subscribe({
      next: (element) => {
        this.agentDetails = element;
        console.log(this.agentDetails)
      },
      error: (element) => {
        console.log("error occurred");
      }
    }
    );

    this.profileService.getClaimsByAgentId(this.userId).subscribe({
      next: (element) => {
        this.Claims = element;
        this.totalClients = this.Claims.length;
        // console.log(this.totalClients);
        const activeArrayList:Claim[] = this.Claims.filter((el) => (
          el.status == 'IN REVIEW' || el.status === 'CREATED'
    ));
        this.activeClients = activeArrayList.length;
        console.log(this.activeClients)
        this.totalAmountClaimPassed = this.Claims
          .filter((el: Claim) => el.status === 'APPROVED')
          .reduce((sum, el) => sum + el.claimAmount, 0);
        this.latestClaims = this.Claims
          .sort((a: Claim, b: Claim) =>
            new Date(b.updatedAt).getTime() - new Date(a.updatedAt).getTime()
          )
          .slice(0, 3);
      }
    })
  }

}
