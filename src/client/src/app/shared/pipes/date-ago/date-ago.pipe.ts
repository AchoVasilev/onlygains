import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateAgo',
  standalone: true
})
export class DateAgoPipe extends DatePipe implements PipeTransform {
  override transform(value: any, args?: any): any {
    if (value) {
      const seconds = Math.floor((+new Date() - +new Date(value)) / 1000);
      if (seconds < 29) {
        // less than 30 seconds ago will show as 'Just now'
        return 'Току-що';
      }

      const dayInSeconds = 86400;
      const counter = Math.floor(seconds / dayInSeconds);
      if (counter > 0) {
        if (counter === 1) {
          return `Преди ${counter} ден`; // singular (1 day ago)
        } else if (counter <= 5) {
          return `Преди ${counter} дни`; // plural (2 days ago)
        } else {
          return super.transform(value, 'dd.MM.yyyy');
        }
      } else if(seconds < dayInSeconds) {
        return `Преди по-малко от 1 ден`;
      }
    }

  }
}
