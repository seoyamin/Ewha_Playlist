import java.sql.*;
 
public class test_connection {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/new_schema"; //DBMS접속할 db명 - 로컬 상황에 맞게 바꿔서 사용해주세요
    
    public test_connection() {
        try {
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("success");
        } catch (SQLException e1) {
        	System.out.println("fail");
        }
    }
}
 
