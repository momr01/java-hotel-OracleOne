package ar.com.momr.back.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	public Connection recuperarConexion() {
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true",
					"root",
					"admin"				
					);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
