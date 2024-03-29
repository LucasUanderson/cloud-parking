package com.tech.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.parking.exception.ParkingNotFoundException;
import com.tech.parking.model.Parking;

@Service
public class ParkingService {
	
	private static Map<String,Parking> parkingMap = new HashMap();
	
	static {
		
		var id = getUUID();
		Parking parking = new Parking(id,"DMS-1111","SC","Celta","Preto");
		parkingMap.put(id, parking);
		
		var id1 = getUUID();
		Parking parking1 = new Parking(id1,"WAS-1111","RJ","BMW","Preto");
		parkingMap.put(id1, parking1);
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-","");
	}

	public Parking findById(String id) {
		Parking parking =  parkingMap.get(id) ;
		 if(parking == null) {
			 throw new ParkingNotFoundException(id);
		 }
		return parking;
	}

	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid,parkingCreate);
		return parkingCreate;
	}

	public void delete(String id) {
		findById(id);
		parkingMap.remove(id);
		
	}

	public Parking update(String id, Parking parkingCreate) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parkingMap.replace(id, parking);
		return parking;
	}
	

}
