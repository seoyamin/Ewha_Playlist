package DB2022Team06;

import java.sql.*;
import java.util.Date;


public class Management_Model {
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06"; //DBMS 접속 시 아이디
    private static final String PASSWORD = "DB2022Team06"; //DBMS 접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; //DBMS 접속할 DB
    
    public static int findMusic(String singer, String title) {
    	String query1 = "select music_id from db2022_music as m natural join db2022_singer as s where s.singer = ? and m.title = ? ";
    	Connection conn = null;
    	try {
         	conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         	PreparedStatement pstmt1 = conn.prepareStatement(query1);
         	pstmt1.setString(1, singer);
         	pstmt1.setString(2, title);
         	
         	ResultSet rs = pstmt1.executeQuery();
         	rs.next();
         	int id = rs.getInt(1);
         	return id;
    	
    	}catch (SQLException sqle) {
		    	return 0; //실패했다는 의미
		    }
	}	
    
    public void insert(Date playtime, String title, int likes, String genre, boolean age_limit, Date release_date, String situation, String season, int music_age, String songwriter, String singer) {
    	String query1 = "insert into db2022_music values(?,?,?,?,?,?,?,?,?,?)";
    	String query2 = "insert into db2022_songwriter values(?,?)";
    	String query3 = "insert into db2022_singer values(?,?)";
    	Connection conn = null;
    	
        try {
        	
        	conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
        	PreparedStatement pStmt1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
        	PreparedStatement pStmt2 = conn.prepareStatement(query2);
        	PreparedStatement pStmt3 = conn.prepareStatement(query3);
        	
        	conn.setAutoCommit(false);
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
        	
        	conn.commit();
        	conn.setAutoCommit(true);
        	
        	//변경내용 확인용 
        	ResultSet rset;
        	rset = stmt.executeQuery("with TMP AS( \r\n"
            		+ "select music_id,title,playtime,likes,genre,age_limit,release_date, GROUP_CONCAT(songwriter SEPARATOR ',') as songwriter \r\n"
            		+ "from db2022_music natural join db2022_songwriter group by music_id) \r\n"
            		+ "SELECT music_id,title,playtime,songwriter,likes,genre,age_limit,release_date,GROUP_CONCAT(singer SEPARATOR ',')\r\n"
            		+ "from TMP natural join db2022_singer group by music_id ");
        	
        	System.out.println("<변경된 점을 확인하세요>\n");

 			while (rset.next()) {

 				System.out.println(rset.getString("music_id")+" | "+rset.getString("title")+" | "+rset.getTime("playtime")+" | "+rset.getString("GROUP_CONCAT(singer SEPARATOR ',')")+" | "+rset.getString("songwriter")+" | "+rset.getInt("likes")+" | "+rset.getString("genre")+" | "+rset.getDate("release_date"));
     		
 			}
        }
        catch (SQLException sqle) {
        	if(conn!=null)
				try {
					conn.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("fail");
				}
        }
    }
    
    public void delete(String singer, String title) {
    	String query2 = "delete from db2022_music where music_id = ?"; 
    	String query3 = "delete from db2022_singer where music_id = ?"; 
    	String query4 = "delete from db2022_songwriter where music_id = ?"; 
    	String query5 = "delete from db2022_playlist_user where music_id = ?"; //foreignkey 있음 얘 먼저 작업해야함.
    	
    	Connection conn = null;
    	
    	try {
         	conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         	Statement stmt = conn.createStatement();
         	
         	int id = findMusic(singer, title); 
         	if (id == 0) {
         		System.out.println("해당하는 곡이 없습니다.");
         		return;
         	}
         			
         	PreparedStatement pstmt2 = conn.prepareStatement(query2);
         	PreparedStatement pstmt3 = conn.prepareStatement(query3);
         	PreparedStatement pstmt4 = conn.prepareStatement(query4);
         	PreparedStatement pstmt5 = conn.prepareStatement(query5);
         	
         	conn.setAutoCommit(false);
         	pstmt2.setInt(1,id);
         	pstmt3.setInt(1,id);
         	pstmt4.setInt(1,id);
         	pstmt5.setInt(1,id);
         	pstmt5.executeUpdate();
         	pstmt2.executeUpdate();
         	pstmt3.executeUpdate();
         	pstmt4.executeUpdate();
         	conn.commit();
         	conn.setAutoCommit(true);
         	
        	ResultSet rset;
        	rset = stmt.executeQuery("with TMP AS( \r\n"
            		+ "select music_id,title,playtime,likes,genre,age_limit,release_date, GROUP_CONCAT(songwriter SEPARATOR ',') as songwriter \r\n"
            		+ "from db2022_music natural join db2022_songwriter group by music_id) \r\n"
            		+ "SELECT music_id,title,playtime,songwriter,likes,genre,age_limit,release_date,GROUP_CONCAT(singer SEPARATOR ',')\r\n"
            		+ "from TMP natural join db2022_singer group by music_id ");
        	
        	System.out.println("<변경된 점을 확인하세요>\n");

 			while (rset.next()) {

 				System.out.println(rset.getString("music_id")+" | "+rset.getString("title")+" | "+rset.getTime("playtime")+" | "+rset.getString("GROUP_CONCAT(singer SEPARATOR ',')")+" | "+rset.getString("songwriter")+" | "+rset.getInt("likes")+" | "+rset.getString("genre")+" | "+rset.getDate("release_date"));
     		
 			}
         	
    	 
    	 }	
    	 catch (SQLException e1) {
    		 if(conn != null)
				try {
					conn.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("fail");
				}
         }
    }
    
