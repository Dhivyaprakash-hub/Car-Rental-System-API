package com.carrentalsystem.carinventoryservice;

import java.util.List;

import com.carrentalsystem.customexception.CarInventoryException;
import com.carrentalsystem.customexception.InputEmptyException;
import com.carrentalsystem.entity.CarInventory;

public interface CarInventoryServiceInterface {

	CarInventory addCar(CarInventory carInventory) throws InputEmptyException;

	public void deleteByCarRegNum(String carRegNum) throws CarInventoryException;

	public List<CarInventory> getAllCars()  throws CarInventoryException;

	public List<CarInventory> getAvailableCars() throws CarInventoryException ;

}
