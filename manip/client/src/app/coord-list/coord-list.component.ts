import { Component, OnInit } from '@angular/core';
import { Coordinates } from '../model/coordinates'
import { CoordinatesService } from '../service/coordinates.service';
import { CoordCompComponent } from '../coord-comp/coord-comp.component';

@Component({
  selector: 'app-coord-list',
  templateUrl: './coord-list.component.html',
  styleUrls: ['./coord-list.component.css']
})
export class CoordListComponent implements OnInit {

  coords: Coordinates[];

  constructor(private coordinatesService: CoordinatesService) {
  }

  ngOnInit() {
    this.coordinatesService.findAll().subscribe((data : any) => {
      this.coords = data;
      console.log(data);
    });
  }

  onDel(coord : any) {
    this.coordinatesService.delete(coord).subscribe((data) => {this.ngOnInit();} );
  }

  onFromStart(coord : any){
    CoordCompComponent.start_lat=coord.latitude;
    CoordCompComponent.start_long=coord.longitude;
    CoordCompComponent.coordStart=coord;
  }

  onToEnd(coord : any){
    CoordCompComponent.end_lat=coord.latitude;
    CoordCompComponent.end_long=coord.longitude;
    CoordCompComponent.coordEnd=coord;
  }
}
