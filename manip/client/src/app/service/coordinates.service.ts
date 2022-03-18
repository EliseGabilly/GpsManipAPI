import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Coordinates } from '../model/coordinates';
import { Observable } from 'rxjs';
import { CoordCompComponent } from '../coord-comp/coord-comp.component';

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

  public compare(coordStart: Coordinates, coordEnd: Coordinates){

    let queryParams = new HttpParams();
    queryParams = queryParams.append("start_lat", coordStart.latitude);
    queryParams = queryParams.append("start_long", coordStart.longitude);
    queryParams = queryParams.append("end_lat", coordEnd.latitude);
    queryParams = queryParams.append("end_long", coordEnd.longitude);

    this.http.get(this.baseUrl+"comp", { params: queryParams })
      .subscribe((response) => {
         console.log(response);
         CoordCompComponent.res = JSON.stringify(response);
      });
 }

}
