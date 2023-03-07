import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";
import {UserResponse} from "../models/response/user-response";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = 'http://localhost:8080/api/user'

  constructor(private http: HttpClient) { }

  getUser(): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.url}/get`)
  }

  // getUserReservations(): Observable<Reservation[]> {
  //   return this.http.get<Reservation[]>(`${this.url}/reservations`)
  // }

  getUserById(id: number): Observable<User>{
    return this.http.get<User>(`${this.url}/${id}`)
  }
  //
  // updateUser(id: number, user: UserDTO): Observable<UserDTO>{
  //   return this.http.put<UserDTO>(`http://localhost:8080/api/user/update/${id}`, user);
  // }

}
