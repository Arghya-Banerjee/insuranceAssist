import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class Client {

  private http = inject(HttpClient)

  onGetProfileDetails(clientId: string) : any{
    return this.http.get(`${environment.apiUrl}/private/profile/get/${clientId}`)
  }
  
}
