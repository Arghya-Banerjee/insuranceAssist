import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { Form, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from '../../../../../../environments/environment.development';
import { Claim } from '../../../../../core/services/api/claim/claim';
import { DocumentService } from '../../../../../core/services/api/document/document';

@Component({
  selector: 'app-client-new-claim',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './client-new-claim.html',
  styleUrl: './client-new-claim.css'
})
export class ClientNewClaim {

  private fb = inject(FormBuilder);
  private claimService = inject(Claim);
  private documentService = inject(DocumentService);
  private http = inject(HttpClient);

  newClaimForm: FormGroup = new FormGroup({});

  policyDetails = {
        policyID: "",  tier: "", startDate: "", endDate: "", premium: "", remainingCoverage: ""

      }

  file: any;

  ngOnInit(){
    this.newClaimForm = this.fb.group({
         claimType: ['', Validators.required],
         procedureNotes: ['', Validators.required],
         claimAmount: [0, [Validators.required, Validators.pattern('^[0-9]+$')]]
         ,documentUpload : [null, Validators.required]
        });

      const userId = localStorage.getItem('userId');
      

      this.http.get(`${environment.apiUrl}/private/policy/get/${userId}`).subscribe({
        next: (res) => {
          this.policyDetails = res as any; 
        },
        error: (err) => {
          console.error('API is not reachable:', err);
        }
      });
  }

  onCreateClaim() {

      const claimDetails = { 
        clientId: localStorage.getItem('userId'), 
        claimType: this.newClaimForm.get('claimType')?.value,
        procedureNotes: this.newClaimForm.get('procedureNotes')?.value,
        claimAmount: this.newClaimForm.get('claimAmount')?.value
      };

      console.log(claimDetails);

      if(this.newClaimForm.valid && this.file){
        this.claimService.onCreateClaim(claimDetails).subscribe({
          next: (claimId: any) => {

            const formData = new FormData();
            formData.append('file', this.file);

            this.documentService.onDocumentUpload(formData, claimId).subscribe({
              next: (res : any) => {
                // alert('Document uploaded successfully!');
                alert('Claim created and document uploaded successfully!');
            },
              error: (err : any) => {
                console.error('Error uploading document:', err);
                alert('Failed to upload document. Please try again.');
              }
            });
          },
          error: (err: any) => {
            console.error('Error creating claim:', err);  
            alert('Failed to create claim. Please try again.');
          }
        });
      }
    }

    onDocumentUpload(event: any) {
      if (event.target.files && event.target.files.length > 0) {
        this.file = event.target.files[0];
        // ensure the reactive form knows a file was provided so validators pass
        this.newClaimForm.get('documentUpload')?.setValue(this.file);
        this.newClaimForm.get('documentUpload')?.updateValueAndValidity();
      } else {
        this.file = null;
        this.newClaimForm.get('documentUpload')?.setValue(null);
        this.newClaimForm.get('documentUpload')?.updateValueAndValidity();
      }
    }


    isClaimAmountExceedsPremium(): boolean {
      const claimAmount = this.newClaimForm?.get('claimAmount')?.value || 0;
      const remainingCoverage = this.policyDetails?.remainingCoverage || 0;
    return claimAmount > remainingCoverage;
  }
}
