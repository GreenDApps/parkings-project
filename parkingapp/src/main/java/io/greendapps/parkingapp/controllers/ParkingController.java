package io.greendapps.parkingapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.greendapps.parkingapp.models.Parking;
import io.greendapps.parkingapp.services.ParkingService;

@RestController
@RequestMapping(path = "/api")
public class ParkingController {

	@Autowired
	private ParkingService parkingService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "/parkings", method = RequestMethod.GET)
	public List<Parking> getListeParking() {
		return parkingService.getListeParkings();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "/parking/{id}", method = RequestMethod.GET)
	public ResponseEntity<Parking> getParking(@PathVariable int id) {
		Optional<Parking> optionalParking = parkingService.findParkingById(id);
		
		if(optionalParking.isPresent()) {
			return new ResponseEntity<>(optionalParking.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "/parking/{id}/{name}", method = RequestMethod.GET)
	public ResponseEntity<Parking> getParking(@PathVariable int id, @PathVariable String name) {
		Optional<Parking> optionalParking = parkingService.findParkingById(id);
		
		if(optionalParking.isPresent()) {
			return new ResponseEntity<>(optionalParking.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
