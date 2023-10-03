import { NgForOf } from '@angular/common';
import { Directive, Host } from '@angular/core';

@Directive({
  selector: '[trackById]',
  standalone: true
})
export class NgForTrackByIdDirective<T extends Item> {

  constructor(@Host() private ngFor: NgForOf<T>) {
    this.ngFor.ngForTrackBy = (index: number, item: T) => item.id;
  }

}

interface Item {
  id: string;
}