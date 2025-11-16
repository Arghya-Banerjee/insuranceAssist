import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { HospitalService } from '../../../../../core/services/api/hospital/hospital-service';
import { FormsModule } from '@angular/forms';
import { ClientDashboard } from '../../client-dashboard';

@Component({
  selector: 'app-network-hospitals',
  imports: [CommonModule, FormsModule],
  templateUrl: './network-hospitals.html',
  styleUrl: './network-hospitals.css'
})
export class NetworkHospitals {

  private hospitalService = inject(HospitalService);
  private x = inject(ClientDashboard);
  
  searchHospital: string = '';
  filteredHospitalList: any[] = [];
  ratingButtonClass: string = 'btn btn-outline-dark rounded-pill px-3 py-2';
  ascendingButtonClass: string = 'btn btn-outline-dark rounded-pill px-3 py-2';
  descendingButtonClass: string = 'btn btn-outline-dark rounded-pill px-3 py-2';
  networkButtonClass: string = 'btn btn-outline-success rounded-pill px-3 py-2';
  
  ascSort: boolean = false;
  dscSort: boolean = false;
  sortByRating: boolean = false;
  showNetworkOnly: boolean = false;

  ngOnInit(): void {
    this.filteredHospitalList = this.x.hospitalList;
  }

  searchHospitals(): void {
    if (!this.searchHospital) {
      this.filteredHospitalList = this.x.hospitalList;
      return;
    }

    const serachValue = this.searchHospital.toLowerCase();
    this.filteredHospitalList = this.x.hospitalList.filter(hospital => 
      hospital.name.toLowerCase().includes(serachValue)
    );
  }

  onRatingButtonPressed(): void {
    this.ascSort = false;
    this.dscSort = false;
    this.sortByRating = !this.sortByRating;
    this.ratingButtonClass = this.sortByRating 
      ? 'btn btn-secondary rounded-pill px-3 py-2' 
      : 'btn btn-outline-dark rounded-pill px-3 py-2';
  }

  onAscendingButtonPressed(): void {
    this.ascSort = true;
    this.dscSort = false;
    this.ascendingButtonClass = this.ascSort 
      ? 'btn btn-outline-dark rounded-pill px-3 py-2' 
      : 'btn btn-secondary rounded-pill px-3 py-2';
    if (this.sortByRating) {
      this.filteredHospitalList.sort((a,b) => a.rating - b.rating);
    } else {
      this.filteredHospitalList.sort((a,b) => b.name < a.name ? 1 : -1);
    }
  }

  onDescendingButtonPressed(): void {
    this.dscSort = true;
    this.ascSort = false;
    if (this.sortByRating) {
      this.filteredHospitalList.sort((a,b) => b.rating - a.rating);
    } else {
      this.filteredHospitalList.sort((a,b) => a.name < b.name ? 1 : -1);
    }
  }

  // ✅ Toggle filter for network hospitals
  filterNetworkHospitals(): void {
    this.showNetworkOnly = !this.showNetworkOnly;

    if (this.showNetworkOnly) {
      this.filteredHospitalList = this.x.hospitalList.filter(hospital => hospital.network == 1);
      this.networkButtonClass = 'btn btn-success text-white rounded-pill px-3 py-2';
    } else {
      this.filteredHospitalList = this.x.hospitalList;
      this.networkButtonClass = 'btn btn-outline-success rounded-pill px-3 py-2';
    }
  }

}
