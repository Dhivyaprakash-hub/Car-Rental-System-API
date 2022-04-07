package com.carrentalsystem.customexception;


import java.util.Date;
import java.util.List;

import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.entity.CarInventory;

public class BookingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5789561353212240200L;

	public BookingException() {

	}

	public BookingException(String s) {
		super(s);
	}

	public static boolean validateCarAvailablity(Booking booking) {

		if (booking.getBookedcarRegNum().isCarAvailablity() == false) {
			return false;
		}
		return true;

	}

	public static boolean checkBookings(List<Booking> allBookings) {

		if (allBookings.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean checkIfCarRegNumExists(List<CarInventory> allCars, String carRegNum) {
		boolean carToBeChecked = allCars.stream().filter(s -> s.getCarRegNum().equals(carRegNum)).findFirst().isPresent();
		return carToBeChecked;
	}
	
	public static boolean validateDates(Booking booking) {
		Date start = booking.getBookingStartDate();
		Date end = booking.getBookingEndDate();
		
		int res = start.compareTo(end);
		if(res < 0) {
			return false;
		}
		return true;
	}
	
	public static boolean checkCustomerBookingExist(List<Booking> allBookings, String customerDlNum) {
		boolean DlNumExist = allBookings.stream().filter(s -> s.getBookedcustomerDlNum().getCustomerDlNum().equalsIgnoreCase(customerDlNum)).findAny().isPresent();
		return DlNumExist;
	}
}
