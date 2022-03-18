import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Coordinates } from '../model/coordinates';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoordinatesService {

  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/';
  }

  public findAll(): Observable<Coordinates[]> {
    return this.http.get<Coordinates[]>(this.baseUrl+"all");
  }

  public save(coord: Coordinates) {
    return this.http.post<Coordinates>(this.baseUrl+"add", coord);
  }

  public delete(coord: Coordinates) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };
    return this.http.delete<Coordinates>(this.baseUrl+"del\\"+coord.id, options);
  }

}
