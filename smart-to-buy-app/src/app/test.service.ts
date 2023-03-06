import {HttpClient, HttpHeaders} from "@angular/common/http";
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
  //TODO: This is useless i guess, if then remove
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    });

    return this.http.get<string[]>(`${this.url}/test/tests`, { headers: headers })
  }

}
