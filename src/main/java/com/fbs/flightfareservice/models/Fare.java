package com.fbs.flightfareservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description="Details about the flight Fare")
public class Fare {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(notes="the unique id of the flight payment")
	private Long id;
	
	@ApiModelProperty(notes="the unique id of the flight booking")
	@Column(nullable=  false)
	private Long bookingId;
	
	@Column(nullable=  false)
	@ApiModelProperty(notes="Name pf tha payer")
	private String name;
	
	@Column(nullable=  false)
	@ApiModelProperty(notes="Credit card number by which payment is to done")
	private String creditCardNo;
	
	@Column(nullable=  false)
	@ApiModelProperty(notes="Expiry date on card")
	private String expiryDate; 
	
	@Column(nullable=  false)
	@ApiModelProperty(notes="Cvv of the card")
	private int cvv; 
	
	@Column(nullable=  false)
	@ApiModelProperty(notes="Amount of fare")
	private int amountOfFare;

	public Fare(Long id, Long bookingId, String name, String creditCardNo, String expiryDate, int cvv,
			int amountOfFare) {
		super();
		this.id = id;
		this.bookingId = bookingId;
		this.name = name;
		this.creditCardNo = creditCardNo;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.amountOfFare = amountOfFare;
	}
	public Fare() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public int getAmountOfFare() {
		return amountOfFare;
	}

	public void setAmountOfFare(int amountOfFare) {
		this.amountOfFare = amountOfFare;
	}
	@Override
	public String toString() {
		return "Fare [id=" + id + ", bookingId=" + bookingId + ", name=" + name + ", creditCardNo=" + creditCardNo
				+ ", expiryDate=" + expiryDate + ", cvv=" + cvv + ", amountOfFare=" + amountOfFare + "]";
	}
	
	
}
