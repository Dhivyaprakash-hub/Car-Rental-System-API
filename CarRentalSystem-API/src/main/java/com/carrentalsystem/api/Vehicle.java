package com.carrentalsystem.api;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
@Entity @Table(name="vehicles",uniqueConstraints=@UniqueConstraint(name="uk_vehicle_registration",columnNames="registration"))
public class Vehicle {
 @Id private UUID id=UUID.randomUUID();
 @Column(nullable=false,length=12) private String registration;
 @Column(nullable=false,length=60) private String make;
 @Column(nullable=false,length=80) private String model;
 @Column(nullable=false,length=24) private String category;
 @Column(nullable=false) private int seats;
 @Column(name="daily_rate",nullable=false,precision=10,scale=2) private BigDecimal dailyRate;
 @Column(nullable=false) private boolean active=true;
 protected Vehicle(){}
 public Vehicle(String registration,String make,String model,String category,int seats,BigDecimal dailyRate){this.registration=registration;this.make=make;this.model=model;this.category=category;this.seats=seats;this.dailyRate=dailyRate;}
 public UUID getId(){return id;} public String getRegistration(){return registration;} public String getMake(){return make;} public String getModel(){return model;} public String getCategory(){return category;} public int getSeats(){return seats;} public BigDecimal getDailyRate(){return dailyRate;} public boolean isActive(){return active;}
}
