import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { PersonalDetails } from './personal-details/personal-details';

@Component({
  selector: 'app-agent-profile',
  standalone: true,
  imports: [CommonModule, PersonalDetails],
  templateUrl: './agent-profile.html',
  styleUrls: ['./agent-profile.css']
})
export class AgentProfile {
  activeTab: 'personal' = 'personal';

  constructor(private location: Location) {}

  goBack() {
    this.location.back();
  }
}
