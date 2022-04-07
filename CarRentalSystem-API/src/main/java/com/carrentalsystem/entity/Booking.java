package com.carrentalsystem.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;

	@Temporal(TemporalType.DATE)
	private Date bookingStartDate;

	@Temporal(TemporalType.DATE)
	private Date bookingEndDate;

	private int bookingCost;

	@OneToOne
	@JoinColumn(name = "bookedCarRegNum", referencedColumnName = "carRegNum")
	private CarInventory bookedcarRegNum;

	@ManyToOne
	@JoinColumn(name = "bookedCustomerDlNum", referencedColumnName = "customerDlNum")
	private Customer bookedcustomerDlNum;

}
