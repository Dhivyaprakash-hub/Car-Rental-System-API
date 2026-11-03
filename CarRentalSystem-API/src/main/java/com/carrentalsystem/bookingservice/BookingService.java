package com.carrentalsystem.bookingservice;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Booking addBooking(String carRegNum, String customerDlNum, Booking booking) throws BookingException, CustomerException {

		CarInventory bookedCar = carInventoryRepository.findById(carRegNum)
				.orElseThrow(() -> new BookingException("No car found for registration number."));
		if (!bookedCar.isCarAvailablity()) {
			throw new BookingException("The car is already booked.");
		}
		Customer bookedCustomer = customerRepository.findById(customerDlNum)
				.orElseThrow(() -> new CustomerException("No customer found for the DL number."));
		Date bookingStartDate = booking.getBookingStartDate();
		Date bookingEndDate = booking.getBookingEndDate();
		if(bookingStartDate == null || bookingEndDate == null || !bookingStartDate.before(bookingEndDate)) {
			throw new BookingException("Booking dates are required and the end date must be after the start date.");
		}
		booking.setBookingStartDate(bookingStartDate);
		booking.setBookingEndDate(bookingEndDate);
		booking.setBookedcarRegNum(bookedCar);
		booking.setBookedcustomerDlNum(bookedCustomer);

		int carCostPerDay = bookedCar.getCarCostPerDay();
		long durationMillis = bookingEndDate.getTime() - bookingStartDate.getTime();
		long durationDays = Math.max(1, (durationMillis + TimeUnit.DAYS.toMillis(1) - 1) / TimeUnit.DAYS.toMillis(1));
		int newBookingCost;
		try {
			newBookingCost = Math.toIntExact(Math.multiplyExact((long) carCostPerDay, durationDays));
		} catch (ArithmeticException exception) {
			throw new BookingException("Booking cost exceeds the supported limit.");
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
