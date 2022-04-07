package com.carrentalsystem.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@Size(min = 15, max = 15)
	private String customerDlNum;

	@NotBlank

	private String customerName;

	@NotBlank
	@Size(min = 10, max = 10)
	private String customerPhoneNum;

	@NotBlank
	private String customerAddress;

	@OneToMany(mappedBy = "bookedcustomerDlNum")
	@JsonIgnore
	private Set<Booking> bookingMadeByCustomer = new HashSet<>();

}
