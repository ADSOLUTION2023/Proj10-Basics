import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpserviceService {

  constructor(public httpClient: HttpClient) { }
  post(endpoint: any, formData: any, callback: any) {
     this.httpClient.post(endpoint, formData).subscribe((response: any) => {
      callback(response); 
    });
  }
   get(endpoint: any, callback: any) {
    this.httpClient.get(endpoint).subscribe((response: any) => {
      callback(response); 
    });
   }
}
