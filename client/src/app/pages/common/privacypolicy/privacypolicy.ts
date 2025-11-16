import { Component, inject } from '@angular/core';
import { CommonModule, Location } from '@angular/common';

@Component({
  selector: 'app-privacypolicy',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './privacypolicy.html',
  styleUrls: ['./privacypolicy.css']
})

export class Privacypolicy {
  location = inject(Location);

  accept() {
    this.location.back();
  }

}
