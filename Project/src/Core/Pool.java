package Core;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class Pool {
	private static Pool connectionPool;
	private BasicDataSource data;

	private Pool() throws IOException, SQLException, PropertyVetoException {
		data = new BasicDataSource();
		data.setDriverClassName("com.mysql.jdbc.Driver");
		data.setUsername("root");
		data.setPassword("gela123");
		data.setUrl("");
	}

	public static Pool getPool() throws IOException, SQLException,
			PropertyVetoException {
		if (connectionPool == null) {
			connectionPool = new Pool();
			return connectionPool;
		} else {
			return connectionPool;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.data.getConnection();
	}
}
