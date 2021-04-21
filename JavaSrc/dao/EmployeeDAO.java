package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.Dbcontext;
import Model.Employee;

public class EmployeeDAO {
	
	private static List<Employee> listEmp = null;
	private static Connection connect;
	static Dbcontext dbcontext = new Dbcontext();
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
    
	public static List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException{
		listEmp = new ArrayList<Employee>();
		connect = dbcontext.getConnection();
		String sql = "SELECT [employeeId]\r\n" + 
				"      ,[account]\r\n" + 
				"      ,[department]\r\n" + 
				"      ,[employeeAddress]\r\n" + 
				"      ,[employeeBirthdate]\r\n" + 
				"      ,[employeeEmail]\r\n" + 
				"      ,[employeeName]\r\n" + 
				"      ,[employeePhone]\r\n" + 
				"      ,[password]\r\n" + 
				"      ,[sex]\r\n" + 
				"  FROM [dbo].[Employee]";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = connect.prepareStatement(sql);
			rs = p.executeQuery();
			while(rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getLong(1));
				e.setAccount(rs.getString(2));
				e.setDepartmentName(rs.getString(3));
				e.setEmployeeAddress(rs.getString(4));
				e.setEmployeeBirthdate(rs.getDate(5).toLocalDate());
				e.setEmployeeEmail(rs.getString(6));
				e.setEmployeeName(rs.getString(7));
				e.setEmployeePhone(rs.getString(8));
				e.setPassword(rs.getString(9));
				e.setSex(rs.getBoolean(10));
				listEmp.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcontext.closeConnection(rs, p, connect);
		}
		return listEmp;
	}
	
	/**
	 * 
	 * @param pindex
	 * @param psize
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static List<Employee> pagingSearch(int pindex, int psize) throws SQLException, ClassNotFoundException{
		listEmp = new ArrayList<Employee>();
		int start = psize * (pindex - 1) + 1; // Ex: 11 -> 20 , psize = 10, pindex = 2 => start = 10 * (2 - 1) + 1 = 11
		int end = pindex * psize;  
		connect = dbcontext.getConnection();
		String sql = "SELECT [employeeId]\r\n" + 
				"      ,[department]\r\n" + 
				"      ,[employeeAddress]\r\n" + 
				"      ,[employeeBirthdate]\r\n" + 
				"      ,[employeeEmail]\r\n" + 
				"      ,[employeeName]\r\n" + 
				"      ,[employeePhone]\r\n" + 
				"      ,[password]\r\n" + 
				"      ,[sex]\r\n" + 
				"  FROM (Select *, ROW_NUMBER() Over(Order by employeeId asc) as row_num from Employee) as clone " +
				"  WHERE row_num >= ? and row_num <= ?";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = connect.prepareStatement(sql);
			p.setInt(1, start);
			p.setInt(2, end);
			rs = p.executeQuery();
			while(rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getLong(1));
				e.setAccount(rs.getString(2));
				e.setDepartmentName(rs.getString(3));
				e.setEmployeeAddress(rs.getString(4));
				e.setEmployeeBirthdate(rs.getDate(5).toLocalDate());
				e.setEmployeeEmail(rs.getString(6));
				e.setEmployeeName(rs.getString(7));
				e.setEmployeePhone(rs.getString(8));
				e.setPassword(rs.getString(9));
				e.setSex(rs.getBoolean(10));
				listEmp.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcontext.closeConnection(rs, p, connect);
		}
		
		return listEmp;
	}
	
	
	/**
	 * 
	 * @param id
	 * @param pindex
	 * @param psize
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static List<Employee> pagingFindById(int id, int pindex, int psize) throws SQLException, ClassNotFoundException{
		listEmp = new ArrayList<Employee>();
		int start = psize * (pindex - 1) + 1; // Ex: 11 -> 20 , psize = 10, pindex = 2 => start = 10 * (2 - 1) + 1 = 11
		int end = pindex * psize;  
		connect = dbcontext.getConnection();
		String sql = "SELECT [employeeId]\r\n" + 
				"      ,[account]\r\n" + 
				"      ,[department]\r\n" + 
				"      ,[employeeAddress]\r\n" + 
				"      ,[employeeBirthdate]\r\n" + 
				"      ,[employeeEmail]\r\n" + 
				"      ,[employeeName]\r\n" + 
				"      ,[employeePhone]\r\n" + 
				"      ,[password]\r\n" + 
				"      ,[sex]\r\n" + 
				"  FROM (Select *, ROW_NUMBER() Over(Order by employeeId asc) as row_num from Employee Where employeeId = ?) as clone " +
				"  WHERE row_num >= ? and row_num <= ?";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = connect.prepareStatement(sql);
			p.setInt(1, id);
			p.setInt(2, start);
			p.setInt(3, end);
			rs = p.executeQuery();
			while(rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getLong(1));
				e.setAccount(rs.getString(2));
				e.setDepartmentName(rs.getString(3));
				e.setEmployeeAddress(rs.getString(4));
				e.setEmployeeBirthdate(rs.getDate(5).toLocalDate());
				e.setEmployeeEmail(rs.getString(6));
				e.setEmployeeName(rs.getString(7));
				e.setEmployeePhone(rs.getString(8));
				e.setPassword(rs.getString(9));
				e.setSex(rs.getBoolean(10));
				listEmp.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcontext.closeConnection(rs, p, connect);
		}
		
		return listEmp;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Employee getEmpById(int id) throws SQLException, ClassNotFoundException{
		Employee e = new Employee();
		connect = dbcontext.getConnection();
		String sql = "SELECT [employeeId]\r\n" + 
				"      ,[account]\r\n" + 
				"      ,[department]\r\n" + 
				"      ,[employeeAddress]\r\n" + 
				"      ,[employeeBirthdate]\r\n" + 
				"      ,[employeeEmail]\r\n" + 
				"      ,[employeeName]\r\n" + 
				"      ,[employeePhone]\r\n" + 
				"      ,[password]\r\n" + 
				"      ,[sex]\r\n" + 
				"  FROM [dbo].[Employee]" +
		        "  WHERE employeeId = ?";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = connect.prepareStatement(sql);
			rs = p.executeQuery();
			if(rs.next()) {
				e.setId(rs.getLong(1));
				e.setAccount(rs.getString(2));
				e.setDepartmentName(rs.getString(3));
				e.setEmployeeAddress(rs.getString(4));
				e.setEmployeeBirthdate(rs.getDate(5).toLocalDate());
				e.setEmployeeEmail(rs.getString(6));
				e.setEmployeeName(rs.getString(7));
				e.setEmployeePhone(rs.getString(8));
				e.setPassword(rs.getString(9));
				e.setSex(rs.getBoolean(10));
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}finally {
			dbcontext.closeConnection(rs, p, connect);
		}
		return e;
	}
	
	
	/**
	 * 
	 * @param emp
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static boolean addEmployee(Employee emp) throws SQLException, ClassNotFoundException{
		connect =  dbcontext.getConnection(); 
		String sql = "INSERT INTO [dbo].[Employee]\r\n" + 
				"           ([account]\r\n" + 
				"           ,[department]\r\n" + 
				"           ,[employeeAddress]\r\n" + 
				"           ,[employeeBirthdate]\r\n" + 
				"           ,[employeeEmail]\r\n" + 
				"           ,[employeeName]\r\n" + 
				"           ,[employeePhone]\r\n" + 
				"           ,[password]\r\n" + 
				"           ,[sex])\r\n" + 
				"     VALUES\r\n" + 
				"           (?,?,?,?,?,?,?,?,?)";
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = connect.prepareStatement(sql);
			p.setString(1, emp.getAccount());
			p.setString(2, emp.getDepartmentName());
			p.setString(3, emp.getEmployeeAddress());
			p.setDate(4, Date.valueOf(emp.getEmployeeBirthdate()));
			p.setString(5, emp.getEmployeeEmail());
			p.setString(6, emp.getEmployeeName());
			p.setString(7, emp.getEmployeePhone());
			p.setString(8, emp.getPassword());
			p.setBoolean(9, emp.isSex());
			p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			dbcontext.closeConnection(rs, p, connect);
		}
		return true;
	}
	public static boolean deleteEmployee(long id) throws ClassNotFoundException, SQLException {
		connect = dbcontext.getConnection();
		String sql = "DELETE FROM [dbo].[Employee]\r\n" + 
				"      WHERE employeeId=?"; 
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; 
        try {
			preparedStatement = connect.prepareStatement(sql); 
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			dbcontext.closeConnection(resultSet, preparedStatement, connect);
		}
        return true;
	}
	
	public static boolean updateEmployee(Employee emp) throws ClassNotFoundException, SQLException {
		connect = dbcontext.getConnection(); 
		String sql = " UPDATE [dbo].[Employee]\r\n" + 
				"   SET [account] = ?\r\n" + 
				"      ,[department] = ?\r\n" + 
				"      ,[employeeAddress] = ?\r\n" + 
				"      ,[employeeBirthdate] = ?\r\n" + 
				"      ,[employeeEmail] = ?\r\n" + 
				"      ,[employeeName] = ?\r\n" + 
				"      ,[employeePhone] = ?\r\n" + 
				"      ,[password] = ?\r\n" + 
				"      ,[sex] = ?\r\n" + 
				" WHERE employeeId=?"; 
		PreparedStatement p = null;
        ResultSet resultSet = null; 
        try {
			p  = connect.prepareStatement(sql); 
			p.setString(1, emp.getAccount());
			p.setString(2, emp.getDepartmentName());
			p.setString(3, emp.getEmployeeAddress());
			p.setDate(4, Date.valueOf(emp.getEmployeeBirthdate()));
			p.setString(5, emp.getEmployeeEmail());
			p.setString(6, emp.getEmployeeName());
			p.setString(7, emp.getEmployeePhone());
			p.setString(8, emp.getPassword());
			p.setBoolean(9, emp.isSex());
			p.setLong(10, emp.getId());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			dbcontext.closeConnection(resultSet, p, connect);
		}
        return true; 
	}
}
