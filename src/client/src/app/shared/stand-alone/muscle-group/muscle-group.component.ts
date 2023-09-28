import { AfterViewInit, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'gains-muscle-group',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './muscle-group.component.html',
  styleUrls: ['./muscle-group.component.scss'],
})
export class MuscleGroupComponent implements OnInit {
  mainGroups = ['Deltoids'];
  synergists = ['Rear-deltoids', 'Abs', 'Obliques', 'Chest', 'Triceps'];

  ngOnInit(): void {
    document.querySelectorAll('.muscle-groups svg g g[id]').forEach((gr) => {
      if (this.mainGroups.includes(gr.id)) {
        let children = Array.from(gr.children);
        children.forEach(child => child.classList.add('red'));
      }
    });
  }
}
