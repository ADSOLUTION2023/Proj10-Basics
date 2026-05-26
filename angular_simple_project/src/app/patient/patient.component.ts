import { Component } from '@angular/core';
import { HttpServiceService } from '../httpservice.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent {
  
  endpoint: any = "http://localhost:8081/Auth/signup"

  constructor(public httpService: HttpServiceService) { }

  form: any = {
    data: {}
  }

  patient() {
    this.httpService.post(this.endpoint, this.form.data, (response: any) => {
      console.log('response === >', response);
    });

    
  }
}
