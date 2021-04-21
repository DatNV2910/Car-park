package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.ParkingLot;
import context.Dbcontext;

public class ParkingLotDao {
private static Connection connection = null; 
static Dbcontext dbcontext = new Dbcontext();
public static boolean addParkinglot(ParkingLot p) throws ClassNotFoundException, SQLException {
	connection = dbcontext.getConnection();
	String sql = "INSERT INTO [dbo].[Parkinglot]\r\n" + 
			"           ([parkArea]\r\n" + 
			"           ,[parkName]\r\n" + 
			"           ,[parkPlace]\r\n" + 
			"           ,[parkPrice]\r\n" + 
			"           ,[parkStatus])\r\n" + 
			"     VALUES\r\n" + 
			"           (?\r\n" + 
			"           ,?\r\n" + 
			"           ,?\r\n" + 
			"           ,?\r\n" + 
			"           ,?)"; 
	PreparedStatement ps = null;
	ResultSet rs = null; 
	try {
		ps = connection.prepareStatement(sql); 
		ps.setLong(1, p.getParkArea());
		ps.setString(2, p.getParkName());
		ps.setString(3, p.getParkPlace());
		ps.setLong(4, p.getParkPrice());
		ps.setString(5, p.getParkStatus());
		ps.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}finally {
	   dbcontext.closeConnection(rs, ps, connection);
	}
	return false;
}
public static int countRecord() throws ClassNotFoundException, SQLException {
	int numberRecord = -1; 
	connection = dbcontext.getConnection();
	String sql = "Select COUNT(*) From Parkinglot"; 
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
public static List<ParkingLot> pagingSearch(int pindex,  int psize) throws ClassNotFoundException, SQLException{
	List<ParkingLot> listParkingLots = new ArrayList<ParkingLot>();
	int start = psize * (pindex - 1) + 1; // Ex: 11 -> 20 , psize = 10, pindex = 2 => start = 10 * (2 - 1) + 1 = 11
	int end = pindex * psize;  
	connection = dbcontext.getConnection();
	String sql = "SELECT parkId , parkArea, parkName, parkPlace, parkPrice, parkStatus\r\n" + 
			"				  FROM (Select *, ROW_NUMBER() Over(Order by parkId asc) as row_num from Parkinglot) as clone \r\n" + 
			"				 WHERE row_num >= ? and row_num <= ?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		ps = connection.prepareStatement(sql); 
		ps.setInt(1, start);
		ps.setInt(2, end);
		rs = ps.executeQuery();
		while(rs.next()) {
			ParkingLot parkingLot = new ParkingLot();
			parkingLot.setParkId(rs.getLong(1));
			parkingLot.setParkArea(rs.getLong(2));
			parkingLot.setParkName(rs.getString(3));
			parkingLot.setParkPlace(rs.getString(4));
			parkingLot.setParkPrice(rs.getLong(5));
			parkingLot.setParkStatus(rs.getString(6));
			listParkingLots.add(parkingLot); 
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	return listParkingLots;
}
public static ParkingLot pagingFindById(int id, int pindex, int psize) throws ClassNotFoundException, SQLException {
	ParkingLot pk = new ParkingLot();
	int start = psize * (pindex - 1) + 1; // Ex: 11 -> 20 , psize = 10, pindex = 2 => start = 10 * (2 - 1) + 1 = 11
	int end = pindex * psize;
	connection = dbcontext.getConnection();
	String sql = "SELECT parkId , parkArea, parkName, parkPlace, parkPrice, parkStatus\r\n" + 
			"				  FROM (Select *, ROW_NUMBER() Over(Order by parkId asc) as row_num from Parkinglot Where parkId = ?) as clone \r\n" + 
			"				 WHERE row_num >= ? and row_num <= ?"; 
	PreparedStatement ps = null;
	ResultSet rs = null; 
	try {
		ps = connection.prepareStatement(sql); 
		ps.setInt(1, id);
		ps.setInt(2, start);
		ps.setInt(3, end);
		rs = ps.executeQuery();
		if(rs.next()) {
			pk.setParkId(rs.getLong(1));
			pk.setParkArea(rs.getLong(2));
			pk.setParkName(rs.getString(3));
			pk.setParkPlace(rs.getString(4));
			pk.setParkPrice(rs.getLong(5));
			pk.setParkStatus(rs.getString(6));
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	return pk;
}
public static boolean updateParkingLot(ParkingLot p) throws ClassNotFoundException, SQLException {
	connection = dbcontext.getConnection();
	String sql = "UPDATE [dbo].[Parkinglot]\r\n" + 
			"   SET [parkArea] = ?\r\n" + 
			"      ,[parkName] = ?\r\n" + 
			"      ,[parkPlace] = ?\r\n" + 
			"      ,[parkPrice] = ?\r\n" + 
			"      ,[parkStatus] = ?\r\n" + 
			" WHERE parkId = ?"; 
	PreparedStatement ps = null;
	ResultSet rs = null; 
	try {
		ps = connection.prepareStatement(sql); 
		ps.setLong(1, p.getParkArea());
		ps.setString(2, p.getParkName());
		ps.setString(3, p.getParkPlace());
		ps.setLong(4, p.getParkPrice());
		ps.setString(5, p.getParkStatus());
		ps.setLong(6, p.getParkId());
		ps.executeUpdate(); 
	} catch (Exception e) {
		e.printStackTrace();
		return false; 
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	return true;
}
public static boolean deleteParkingLot(int id) throws ClassNotFoundException, SQLException {
	connection = dbcontext.getConnection();
	String sql = "DELETE FROM [dbo].[Parkinglot]\r\n" + 
			"      WHERE parkId = ?"; 
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		ps = connection.prepareStatement(sql); 
	    ps.setInt(1, id);
	    ps.executeUpdate();
	    
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		dbcontext.closeConnection(rs, ps, connection);
	}
	return true;
}
public static int countRecordFilter(int type, String input) throws ClassNotFoundException, SQLException {
	int numberRecord = -1; 
	connection = dbcontext.getConnection(); 
	String param = ""; 
	switch (type) {
	case 1:
		param += " parkId = ?"; 
		break;
	case 2: 
		param += " parkArea = ?"; 
		break;
	case 3: 
		param += " parkName like ?"; 
		break;
	case 4: 
		param += " parkPlace like ?"; 
		break;
	case 5: 
		param += " parkPrice = ?";
		break;
	case 6: 
		param += " parkStatus like ?";
		break;
	
	}
	String sql = "Select COUNT(*) From Parkinglot where " + param; 
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		ps = connection.prepareStatement(sql); 
		switch (type) {
		case 1:
		case 2: 
		case 5: 
			ps.setLong(1, Long.parseLong(input));
			break;
		case 3: 
		case 4: 
		case 6: 
			ps.setString(1, "%" + input +"%");
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
public static List<ParkingLot> searchByFilter(int type, String input, int pindex, int psize) throws ClassNotFoundException, SQLException{
	List<ParkingLot> listP = new ArrayList<ParkingLot>();
	connection = dbcontext.getConnection();
	int start = psize * (pindex - 1) + 1;
	int end = psize * pindex;
	String param ="";
	switch (type) {
	case 1:
		param += " parkId = ?"; 
		break;
	case 2: 
		param += " parkArea = ?"; 
		break;
	case 3: 
		param += " parkName like ?"; 
		break;
	case 4: 
		param += " parkPlace like ?"; 
		break;
	case 5: 
		param += " parkPrice = ?";
		break;
	case 6: 
		param += " parkStatus like ?";
		break;
	} 
	String sql = "SELECT  parkId, parkArea, parkName, parkPlace, parkPrice, parkStatus\r\n" + 
			"				  FROM (Select *, ROW_NUMBER() Over(Order by employeeId asc) as row_num from Parkinglot where "+param+ " \r\n" + 
			"				) as clone Where row_num >= ? and row_num <= ? ";
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		ps = connection.prepareStatement(sql); 
		System.out.println(sql);
		switch (type) {
		case 1:
		case 2: 
		case 5: 
			ps.setLong(1, Long.parseLong(input));
			break;
		case 3: 
		case 4: 
		case 6: 
			ps.setString(1, "%" + input +"%");
			break;
		
		}
		ps.setInt(2, start);
		ps.setInt(3, end);
		rs=ps.executeQuery();
		while(rs.next()) {
			ParkingLot parkingLot = new ParkingLot();
			parkingLot.setParkId(rs.getLong(1));
			parkingLot.setParkArea(rs.getLong(2));
			parkingLot.setParkName(rs.getString(3));
			parkingLot.setParkPlace(rs.getString(4));
			parkingLot.setParkPrice(rs.getLong(5));
			parkingLot.setParkStatus(rs.getString(6));
			listP.add(parkingLot); 
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbcontext.closeConnection(rs, ps, connection);
	} 
	return listP;
}
}
