import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Coordinates } from '../model/coordinates'
import { CoordinatesService } from '../service/coordinates.service';

@Component({
  selector: 'app-coord-comp',
  templateUrl: './coord-comp.component.html',
  styleUrls: ['./coord-comp.component.css']
})
export class CoordCompComponent implements OnInit {

  public static start_lat : string;
  public static start_long : string;
  public static end_lat : string;
  public static end_long : string;

  public static coordStart: Coordinates;
  public static coordEnd: Coordinates;

  public static res: String;

  public classReference = CoordCompComponent;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private coordinatesService: CoordinatesService) {

    CoordCompComponent.coordStart = new Coordinates();
    CoordCompComponent.coordEnd = new Coordinates();

    CoordCompComponent.res = " ";

    CoordCompComponent.start_lat = "...";
    CoordCompComponent.start_long = "...";
    CoordCompComponent.end_lat = "...";
    CoordCompComponent.end_long = "...";
  }

  ngOnInit(): void {
  }
  onCompare(){
    this.coordinatesService.compare(CoordCompComponent.coordStart, CoordCompComponent.coordEnd);
  }

}
