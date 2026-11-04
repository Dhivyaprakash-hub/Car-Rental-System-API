package com.carrentalsystem.api;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
@Service public class BookingService {
 private final VehicleRepository vehicles; private final BookingRepository bookings;
 public BookingService(VehicleRepository vehicles,BookingRepository bookings){this.vehicles=vehicles;this.bookings=bookings;}
 @Transactional(readOnly=true) public List<Vehicle> listVehicles(){return vehicles.findAll();}
 @Transactional(readOnly=true) public Booking find(UUID id){return bookings.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Booking reference not found"));}
 @Transactional public Booking create(UUID vehicleId,LocalDate start,LocalDate end){
  if(start==null||end==null||!end.isAfter(start)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"End date must be after start date");
  Vehicle vehicle=vehicles.lockById(vehicleId).filter(Vehicle::isActive).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Vehicle not found"));
  if(bookings.hasOverlap(vehicleId,start,end)) throw new ResponseStatusException(HttpStatus.CONFLICT,"Vehicle is already reserved for part of those dates");
  long days=ChronoUnit.DAYS.between(start,end);
  var total=vehicle.getDailyRate().multiply(java.math.BigDecimal.valueOf(days)).setScale(2);
  return bookings.save(new Booking(vehicle,start,end,vehicle.getDailyRate(),total));
 }
}
