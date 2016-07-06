package Core;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class Pool {
	private static Pool connectionPool;
	private BasicDataSource data;

	Pool() throws IOException, SQLException, PropertyVetoException {
		data = new BasicDataSource();
		data.setDriverClassName("com.mysql.jdbc.Driver");
		data.setUsername("root");
		data.setPassword("asakujaku12");
		data.setUrl("jdbc:mysql://localhost/ideacloud");
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