    public void modify(int music_id, Date playtime, String title, int likes, String genre, boolean age_limit, Date release_date, String situation, String season, int music_age, String songwriter, String singer) {
    	Connection conn = null;
    	
    	String query1 = "update db2022_music set playtime = ?, title = ?, likes = ?, genre = ?, age_limit = ?, release_date = ?, situation = ?, season = ?, music_age = ? where music_id = ?"; 
    	
    	String query2 = "delete from db2022_songwriter where music_id = ?";
    	String query3 = "delete from db2022_singer where music_id = ?";
    	String query4 = "insert into db2022_songwriter values(?,?)";
    	String query5 = "insert into db2022_singer values(?,?)";
    	
    			
    	 try {
         	conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            PreparedStatement pStmt1 = conn.prepareStatement(query1);
            PreparedStatement pStmt2 = conn.prepareStatement(query2);
            PreparedStatement pStmt3 = conn.prepareStatement(query3);
            PreparedStatement pStmt4 = conn.prepareStatement(query4);
            PreparedStatement pStmt5 = conn.prepareStatement(query5);
        	
        	//conn.setAutoCommit(false);
        	pStmt1.setTimestamp(1, new java.sql.Timestamp(playtime.getTime()));
        	pStmt1.setString(2, title);
        	pStmt1.setInt(3, likes);
        	pStmt1.setString(4, genre);
        	pStmt1.setBoolean(5, age_limit);
        	pStmt1.setDate(6, new java.sql.Date(release_date.getTime()));
        	pStmt1.setString(7, situation);
        	pStmt1.setString(8, season);
        	pStmt1.setInt(9, music_age);
        	pStmt1.setInt(10, music_id);
        	pStmt1.executeUpdate();
        	
        	pStmt2.setInt(1,music_id);
        	pStmt3.setInt(1, music_id);
        	pStmt4.setInt(1, music_id);
        	pStmt4.setString(2,songwriter);
        	pStmt5.setInt(1, music_id);
        	pStmt5.setString(2,singer);
        	
        	pStmt2.executeUpdate();
        	pStmt3.executeUpdate();
        	pStmt4.executeUpdate();
        	pStmt5.executeUpdate();
        	
        	
        	//conn.commit();
        	//conn.setAutoCommit(true);
        	
        	ResultSet rset;
        	rset = stmt.executeQuery("with TMP AS( \r\n"
            		+ "select music_id,title,playtime,likes,genre,age_limit,release_date, GROUP_CONCAT(songwriter SEPARATOR ',') as songwriter \r\n"
            		+ "from db2022_music natural join db2022_songwriter group by music_id) \r\n"
            		+ "SELECT music_id,title,playtime,songwriter,likes,genre,age_limit,release_date,GROUP_CONCAT(singer SEPARATOR ',')\r\n"
            		+ "from TMP natural join db2022_singer group by music_id ");
        	
        	System.out.println("<변경된 점을 확인하세요>\n");

 			while (rset.next()) {

 				System.out.println(rset.getString("music_id")+" | "+rset.getString("title")+" | "+rset.getTime("playtime")+" | "+rset.getString("GROUP_CONCAT(singer SEPARATOR ',')")+" | "+rset.getString("songwriter")+" | "+rset.getInt("likes")+" | "+rset.getString("genre")+" | "+rset.getDate("release_date"));
     		
 			}
         	
    	 }
    	 catch (SQLException e1) {
    		 if(conn != null)
 				try {
 					conn.rollback();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					System.out.println("fail");
 				}
         }
    }
}