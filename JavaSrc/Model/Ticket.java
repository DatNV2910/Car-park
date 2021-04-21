package Model;

import java.io.Serializable;
import java.time.LocalTime;

public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long ticketId;
	private LocalTime bookingTime;
	private String customerName;
	private Car car;
	private Trip trip;
	
	public Ticket() {
		// TODO Auto-generated constructor stub
	}
	public Ticket(long ticketId, LocalTime bookingTime, String customerName, Car car, Trip trip) {
		super();
		this.ticketId = ticketId;
		this.bookingTime = bookingTime;
		this.customerName = customerName;
		this.car = car;
		this.trip = trip;
	}


	public long getTicketId() {
		return ticketId;
	}


	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}


	public LocalTime getBookingTime() {
		return bookingTime;
	}


	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public Trip getTrip() {
		return trip;
	}


	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	

}
