import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment.development';

interface Dependent {
  id?: any;
  name: any;
  dob: any;
  phone: any;
  address: any;
  relationName: any;
  gender: any;
  email: any;
  editing?: boolean;
  temp?: Partial<Dependent>;
  isNew?: boolean;
  validationErrors?: string[];
}

@Component({
  selector: 'app-dependents-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dependents-details.html',
  styleUrls: ['./dependents-details.css']
})
export class DependentsDetails implements OnInit {
  dependents: Dependent[] = [];

  readonly maxDependents = 4;

  private phoneRegex = /^[0-9]{10}$/;
  private emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
  private minNameLen = 2;
  private minAddressLen = 5;

  relationOptions = [
    { label: 'Father', value: 1 },
    { label: 'Mother', value: 2 },
    { label: 'Brother', value: 3 },
    { label: 'Sister', value: 4 },
    { label: 'Wife', value: 5 },
    { label: 'Husband', value: 6 },
    { label: 'Son', value: 7 },
    { label: 'Daughter', value: 8 },
    { label: 'Gaurdian', value: 9 },
  ];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadDependents();
  }

  /** Whether user can add another dependent (UI + TS guard use this) */
  canAddDependent(): boolean {
    return (this.dependents?.length || 0) < this.maxDependents;
  }

  addDependent() {
    if (!this.canAddDependent()) {
      alert(`You can add a maximum of ${this.maxDependents} dependents.`);
      return;
    }
    this.dependents.push({
      name: '',
      dob: '',                    // Will be edited via <input type="date">
      phone: '',
      address: '',
      relationName: '',
      gender: '',                 // For NEW: dropdown "M" / "F"
      email: '',
      editing: true,
      isNew: true,
      validationErrors: [],
      temp: {
        name: '',
        dob: '',
        phone: '',
        address: '',
        relationName: '',
        gender: '',               // For NEW: user picks "M" or "F"
        email: ''
      }
    });
  }

  edit(dep: Dependent) {
    dep.editing = true;
    dep.validationErrors = [];
    dep.temp = { ...dep };
  }

  save(dep: Dependent) {
    if (!this.validateTemp(dep)) {
      dep.validationErrors = dep.validationErrors?.length
        ? dep.validationErrors
        : ['Please fix the highlighted fields and try again.'];
      return;
    }

    const clientId = localStorage.getItem('userId');

    if (dep.isNew) {
      const createUrl = `${environment.apiUrl}/private/dependent/create`;
      const payload = {
        name: dep.temp?.name?.toString().trim(),
        dob: dep.temp?.dob, // ISO yyyy-MM-dd coming from <input type="date">
        phone: dep.temp?.phone?.toString().trim(),
        address: dep.temp?.address?.toString().trim(),
        relationTypeId: dep.temp?.relationName,
        gender: dep.temp?.gender, // "M" or "F" for new
        email: dep.temp?.email?.toString().trim(),
        clientId: clientId
      };

      this.http.post<Dependent>(createUrl, payload).subscribe({
        next: () => this.loadDependents(),
        error: (err) => {
          console.error(err);
          dep.validationErrors = ['Failed to create dependent. Please try again.'];
        }
      });
    } else {
      const updateUrl = `${environment.apiUrl}/private/dependent/update/${dep.id}`;
      const payload = {
        name: dep.temp?.name?.toString().trim(),
        dob: dep.temp?.dob,                         // still a date string if edited
        phone: dep.temp?.phone?.toString().trim(),
        address: dep.temp?.address?.toString().trim(),
        gender: dep.temp?.gender,                   // leave as-is for existing
        email: dep.temp?.email?.toString().trim()
      };

      this.http.put<Dependent>(updateUrl, payload).subscribe({
        next: (updatedDep) => {
          Object.assign(dep, updatedDep);
          dep.editing = false;
          dep.validationErrors = [];
        },
        error: (err) => {
          console.error(err);
          dep.validationErrors = ['Failed to update dependent. Please try again.'];
        }
      });
    }
  }

  cancel(dep: Dependent) {
    if (dep.isNew) {
      this.dependents = this.dependents.filter(d => d !== dep);
    } else {
      dep.editing = false;
      dep.validationErrors = [];
    }
  }

  delete(dep: Dependent) {
    const dependentId = dep.id;
    const deleteUrl = `${environment.apiUrl}/private/dependent/delete/${dependentId}`;
    this.http.delete(deleteUrl, { responseType: 'text' }).subscribe({
      next: () => this.loadDependents(),
      error: (err) => console.error(err)
    });
  }

  loadDependents() {
    const clientId = localStorage.getItem('userId');
    const getUrl = `${environment.apiUrl}/private/dependent/get/${clientId}`;
    this.http.get<Dependent[]>(getUrl).subscribe({
      next: (data) => {
        // Normalize incoming list, ensure editing=false/isNew=false
        this.dependents = (data || []).map((d) => ({
          ...d,
          editing: false,
          isNew: false,
          validationErrors: []
        }));
      },
      error: (err) => console.error(err)
    });
  }

  private validateTemp(dep: Dependent): boolean {
    const t = dep.temp || {};
    const errors: string[] = [];

    const name = (t.name ?? '').toString().trim();
    const phone = (t.phone ?? '').toString().trim();
    const address = (t.address ?? '').toString().trim();
    const email = (t.email ?? '').toString().trim();

    if (!name || name.length < this.minNameLen) {
      errors.push(`Name should be at least ${this.minNameLen} characters.`);
    }
    if (!this.phoneRegex.test(phone)) {
      errors.push('Phone number must be exactly 10 digits.');
    }
    if (!address || address.length < this.minAddressLen) {
      errors.push(`Address should be at least ${this.minAddressLen} characters.`);
    }
    if (!email || !this.emailRegex.test(email)) {
      errors.push('Enter a valid email address.');
    }

    dep.validationErrors = errors;
    return errors.length === 0;
  }
}
