import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../material/material.module';
import { TagComponent } from './components/tag/tag.component';
import { RouterLink } from '@angular/router';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { DateAgoPipe } from './pipes/date-ago/date-ago.pipe';
import { IconButtonComponent } from './components/icon-button/icon-button.component';
import { SafeHtmlPipe } from './pipes/safe-html/safe-html.pipe';
import { NgForTrackByIdDirective } from './directives/ng-for-track-by-id.directive';

@NgModule({
  declarations: [
    TagComponent,
    SideBarComponent,
    DateAgoPipe,
    IconButtonComponent,
    SafeHtmlPipe,
    NgForTrackByIdDirective,
  ],
  imports: [CommonModule, MaterialModule, RouterLink],
  exports: [
    CommonModule,
    MaterialModule,
    TagComponent,
    SideBarComponent,
    IconButtonComponent,
    DateAgoPipe,
    SafeHtmlPipe,
    NgForTrackByIdDirective
  ],
})
export class SharedModule {}
