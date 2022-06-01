package DB2022Team06;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class music_Info_delete extends JFrame {
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06";


    public void delete(String nickname, String title, String singer) {
    	String query="insert into db2022_playlist_user(user_id, music_id,age_limit) "
    			+ "values((select user_id from db2022_user where nickname=?),(select T.music_id from db2022_music as T,db2022_singer as S where (T.title=?) and (S.singer=?)),(select T.age_limit from db2022_music as T,db2022_singer as S where (T.title=?) and (S.singer=?)))";
    	int age=0,bool=0;
    	
    	//delete from db2022_playlist_user where ((select user_id from db2022_user where nickname='ju') and (select T.music_id from db2022_music as T,db2022_singer as S where (T.title='ZOOM') and (S.singer='제시')))
    	
		try {
			System.out.println(nickname);
			System.out.println(title);
			System.out.println(singer);
			
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt1 = conn.createStatement(); 
			Statement stmt2 = conn.createStatement(); 
			//stmt.executeQuery("use DB2022Team06");
			ResultSet rs1 = stmt1.executeQuery("select age from db2022_user where nickname="+"'"+nickname+"'");
			ResultSet rs = stmt2.executeQuery("select age_limit from db2022_music, db2022_singer where (db2022_music.title="+"'"+title+"')"+"and (db2022_singer.singer="+"'"+singer+"')");
		
			conn.setAutoCommit(false);
			
			while(rs1.next()) {
				
				age=rs1.getInt(1);
				
				while(rs.next()) {
		
				bool=rs.getInt(1);
					
				if(bool==1 && age<=19) {
					System.out.print("denied: Age Limit");
					}
					
					else {
						PreparedStatement pStmt = conn.prepareStatement(query);
						
			            pStmt.setString(1, nickname);
			            pStmt.setString(2, title);
			            pStmt.setString(3, singer);
			            pStmt.setString(4, title);
			            pStmt.setString(5, singer);
			            pStmt.executeUpdate();
			            
			            System.out.println("Successfully Add\n");
			            System.out.println("---------------------------------------------------------------\n");
			            conn.commit();
					}
				}
			}
		}
			
		/*	PreparedStatement pStmt = conn.prepareStatement(query);

            pStmt.setString(1, nickname);
            pStmt.setString(2, title);
            pStmt.setString(3, singer);
            pStmt.executeUpdate();
            
            System.out.println("Successfully Add\n");
            System.out.println("---------------------------------------------------------------\n");
            conn.commit();*/
		
		catch (SQLException e1) {
			e1.printStackTrace();
			System.out.print("error");
		}
		
		}
    
	public music_Info_delete(String nickname) {
		
		JPanel jPanel = new JPanel();
		add(jPanel);
		setVisible(true);
		setTitle("곡명, 가수명 검색");
		setSize(500, 500);
		jPanel.setLayout(null);// 배치관리자 해제
		
		JLabel music=new JLabel("곡명");
		JLabel singer=new JLabel("가수명");
		jPanel.add(music);
		jPanel.add(singer);
		
		music.setLocation(125,0); 
		music.setSize(250,100); 
		
		singer.setLocation(155,0); 
		singer.setSize(250,100); 
		
		music.setVisible(true);
		singer.setVisible(true);
		
		JTextField box1=new JTextField();
		JTextField box2=new JTextField();
		
		jPanel.add(box1);
		jPanel.add(box2);
		box1.setVisible(true);
		box2.setVisible(true);	
		box1.setLocation(150, 10);
		box1.setSize(100, 19);
		box2.setLocation(100, 30);
		box2.setSize(100, 19);
		
		JButton btn=new JButton("추가");
		jPanel.add(btn);
		btn.setVisible(true);
		btn.setSize(100, 30);

		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String title,singer;
				
				title=box1.getText();
				singer=box2.getText();
		
				delete(nickname,title,singer);
				
			}
		});
		
		JButton btn1=new JButton("이전");
		jPanel.add(btn1);
		btn1.setVisible(true);
		btn1.setLocation(100, 50);
		btn1.setSize(100, 30); 
		
		btn1.addActionListener((ActionListener) new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new playlist_main(nickname);
			setVisible(false);
		}
	});
	}
}
