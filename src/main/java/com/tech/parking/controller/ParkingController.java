package com.tech.parking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.parking.controller.mapper.ParkingMapper;
import com.tech.parking.dto.ParkingCreateDto;
import com.tech.parking.dto.ParkingDTO;
import com.tech.parking.model.Parking;
import com.tech.parking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;

	public ParkingController(ParkingService parkingService,ParkingMapper parkingMapper ) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}
	
	@GetMapping
	public  ResponseEntity<List<ParkingDTO>> findAll(){
		 List<Parking> parkingList = parkingService.findAll();
		 List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
	     return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		 Parking parking = parkingService.findById(id);
		 ParkingDTO result = parkingMapper.toParkingDTO(parking);
	     return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity delete(@PathVariable String id){
		 parkingService.delete(id);
	     return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping
	public ResponseEntity <ParkingDTO> create(@RequestBody ParkingCreateDto dto){
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		var parking = parkingService.create(parkingCreate);
		var result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping
	public ResponseEntity <ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDto dto){
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		var parking = parkingService.update(id, parkingCreate);
		var result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	

}
