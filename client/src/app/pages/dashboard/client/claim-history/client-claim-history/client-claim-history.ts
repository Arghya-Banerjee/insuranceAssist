import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Claim } from '../../../../../core/services/api/claim/claim';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-client-claim-history',
  imports: [CommonModule, FormsModule],
  templateUrl: './client-claim-history.html',
  styleUrl: './client-claim-history.css'
})

export class ClientClaimHistory {

  private claimService = inject(Claim);
  private clientId: any = localStorage.getItem('userId');

  ngOnInit(){
    this.onClaimHistory();
  }

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

  onClaimHistory(){
    this.claimService.onGetClaimHistory(this.clientId).subscribe({
      next: (result:any) => {
        this.result = result;
        this.resultDefault = result;
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

  applyFilters(){
    this.result = this.resultDefault.filter(claim => {

      const isStatusCorrect = this.filterStatusList && this.filterStatusList.length > 0
        ? this.filterStatusList.includes(claim.status)
        : true;

      const isTypeCorrect = this.filterTypeList && this.filterTypeList.length > 0
        ? this.filterTypeList.includes(claim.claimType) 
        : true;

      let matchesDate = true;
      if(this.filterStartDate){
        matchesDate = new Date(claim.openDate) >= new Date(this.filterStartDate);
      }

      if(matchesDate && this.filterEndDate){
        matchesDate = new Date(claim.openDate) <= new Date(this.filterEndDate);
      }

      const matchesSearch = this.filterSearchTerm
      ? claim.procedureNotes &&
        claim.procedureNotes.toString().toLowerCase()
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
