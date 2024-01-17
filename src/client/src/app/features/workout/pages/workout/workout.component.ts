import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SideBarComponent } from 'app/shared/components/side-bar/side-bar.component';

@Component({
  selector: 'active-workout',
  standalone: true,
  imports: [RouterOutlet, SideBarComponent],
  templateUrl: './workout.component.html',
})
export class WorkoutComponent {}
