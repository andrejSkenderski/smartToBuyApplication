import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class TestService {

  url = "http://localhost:8080/api"

  constructor(private http: HttpClient) {
  }

  getTest(): Observable<string[]> {
    console.log("tservice")
    return this.http.get<string[]>(`${this.url}/test/tests`)
  }

}
