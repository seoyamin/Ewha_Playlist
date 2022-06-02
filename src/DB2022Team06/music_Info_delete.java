package DB2022Team06;
import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class music_Info_delete extends JFrame {
	
	Color EWHA_GREEN = new Color(0,70,42);
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06";


    public void delete(String nickname, String title, String singer) {
    	String query="delete from db2022_playlist_user where user_id=(select user_id from db2022_user where nickname=?) and music_id=(select T.music_id from db2022_music as T,db2022_singer as S where (T.title=?) and (S.singer=?))";
    	int age=0,bool=0;
    	
		try {
			System.out.println("---------------------------------------------------------------\n");
			System.out.println("nickname: "+nickname);
			System.out.println("title: "+title);
			System.out.println("singer: "+singer+"\n");
			
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
			            pStmt.executeUpdate();
			            
			            System.out.println("Successfully DELETE\n");
			            System.out.println("---------------------------------------------------------------\n");
			            conn.commit();
					}
				}
			}
		}
		
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
		setSize(450, 800);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jPanel.setLayout(null);// 배치관리자 해제
		
		JLabel labe=new JLabel("플레이리스트에서 삭제할 노래의 곡명과 가수명을 입력하세요");
		jPanel.add(labe); labe.setVisible(true); labe.setLocation(60, 70); labe.setSize(400, 100);
		
		JLabel music=new JLabel("곡명"); JLabel singer=new JLabel("가수명");
		jPanel.add(music); jPanel.add(singer);
		
		music.setLocation(100,150); music.setSize(250,100); 
		singer.setLocation(100,200); singer.setSize(250,100); 
		music.setVisible(true); singer.setVisible(true);
		
		JTextField box1=new JTextField(); JTextField box2=new JTextField();
		
		jPanel.add(box1); jPanel.add(box2);
		box1.setVisible(true); box2.setVisible(true);	
		box1.setLocation(170, 185); box1.setSize(150, 30);
		box2.setLocation(170, 235); box2.setSize(150, 30);
		
		JButton btn=new JButton("삭제");
		jPanel.add(btn); btn.setVisible(true); btn.setSize(100, 40);btn.setLocation(170, 300);
		btn.setBackground(EWHA_GREEN); btn.setForeground(Color.WHITE);
		
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
		jPanel.add(btn1); btn1.setVisible(true); 
		btn1.setLocation(170, 360); btn1.setSize(100, 40); btn1.setBackground(Color.gray); btn1.setForeground(Color.WHITE);
		btn1.addActionListener((ActionListener) new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new playlist_main(nickname);
			setVisible(false);
		}
	});
	}
}
