package com.guiodai.dominio;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DDLCreator {

	public static void fiatLux(){
		
		
		Connection con = H2Connection.getConnection();
		try {
			Statement st = con.createStatement();
			st.executeQuery("CREATE TABLE USER (ID VARCHAR(255), ETAPA INT)");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
