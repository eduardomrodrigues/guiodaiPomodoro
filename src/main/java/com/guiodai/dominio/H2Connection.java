package com.guiodai.dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class H2Connection {

	private static Connection conn;

	public static synchronized Connection getConnection() {

		if (conn == null) {
			try {
				Class.forName("org.h2.Driver");
				return DriverManager.getConnection("jdbc:h2:/pomodoro");

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return conn;

	}

}
