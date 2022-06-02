package DB2022Team06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TOP10 {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; //DBMS접속할 db명 - 로컬 상황에 맞게 바꿔서 사용해주세요
    
    public TOP10() {

    	try {
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("success");
            
            Statement stmt = conn.createStatement(); 
            
            try {
            ResultSet rset = stmt.executeQuery(
            		"with TMP AS( \r\n"
            		+ "select music_id,title,playtime,likes,genre,age_limit,release_date, GROUP_CONCAT(songwriter SEPARATOR ',') as songwriter \r\n"
            		+ "from db2022_music natural join db2022_songwriter group by music_id) \r\n"
            		+ "SELECT music_id,title,playtime,songwriter,likes,genre,age_limit,release_date,GROUP_CONCAT(singer SEPARATOR ',')\r\n"
            		+ "from TMP natural join db2022_singer group by music_id order by likes desc"
            	);
            

			System.out.println("전체음원목록\n");
			System.out.println("순위|제목|재생시간|가수|작사작곡가|likes|장르|발매일\n");
			int rank=1;
			
            		while (rset.next()&&rank<=10) {
            			
            		System.out.println(rank+"위: "+rset.getString("title")+" | "+rset.getTime("playtime")+" | "+rset.getString("GROUP_CONCAT(singer SEPARATOR ',')")+" | "+rset.getString("songwriter")+" | "+rset.getInt("likes")+" | "+rset.getString("genre")+" | "+rset.getDate("release_date"));
            		rank++;
            		
            		
            		}
            
            }
            catch (SQLException sqle)
            {
            System.out.println("Could not select tuple. " + sqle);
            }
            	
            stmt.close();
            conn.close();
        } catch (SQLException e1) {
        	System.out.println("fail");
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 보이도록하기.
        
        new TOP10();
        
     }
}