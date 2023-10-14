import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'snakeCase',
  standalone: true
})
export class SnakeCasePipe implements PipeTransform {

  transform(value: string | undefined, ...args: unknown[]): string {
    return value ? value?.toLocaleLowerCase().replaceAll(' ', '-') : '';
  }

}
