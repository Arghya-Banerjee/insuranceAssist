import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './signup.html',
  styleUrls: ['./signup.css']
})
export class Signup implements OnInit {

  private router = inject(Router);
  private fb = inject(FormBuilder);

  signupForm: FormGroup = new FormGroup({});
  maxDate: string = "";
  step = 1; // wizard step (1..3)

  ngOnInit(): void {
    const saved = localStorage.getItem('signupDetails');
    const parsed = saved ? JSON.parse(saved) : [];

    const today = new Date();
    today.setFullYear(today.getFullYear() - 3); // subtract 3 years
    this.maxDate = today.toISOString().split('T')[0]; // format yyyy-MM-dd

    this.signupForm = this.fb.group({
      name: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]+$')]],
      // username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      confirmEmail: ['', [Validators.required, Validators.email]],
      dob: ['', Validators.required],
      gender: ['', Validators.required],
      address: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('^[6-9]{1}[0-9]{9}$')]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]],
    }, {
      validators: [
        matchFieldsValidator('email', 'confirmEmail', 'emailMismatch'),
        matchFieldsValidator('password', 'confirmPassword', 'passwordMismatch')
      ]
    });

    // Optional: if you want to prefill from saved state, uncomment
    if (parsed) { this.signupForm.patchValue(parsed); }

    // Optional: persist on change
    this.signupForm.valueChanges.subscribe(val => {
      localStorage.setItem('signupDetails', JSON.stringify(val));
    });
  }

  // Submit handler wired in template via (ngSubmit)
  onSignup(): void {
    if (this.signupForm.valid) {
      localStorage.setItem('signupDetails', JSON.stringify(this.signupForm.value));
      this.router.navigateByUrl('/auth/dependents-nomination');
    } else {
      this.signupForm.markAllAsTouched();
      const firstInvalidStep = this.findFirstInvalidStep();
      if (firstInvalidStep) this.step = firstInvalidStep;
    }
  }

  // Wizard navigation
  nextStep(): void {
    if (this.isStepValid(this.step)) {
      this.step = Math.min(3, this.step + 1);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    } else {
      this.markStepControlsTouched(this.step);
    }
  }

  prevStep(): void {
    this.step = Math.max(1, this.step - 1);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  // Validation helpers
  isStepValid(step: number): boolean {
    const mapping: Record<number, string[]> = {
      1: ['name', 'dob', 'gender', 'phone'],
      2: ['email', 'confirmEmail', 'address'],
      3: ['password', 'confirmPassword']
    };
    const controls = mapping[step] || [];
    return controls.every(ctrlName => {
      const c = this.signupForm.get(ctrlName);
      return !!c && c.valid;
    });
  }

  markStepControlsTouched(step: number): void {
    const mapping: Record<number, string[]> = {
      1: ['name', 'dob', 'gender', 'phone'],
      2: ['email', 'confirmEmail', 'address'],
      3: ['password', 'confirmPassword']
    };
    const controls = mapping[step] || [];
    controls.forEach(name => {
      const c = this.signupForm.get(name);
      if (c) c.markAsTouched();
    });
  }

  // Find first step that contains an invalid control — used to show user where to fix
  private findFirstInvalidStep(): number | null {
    for (let s = 1; s <= 3; s++) {
      if (!this.isStepValid(s)) return s;
    }
    return null;
  }
}

/** Cross-field validator that sets an error key on the form group when fields mismatch */
export function matchFieldsValidator(field1: string, field2: string, errorKey: string): ValidatorFn {
  return (group: AbstractControl): ValidationErrors | null => {
    const f1 = group.get(field1)?.value;
    const f2 = group.get(field2)?.value;
    return f1 === f2 ? null : { [errorKey]: true };
  };
}
