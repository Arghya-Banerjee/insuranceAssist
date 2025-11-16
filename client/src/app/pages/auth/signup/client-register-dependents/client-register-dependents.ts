import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormArray, FormBuilder, ReactiveFormsModule, Validators, FormGroup, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-register-dependents',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './client-register-dependents.html',
  styleUrls: ['./client-register-dependents.css']
})
export class ClientRegisterDependents implements OnInit {
  registerDependentForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) {
    this.registerDependentForm = this.fb.group({
      dependents: this.fb.array([])
    });

    // initialize with one empty dependent (will be replaced in ngOnInit if saved data exists)
    this.addDependent();
  }

  ngOnInit(): void {
    // Load saved dependents from localStorage (if any) and rebuild the FormArray
    const saved = localStorage.getItem('dependents');
    if (saved) {
      try {
        const list = JSON.parse(saved);
        if (Array.isArray(list) && list.length > 0) {
          // clear existing default entry(s)
          this.dependents.clear();
          list.forEach((d: any) => {
            this.dependents.push(this.fb.group({
              name: [d.name || '', Validators.required],
              dob: [d.dob || '', Validators.required],
              contact: [d.contact || '', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
              address: [d.address || '', Validators.required],
              gender: [d.gender || '', Validators.required],
              relationTypeId: [d.relationTypeId || '', Validators.required],
              email: [d.email || '', [Validators.required, Validators.email]]
            }));
          });
        }
      } catch (err) {
        // If parse fails, keep the default single empty dependent
        console.warn('Failed to parse saved dependents:', err);
      }
    }

    // Ensure at least one dependent exists
    if (this.dependents.length === 0) {
      this.addDependent();
    }

    // Auto-save dependents to localStorage on any change (debounce not required for small forms)
    this.registerDependentForm.get('dependents')?.valueChanges?.subscribe((val) => {
      try {
        localStorage.setItem('dependents', JSON.stringify(val));
      } catch (e) {
        console.warn('Failed to persist dependents to localStorage', e);
      }
    });
  }

  // typed getter
  get dependents(): FormArray {
    return this.registerDependentForm.get('dependents') as FormArray;
  }

  addDependent(): void {
    if (this.dependents.length >= 4) return; // enforce max 4
    this.dependents.push(this.fb.group({
      name: ['', Validators.required],
      dob: ['', Validators.required],
      contact: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      address: ['', Validators.required],
      gender: ['', Validators.required],
      relationTypeId: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    }));
  }

  removeDependent(index: number): void {
    if (this.dependents.length > 1) {
      this.dependents.removeAt(index);
    }
  }

  nextStep(): void {
    console.log('workin');
    if (this.registerDependentForm.valid) {
      const dependentsList = this.registerDependentForm.value.dependents;
      try {
        localStorage.setItem('dependents', JSON.stringify(dependentsList));
      } catch (e) {
        console.warn('Failed to persist dependents to localStorage', e);
      }
      this.router.navigate(['/auth/policy-plan']);
    } else {
      // mark touched so validation messages show (if you add them)
      this.markAllDependentsTouched();
    }
  }

  skipDependents(): void {
    localStorage.setItem('dependents', JSON.stringify([]));
    this.router.navigate(['/auth/policy-plan']);
  }

  // helper to mark all controls touched when user tries to continue with invalid form
  private markAllDependentsTouched(): void {
    this.dependents.controls.forEach((ctrl: AbstractControl) => {
      if (ctrl instanceof FormGroup) {
        Object.keys(ctrl.controls).forEach(key => ctrl.get(key)?.markAsTouched());
      } else {
        ctrl.markAsTouched();
      }
    });
  }
}
