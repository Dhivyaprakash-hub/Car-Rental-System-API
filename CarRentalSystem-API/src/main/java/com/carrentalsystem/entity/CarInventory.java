package com.carrentalsystem.entity;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarInventory {

	@Id
	@Size(min = 10, max = 10)
	private String carRegNum;
	private String carType;
	private String carModel;
	private int carCostPerDay;

	private boolean carAvailablity;

	@OneToOne(mappedBy = "bookedcarRegNum")
	@JsonIgnore
	private Booking bookingId;
}
