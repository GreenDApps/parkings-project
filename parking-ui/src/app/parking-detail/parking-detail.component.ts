import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ParkingService } from '../parking.service';
import { ParkingInfo } from '../parkingInfo';

@Component({
  selector: 'app-parking-detail',
  templateUrl: './parking-detail.component.html',
  styleUrls: ['./parking-detail.component.scss']
})
export class ParkingDetailComponent implements OnInit {

  parking: ParkingInfo = {
    identifiant: 0,
    nom: '',
    nbPlacesDispo: 0,
    nbPlacesTotal: 0,
    statut: '',
    heureMaj: ''
  };
  isLoaded: boolean = false;

  constructor(private route: ActivatedRoute,
    private parkingService: ParkingService,
    private router: Router) { }

  ngOnInit(): void {
    const parkingId = this.route.snapshot.paramMap.get('id');
    console.log("Called id : " + parkingId);
    if (parkingId != null) {
      this.parkingService.getParking(parseInt(parkingId)).subscribe(
        (reponse: ParkingInfo) => {
          this.parking = reponse;
          this.isLoaded = true;
        }
      );
    } else {
      this.router.navigate(['/parkings']);
    }
  }

}
