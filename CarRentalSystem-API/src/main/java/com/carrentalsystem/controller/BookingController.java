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

import com.carrentalsystem.bookingservice.BookingServiceInterface;
import com.carrentalsystem.customexception.BookingException;
import com.carrentalsystem.customexception.CustomerException;
import com.carrentalsystem.entity.Booking;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingServiceInterface bookingServiceInterface;

	@PostMapping("/{carRegNum}/add/{customerDlNum}")
	public ResponseEntity<Booking> addBooking(@PathVariable String carRegNum, @PathVariable String customerDlNum,
			@RequestBody Booking booking) throws BookingException, CustomerException{
		Booking newBooking = bookingServiceInterface.addBooking(carRegNum, customerDlNum, booking);
		return new ResponseEntity<Booking>(newBooking, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Booking>> getAllBookings() throws BookingException{
		List<Booking> allBookings = bookingServiceInterface.getAllBookings();
		return new ResponseEntity<List<Booking>>(allBookings, HttpStatus.OK);
	}


	@GetMapping("/{customerDlNum}")
	public ResponseEntity<List<Booking>> viewCustomerBookings(@PathVariable String customerDlNum) throws BookingException{
		List<Booking> customerRetrivedBookings = bookingServiceInterface.viewCustomerBookings(customerDlNum);
		return new ResponseEntity<List<Booking>>(customerRetrivedBookings, HttpStatus.OK);
	}

	@PutMapping("/return/{carRegNum}")
	public ResponseEntity<Booking> returnCar(@PathVariable String carRegNum) throws BookingException{
		Booking returnedBookingDetails = bookingServiceInterface.returnCar(carRegNum);
		return new ResponseEntity<Booking>(returnedBookingDetails, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{carRegNum}")
	public ResponseEntity<Void> deleteReturnedCar(@PathVariable String carRegNum) throws BookingException {
		bookingServiceInterface.deleteReturnedCar(carRegNum);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
