import { Component } from '@angular/core';
import { HttpServiceService } from '../httpservice.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  endpoint: any = "http://localhost:8081/Auth/signup"
    form: any = {
    data: {},
    inputerror:{},
    message:''
  }
  constructor(public httpService: HttpServiceService) { }

  signUp() {
    let self = this;
    this.httpService.post(this.endpoint, this.form.data, (response: any) => {
      console.log('response === >', response);
      if (response.success == false && response.result.inputerror){
        self.form.inputerror = response.result.inputerror;
      }else if (response.success == true){
        self.form.message = response.result.mesaage;
      }
    });
  }
}
