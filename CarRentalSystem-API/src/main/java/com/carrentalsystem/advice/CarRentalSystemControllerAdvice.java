package com.carrentalsystem.advice;

import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.carrentalsystem.customexception.BookingException;
import com.carrentalsystem.customexception.CarInventoryException;
import com.carrentalsystem.customexception.CustomerException;

import com.carrentalsystem.customexception.InputEmptyException;

@ControllerAdvice
public class CarRentalSystemControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintVoilation(ConstraintViolationException constraintViolationException) {

		return new ResponseEntity<String>("Entered details are incorrect.", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InputEmptyException.class)
	public ResponseEntity<String> handleInputEmptyException(InputEmptyException emptyException) {

		return new ResponseEntity<String>(emptyException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException) {

		return new ResponseEntity<String>("Entered details are incorrect.", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCustomerException(CustomerException customerException) {

		return new ResponseEntity<String>(customerException.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CarInventoryException.class)
	public ResponseEntity<String> handleCarInventoryException(CarInventoryException carInventoryException) {

		return new ResponseEntity<String>(carInventoryException.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BookingException.class)
	public ResponseEntity<String> handleBookingException(BookingException bookingException) {

		return new ResponseEntity<String>(bookingException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {

		return new ResponseEntity<String>("No details found.", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> handleEmptyResultDataAccessException(
			EmptyResultDataAccessException emptyResultDataAccessException) {

		return new ResponseEntity<String>("No details found.", HttpStatus.NOT_FOUND);
	}

}
