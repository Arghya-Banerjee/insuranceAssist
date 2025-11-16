import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { PolicyDetails } from './policy-details/policy-details';
import { DependentsDetails } from './dependents-details/dependents-details';
import { Location } from '@angular/common';
import { PersonalDetails } from './personal-details/personal-details';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, PersonalDetails, PolicyDetails, DependentsDetails],
  templateUrl: './profile.html',
  styleUrls: ['./profile.css']
})
export class Profile {
  activeTab: 'personal' | 'policy' | 'dependents' = 'personal';

  constructor(private location: Location) {}

  goBack() {
    this.location.back();
  }
}