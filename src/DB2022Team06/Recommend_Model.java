package DB2022Team06;

import java.sql.*;

import javax.swing.JOptionPane;

public class Recommend_Model {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06"; //DBMS 접속 시 아이디
    private static final String PASSWORD = "DB2022Team06"; //DBMS 접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; //DBMS 접속할 DB
    
    public void recommend(String nickname) {
    	String query1 = "select * from db2022_music natural join db2022_user where (db2022_music.genre = db2022_user.favorite_genre and nickname = ?) or genre in (select genre from db2022_playlist_user natural join db2022_user where db2022_playlist_user.user_id = db2022_user.user_id and nickname = ?)";
        String query2 = "select singer from db2022_singer where music_id = ?";
    	String query3 = "select songwriter from db2022_songwriter where music_id = ?";
        
    	try ( 
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
        	PreparedStatement pStmt1 = conn.prepareStatement(query1);
    		PreparedStatement pStmt2 = conn.prepareStatement(query2);
    		PreparedStatement pStmt3 = conn.prepareStatement(query3);
    		)
        {
        	pStmt1.setString(1, nickname);
        	pStmt1.setString(2, nickname);
        	ResultSet rs1 = pStmt1.executeQuery();
        	
        	if(!rs1.next()) {
        		JOptionPane.showMessageDialog(null, "일치하는 회원 정보가 없습니다. 회원 가입 후 서비스를 이용해주세요.");
        	}else {
        		System.out.println(nickname + "님의 취향에 맞는 추천 음악 리스트 입니다.");
        		System.out.println("---------------------------------------------------------------------------------------------");
        		System.out.println("    제목    |    가수    |    작사작곡    |    재생시간    |    좋아요 수    |    장르    |    발매일    ");
        		System.out.println("---------------------------------------------------------------------------------------------");
        		do{
        			if(rs1.getBoolean("age_limit") && rs1.getInt("age") < 20) { //유저가 20살 미만이고 곡의 나이 제한이 true면 이 튜플 건너뛰기
        				continue;
        			}else {
        				// 해당 튜플(곡)의 제목 정보 출력
        				System.out.print(rs1.getString("title") + " | ");
        				
        				// 해당 튜플(곡)의 가수 정보 출력
        				pStmt2.setInt(1, rs1.getInt("music_id"));
        				ResultSet rs2 = pStmt2.executeQuery();
        				if(!rs2.next()) {
        					System.out.println("가수 정보가 없습니다");
        				}else {
        					do {
            					System.out.print(rs2.getString("singer") + " ");
        					}while(rs2.next());
        					System.out.print("| ");
        				}
        				
        				// 해당 튜플(곡)의 작사작곡가 정보 출력
        				pStmt3.setInt(1, rs1.getInt("music_id"));
        				ResultSet rs3 = pStmt3.executeQuery(); 
        				if(!rs3.next()) {
        					System.out.println("작사작곡가 정보가 없습니다");
        				}else {
        					do {
            					System.out.print(rs3.getString("songwriter") + " ");
        					}while(rs3.next());
        					System.out.print("| ");
        				}
        				
        				// 해당 튜플(곡)의 재생시간 정보 출력
        				System.out.print(rs1.getString("playtime") + " | ");
        				
        				// 해당 튜플(곡)의 좋아요 수 정보 출력
        				System.out.print(rs1.getInt("likes") + " | ");
        				
        				// 해당 튜플(곡)의 장르 정보 출력
        				System.out.print(rs1.getString("genre") + " | ");
        				
        				// 해당 튜플(곡)의 발매일 정보 출력
        				System.out.print(rs1.getDate("release_date"));
        				System.out.println();
        			}
        		}while(rs1.next());
        		System.out.println();
        	}
        } catch (SQLException se) {
        	System.out.println("fail");
        	se.printStackTrace();
        }
    }
}