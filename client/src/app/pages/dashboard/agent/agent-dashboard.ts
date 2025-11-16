import { Component, inject } from '@angular/core';
import { Overview } from './overview/overview';
import { ClaimApproval } from "./claim-approval/claim-approval";
import { ClaimHistory } from './claim-history/claim-history';
import { HttpClient } from '@angular/common/http';
import { HospitalService } from '../../../core/services/api/hospital/hospital-service';
import { AgentNetworkHospitals } from './agent-network-hospitals/agent-network-hospitals';

@Component({
  selector: 'app-agent',
  imports: [Overview, ClaimApproval,ClaimHistory, AgentNetworkHospitals],
  templateUrl: './agent.html',
  styleUrl: './agent.css'
})
export class AgentDashboard {
  activeTab: 'overview' | 'claim-approval' | 'claim-history' | 'network-hospitals' = 'overview';
  setTab(tab: 'overview' | 'claim-approval' | 'claim-history' | 'network-hospitals') {
    this.activeTab = tab;
  }

  http = inject(HttpClient);

  private hospitalService = inject(HospitalService);
    
  hospitalList: any[] = [];

  
  ngOnInit(): void{
    this.hospitalService.onGetAllHospitals().subscribe({
      next: (result: any) => {
        this.hospitalList = result;
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
    
    
  }


}
