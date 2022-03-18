import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CoordListComponent } from './coord-list/coord-list.component';
import { CoordFormComponent } from './coord-form/coord-form.component';
import { CoordCompComponent } from './coord-comp/coord-comp.component';
import { CoordinatesService } from './service/coordinates.service';


@NgModule({
  declarations: [
    AppComponent,
    CoordListComponent,
    CoordFormComponent,
    CoordCompComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [CoordinatesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
