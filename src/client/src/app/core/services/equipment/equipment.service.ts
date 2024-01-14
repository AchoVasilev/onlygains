import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EquipmentResource } from 'app/shared/models/equipment';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EquipmentService {
  private apiUrl = environment.apiUrl + '/equipment';

  constructor(private readonly http: HttpClient) {}

  getAll(): Observable<EquipmentResource[]> {
    return this.http.get<EquipmentResource[]>(this.apiUrl);
  }
}
