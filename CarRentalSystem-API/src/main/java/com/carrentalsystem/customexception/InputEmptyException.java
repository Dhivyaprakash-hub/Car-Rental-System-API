package com.carrentalsystem.customexception;

import com.carrentalsystem.entity.CarInventory;
import com.carrentalsystem.entity.Customer;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InputEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8788363643026626043L;

	public InputEmptyException(String s) {
		super(s);
	}

	public static boolean validateCustomerBody(Customer customer) {
		if (customer.getCustomerDlNum().isEmpty() || customer.getCustomerName().isEmpty()
				|| customer.getCustomerPhoneNum().isEmpty() || customer.getCustomerAddress().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean validateCarBodyString(CarInventory carInventory) {
		if (carInventory.getCarRegNum().isEmpty() || carInventory.getCarType().isEmpty()
				|| carInventory.getCarModel().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean validateCarCostPerDay(CarInventory carInventory) {
		if (carInventory.getCarCostPerDay() <= 0) {
			return true;
		}
		return false;
	}

	public static boolean validateCarAvailablity(CarInventory carInventory) {
		if (carInventory.isCarAvailablity() == false) {
			return true;
		}
		return false;
	}
	
//	public static boolean validateBookingDates(Booking booking) {
//		DateFormatter dateFormatter;
//		if(dateFormatter.parse(null, null)) {
//			
//		}
//	}
}
