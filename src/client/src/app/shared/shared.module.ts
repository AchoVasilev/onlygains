import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarouselComponent } from './carousel/carousel.component';
import { CardComponent } from './card/card.component';
import { MaterialModule } from '../material/material.module';



@NgModule({
  declarations: [
    CarouselComponent,
    CardComponent
  ],
  imports: [
    CommonModule,
    MaterialModule
  ],
  exports: [
    CommonModule,
    CarouselComponent
  ]
})
export class SharedModule { }
