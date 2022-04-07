package com.carrentalsystem.bookingservice;

import java.util.List;

import com.carrentalsystem.customexception.BookingException;
import com.carrentalsystem.customexception.CustomerException;
import com.carrentalsystem.entity.Booking;

public interface BookingServiceInterface {

	public List<Booking> getAllBookings() throws BookingException;

	public Booking addBooking(String carRegNum, String customerDlNum, Booking booking) throws BookingException, CustomerException;

	public List<Booking> viewCustomerBookings(String customerDlNum) throws BookingException;

	public Booking returnCar(String carRegNum) throws BookingException ;

	public void deleteReturnedCar(String carRegNum) throws BookingException;

}
