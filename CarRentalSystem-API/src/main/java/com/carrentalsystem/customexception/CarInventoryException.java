package com.carrentalsystem.customexception;

import java.util.List;

import com.carrentalsystem.entity.CarInventory;


public class CarInventoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8888435367687861212L;

	public CarInventoryException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarInventoryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public static boolean checkCarInventory(List<CarInventory> allCars) {

		if (allCars.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean checkIfCarRegNumExists(List<CarInventory> allCars, String carRegNum) {
		boolean carToBeDelete = allCars.stream().filter(s -> s.getCarRegNum().equals(carRegNum)).findFirst().isPresent();
		return carToBeDelete;
	}
}
