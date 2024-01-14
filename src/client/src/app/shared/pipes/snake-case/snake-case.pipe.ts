import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'snakeCase',
  standalone: true,
})
export class SnakeCasePipe implements PipeTransform {
  transform(value: string | undefined): string {
    return value ? value?.toLocaleLowerCase().replaceAll(' ', '-') : '';
  }
}
