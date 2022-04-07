package com.carrentalsystem.customerservice;

import java.util.List;

import com.carrentalsystem.customexception.CustomerException;
import com.carrentalsystem.customexception.InputEmptyException;
import com.carrentalsystem.entity.Customer;

public interface CustomerServiceInterface {

	public Customer addCustomer(Customer customer) throws InputEmptyException;

	public List<Customer> viewAllCustomers() throws CustomerException ;

	public Customer getCustomerByDlNum(String dlNum) throws CustomerException;

	public void deleteCustomer(String customerDlNum) throws CustomerException;

	public Customer updateCustomer(String customerDlNum, Customer customer) throws CustomerException ;


}
