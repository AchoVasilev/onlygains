import { AfterViewInit, Component, ElementRef, Input, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExerciseListResource } from 'app/shared/shared-module/models/exercise';

@Component({
  selector: 'gains-similar-exercise',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './similar-exercise.component.html',
  styleUrls: ['./similar-exercise.component.scss']
})
export class SimilarExerciseComponent implements AfterViewInit{

  @ViewChild('equipment') equipmentElement!: ElementRef;

  @Input()
  exercise?: ExerciseListResource;

  ngAfterViewInit(): void {
    this.equipmentElement.nativeElement.innerHTML = this.getEquipment();
  }

  getEquipment() {
    return this.exercise?.equipment.map(eq => `${eq.name}<br>`).join('');
  }
}
