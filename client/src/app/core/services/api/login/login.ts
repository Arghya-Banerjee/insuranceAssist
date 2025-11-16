import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private http = inject(HttpClient);


  onLogin(formValue: any): Observable<any> {

    return this.http.post(`${environment.apiUrl}/public/login`, formValue); 
    
  }
}