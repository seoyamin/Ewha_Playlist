package DB2022Team06;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class music_Info_insert extends JFrame {
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06";
    
	
	public music_Info_insert(String nickname) {
		
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
		box1.setLocation(90, 50);
		box1.setSize(100, 19);
		box2.setLocation(100, 30);
		box2.setSize(100, 19);
		
		JButton btn=new JButton("추가");
		jPanel.add(btn);
		btn.setVisible(true);
		btn.setSize(100, 30);
		
		btn.addActionListener(new ActionListener() {
			String title,singer;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String query="insert into db2022_playlist_user(user_id,music_id) values(?, select music_id from db2022_music,db2022_singer where (db2022_music.id=db2022_singer.music.id), (db2022_music.title=?) and (db2022_singer.singer=?))";
				
				title=box1.getText();
				singer=box2.getText();
				
				try (
					Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					Statement stmt = conn.createStatement(); 
		            //stmt.executeQuery("use DB2022Team06");
					PreparedStatement pStmt = conn.prepareStatement(query);)
				{
					conn.setAutoCommit(false);

	                pStmt.setString(1, nickname);
	                pStmt.setString(2, title);
	                pStmt.executeUpdate();
	                
	                System.out.println("플레이리스트에 음악 추가가 완료되었습니다.\n");
	                System.out.println("---------------------------------------------------------------\n");
	                conn.commit();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
					System.out.print("error");
				}
				
			}
		});
		
		JButton btn1=new JButton("이전");
		jPanel.add(btn1);
		btn1.setVisible(true);
		btn1.setSize(100, 30); 
		
		/*btn1.addActionListener((ActionListener) new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new playlist_main(nickname);
			setVisible(false);
		}
	});*/
	}
}

