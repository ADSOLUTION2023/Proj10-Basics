import { Component } from '@angular/core';
import { HttpserviceService } from '../httpservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  endpoint: any = "http://localhost:8080/Auth/login"
  constructor(public httpService: HttpserviceService) { };
  form: any = {
    data: {}
  }

  login() {
    this.httpService.post(this.endpoint, this.form.data, (response: any) => {
      console.log('response === >', response);
    });
  }
}


