package com.carrentalsystem.bookingservice;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.customexception.BookingException;
import com.carrentalsystem.customexception.CustomerException;
import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.entity.CarInventory;
import com.carrentalsystem.entity.Customer;
import com.carrentalsystem.repository.BookingRepository;
import com.carrentalsystem.repository.CarInventoryRepository;
import com.carrentalsystem.repository.CustomerRepository;

@Service
public class BookingService implements BookingServiceInterface {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private CarInventoryRepository carInventoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Booking> getAllBookings() throws BookingException{
		List<Booking> allBookings = bookingRepository.findAll();
		if(BookingException.checkBookings(allBookings)) {
			throw new BookingException("No bookings are made.");
		}
		return allBookings;
	}

	@Override
	public Booking addBooking(String carRegNum, String customerDlNum, Booking booking) throws BookingException, CustomerException {

		CarInventory bookedCar = carInventoryRepository.findById(carRegNum).get();
		if (!bookedCar.isCarAvailablity()) {
			throw new BookingException("The car is already booked.");
		}
		List<Customer> allCustomer = customerRepository.findAll();
		Customer bookedCustomer = customerRepository.findById(customerDlNum).get();
		if(!CustomerException.checkIfCustomerExist(allCustomer, customerDlNum)) {
			throw new CustomerException("No customer found for the DL number.") ;
		}
		Date bookingStartDate = booking.getBookingStartDate();
		Date bookingEndDate = booking.getBookingEndDate();
		if(BookingException.validateDates(booking)) {
			throw new BookingException("Booking start date cannot be after end date");
		}
		booking.setBookingStartDate(bookingStartDate);
		booking.setBookingEndDate(bookingEndDate);
		booking.setBookedcarRegNum(bookedCar);
		booking.setBookedcustomerDlNum(bookedCustomer);

		int carCostPerDay = bookedCar.getCarCostPerDay();
		long duration = bookingEndDate.getTime() - bookingStartDate.getTime();
		duration = TimeUnit.MILLISECONDS.toDays(duration);

		int newBookingCost = 0;
		if (duration == 0) {
			newBookingCost = booking.getBookedcarRegNum().getCarCostPerDay();
		} else {
			newBookingCost = (int) (carCostPerDay * duration);
		}
		booking.setBookingCost(newBookingCost);
		bookedCar.setCarAvailablity(false);
		carInventoryRepository.save(bookedCar);

		bookingRepository.save(booking);
		return booking;

	}

	@Override
	public List<Booking> viewCustomerBookings(String customerDlNum) throws BookingException {
		Customer bookedCustomer = customerRepository.findById(customerDlNum).get();
		List<Booking> allBookings = bookingRepository.findAll();
		if(!BookingException.checkCustomerBookingExist(allBookings, customerDlNum)) {
			throw new BookingException("No bookings found for the DL number");
		}
		List<Booking> retrievedBookings = allBookings.stream().filter(s -> s.getBookedcustomerDlNum().equals(bookedCustomer))
				.toList();
		return retrievedBookings;
	}

	@Override
	public Booking returnCar(String carRegNum) throws BookingException {
		List<CarInventory> allCars = carInventoryRepository.findAll();
		
		if(!BookingException.checkIfCarRegNumExists(allCars, carRegNum)) {
			throw new BookingException("No details found for the registrartion number");
		}
		CarInventory bookedCar = carInventoryRepository.findById(carRegNum).get();
		bookedCar.setCarAvailablity(true);
		carInventoryRepository.save(bookedCar);
		List<Booking> allBookings = getAllBookings();
		Booking returnedBookingDetails = allBookings.stream()
				.filter(s -> s.getBookedcarRegNum().getCarRegNum().equals(carRegNum)).findAny().get();
		return returnedBookingDetails;
	}

	@Override
	public void deleteReturnedCar(String carRegNum) throws BookingException {
		CarInventory bookedCar = carInventoryRepository.findById(carRegNum).get();

		List<Booking> allBookings = getAllBookings();
		if (bookedCar.isCarAvailablity()) {
			Booking currentBooking = allBookings.stream().filter(s -> s.getBookedcarRegNum().equals(bookedCar))
					.findFirst().get();
			bookingRepository.delete(currentBooking);
		} else {
			throw new BookingException("The has not been returned by to inventory");
		}

	}


}
