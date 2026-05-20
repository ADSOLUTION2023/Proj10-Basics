import { Component } from '@angular/core';
import { HttpserviceService } from '../httpservice.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent {
  
  endpoint: any = "http://localhost:8080/Auth/signup"

  constructor(public httpservice: HttpserviceService) { }

  form: any = {
    data: {}
  }

  patient() {
    this.httpservice.post(this.endpoint, this.form.data, (response: any) => {
      console.log('response === >', response);
    });

    
  }
}
