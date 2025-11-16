import { Component, inject, OnInit } from '@angular/core';
import { ClaimManagement } from '../../../../core/services/api/AgentDashBoard/claim-management';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';




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
  selector: 'app-claim-history',
  imports: [CommonModule,DatePipe, FormsModule],
  templateUrl: './claim-history.html',
  styleUrl: './claim-history.css'
})

export class ClaimHistory implements OnInit{

  resultDefault: any[] = [];
  result: any[] = [];

  filterStatusList: string[] = [];
  filterTypeList: string[] = [];
  activeStatusList: string[] = [];
  activeTypeList: string[] = [];
  filterStartDate?: string;
  filterEndDate?: string;
  filterSearchTerm: string = '';

  today: string = new Date().toISOString().split('T')[0];

  // Claims:any[] = []
  private service = inject(ClaimManagement)
  agentId!:string | null
  ngOnInit(): void {
  this.agentId = localStorage.getItem('userId');
  this.service.getClaimsByAgentId(this.agentId).subscribe({
    next: (claims: Claim[]) => {
      this.result = claims.sort((a, b) => {
        const dateA = new Date(a.openDate).getTime();
        const dateB = new Date(b.openDate).getTime();

        if (dateA !== dateB) {
          return dateA - dateB;
        }

        return a.procedureNotes.localeCompare(b.procedureNotes);
      });

      this.resultDefault = [...this.result];
      console.log("Claims loaded:", this.result);
    },
    error: (err) => {
      console.error("Failed to load claims:", err);
    }
  });
}

filterByStatus(status: string){
    if(this.filterStatusList.includes(status)){
      let ind = this.filterStatusList.indexOf(status);
      this.filterStatusList.splice(ind, 1);
      ind = this.activeStatusList.indexOf(status);
      this.activeStatusList.splice(ind, 1);
    }
    else{
      this.filterStatusList.push(status);
      this.activeStatusList.push(status);
    }
    this.applyFilters();
  }

  filterByType(type: string){
    if(this.filterTypeList.includes(type)){
      let ind = this.filterTypeList.indexOf(type);
      this.filterTypeList.splice(ind, 1);
      ind = this.activeTypeList.indexOf(type);
      this.activeTypeList.splice(ind, 1);
    }
    else{
      this.filterTypeList.push(type);
      this.activeTypeList.push(type);
    }
    this.applyFilters();
  }

  applyFilters() {
    this.result = this.resultDefault.filter(claim => {

      const isStatusCorrect = this.filterStatusList?.length
        ? this.filterStatusList.includes(claim.status)
        : true;

      const isTypeCorrect = this.filterTypeList?.length
        ? this.filterTypeList.includes(claim.claimType)
        : true;

      const openISO = (claim.openDate ?? '').slice(0, 10);

      let matchesDate = true;
      if (this.filterStartDate) {
        matchesDate = openISO >= this.filterStartDate;
      }
      if (matchesDate && this.filterEndDate) {
        matchesDate = openISO <= this.filterEndDate;
      }

      const matchesSearch = this.filterSearchTerm
        ? (claim.procedureNotes ?? '')
            .toString()
            .toLowerCase()
            .includes(this.filterSearchTerm.toLowerCase())
        : true;

      return isStatusCorrect && isTypeCorrect && matchesDate && matchesSearch;
    });
  }

  resetFilters(){
    this.filterStatusList = [];
    this.filterTypeList = [];
    this.activeStatusList = [];
    this.activeTypeList = [];
    this.filterStartDate = undefined;
    this.filterEndDate = undefined;
    this.result = this.resultDefault;
  }

}