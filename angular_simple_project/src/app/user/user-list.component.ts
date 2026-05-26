import { Component,OnInit } from '@angular/core';
import { HttpServiceService } from '../httpservice.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  constructor(private httpService: HttpServiceService) { }

  form: any = {
    list: [],
    searchParam: {},
    pageNo: 0,
  }

  ngOnInit(): void {
    this.search();
  }

  next() {
    this.form.pageNo++;
    this.search();
  }

  previous() {
    this.form.pageNo--;
    this.search();
  }

  search() {
    let self = this;
    this.httpService.post('http://localhost:8081/User/search/' + this.form.pageNo, this.form.searchParam, (response: any) => {
      console.log('response === >', response);
      if (response.success == true) {
        self.form.list = response.result.data;
      }
    });
  }

  reset() {
    // location.reload();
    this.form.searchParam = {};
    this.search();
  }

}
