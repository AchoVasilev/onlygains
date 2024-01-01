import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ComponentType } from '@angular/cdk/overlay';

@Injectable({
  providedIn: 'root',
})
export class MatDialogService {
  constructor(private matDialog: MatDialog) {}

  open<T>(componentType: ComponentType<T>, config: MatDialogConfig) {
    const dialogRef = this.matDialog.open(componentType, config);

    return dialogRef;
  }
}
