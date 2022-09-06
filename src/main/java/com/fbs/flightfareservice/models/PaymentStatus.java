package com.fbs.flightfareservice.models;

public class PaymentStatus {

	private Fare fare;
	private String status;//progress,completed
    private String message;
    
    
	public PaymentStatus(Fare fare, String status, String message) {
		super();
		this.fare = fare;
		this.status = status;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Fare getFare() {
		return fare;
	}
	public void setFare(Fare fare) {
		this.fare = fare;
	}
	@Override
	public String toString() {
		return "PaymentStatus [fare=" + fare + ", status=" + status + ", message=" + message + "]";
	}
	
	
	
}
