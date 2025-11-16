import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment.development';

@Component({
  selector: 'app-agent-personal-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './personal-details.html',
  styleUrls: ['./personal-details.css']
})
export class PersonalDetails implements OnInit {
  editing = false;
  validationErrors: string[] = [];

  personal = {
    username: '',
    name: '',
    gender: '',
    dob: '',
    address: '',
    email: '',
    phone: ''
  };
  temp = { ...this.personal };

  private phoneRegex = /^[0-9]{10}$/;
  private minAddressLen = 5;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    const clientId = localStorage.getItem('userId');
    const getUrl = `${environment.apiUrl}/private/profile/get/${clientId}`;
    this.http.get<any>(getUrl).subscribe(data => {
      this.personal = data;
      this.temp = { ...data };
    });
  }

  edit() {
    this.editing = true;
    this.validationErrors = [];
    this.temp = { ...this.personal };
  }

  save() {
    // Defensive validation (in addition to template validation)
    this.validationErrors = [];
    const phone = (this.temp.phone ?? '').toString().trim();
    const address = (this.temp.address ?? '').toString().trim();

    if (!this.phoneRegex.test(phone)) {
      this.validationErrors.push('Phone number must be exactly 10 digits.');
    }
    if (!address || address.length < this.minAddressLen) {
      this.validationErrors.push(`Address should be at least ${this.minAddressLen} characters.`);
    }
    if (this.validationErrors.length) return;

    const clientId = localStorage.getItem('userId');
    const updateUrl = `${environment.apiUrl}/private/profile/update/${clientId}`;

    const payload = {
      address: address,
      phone: phone
    };

    this.http.put(updateUrl, payload).subscribe(updatedPeronalDetails => {
      Object.assign(this.personal, updatedPeronalDetails);
      this.editing = false;
    });
  }

  cancel() {
    this.editing = false;
    this.validationErrors = [];
    this.temp = { ...this.personal };
  }
}
