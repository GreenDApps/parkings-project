package io.greendapps.parkingapp.services;

import java.util.List;
import java.util.Optional;

import io.greendapps.parkingapp.models.Parking;

public interface ParkingService {

	public Optional<Parking> findParkingById(int id);
	public List<Parking> getListeParkings();
	
}
