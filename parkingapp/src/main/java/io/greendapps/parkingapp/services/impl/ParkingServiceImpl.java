package io.greendapps.parkingapp.services.impl;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.greendapps.parkingapp.dao.ParkingAPIDAO;
//import io.greendapps.parkingapp.dao.entity.FieldsEntity;
import io.greendapps.parkingapp.dao.entity.RecordEntity;
import io.greendapps.parkingapp.dao.entity.ReponseParkingAPIEntity;
import io.greendapps.parkingapp.models.Parking;
import io.greendapps.parkingapp.services.ParkingService;

@Service
public class ParkingServiceImpl implements ParkingService {
	
	@Autowired
	private ParkingAPIDAO parkingAPIDAO;

	@Override
	public Optional<Parking> findParkingById(int id) {
		List<Parking> parkings = this.getListeParkings();
		Parking parking = null;
		Optional<Parking> optionalParking = parkings.stream()
				.filter(p -> p.getIdentifiant() == id)
				.findFirst();
		return optionalParking;
	}
	
	@Override
	public List<Parking> getListeParkings() {
		ReponseParkingAPIEntity reponse = parkingAPIDAO.getListeParkings();	
		return transformEntityToModel(reponse);
	}

	private List<Parking> transformEntityToModel(ReponseParkingAPIEntity reponse) {
		List<Parking> resultat = new ArrayList<Parking>();
		for (RecordEntity record : reponse.getRecords()) {
			Parking parking = new Parking();
			parking.setIdentifiant(Integer.parseInt(record.getFields().getIdObj()));
			parking.setNom(record.getFields().getGrpNom());
			parking.setStatut(getLibelleStatut(record));
			parking.setNbPlacesTotal(record.getFields().getGrpExploitation());
			parking.setNbPlacesDispo(record.getFields().getGrpDisponible());
			parking.setHeureMaj(getHeureMaj(record));		
			resultat.add(parking);
		}		
		return resultat;
	}

	// Format updating hours and minutes
	private String getHeureMaj(RecordEntity record) {
		OffsetDateTime dateMaj = OffsetDateTime.parse(record.getFields().getGrpHorodatage());
		OffsetDateTime dateMajWithOffsetPlus2 = dateMaj.withOffsetSameInstant(ZoneOffset.of("+02:00"));

		// Format hours
		String hours = String.valueOf(dateMajWithOffsetPlus2.getHour());
		if(dateMajWithOffsetPlus2.getHour() == 0)
			hours = "00";
		if(dateMajWithOffsetPlus2.getHour() != 0 && dateMajWithOffsetPlus2.getHour()<10)
			hours = "0" + String.valueOf(dateMajWithOffsetPlus2.getHour());
		
		// Format minutes
		String minutes = String.valueOf(dateMajWithOffsetPlus2.getMinute());
		if(dateMajWithOffsetPlus2.getMinute() == 0)
			minutes = "00";
		if(dateMajWithOffsetPlus2.getMinute() != 0 && dateMajWithOffsetPlus2.getMinute()<10)
			minutes = "0" + String.valueOf(dateMajWithOffsetPlus2.getMinute());
		
		return hours + "h" + minutes;
	}

	private String getLibelleStatut(RecordEntity record) {
		switch(record.getFields().getGrpStatut()) {
		case "1" : {
			return "FERME";
		}
		case "2" : {
			return "ABONNES";
		}
		case "5" : {
			return "OUVERT";
		}
		}
		return "Données non disponibles";
	}

}
