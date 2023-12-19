import { Component, Input } from '@angular/core';
import { ChartConfiguration, Plugin } from 'chart.js';
import { AnyObject } from 'chart.js/types/basic';
import { NgChartsModule } from 'ng2-charts';

@Component({
  selector: 'active-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.scss'],
  standalone: true,
  imports: [NgChartsModule]
})
export class BarChartComponent {
  @Input()
  plugins: Plugin<'bar', AnyObject>[] = [];

  @Input()
  chartLegend: boolean = false;

  @Input({ required: true })
  data!: ChartConfiguration<'bar'>['data'];

  @Input()
  options?: ChartConfiguration<'bar'>['options'] = {
    scales: {
      y: {
        beginAtZero: true,
        ticks: {
          display: false,
        },
        grid: {
          drawTicks: false,
          drawBorder: false,
        },
      },
    },
  };

  constructor() {}
}
