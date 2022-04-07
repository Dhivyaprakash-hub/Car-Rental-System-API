package com.carrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.carinventoryservice.CarInventoryServiceInterface;
import com.carrentalsystem.customexception.CarInventoryException;
import com.carrentalsystem.customexception.InputEmptyException;
import com.carrentalsystem.entity.CarInventory;

@RestController
@RequestMapping("/car")
public class CarInventoryController {

	@Autowired
	private CarInventoryServiceInterface carInventoryServiceInterface;

	@PostMapping("/add")
	public ResponseEntity<CarInventory> addCar(@RequestBody CarInventory carInventory) throws InputEmptyException{
		CarInventory newCar = carInventoryServiceInterface.addCar(carInventory);
		return new ResponseEntity<CarInventory>(newCar, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CarInventory>> getAllCars() throws CarInventoryException{
		List<CarInventory> allCars = carInventoryServiceInterface.getAllCars();
		return new ResponseEntity<List<CarInventory>>(allCars,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{carRegNum}")
	public ResponseEntity<Void> deleteCar(@PathVariable("carRegNum") String carRegNum) throws CarInventoryException{
		carInventoryServiceInterface.deleteByCarRegNum(carRegNum);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<CarInventory>> getAvailableCars() throws CarInventoryException {
		List<CarInventory> availableCars = carInventoryServiceInterface.getAvailableCars();
		return new ResponseEntity<List<CarInventory>>(availableCars,HttpStatus.OK);
	}
	
	

}
