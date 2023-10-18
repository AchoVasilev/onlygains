import { ChangeDetectionStrategy, Component, ElementRef, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild, signal } from '@angular/core';
import { NgForOf } from '@angular/common';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { NgForTrackByIdDirective } from 'app/shared/directives/ng-for-track-by-id.directive';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { Selectable } from 'app/shared/models/selectable';
import { Subscription, debounceTime } from 'rxjs';

@Component({
  selector: 'gains-auto-complete',
  standalone: true,
  imports: [NgForOf, NgForTrackByIdDirective, FormsModule, ReactiveFormsModule, MatFormFieldModule, MatIconModule, MatAutocompleteModule, MatChipsModule],
  templateUrl: './auto-complete.component.html',
  styleUrls: ['./auto-complete.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AutoCompleteComponent implements OnInit, OnDestroy {
 
  @Input({required: true})
  label: string = '';

  @Input({required: true})
  placeholder: string = '';

  @Input({required: true})
  control!: FormControl

  @Input({required: true})
  items!: Selectable[] | null;

  selectedItems = signal<Selectable[]>([]);
  separatorKeysCodes: number[] = [ENTER, COMMA];
  
  @ViewChild('inputItem') variationInput?: ElementRef<HTMLInputElement>;

  valueChangesSubscription$?: Subscription;

  @Output()
  onFieldInput: EventEmitter<string> = new EventEmitter();

  ngOnInit(): void {
    this.valueChangesSubscription$ = this.control.valueChanges
      .pipe(debounceTime(200))
      .subscribe(val => this.onFieldInput.emit(val));
  }

  ngOnDestroy(): void {
    this.valueChangesSubscription$?.unsubscribe();
  }

  remove(variation: Selectable): void {
    const index = this.selectedItems().indexOf(variation);

    if (index >= 0) {
      this.selectedItems.mutate(items => items.splice(index, 1));
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    const selected = event.option.value as Selectable;
    this.selectedItems.mutate(items => items.push(selected));
    this.variationInput!.nativeElement.value = '';
    this.control.patchValue(this.selectedItems().map(v => v.id));
  }
}

