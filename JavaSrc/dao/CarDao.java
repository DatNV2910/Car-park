package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Car;
import Model.ParkingLot;
import context.Dbcontext;

public class CarDao {

	private static List<Car> listCars = null;
	
	private static Connection connection;
	static Dbcontext dbcontext = new Dbcontext();

	public static List<Car> getAllCars() throws SQLException, ClassNotFoundException {
		listCars = new ArrayList<Car>();
		connection = dbcontext.getConnection();
		String sql = " select * from Car ";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = connection.prepareStatement(sql);
			rs = p.executeQuery();
			while (rs.next()) {
				Car car = new Car();
				car.setLicensePlate(rs.getString("licensePlate"));
				car.setCarColor(rs.getString("carColor"));
				car.setCarType(rs.getString("carType"));
				car.setCompany(rs.getString("company"));
				ParkingLot parkingLot = new ParkingLot();
				parkingLot.setParkId(rs.getLong("parkId"));
				parkingLot.setParkName(rs.getString("parkName"));
				car.setParkingLot(parkingLot);
				listCars.add(car);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcontext.closeConnection(rs, p, connection);
		}
		return listCars;
	}
	public static Car getCarById(String linse) throws SQLException, ClassNotFoundException {
		Car car = new Car();
		connection = dbcontext.getConnection();
		String sql = " \r\n" + 
				"select * from Car \r\n" + 
				"where licensePlate = ?"; 
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p= connection.prepareStatement(sql); 
		    rs=p.executeQuery();
		    if(rs.next()) {
		    car.setLicensePlate(rs.getString("licensePlate"));
			car.setCarColor(rs.getString("carColor"));
			car.setCarType(rs.getString("carType"));
			car.setCompany(rs.getString("company"));
			ParkingLot parkingLot = new ParkingLot();
			parkingLot.setParkId(rs.getLong("parkId")); 
			car.setParkingLot(parkingLot);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbcontext.closeConnection(rs, p, connection);
		}
		return car;
	}
	public static boolean deleteCars(String licensePlate) throws SQLException, ClassNotFoundException {
		connection = dbcontext.getConnection();
		String sql ="DELETE FROM [dbo].[Car]\r\n" + 
				"      WHERE licensePlate = ?"; 
		PreparedStatement p = null;
		ResultSet rs = null; 
		try {
			p = connection.prepareStatement(sql);
			p.setString(1, licensePlate);
			p.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			dbcontext.closeConnection(rs, p, connection);
		}
		return true;
	}
	public static boolean UpdateCar(Car car) throws ClassNotFoundException, SQLException {
		connection = dbcontext.getConnection();
		String sql = "UPDATE [dbo].[Car]\r\n" + 
				"   SET [licensePlate] = ?\r\n" + 
				"      ,[carColor] = ?\r\n" + 
				"      ,[carType] = ?\r\n" + 
				"      ,[company] = ?\r\n" + 
				"      ,[parkId] = ?\r\n" + 
				" WHERE licensePlate = ?";
		PreparedStatement p =null;
		ResultSet rs = null;
		try {
			ParkingLot parkingLot = new ParkingLot();
			p = connection.prepareStatement(sql); 
			p.setString(1, car.getLicensePlate());
			p.setString(2, car.getCarColor());
			p.setString(3, car.getCarType());
			p.setString(4, car.getCompany());
			p.setLong(5, parkingLot.getParkId());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false; 
		} finally {
		   dbcontext.closeConnection(rs, p, connection);
		}
		return true;
	}
	public static boolean addCar(Car car) throws SQLException, ClassNotFoundException {
		connection = dbcontext.getConnection();
		String sql = " INSERT INTO [dbo].[Car]\r\n" + 
				"           ([licensePlate]\r\n" + 
				"           ,[carColor]\r\n" + 
				"           ,[carType]\r\n" + 
				"           ,[company]\r\n" + 
				"           ,[parkId])\r\n" + 
				"     VALUES\r\n" + 
				"           (?\r\n" + 
				"           ,?\r\n" + 
				"           ,?\r\n" + 
				"           ,?\r\n" + 
				"           ,?)";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			ParkingLot parkingLot = new ParkingLot();
			p = connection.prepareStatement(sql); 
			p.setString(1, car.getLicensePlate());
			p.setString(2, car.getCarColor());
			p.setString(3, car.getCarType());
			p.setString(4, car.getCompany());
			p.setLong(5, parkingLot.getParkId());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
		    dbcontext.closeConnection(rs, p, connection);
		}
		return true;
	}
	public static List<Car> pagingSearch(int pindex, int psize) throws SQLException, ClassNotFoundException{
		listCars = new ArrayList<Car>();
		int start = psize * (pindex - 1) + 1;
		int end = pindex * psize;
		connection = dbcontext.getConnection();
		String sql = "select licensePlate, carColor,carType,company,parkId\r\n" + 
				"from ( select *, ROW_NUMBER() Over(Order by licensePlate asc) as row_num from Car) as clone \r\n" + 
				"where row_num>=? and row_num <=?\r\n" + 
				"";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = connection.prepareStatement(sql);
			p.setInt(1, start);
			p.setInt(2, end);
			rs = p.executeQuery();
			while(rs.next()) {
				Car car = new Car();
				ParkingLot parkingLot = new ParkingLot();
				car.setLicensePlate(rs.getString(1));
				car.setCarColor(rs.getString(2));
				car.setCarType(rs.getString(3));
				car.setCompany(rs.getString(4));
				parkingLot.setParkId(rs.getLong(5));
				car.setParkingLot(parkingLot);
				listCars.add(car);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcontext.closeConnection(rs, p, connection);
		}
		return listCars;
	}
	public static List<Car> pagingFindByLicese(String lincese, int pIndex, int pSize) throws SQLException, ClassNotFoundException{
		listCars = new ArrayList<Car>();
		int start = pSize * (pIndex - 1) + 1;
		int end = pIndex * pSize; 
		connection = dbcontext.getConnection();
		String sql = "select licensePlate, carColor,carType,company,parkId\r\n" + 
				"from ( select *, ROW_NUMBER() Over(Order by licensePlate asc) as row_num from Car where licensePlate=?) as clone \r\n" + 
				"where row_num>=? and row_num <=?";
		PreparedStatement p = null;
		ResultSet rs = null; 
		try {
			p = connection.prepareStatement(sql); 
			p.setString(1, lincese);
			p.setInt(2, start);
			p.setInt(3, end);
			rs=p.executeQuery();
			while(rs.next()) {
				Car car = new Car();
				ParkingLot parkingLot = new ParkingLot();
				car.setLicensePlate(rs.getString(1));
				car.setCarColor(rs.getString(2));
				car.setCarType(rs.getString(3));
				car.setCompany(rs.getString(4));
				parkingLot.setParkId(rs.getLong(5));
			    car.setParkingLot(parkingLot);
			    listCars.add(car); 
			    
			}
		} catch (Exception e) {
		e.printStackTrace();
		
		}finally {
			dbcontext.closeConnection(rs, p, connection);
		}
		return listCars;
	}
	public static int countRecord() throws ClassNotFoundException, SQLException {
		int numberRecourd = -1;
		connection = dbcontext.getConnection();
		String sql = "Select COUNT(*) From Car"; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql); 
			rs = ps.executeQuery();
			if(rs.next()) {
				numberRecourd = rs.getInt(1); 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			dbcontext.closeConnection(rs, ps, connection);
		}
		return numberRecourd;
	}
	public static int countRecordFilter(int type, String input) throws ClassNotFoundException, SQLException {
		int numberRecord = -1; 
		connection = dbcontext.getConnection();
		String param = ""; 
		switch (type) {
		case 1:
			param += "where carColor like ?";
			break;
		case 2: 
			param += "where carType like ?"; 
            break;
		case 3: 
			param += "where company like ?";
			break;
		case 4: 
			param += "inner join Parkinglot t on Car.parkId = t.parkId Where t.parkName like ?";
			break;
		
		}
		String sql = "Select COUNT(*) From Car" + param;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql); 
			ps.setString(1, "%"+input +"%");
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
	public static List<Car> searchByFilter(int type, String input, int pIndex, int pSize) throws ClassNotFoundException, SQLException{
		connection  = dbcontext.getConnection();
		int start = pSize * (pIndex-1)+1; 
		int end = pSize * pIndex;
		String param = ""; 
		switch (type) {
		case 1:
			param += " and carType = ?"; 
			break;
		case 2: 
			param += "and carColor = ?"; 
			break;
		case 3: 
			param += "and company = ?";
			break;
		case 4: 
			param += " and p.parkName like ?"; 
			break;
		}
		String sql = "select clone.licensePlate,\r\n" + 
				"clone.carColor, clone.carType, clone.company, p.parkId, p.parkName \r\n" + 
				"from ( Select * , ROW_NUMBER() Over(Order by licensePlate asc) as row_num From Car ) as clone inner join Parkinglot p on clone.parkId=p.parkId \r\n" + 
				"where row_num >= ? and row_num<= ? " + param;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		   ps = connection.prepareStatement(sql); 
		   System.out.println(sql);
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   ps.setString(3, "%" + input + "%");
		   rs = ps.executeQuery();
		   while(rs.next()) {
			   Car car = new Car();
			   car.setLicensePlate(rs.getString(1));
			   car.setCarColor(rs.getString(2));
			   car.setCarType(rs.getString(3));
			   car.setCompany(rs.getString(4));
			   ParkingLot parkingLot = new ParkingLot();
			   parkingLot.setParkId(rs.getLong(5));
			   parkingLot.setParkName(rs.getString(6));
			   car.setParkingLot(parkingLot);
			   listCars.add(car); 
			   
		   }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcontext.closeConnection(rs, ps, connection);
		}
		return listCars;
	}
}
