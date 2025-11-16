import { Component, inject } from '@angular/core';
import { AgentDashboard } from '../agent-dashboard';
import { HospitalService } from '../../../../core/services/api/hospital/hospital-service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-agent-network-hospitals',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './agent-network-hospitals.html',
  styleUrl: './agent-network-hospitals.css'
})
export class AgentNetworkHospitals {
  
  hospitalForm: FormGroup = new FormGroup({});
  editMode: boolean = false;
  editingHospitalId: any = null;

  
  private hospitalService = inject(HospitalService);
  private x = inject(AgentDashboard);
  private fb = inject(FormBuilder);
  
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
    // this.filteredHospitalList = this.x.hospitalList;
    this.loadHospitals();
    this.hospitalForm = this.fb.group({
      name: ['', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]],
      clientContactNumber: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
      clientContactEmail: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required],
      rating: [0, [Validators.required, Validators.min(0), Validators.max(5)]],
      network: [1, Validators.required]
    });
  }

  private loadHospitals(): void {
    this.hospitalService.onGetAllHospitals().subscribe({
      next: (result: any[]) => {
        this.x.hospitalList = result;
        this.filteredHospitalList = result;
      }
    });
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

  
  onHospitalFormSubmit() {

    if (this.hospitalForm.invalid) {
      // Mark all controls as touched so errors show
      this.hospitalForm.markAllAsTouched();
      return;
    }

    const hospitalData = this.hospitalForm.value;
    if (this.editMode) {
      debugger
      // Edit hospital API call
      this.hospitalService.onUpdateHospital(hospitalData, this.editingHospitalId).subscribe({
        next: () => {
          // const idx = this.filteredHospitalList.findIndex(h => h.id === this.editingHospitalId);
          // if (idx > -1) this.filteredHospitalList[idx] = { ...hospitalData, id: this.editingHospitalId };
          this.loadHospitals();
          this.editMode = false;
          this.editingHospitalId = null;
          this.hospitalForm.reset({ network: 1, rating: 0 });
        }
      });
    } else {
      debugger
      // Add hospital API call
      this.hospitalService.onCreateHospital(hospitalData).subscribe({
        next: () => {
          // this.filteredHospitalList.push(newHospital);
          this.loadHospitals();
          this.hospitalForm.reset({ network: 1, rating: 0 });
        }
      });
    }
  }

  editHospital(hospital: any) {
    console.log(hospital);
    this.editMode = true;
    this.editingHospitalId = hospital.id;
    this.hospitalForm.patchValue(hospital);
    console.log(this.hospitalForm);

    
  }

  cancelEdit() {
    this.editMode = false;
    this.editingHospitalId = null;
    this.hospitalForm.reset({ network: 1, rating: 0 });
  }

  deleteHospital(hospital: any) {
    if (confirm(`Are you sure you want to delete ${hospital.name}?`)) {
      this.hospitalService.onDeleteHospital(hospital.id).subscribe({
        next: () => {
          // this.filteredHospitalList = this.filteredHospitalList.filter(h => h.id !== hospital.id);
          this.loadHospitals();
        }
      });
    }
  }
}
