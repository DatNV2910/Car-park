package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Trip implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long tripId;
	private int bookedTicketNumber;
	private String carType;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private String destination;
	private String driver;
	private int maximumOnlineTicketNumber;
	
	
	public Trip() {
		// TODO Auto-generated constructor stub
	}
	public Trip(long tripId, int bookedTicketNumber, String carType, LocalDate departureDate, LocalTime departureTime,
			String destination, String driver, int maximumOnlineTicketNumber) {
		super();
		this.tripId = tripId;
		this.bookedTicketNumber = bookedTicketNumber;
		this.carType = carType;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.destination = destination;
		this.driver = driver;
		this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
	}
	
	public Trip(int bookedTicketNumber, String carType, LocalDate departureDate, LocalTime departureTime,
			String destination, String driver, int maximumOnlineTicketNumber) {
		super();
		this.bookedTicketNumber = bookedTicketNumber;
		this.carType = carType;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.destination = destination;
		this.driver = driver;
		this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
	}
	public long getTripId() {
		return tripId;
	}
	
	public void setTripId(long tripId) {
		this.tripId = tripId;
	}
	
	public int getBookedTicketNumber() {
		return bookedTicketNumber;
	}
	
	public void setBookedTicketNumber(int bookedTicketNumber) {
		this.bookedTicketNumber = bookedTicketNumber;
	}
	
	public String getCarType() {
		return carType;
	}
	
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getDriver() {
		return driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	public int getMaximumOnlineTicketNumber() {
		return maximumOnlineTicketNumber;
	}
	
	public void setMaximumOnlineTicketNumber(int maximumOnlineTicketNumber) {
		this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
	}
	
	

}
