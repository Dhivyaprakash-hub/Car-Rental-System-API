package com.carrentalsystem.api;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Entity @Table(name="bookings",indexes=@Index(name="idx_booking_vehicle_dates",columnList="vehicle_id,start_date,end_date"))
public class Booking {
 @Id private UUID id=UUID.randomUUID();
 @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="vehicle_id",nullable=false) private Vehicle vehicle;
 @Column(name="start_date",nullable=false) private LocalDate startDate;
 @Column(name="end_date",nullable=false) private LocalDate endDate;
 @Column(name="daily_rate",nullable=false,precision=10,scale=2) private BigDecimal dailyRate;
 @Column(name="total_price",nullable=false,precision=12,scale=2) private BigDecimal totalPrice;
 @Column(nullable=false,length=20) private String status="CONFIRMED";
 protected Booking(){}
 public Booking(Vehicle vehicle,LocalDate startDate,LocalDate endDate,BigDecimal dailyRate,BigDecimal totalPrice){this.vehicle=vehicle;this.startDate=startDate;this.endDate=endDate;this.dailyRate=dailyRate;this.totalPrice=totalPrice;}
 public UUID getId(){return id;} public Vehicle getVehicle(){return vehicle;} public LocalDate getStartDate(){return startDate;} public LocalDate getEndDate(){return endDate;} public BigDecimal getDailyRate(){return dailyRate;} public BigDecimal getTotalPrice(){return totalPrice;} public String getStatus(){return status;}
}
