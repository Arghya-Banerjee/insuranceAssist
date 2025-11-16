import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HospitalService {
  
  private http = inject(HttpClient);
  private hospitalUrl: string = "/private/hospital";

  onGetAllHospitals(): any{
    return this.http.get(`${environment.apiUrl}${this.hospitalUrl}/get`);
  }

  onGetHospitalById(id: String): any{
    return this.http.get(`${environment.apiUrl}${this.hospitalUrl}/get/${id}`);
  }

  onCreateHospital(formValue: any): any{
    return this.http.post(`${environment.apiUrl}${this.hospitalUrl}/create`, formValue);
  }

  onUpdateHospital(formValue: any, id: String): any{
    return this.http.put(`${environment.apiUrl}${this.hospitalUrl}/update/${id}`, formValue);
  }

  onDeleteHospital(id: string): any{
    return this.http.delete(`${environment.apiUrl}${this.hospitalUrl}/delete/${id}`);

  }
  
}
