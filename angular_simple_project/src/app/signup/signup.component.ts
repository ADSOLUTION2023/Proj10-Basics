import { Component } from '@angular/core';
import { HttpserviceService } from '../httpservice.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  endpoint: any = "http://localhost:8080/Auth/signup"
    form: any = {
    data: {}
  }
  constructor(public httpService: HttpserviceService) { }

  signUp() {
    this.httpService.post(this.endpoint, this.form.data, (response: any) => {
      console.log('response === >', response);
    });
  }
}
