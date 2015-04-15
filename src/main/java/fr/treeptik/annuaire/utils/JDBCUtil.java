package fr.treeptik.annuaire.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

	private static Connection connection;

	public static Connection getConnection()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {

		// Chargement du driver mysql
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		if (connection == null || connection.isClosed()) {

			// URL
			String url = "jdbc:mysql://localhost:3306/tpannuairemaven";
			// Connection
			connection = DriverManager.getConnection(url, "root", "root");
			connection.setAutoCommit(false);
		}
		return connection;

	}
}
