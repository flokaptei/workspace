package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
	{

	}

	Connection connection;

	public DataBaseConnector(String dbIP, String dbName, String dbPort, String username, String password)
			throws SQLException {

		String connectionstring = "jdbc:mysql://";
		if(dbIP == null || dbIP.equals("")){
			connectionstring+="127.0.0.1:";
		}else{
			connectionstring+=dbIP+":";
		}
		if(dbPort == null || dbPort.equals("")){
			connectionstring+="3306/"+dbName;
		}else{
			connectionstring+=dbPort+"/"+dbName;
		}
		connection = DriverManager.getConnection(connectionstring,username,password);
		

	}
}
