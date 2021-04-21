package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Model.Car;
import Model.Ticket;
import Model.Trip;
import context.Dbcontext;

public class TicketDao {
private static List<Ticket> listTickets= null;
private static Connection connection; 
static Dbcontext dbcontext = new Dbcontext();
public static int countRecord() throws ClassNotFoundException, SQLException {
	int numberRecord = -1; 
	connection = dbcontext.getConnection();
	String sql = "Select COUNT(*) From Ticket"; 
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		ps = connection.prepareStatement(sql); 
		rs = ps.executeQuery();
		if(rs.next()) {
			numberRecord = rs.getInt(1); 
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	return numberRecord;
}
public static List<Ticket> PagingSearch(int pIndex, int pSize) throws ClassNotFoundException, SQLException{
	listTickets = new ArrayList<Ticket>();
	int start = pSize *(pIndex-1)+1;
	int end = pIndex * pSize;
	connection = dbcontext.getConnection();
	String sql = "select t.ticketId, Trip.destination, c.licensePlate, t.customerName, t.bookingTime\r\n" + 
			"From (Select * , ROW_NUMBER() Over(Order by ticketId asc) as row_num From Ticket) as t inner join Trip on t.tripId = Trip.tripId  inner join Car as c on t.licensePlate=c.licensePlate \r\n" + 
			"Where row_num >= ? and row_num <= ?"; 
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		ps = connection.prepareStatement(sql);
		ps.setInt(1, start);
		ps.setInt(2, end);
		rs= ps.executeQuery();
		while(rs.next()) {
			Ticket ticket = new Ticket();
			Trip trip = new Trip(); 
			Car car = new Car();
			ticket.setTicketId(rs.getLong(1));
			trip.setDestination(rs.getString(2));
			ticket.setTrip(trip);
			car.setLicensePlate(rs.getString(3));
			ticket.setCar(car);
			ticket.setCustomerName(rs.getString(4));
			ticket.setBookingTime(rs.getTime(5).toLocalTime());
			listTickets.add(ticket); 
			
		}
	} catch (Exception e) {
	     e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	} 
	return listTickets;
}
public static Ticket pagingFindById(int id, int pIndex, int pSize) throws ClassNotFoundException, SQLException {
	Ticket ticket = new Ticket();
	int start = pSize *(pIndex-1)+1;
	int end = pIndex * pSize;
	connection = dbcontext.getConnection();
	String sql = "\r\n" + 
			"select t.ticketId, Trip.destination, c.licensePlate, t.customerName, t.bookingTime\r\n" + 
			"From (Select * , ROW_NUMBER() Over(Order by ticketId asc) as row_num From Ticket where ticketId = ?) as t inner join Trip on t.tripId = Trip.tripId  inner join Car as c on t.licensePlate=c.licensePlate \r\n" + 
			"Where row_num >= ? and row_num <= ?"; 
	PreparedStatement p = null;
	ResultSet rs = null;
	try {
		p = connection.prepareStatement(sql);
		p.setInt(1, id);
		p.setInt(2, start);
		p.setInt(3, end);
		rs = p.executeQuery();
		if(rs.next()) {
			
			Trip trip = new Trip(); 
			Car car = new Car();
			ticket.setTicketId(rs.getLong(1));
			trip.setDestination(rs.getString(2));
			ticket.setTrip(trip);
			car.setLicensePlate(rs.getString(3));
			ticket.setCar(car);
			ticket.setCustomerName(rs.getString(4));
			ticket.setBookingTime(rs.getTime(5).toLocalTime());
			listTickets.add(ticket); 
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, p, connection);
	}
	return ticket;
}
public static List<Ticket> searchByFilter(int type, String input, int pIndex, int pSize) throws ClassNotFoundException, SQLException{
        connection = dbcontext.getConnection();
        int start = pSize *(pIndex-1)+1;
    	int end = pIndex * pSize;
    	String param = ""; 
    	switch (type) {
		case 1:
			param += " and customerName like ? "; 
			break;
		case 2: 
			param += " and bookingTime = ?"; 
			break;
		case 3: 
			param += "and Trip.destination = ?"; 
			break;
		case 4: 
			param += "and c.licensePlate = ?"; 
			break;
		
		}
    	String sql = "select t.ticketId, Trip.destination, c.licensePlate, t.customerName, t.bookingTime\r\n" + 
    			"From (Select * , ROW_NUMBER() Over(Order by ticketId asc) as row_num From Ticket ) as t inner join Trip on t.tripId = Trip.tripId  inner join Car as c on t.licensePlate=c.licensePlate \r\n" + 
    			"Where row_num >= ? and row_num <= ? \r\n" + 
    			"" + param; 
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			ps = connection.prepareStatement(sql); 
			System.out.println(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
		    switch (type) {
			case 1:
			case 3: 
			case 4: 
				ps.setString(3, "%"+ input +"%");
				break;
			case 2: 
				LocalTime localTime = LocalTime.parse(input); 
				ps.setTime(3, Time.valueOf(localTime));
			    break;
			}
		    rs = ps.executeQuery();
		    while(rs.next()) {
		    	Ticket ticket = new Ticket();
		    	Trip trip = new Trip(); 
				Car car = new Car();
				ticket.setTicketId(rs.getLong(1));
				trip.setDestination(rs.getString(2));
				ticket.setTrip(trip);
				car.setLicensePlate(rs.getString(3));
				ticket.setCar(car);
				ticket.setCustomerName(rs.getString(4));
				ticket.setBookingTime(rs.getTime(5).toLocalTime());
				listTickets.add(ticket); 
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcontext.closeConnection(rs, ps, connection);
		}
    	return listTickets;
    	
}
public static int countRecordFilter(int type, String input) throws ClassNotFoundException, SQLException {
	int numberRecord = -1 ; 
	connection = dbcontext.getConnection();
	String param = ""; 
	switch (type) {
	case 1:
		param += "where customerName like?";
		break;
	case 2: 
		param += "where bookingTime like?";
		break;
	case 3: 
		param += "as t inner join Trip on t.tripId = Trip.tripId \r\n" + 
				"where Trip.destination like?";
		break;
	case 4: 
		param +="as t inner join Car as c on t.licensePlate=c.licensePlate\r\n" + 
				"where c.licensePlate like?"; 
		break;
	}
	String sql = "select COUNT(*) \r\n" + 
			"from Ticket" + param;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		ps = connection.prepareStatement(sql); 
		 switch (type) {
			case 1:
			case 3: 
			case 4: 
				ps.setString(3, "%"+ input +"%");
				break;
			case 2: 
				LocalTime localTime = LocalTime.parse(input); 
				ps.setTime(3, Time.valueOf(localTime));
			    break;
			}
		 rs = ps.executeQuery();
		 if(rs.next()) {
			 numberRecord = rs.getInt(1);
		 }
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	return numberRecord;
} 
public static boolean deleteTicket(int id) throws ClassNotFoundException, SQLException {
	connection = dbcontext.getConnection();
	String sql = "DELETE FROM [dbo].[Ticket]\r\n" + 
			"      WHERE ticketId = ?"; 
	PreparedStatement ps = null;
	ResultSet rs = null; 
	try {
		ps = connection.prepareStatement(sql); 
		ps.setInt(1, id);
		ps.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	return true;
}
}
