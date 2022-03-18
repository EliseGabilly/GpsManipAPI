import { Component, OnInit } from '@angular/core';
import { Coordinates } from '../model/coordinates'
import { CoordinatesService } from '../service/coordinates.service';

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

}
