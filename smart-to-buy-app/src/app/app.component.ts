import {Component, OnInit} from '@angular/core';
import {TestService} from "./test.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'smart-to-buy-app';
  test: string[] = []

  constructor(private testService: TestService) {
  }

  ngOnInit(): void {
    this.testService.getTest().subscribe({
      next: value => {
        this.test = value;
        console.log(value)
      }
    })
  }

}
