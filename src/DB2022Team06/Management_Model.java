package DB2022Team06;

import java.sql.*;
import java.util.Date;


public class Management_Model {
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06"; //DBMS 접속 시 아이디
    private static final String PASSWORD = "DB2022Team06"; //DBMS 접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; //DBMS 접속할 DB
    
    public void insert(Date playtime, String title, int likes, String genre, boolean age_limit, Date release_date, String situation, String season, int music_age, String songwriter, String singer) {
    	String query1 = "insert into db2022_music values(?,?,?,?,?,?,?,?,?,?)";
    	String query2 = "insert into db2022_songwriter values(?,?)";
    	String query3 = "insert into db2022_singer values(?,?)";
    	
        try {
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
        	PreparedStatement pStmt1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
        	PreparedStatement pStmt2 = conn.prepareStatement(query2);
        	PreparedStatement pStmt3 = conn.prepareStatement(query3);
        	
          	pStmt1.setInt(1,0);
        	pStmt1.setTimestamp(2, new java.sql.Timestamp(playtime.getTime()));
        	pStmt1.setString(3, title);
        	pStmt1.setInt(4, likes);
        	pStmt1.setString(5, genre);
        	pStmt1.setBoolean(6, age_limit);
        	pStmt1.setDate(7, new java.sql.Date(release_date.getTime()));
        	pStmt1.setString(8, situation);
        	pStmt1.setString(9, season);
        	pStmt1.setInt(10, music_age);
        	pStmt1.executeUpdate();
        	
        	//새로 입력한 id 구하기
        	int autoIncrement = 0;
        	ResultSet rs = pStmt1.getGeneratedKeys();
        	if (rs.next()) {
        		autoIncrement = rs.getInt(1);
        	}
        	System.out.println(autoIncrement);
        	
        	pStmt2.setInt(1, autoIncrement);
        	pStmt2.setString(2, songwriter);
        	pStmt2.executeUpdate();
        	
        	pStmt3.setInt(1, autoIncrement);
        	pStmt3.setString(2, singer);
        	pStmt3.executeUpdate();
        	
        	ResultSet rset;
        	rset = stmt.executeQuery("with TMP AS( \r\n"
            		+ "select music_id,title,playtime,likes,genre,age_limit,release_date, GROUP_CONCAT(songwriter SEPARATOR ',') as songwriter \r\n"
            		+ "from db2022_music natural join db2022_songwriter group by music_id) \r\n"
            		+ "SELECT music_id,title,playtime,songwriter,likes,genre,age_limit,release_date,GROUP_CONCAT(singer SEPARATOR ',')\r\n"
            		+ "from TMP natural join db2022_singer group by music_id ");
        	
        	System.out.println("<전체 음원 목록>\n");

 			while (rset.next()) {

 				System.out.println(rset.getString("music_id")+" | "+rset.getString("title")+" | "+rset.getTime("playtime")+" | "+rset.getString("GROUP_CONCAT(singer SEPARATOR ',')")+" | "+rset.getString("songwriter")+" | "+rset.getInt("likes")+" | "+rset.getString("genre")+" | "+rset.getDate("release_date"));
     		
 			}
        }
        catch (SQLException e1) {
        	System.out.println("fail");
        }
    }
}