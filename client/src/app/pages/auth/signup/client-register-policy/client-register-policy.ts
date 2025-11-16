import { Component, inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment.development';

interface PlanDetails {
  id: number;
  name: string;
  basePremium: number;
  coverage: string;
  proceduresInsured: string;
  deductible: number;
  premiumPerDependent: number;
}

@Component({
  selector: 'app-client-register-policy',
  imports: [ReactiveFormsModule],
  templateUrl: './client-register-policy.html',
  styleUrl: './client-register-policy.css'
})
export class ClientRegisterPolicy {

  private http = inject(HttpClient);
  private router = inject(Router);
  private fb = inject(FormBuilder);

  choosePlanForm: FormGroup = this.fb.group({
    plan: ['', Validators.required]
  });

  plans: PlanDetails[] = [
    { id: 1, name: 'Gold', basePremium: 12500, coverage: '₹10,00,000', proceduresInsured: 'Regular healthcare users', deductible: 4000, premiumPerDependent: 3100 },
    { id: 2, name: 'Silver', basePremium: 9200, coverage: '₹5,00,000', proceduresInsured: 'Families with occasional care', deductible: 6000, premiumPerDependent: 2300 },
    { id: 3, name: 'Bronze', basePremium: 7500, coverage: '₹2,50,000', proceduresInsured: 'Young, healthy individuals', deductible: 8000, premiumPerDependent: 700 },
    { id: 4, name: 'Basic', basePremium: 6800, coverage: '₹1,00,00', proceduresInsured: 'General Consultation', deductible: 10000, premiumPerDependent: 1700 },
    { id: 5, name: 'Platinum', basePremium: 14000, coverage: '₹20,00,000', proceduresInsured: 'Chronic conditions, seniors', deductible: 2000, premiumPerDependent: 3700 }
  ];

  selectedPlan: PlanDetails | undefined;
  numDependents: number = 0;
  totalPremium: number = 0;

  // Toast & modal state
  showToast = false;
  toastMessage = '';
  toastTitle = '';
  toastType: 'success' | 'error' | 'info' = 'info';
  toastTimeoutHandle: any;

  showModal = false;
  modalTitle = '';
  modalMessage = '';
  private modalAction: (() => void) | null = null;

  ngOnInit() {
    const dependents = JSON.parse(localStorage.getItem('dependents') || '[]');
    this.numDependents = Array.isArray(dependents) ? dependents.length : 0;

    this.choosePlanForm.get('plan')?.valueChanges.subscribe(planName => {
      this.selectedPlan = this.plans.find(p => p.name === planName);
      this.calculateTotalPremium();
    });
  }

  calculateTotalPremium() {
    if (this.selectedPlan) {
      this.totalPremium = this.selectedPlan.basePremium +
        (this.selectedPlan.premiumPerDependent * this.numDependents);
    } else {
      this.totalPremium = 0;
    }
  }

  // Simple toast helper
  showToastMessage(title: string, message: string, type: 'success' | 'error' | 'info' = 'info', duration = 4000) {
    clearTimeout(this.toastTimeoutHandle);
    this.toastTitle = title;
    this.toastMessage = message;
    this.toastType = type;
    this.showToast = true;
    // auto-dismiss
    this.toastTimeoutHandle = setTimeout(() => this.showToast = false, duration);
  }

  dismissToast() {
    clearTimeout(this.toastTimeoutHandle);
    this.showToast = false;
  }

  // modal helper
  showModalDialog(title: string, message: string, onConfirm?: () => void) {
    this.modalTitle = title;
    this.modalMessage = message;
    this.modalAction = onConfirm || null;
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
    this.modalAction = null;
  }

  modalConfirm() {
    this.showModal = false;
    if (this.modalAction) {
      try { this.modalAction(); } catch (e) { console.warn(e); }
      this.modalAction = null;
    }
  }

  // MAIN submit flow (I replaced alert(...) calls with toasts/modals)
  onSubmit() {
    if (this.choosePlanForm.valid && this.selectedPlan) {
      const signupDetails = JSON.parse(localStorage.getItem('signupDetails') || '{}');
      const dependents = JSON.parse(localStorage.getItem('dependents') || '[]');

      // Register client
      this.http.post<{ client_id: number }>(`${environment.apiUrl}/public/register`, { ...signupDetails }).subscribe({
        next: (registerRes) => {
          // Auto-login
          this.http.post(`${environment.apiUrl}/public/login`, {
            username: signupDetails.email.replace(/[@]|.com/g, ""),
            password: signupDetails.password
          }).subscribe({
            next: (loginRes: any) => {
              if (loginRes && loginRes.token) {
                localStorage.setItem('token', loginRes.token);
                localStorage.setItem('role', loginRes.role);
                localStorage.setItem('userId', loginRes.userId);

                const clientId = localStorage.getItem('userId');
                const today = new Date();
                const startDate = today.toISOString().split('T')[0]; // yyyy-mm-dd
                const endDate = new Date(today.getFullYear() + 1, today.getMonth(), today.getDate()).toISOString().split('T')[0];
                const policyType = this.selectedPlan!.id;

                // Create policy
                this.http.post(`${environment.apiUrl}/private/policy/create`, {
                  clientId,
                  policyType,
                  startDate,
                  endDate,
                  noOfDependents: this.numDependents
                }).subscribe({
                  next: () => {
                    // Create dependents sequentially / or parallel — track completion
                    let completed = 0;
                    let failed = false;

                    if (Array.isArray(dependents) && dependents.length > 0) {
                      dependents.forEach((dep: any) => {
                        const dependentPayload = {
                          name: dep.name,
                          dob: dep.dob,
                          phone: dep.contact,
                          address: dep.address,
                          relationTypeId: dep.relationTypeId,
                          gender: dep.gender,
                          email: dep.email,
                          clientId: clientId
                        };

                        this.http.post(`${environment.apiUrl}/private/dependent/create`, dependentPayload)
                          .subscribe({
                            next: () => {
                              completed++;
                              // when all done, show success toast and navigate
                              if (completed === dependents.length && !failed) {
                                this.showToastMessage('Success', 'Registration and policy created successfully.', 'success', 5000);
                                // navigate after short delay to let toast be seen
                                setTimeout(() => this.router.navigate(['/auth']), 1400);
                              }
                            },
                            error: () => {
                              if (!failed) {
                                failed = true;
                                this.showToastMessage('Error', 'One or more dependents failed to be created.', 'error', 6000);
                              }
                            }
                          });
                      });
                    } else {
                      // no dependents - success immediate
                      this.showToastMessage('Success', 'Registration and policy created successfully.', 'success', 5000);
                      setTimeout(() => this.router.navigate(['/auth']), 1400);
                    }
                  },
                  error: () => {
                    this.showModalDialog('Policy error', 'Policy creation failed. Please try again later.', () => {
                      // optional confirm action
                      this.router.navigate(['/auth']);
                    });
                  }
                });
              } else {
                this.showToastMessage('Login failed', 'Auto-login after registration failed.', 'error', 5000);
              }
            },
            error: () => {
              this.showToastMessage('Login failed', 'Auto-login after registration failed.', 'error', 5000);
            }
          });
        },
        error: () => {
          this.showModalDialog('Registration failed', 'Registration failed. Please check your details and try again.', () => {
            // user confirmed - stay on page or navigate
          });
        }
      });

      // remove immediate router.navigate(['/auth']) here — navigation happens after success above
    }
  }

  goBack() {
    this.router.navigate(['/auth/dependents-nomination']);
  }
}
