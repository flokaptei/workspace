package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseConnector {
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Connection connection;

	public DataBaseConnector(String dbIP, String dbName, String dbPort, String username, String password)
			throws SQLException {

		String connectionstring = "jdbc:mysql://";
		if (dbIP == null || dbIP.equals("")) {
			connectionstring += "127.0.0.1:";
		} else {
			connectionstring += dbIP + ":";
		}
		if (dbPort == null || dbPort.equals("")) {
			connectionstring += "3306/" + dbName;
		} else {
			connectionstring += dbPort + "/" + dbName+"?autoreconnect=true&useSSL=false";
		}
		connection = DriverManager.getConnection(connectionstring, username, password);

	}

	public String executeQuery(String Query) {

		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(Query);
			ResultSetMetaData resultsetmetadata = resultset.getMetaData();

			String out = "";
			while (resultset.next()) {
				for (int i = 1; i <= resultsetmetadata.getColumnCount(); i++) {
					out += resultset.getString(i) + "    ";
				}
				out += "\n";
			}

			statement.close();
			resultset.close();
			return out;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getErrorCode();
			e.getSQLState();
			return null;
		}

	}
	
	public static void main(String[] args) {
		try {
			DataBaseConnector d = new DataBaseConnector("127.0.0.1", "test", "3306", "flo", null);
			System.out.println(d.executeQuery("Select * From kunden"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
