package com.carrentalsystem.api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@RestController @RequestMapping("/api/v1") public class BookingController {
 private final BookingService service; public BookingController(BookingService service){this.service=service;}
 @GetMapping("/vehicles") public List<VehicleView> vehicles(){return service.listVehicles().stream().map(v->new VehicleView(v.getId(),v.getRegistration(),v.getMake(),v.getModel(),v.getCategory(),v.getSeats(),v.getDailyRate(),v.isActive())).toList();}
 @PostMapping("/bookings") @ResponseStatus(HttpStatus.CREATED) public BookingView create(@Valid @RequestBody CreateBooking request){return BookingView.from(service.create(request.vehicleId(),request.startDate(),request.endDate()));}
 @GetMapping("/bookings/{reference}") public BookingView get(@PathVariable UUID reference){return BookingView.from(service.find(reference));}
 public record CreateBooking(@NotNull UUID vehicleId,@NotNull @FutureOrPresent LocalDate startDate,@NotNull @FutureOrPresent LocalDate endDate){}
 public record VehicleView(UUID id,String registration,String make,String model,String category,int seats,java.math.BigDecimal dailyRate,boolean active){}
 public record BookingView(UUID reference,UUID vehicleId,String vehicle,LocalDate startDate,LocalDate endDate,java.math.BigDecimal dailyRate,java.math.BigDecimal totalPrice,String status){
  static BookingView from(Booking b){Vehicle v=b.getVehicle();return new BookingView(b.getId(),v.getId(),v.getMake()+" "+v.getModel(),b.getStartDate(),b.getEndDate(),b.getDailyRate(),b.getTotalPrice(),b.getStatus());}
 }
}
