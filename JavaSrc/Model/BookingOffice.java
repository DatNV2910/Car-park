package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class BookingOffice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long officeId;
	private LocalDate endContractDeadline;
	private String officeName;
	private String officePhone;
	private String officePlace;
	private long officePrice;
	private LocalDate startContractDeadline;
	private Trip trip;
	public BookingOffice() {
		// TODO Auto-generated constructor stub
	}
	public BookingOffice(long officeId, LocalDate endContractDeadline, String officeName, String officePhone,
			String officePlace, long officePrice, LocalDate startContractDeadline, Trip trip) {
		super();
		this.officeId = officeId;
		this.endContractDeadline = endContractDeadline;
		this.officeName = officeName;
		this.officePhone = officePhone;
		this.officePlace = officePlace;
		this.officePrice = officePrice;
		this.startContractDeadline = startContractDeadline;
		this.trip = trip;
	}

	public long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(long officeId) {
		this.officeId = officeId;
	}

	public LocalDate getEndContractDeadline() {
		return endContractDeadline;
	}

	public void setEndContractDeadline(LocalDate endContractDeadline) {
		this.endContractDeadline = endContractDeadline;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getOfficePlace() {
		return officePlace;
	}

	public void setOfficePlace(String officePlace) {
		this.officePlace = officePlace;
	}

	public long getOfficePrice() {
		return officePrice;
	}

	public void setOfficePrice(long officePrice) {
		this.officePrice = officePrice;
	}

	public LocalDate getStartContractDeadline() {
		return startContractDeadline;
	}

	public void setStartContractDeadline(LocalDate startContractDeadline) {
		this.startContractDeadline = startContractDeadline;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	
	
}
