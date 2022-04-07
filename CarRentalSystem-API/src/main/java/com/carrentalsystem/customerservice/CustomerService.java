package com.carrentalsystem.customerservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.customexception.CustomerException;
import com.carrentalsystem.customexception.InputEmptyException;
import com.carrentalsystem.entity.Customer;
import com.carrentalsystem.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerServiceInterface {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) throws InputEmptyException {
		boolean checkBody = InputEmptyException.validateCustomerBody(customer);
		if (checkBody) {
			throw new InputEmptyException("Input fields cannot be empty.");
		}
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> viewAllCustomers() throws CustomerException {
		List<Customer> allCustomers = customerRepository.findAll();
		if (CustomerException.checkIfListEmpty(allCustomers)) {
			throw new CustomerException("No customers in the records.");
		}
		return allCustomers;
	}

	@Override
	public Customer getCustomerByDlNum(String dlNum) throws CustomerException {
		List<Customer> allCustomers = customerRepository.findAll();
		if (!CustomerException.checkIfCustomerExist(allCustomers, dlNum)) {
			throw new CustomerException("No details found for the DL number.");
		}
		return customerRepository.findById(dlNum).get();
	}

	@Override
	public void deleteCustomer(String customerDlNum) throws CustomerException {
		List<Customer> allCustomers = customerRepository.findAll();
		if (!CustomerException.checkIfCustomerExist(allCustomers, customerDlNum)) {
			throw new CustomerException("No details found for the DL number.");
		}
		customerRepository.deleteById(customerDlNum);
	}

	@Override
	public Customer updateCustomer(String customerDlNum, Customer customer) throws CustomerException {
		List<Customer> allCustomers = customerRepository.findAll();
		if (!CustomerException.checkIfCustomerExist(allCustomers, customerDlNum)) {
			throw new CustomerException("No details found for the DL number.");
		}
		
		customerRepository.save(customer);
		return customer;	
	}

}
