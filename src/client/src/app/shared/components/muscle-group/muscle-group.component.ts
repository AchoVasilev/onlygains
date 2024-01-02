import { DOCUMENT } from '@angular/common';
import { Component, Inject, Input, OnInit } from '@angular/core';

@Component({
  selector: 'active-muscle-group',
  standalone: true,
  templateUrl: './muscle-group.component.html',
  styleUrls: ['./muscle-group.component.scss'],
})
export class MuscleGroupComponent implements OnInit {
  @Input()
  heading?: string;

  constructor(@Inject(DOCUMENT) private document: Document) {}

  mainGroups = ['Deltoids'];
  synergists = ['Rear-deltoids', 'Abs', 'Obliques', 'Chest', 'Triceps'];

  ngOnInit(): void {
    this.document.querySelectorAll('.muscle-groups svg g g[id]').forEach((gr) => {
      if (this.synergists.includes(gr.id)) {
        let children = Array.from(gr.children);
        children.forEach(child => child.classList.add('light-red'));
      }

      if (this.mainGroups.includes(gr.id)) {
        let children = Array.from(gr.children);
        children.forEach(child => child.classList.add('red'));
      }
    });
  }
}
