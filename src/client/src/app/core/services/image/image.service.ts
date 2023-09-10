import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs';
import { ImageResponseResource } from 'app/shared/shared-module/models/image';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private apiUrl: string = environment.apiUrl + '/images';

  constructor(private http: HttpClient) { }

  upload(file: File, folder: string): Observable<ImageResponseResource> {
    const formData = new FormData();
    formData.append('file', file, file.name);

    return this.http.post<ImageResponseResource>(`${this.apiUrl}/${folder}`, formData);
  }
}
