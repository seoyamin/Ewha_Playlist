package DB2022Team06;

import java.sql.*;
import java.util.Date;


public class Management_Model {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06"; //DBMS 접속 시 아이디
    private static final String PASSWORD = "DB2022Team06"; //DBMS 접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; //DBMS 접속할 DB
    
    public void insert(String playtime, String title, int likes, String genre, boolean age_limit, String release_date, String situation, String season, int music_age) {
    	
        try (
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
        )
        {
        	
        }
        catch (SQLException e1) {
        	System.out.println("fail");
        }
    }
}