package DB2022Team06;

import java.sql.*;

import javax.swing.JOptionPane;

public class Recommend_Model {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06"; //DBMS 접속 시 아이디
    private static final String PASSWORD = "DB2022Team06"; //DBMS 접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; //DBMS 접속할 DB
    
    public void recommend(String nickname) {
    	//유저의 선호 장르와 나만의 플레이리스트에 담은 곡의 장르 전체를 fav_genre(genre)에 저장하고 이를 이용해 music 테이블에서 해당하는 장르의 곡 정보를 모두 가져오는 쿼리
    	String query1 = "with fav_genre(genre) as ((select distinct favorite_genre as genre from db2022_user where nickname = ?) "
    			+ "union (select distinct genre from db2022_playlist_user natural join db2022_user natural join db2022_music where db2022_music.music_id = db2022_playlist_user.music_id and db2022_playlist_user.user_id = db2022_user.user_id and nickname = ?)) "
    			+ "select * from db2022_music where genre in (select * from fav_genre)";
        String query2 = "select singer from db2022_singer where music_id = ?"; //추천 목록에 들어간 곡의 가수 정보를 가져오기 위한 쿼리
    	String query3 = "select songwriter from db2022_songwriter where music_id = ?"; //추천 목록에 들어간 곡의 작사작곡 정보를 가져오기 위한 쿼리
    	String query4 = "select age from db2022_user where nickname = ?"; //유저의 연령을 확인하기 위한 쿼리
        
    	try ( 
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
        	PreparedStatement pStmt1 = conn.prepareStatement(query1);
    		PreparedStatement pStmt2 = conn.prepareStatement(query2);
    		PreparedStatement pStmt3 = conn.prepareStatement(query3);
    		PreparedStatement pStmt4 = conn.prepareStatement(query4);
    		)
        {
    		//입력된 nickname 쿼리에 넣어 쿼리 실행하기
        	pStmt1.setString(1, nickname);
        	pStmt1.setString(2, nickname);
        	pStmt4.setString(1, nickname);
        	ResultSet rs1 = pStmt1.executeQuery(); //추천할 곡 목록 및 정보 가져오기
        	ResultSet rs4 = pStmt4.executeQuery(); //유저의 나이 정보 가져오기
        	
        	if(!rs1.next()) {
        		JOptionPane.showMessageDialog(null, "일치하는 회원 정보가 없습니다. 회원 가입 후 서비스를 이용해주세요.");
        	}else {
        		rs4.next();
        		System.out.println(nickname + "님의 취향에 맞는 추천 음악 리스트 입니다.");
        		System.out.println("---------------------------------------------------------------------------------------------");
        		System.out.println("    제목    |    가수    |    작사작곡    |    재생시간    |    좋아요 수    |    장르    |    발매일    ");
        		System.out.println("---------------------------------------------------------------------------------------------");
        		do{
        			if(rs1.getBoolean("age_limit") && rs4.getInt("age") < 20) { //곡의 나이 제한이 true고 유저가 20살 미만이면 이 튜플 건너뛰기
        				continue;
        			}else {
        				//해당 튜플(곡)의 제목 정보 출력
        				System.out.print(rs1.getString("title") + " | ");
        				
        				//해당 튜플(곡)의 가수 정보 출력
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
        				
        				//해당 튜플(곡)의 작사작곡가 정보 출력
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
        				
        				//해당 튜플(곡)의 재생시간 정보 출력
        				System.out.print(rs1.getString("playtime") + " | ");
        				
        				//해당 튜플(곡)의 좋아요 수 정보 출력
        				System.out.print(rs1.getInt("likes") + " | ");
        				
        				//해당 튜플(곡)의 장르 정보 출력
        				System.out.print(rs1.getString("genre") + " | ");
        				
        				//해당 튜플(곡)의 발매일 정보 출력
        				System.out.print(rs1.getDate("release_date"));
        				System.out.println();
        			}
        		}while(rs1.next());
        		System.out.println();
        	}
        } catch (SQLException se) { //에러가 발생할 경우 fail 메시지와 함께 에러 내용 출력
        	System.out.println("fail");
        	se.printStackTrace();
        }
    }
}