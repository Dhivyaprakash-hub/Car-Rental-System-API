package com.carrentalsystem.customexception;

import java.util.List;

import com.carrentalsystem.entity.Customer;

public class CustomerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -362688962640929130L;

	public CustomerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public static boolean checkIfListEmpty(List<Customer> allCustomers) {
		if (allCustomers.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean checkIfCustomerExist(List<Customer> allCustomers, String customerDlNum) {
		boolean customerExists = allCustomers.stream().filter(s -> s.getCustomerDlNum().equals(customerDlNum)).findFirst().isPresent();
		return customerExists;
	}

}
