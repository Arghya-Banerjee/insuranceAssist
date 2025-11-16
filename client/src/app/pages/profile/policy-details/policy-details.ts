import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment.development';

@Component({
  selector: 'app-policy-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './policy-details.html',
  styleUrls: ['./policy-details.css']
})
export class PolicyDetails implements OnInit {
  policy: any;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    // const clientId = '95df5a86-fca9-41f0-bf81-3d64a2811f8f'; // Temp Value
    const clientId = localStorage.getItem('userId');
    const url = `${environment.apiUrl}/private/policy/get/${clientId}`;
    this.http.get(url).subscribe(data => {
      this.policy = data;
    });
  }
}