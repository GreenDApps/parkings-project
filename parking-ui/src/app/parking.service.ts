import { Injectable } from '@angular/core';
import { ParkingInfo } from './parkingInfo';
import { Observable, catchError, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  getParkings(): Observable<ParkingInfo[]> {
    return this.httpClient.get<ParkingInfo[]>(environment.apiUrl + '/parkings');
  }

  getParking(id: number): Observable<ParkingInfo> {
    return this.httpClient.get<ParkingInfo>(environment.apiUrl + '/parking/' + id)
      .pipe(
        catchError(err => {
          if (err.status === 404) {
            this.router.navigate(['/parkings']);
          }
          return throwError(err);
        })
      );
  }
}
