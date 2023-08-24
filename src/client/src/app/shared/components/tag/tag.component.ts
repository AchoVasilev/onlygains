import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { TagViewResource } from 'app/shared/models/tag';

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'gains-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.scss'],
})
export class TagComponent {
  private stylingClasses: string[] = ['tag-brown', 'tag-blue', 'tag-red'];
  private currentIndex?: number;

  stylingClass?: string;

  @Input()
  tag?: TagViewResource;

  @Input()
  urlPrefix?: string;

  getUrl(prefix: string) {
    return `${prefix}/${this.tag?.name}/${this.tag?.id}`;
  }

  getColor(): string {
    this.currentIndex = this.getRandomNumberBetween(0, 3);
    this.stylingClass = this.stylingClasses[this.currentIndex];
    this.currentIndex = (this.currentIndex + 1) % this.stylingClasses.length;
    
    return this.stylingClass;
  }

  private getRandomNumberBetween(min: number, max: number) {
    min = Math.floor(min);
    max = Math.floor(max);

    return Math.floor(Math.random() * (max - min) + min);
  }
}
