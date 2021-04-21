package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Model.Trip;
import context.Dbcontext;

public class TripDao {
private static List<Trip> listTrips = null;
private static Connection connection;
static Dbcontext dbcontext = new Dbcontext();
public static List<Trip> getAllTrips() throws ClassNotFoundException, SQLException{
	listTrips = new ArrayList<Trip>();
	connection = dbcontext.getConnection();
	String sql = "select * from Trip"; 
	PreparedStatement p = null;
	ResultSet rs = null;
	try {
		p = connection.prepareStatement(sql); 
		rs= p.executeQuery();
		while(rs.next()) {
			Trip trip = new Trip();
			trip.setTripId(rs.getLong("tripId"));
			trip.setBookedTicketNumber(rs.getInt("bookedTicketNumber"));
			trip.setCarType(rs.getString("carType"));
			trip.setDepartureDate(rs.getDate("departureDate").toLocalDate());
			trip.setDepartureTime(rs.getTime("departureTime").toLocalTime());
			trip.setDestination(rs.getString("destination"));
			trip.setDriver(rs.getString("driver"));
			trip.setMaximumOnlineTicketNumber(rs.getInt("maximumOnlineTicketNumber"));
			listTrips.add(trip); 
		}
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	} finally {
		dbcontext.closeConnection(rs, p, connection);
	}
	return listTrips;
}
public static Trip getTripById(int id) throws SQLException, ClassNotFoundException {
	Trip  trip = new Trip();
	connection = dbcontext.getConnection();
	String sql = "select * from Trip where tripId = ?"; 
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		ps = connection.prepareStatement(sql); 
		rs = ps.executeQuery();
	    if(rs.next()) {
	    	trip.setTripId(rs.getLong("tripId"));
	    	trip.setBookedTicketNumber(rs.getInt("bookedTicketNumber"));
	    	trip.setCarType(rs.getString("carType"));
	    	trip.setDepartureDate(rs.getDate("departureDate").toLocalDate());
	    	trip.setDepartureTime(rs.getTime("departureTime").toLocalTime());
	    	trip.setDestination(rs.getString("destination"));
	    	trip.setDriver(rs.getString("driver"));
	    	trip.setMaximumOnlineTicketNumber(rs.getInt("maximumOnlineTicketNumber"));
	    }
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	return trip;
}
public static boolean addTrip(Trip trip) throws ClassNotFoundException, SQLException {
	connection = dbcontext.getConnection();
	String sql = "INSERT INTO [dbo].[Trip]\r\n" + 
			"           ([bookedTicketNumber]\r\n" + 
			"           ,[carType]\r\n" + 
			"           ,[departureDate]\r\n" + 
			"           ,[departureTime]\r\n" + 
			"           ,[destination]\r\n" + 
			"           ,[driver]\r\n" + 
			"           ,[maximumOnlineTicketNumber])\r\n" + 
			"     VALUES\r\n" + 
			"           (?\r\n" + 
			"           ,?\r\n" + 
			"           ,?\r\n" + 
			"           ,?\r\n" + 
			"           ,?\r\n" + 
			"           ,?\r\n" + 
			"           ,?)";
	PreparedStatement p = null;
	ResultSet rs = null;
	try {
		p = connection.prepareStatement(sql);
		p.setInt(1, trip.getBookedTicketNumber());
		p.setString(2, trip.getCarType());
		p.setDate(3, java.sql.Date.valueOf(trip.getDepartureDate()));
		p.setTime(4, Time.valueOf(trip.getDepartureTime()));
		p.setString(5, trip.getDestination());
		p.setString(6, trip.getDriver());
		p.setInt(7, trip.getMaximumOnlineTicketNumber());
		p.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} finally {
		dbcontext.closeConnection(rs, p, connection);
	}
	return true;
}
public static boolean deleteTrip(long id) throws ClassNotFoundException, SQLException { 
	connection = dbcontext.getConnection();
	String sql = "DELETE FROM [dbo].[Trip]\r\n" + 
			"      WHERE tripId = ? ";
	PreparedStatement p = null;
	ResultSet rs = null;
	try {
		 p = connection.prepareStatement(sql); 
		 p.setLong(1, id);
		 p.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
		return false; 
		
	} finally {
		dbcontext.closeConnection(rs, p, connection);
	}
	return true;
}
public static boolean UpdateTrip(Trip trip) throws ClassNotFoundException, SQLException {
	connection = dbcontext.getConnection();
	String sql = "UPDATE [dbo].[Trip]\r\n" + 
			"   SET [bookedTicketNumber] = ?\r\n" + 
			"      ,[carType] = ?\r\n" + 
			"      ,[departureDate] = ?\r\n" + 
			"      ,[departureTime] = ?\r\n" + 
			"      ,[destination] = ?\r\n" + 
			"      ,[driver] = ?\r\n" + 
			"      ,[maximumOnlineTicketNumber] = ?\r\n" + 
			" WHERE tripId = ?";
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	try {
		preparedStatement = connection.prepareStatement(sql); 
		preparedStatement.setInt(1, trip.getBookedTicketNumber());
		preparedStatement.setString(2, trip.getCarType());
		preparedStatement.setDate(3, java.sql.Date.valueOf(trip.getDepartureDate()));
		preparedStatement.setTime(4, Time.valueOf(trip.getDepartureTime()));
		preparedStatement.setString(5, trip.getDestination());
		preparedStatement.setString(6, trip.getDriver());
		preparedStatement.setInt(7, trip.getMaximumOnlineTicketNumber());
		preparedStatement.setLong(8, trip.getTripId());
		preparedStatement.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} finally {
		dbcontext.closeConnection(resultSet, preparedStatement, connection);
	}
	return true;
}
   public static List<Trip> pagingSearch(int pIndex, int pSize) throws ClassNotFoundException, SQLException{
	   listTrips = new ArrayList<Trip>();
	   int start = pIndex * (pIndex-1) +1; 
	   int end = pIndex * pSize; 
	   connection = dbcontext.getConnection();
	   String sql = "SELECT tripId, bookedTicketNumber, carType , departureDate, departureTime , destination, driver, maximumOnlineTicketNumber\r\n" + 
	   		"				  FROM (Select *, ROW_NUMBER() Over(Order by tripId asc) as row_num from Trip) as clone \r\n" + 
	   		"				 WHERE row_num >= ? and row_num <= ?"; 
	   PreparedStatement p = null;
	   ResultSet rs = null;
	   try {
		p = connection.prepareStatement(sql); 
		p.setInt(1, start);
		p.setInt(2, end);
		rs = p.executeQuery();
		while(rs.next()) {
			Trip trip = new Trip();
			trip.setTripId(rs.getLong(1));
			trip.setBookedTicketNumber(rs.getInt(2));
			trip.setCarType(rs.getString(3));
			trip.setDepartureDate(rs.getDate(4).toLocalDate());
			trip.setDepartureTime(rs.getTime(5).toLocalTime());
			trip.setDestination(rs.getString(6));
			trip.setDriver(rs.getString(7));
			trip.setMaximumOnlineTicketNumber(rs.getInt(8));
			listTrips.add(trip); 
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, p, connection);
	}
	   return listTrips;
   }
   public static List<Trip> pagingFindById(int id, int pIndex, int pSize) throws ClassNotFoundException, SQLException{
	   listTrips = new ArrayList<Trip>();
	   int start = pSize * (pIndex-1) + 1 ; 
	   int end = pIndex * pSize; 
	   connection = dbcontext.getConnection();
	   String sql = "SELECT tripId, bookedTicketNumber, carType , departureDate, departureTime , destination, driver, maximumOnlineTicketNumber\r\n" + 
	   		"				  FROM (Select *, ROW_NUMBER() Over(Order by tripId asc) as row_num from Trip where tripId=?) as clone \r\n" + 
	   		"				 WHERE row_num >= ? and row_num <= ?";
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	   try {
		ps = connection.prepareStatement(sql); 
		ps.setInt(1, id);
		ps.setInt(2, start);
		ps.setInt(3, end);
		rs=ps.executeQuery(); 
		if(rs.next()) {
			Trip trip = new Trip();
			trip.setTripId(rs.getLong(1));
			trip.setBookedTicketNumber(rs.getInt(2));
			trip.setCarType(rs.getString(3));
			trip.setDepartureDate(rs.getDate(4).toLocalDate());
			trip.setDepartureTime(rs.getTime(5).toLocalTime());
			trip.setDestination(rs.getString(6));
			trip.setDriver(rs.getString(7));
			trip.setMaximumOnlineTicketNumber(rs.getInt(8));
			listTrips.add(trip); 
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	   return listTrips;
   }
   public static int countRecordFilter(int type, String input) throws ClassNotFoundException, SQLException {
	   int numberRecord = -1; 
	   connection = dbcontext.getConnection();
	   String param = ""; 
	   switch (type) {
	case 1:
		param += "bookedTicketNumber = ?"; 
		break;
	case 2: 
		param += "carType like ?"; 
		break;
	case 3: 
		param += "departureDate = ?";
		break;
	case 4: 
		param += "departureTime = ?"; 
		break;
	case 5: 
		param += "destination like ?";
		break;
	case 6: 
		param += "driver like ?"; 
		break;
	
	}
	   String sql = "Select COUNT(*) From Trip Where" + param;
	   PreparedStatement ps = null; 
	   ResultSet rs = null;
	   try {
		ps = connection.prepareStatement(sql); 
		switch (type) {
		case 1: 
			ps.setInt(1, Integer.parseInt(input));
			break;
		case 2:
		case 5:
		case 6: 
			ps.setString(1, "%"+ input +"%");
			break;
		case 3: 
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			LocalDate localDate = LocalDate.parse(input,dt);
			ps.setDate(1, Date.valueOf(localDate));
			break;
		case 4: 
		    DateTimeFormatter tm = DateTimeFormatter.ofPattern("HH:mm:ss"); 
		    LocalTime localTime = LocalTime.parse(input,tm);
		    ps.setTime(1, Time.valueOf(localTime));
		    break;
		
		default:
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
   public static List<Trip> searchListByFilter(int type, String input, int pIndex, int pSize) throws ClassNotFoundException, SQLException{
	   listTrips = new ArrayList<Trip>(); 
	   connection = dbcontext.getConnection();
	   int start = pSize * (pIndex-1)+1; 
	   int end = pSize*pIndex;
	   String param =""; 
	   switch (type) {
	   case 1:
			param += "bookedTicketNumber = ?"; 
			break;
		case 2: 
			param += "carType like ?"; 
			break;
		case 3: 
			param += "departureDate = ?";
			break;
		case 4: 
			param += "departureTime = ?"; 
			break;
		case 5: 
			param += "destination like ?";
			break;
		case 6: 
			param += "driver like ?"; 
			break;
        
	
	}
	   String sql = "SELECT tripId, bookedTicketNumber, carType , departureDate, departureTime , destination, driver, maximumOnlineTicketNumber\r\n" + 
	   		"	   					  FROM (Select *, ROW_NUMBER() Over(Order by tripId asc) as row_num from Trip where + "+param+") as clone \r\n" + 
	   		"	   					 WHERE row_num >= ? and row_num <= ?"; 
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	   try {
		ps = connection.prepareStatement(sql); 
		System.out.println(sql);
		switch (type) {
		case 1: 
			ps.setInt(1, Integer.parseInt(input));
			break;
		case 2:
		case 5:
		case 6: 
			ps.setString(1, "%"+ input +"%");
			break;
		case 3: 
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			LocalDate localDate = LocalDate.parse(input,dt);
			ps.setDate(1, Date.valueOf(localDate));
			break;
		case 4: 
		    DateTimeFormatter tm = DateTimeFormatter.ofPattern("HH:mm:ss"); 
		    LocalTime localTime = LocalTime.parse(input,tm);
		    ps.setTime(1, Time.valueOf(localTime));
		    break;
 
		}
	    ps.setInt(2, start);
	    ps.setInt(3, end);
	    rs = ps.executeQuery();
	    while(rs.next()) {
	    	Trip trip = new Trip();
			trip.setTripId(rs.getLong(1));
			trip.setBookedTicketNumber(rs.getInt(2));
			trip.setCarType(rs.getString(3));
			trip.setDepartureDate(rs.getDate(4).toLocalDate());
			trip.setDepartureTime(rs.getTime(5).toLocalTime());
			trip.setDestination(rs.getString(6));
			trip.setDriver(rs.getString(7));
			trip.setMaximumOnlineTicketNumber(rs.getInt(8));
	    	listTrips.add(trip); 
	    	
	    }
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	   return listTrips;
   }
   public static int countRecord() throws ClassNotFoundException, SQLException {
	   int numberRecord = -1;
	   connection =dbcontext.getConnection();
	   String sql = "Select COUNT(*) From trip"; 
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
}
