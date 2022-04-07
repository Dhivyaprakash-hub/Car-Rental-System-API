package com.carrentalsystem.carinventoryservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.customexception.CarInventoryException;
import com.carrentalsystem.customexception.InputEmptyException;
import com.carrentalsystem.entity.CarInventory;
import com.carrentalsystem.repository.CarInventoryRepository;

@Service
public class CarInventoryService implements CarInventoryServiceInterface {

	@Autowired
	private CarInventoryRepository carInventoryRepository;

	@Override
	public CarInventory addCar(CarInventory carInventory) throws InputEmptyException {
		boolean checkCarBodyString = InputEmptyException.validateCarBodyString(carInventory);
		boolean checkCarCostPerDay = InputEmptyException.validateCarCostPerDay(carInventory);
		boolean checkCarAvailiablity = InputEmptyException.validateCarAvailablity(carInventory);
		if (checkCarBodyString)  {
			throw new InputEmptyException("Input fields cannot be empty.");
		}
		if(checkCarCostPerDay) {
			throw new InputEmptyException("Car cost per day is invalid.");
		}
		if(checkCarAvailiablity) {
			throw new InputEmptyException("Car Availablity cannot be false");
		}
		return carInventoryRepository.save(carInventory);

	}

	@Override
	public List<CarInventory> getAllCars() throws CarInventoryException {
		List<CarInventory> allCars =  carInventoryRepository.findAll();
		if(CarInventoryException.checkCarInventory(allCars)) {
			throw new CarInventoryException("No cars in inventory.");
		}
		return allCars;
	}

	@Override
	public void deleteByCarRegNum(String carRegNum) throws CarInventoryException{
		if(!CarInventoryException.checkIfCarRegNumExists(getAllCars(), carRegNum)) {
			throw new CarInventoryException("Car to be deleted does not exist.");
		}
		carInventoryRepository.deleteById(carRegNum);;
	}

	@Override
	public List<CarInventory> getAvailableCars() throws CarInventoryException {
		List<CarInventory> allCars = getAllCars();
		List<CarInventory> availableCars = allCars.stream().filter(s -> s.isCarAvailablity() == true).toList();
		if(CarInventoryException.checkCarInventory(availableCars)) {
			throw new CarInventoryException("None of the cars are available.");
		}
		return availableCars;
	}

}
