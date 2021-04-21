package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbcontext {

	private final static String serverName = "DATNV\\SQLEXPRESS";

	private final static String dbName = "Car_Park";

	private final static String portNumber = "1433";

	private final static String username = "datnv";
	
	private final static String password = "123456";
    
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, username, password);
	}

	public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
		if (rs != null && !rs.isClosed()) {
			rs.close();
		}
		if (ps != null && !ps.isClosed()) {
			ps.close();
		}
		if (con != null && !con.isClosed()) {
			con.close();

	}
}
}
