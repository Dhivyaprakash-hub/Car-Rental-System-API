package com.carrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.carrentalsystem.customerservice.CustomerServiceInterface;
import com.carrentalsystem.customexception.CustomerException;
import com.carrentalsystem.customexception.InputEmptyException;
import com.carrentalsystem.entity.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServiceInterface customerServiceInterface;

	@PostMapping("/save")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws InputEmptyException{
		Customer newCustomer = customerServiceInterface.addCustomer(customer);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> viewAllCustomers() throws CustomerException {
		List<Customer> allCustomers = customerServiceInterface.viewAllCustomers();
		return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
	}
	
	@GetMapping("/getDetails/{dlNum}")
	public ResponseEntity<Customer> getCustomerByDlNum(@PathVariable("dlNum") String dlNum) throws CustomerException{
		Customer retrievedCustomer = customerServiceInterface.getCustomerByDlNum(dlNum);
		return new ResponseEntity<Customer>(retrievedCustomer,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{customerDlNum}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("customerDlNum") String customerDlNum) throws CustomerException{
		customerServiceInterface.deleteCustomer(customerDlNum);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/update/{customerDlNum}")
	public ResponseEntity<Customer> updateCustomerDetails(@PathVariable String customerDlNum, @RequestBody Customer customer) throws CustomerException {
		
		customer.setCustomerDlNum(customerDlNum);
		Customer updatedCustomer = customerServiceInterface.updateCustomer(customerDlNum, customer);
		
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.CREATED);
	}
	
}
