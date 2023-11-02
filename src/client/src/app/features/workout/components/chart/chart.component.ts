import { Component, Input } from '@angular/core';
import { ChartConfiguration, Plugin } from 'chart.js';
import { AnyObject } from 'chart.js/types/basic';
import { NgChartsModule } from 'ng2-charts';

@Component({
  selector: 'gains-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss'],
  standalone: true,
  imports: [NgChartsModule],
})
export class ChartComponent {
  
  @Input()
  plugins: Plugin<'bar', AnyObject>[] = [];

  @Input()
  chartLegend: boolean = true;

  @Input({required: true})
  data!: ChartConfiguration<'bar'>['data'];

  @Input()
  options?: ChartConfiguration<'bar'>['options'];
}
