import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Coordinates } from '../model/coordinates'
import { CoordinatesService } from '../service/coordinates.service';

@Component({
  selector: 'app-coord-form',
  templateUrl: './coord-form.component.html',
  styleUrls: ['./coord-form.component.css']
})
export class CoordFormComponent {

  coord: Coordinates;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private coordService: CoordinatesService) {
    this.coord = new Coordinates();
  }

  onSubmit() {
    this.coordService.save(this.coord).subscribe(result => window.location.reload());
  }
}
