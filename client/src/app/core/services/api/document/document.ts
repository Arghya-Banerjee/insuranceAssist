import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {
  
  private http = inject(HttpClient);

  onDocumentUpload(formData: FormData, claimId: string) : any {
      return this.http.post(`${environment.apiUrl}/private/document/upload/${claimId}`, formData);
  }

  onDocumentDownload(claimId: string) : any {
      return this.http.get(`${environment.apiUrl}/private/document/download/${claimId}`);
  }

}
