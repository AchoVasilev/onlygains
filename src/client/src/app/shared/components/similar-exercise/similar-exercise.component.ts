import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  ViewChild,
} from '@angular/core';
import { ExerciseListResource } from 'app/shared/models/exercise';

@Component({
  selector: 'active-similar-exercise',
  standalone: true,
  imports: [],
  templateUrl: './similar-exercise.component.html',
  styleUrls: ['./similar-exercise.component.scss'],
})
export class SimilarExerciseComponent implements AfterViewInit {
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
