import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../material/material.module';
import { TagComponent } from './components/tag/tag.component';
import { RouterModule } from '@angular/router';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { DateAgoPipe } from './pipes/date-ago/date-ago.pipe';

@NgModule({
  declarations: [TagComponent, SideBarComponent, DateAgoPipe],
  imports: [CommonModule, MaterialModule, RouterModule],
  exports: [
    CommonModule,
    MaterialModule,
    TagComponent,
    SideBarComponent,
    DateAgoPipe,
  ],
})
export class SharedModule {}
