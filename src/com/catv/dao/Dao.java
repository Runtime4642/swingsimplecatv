package com.catv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

	protected Connection getConnection() throws SQLException  
	{
		Connection con =null;
		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2 연결
			String url = "jdbc:mysql://localhost:3306/catv?useSSL=false&serverTimezone=UTC";
			 con = DriverManager.getConnection(url, "root", "123420");
		}
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		return con;
	}
	
	

}
