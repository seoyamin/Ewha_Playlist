package DB2022Team06;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class playlist_main extends JFrame {
	
	Color EWHA_GREEN = new Color(0,70,42);
    int BTNSIZE = 250;
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06";
    
    /*나만의 플레이리스트 음악 조회*/
    public void user_musicPlaylist(String nickname) {
    	String query = "select title, singer from db2022_music as S, db2022_singer as M,db2022_playlist_user as T "
    			+ "where user_id=(select user_id from db2022_user where nickname=?) and T.music_id=S.music_id and S.music_id=M.music_id";
    	try {
    		int i=1;
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	            //stmt.executeQuery("use DB2022Team06");

	            PreparedStatement pStmt = conn.prepareStatement(query);
	            pStmt.setString(1, nickname);
	            
	            ResultSet rs = pStmt.executeQuery();
	            
	            System.out.println("_____________________________");
            	System.out.println(nickname+"님의 플레이리스트 조회");
            	System.out.println("_____________________________");
            	
	            while(rs.next()) {
	            	System.out.printf("%d. %-20s| %-20s\n", i, rs.getString(1), rs.getString(2));
	            	i++;
	            }
    		} 
    		catch (SQLException e) {
				e.printStackTrace();
			}
    }
    
    /* 전체 음악 목록 sql*/
    public void Total_musicPlaylist() {
    	try {
    		int i=1;
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement stmt = conn.createStatement(); 
	            //stmt.executeQuery("use DB2022Team06");
	            ResultSet rs = stmt.executeQuery("select * from db2022_all_song");
	            System.out.println("_____________________________");
            	System.out.println("전체 음악 목록 조회");
            	System.out.println("_____________________________");
	            while(rs.next()) {
	            	System.out.printf("%d. %-20s| %-20s\n", i, rs.getString(1), rs.getString(2));
	            	i++;
	            }
    		} 
    		catch (SQLException e) {
				e.printStackTrace();
			}
    }

	public playlist_main(String nickname) {
		
		super("닉네임 확인 완료_플레이리스트");
		
		JPanel jPanel=new JPanel();
		add(jPanel);
		setSize(450,800); setVisible(true); setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jPanel.setLayout(null);// 배치관리자 해제
		
		JLabel jLabel=new JLabel(nickname+"의 플레이리스트");
		jPanel.add(jLabel); jLabel.setVisible(true); jLabel.setSize(250, 100); jLabel.setLocation(170,30);
		
		/* 플레이리스트에 음악 추가 */
		JButton btn1=new JButton("음악 추가");
		jPanel.add(btn1);
		btn1.setVisible(true); btn1.setSize(BTNSIZE, 50); btn1.setLocation(85, 130);
		btn1.setForeground(Color.WHITE); btn1.setBackground(EWHA_GREEN);
		btn1.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new music_Info_insert(nickname);
				setVisible(false);
			}
		});
		
		/* 플레이리스트에 음악 삭제 */
		JButton btn2=new JButton("음악 삭제");
		jPanel.add(btn2,BorderLayout.CENTER);
		btn2.setVisible(true);  btn2.setSize(BTNSIZE, 50); btn2.setLocation(85,230);
		btn2.setForeground(Color.WHITE); btn2.setBackground(EWHA_GREEN);
		btn2.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new music_Info_delete(nickname);
				setVisible(false);
			}
		});
		
		/* 나만의 플레이리스트 음악 조회 */
		JButton btn3=new JButton("나의 음악 목록 조회");
		jPanel.add(btn3,BorderLayout.CENTER);
		btn3.setVisible(true); btn3.setSize(BTNSIZE, 50); btn3.setLocation(85, 330);
		btn3.setForeground(Color.WHITE); btn3.setBackground(EWHA_GREEN);
		btn3.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user_musicPlaylist(nickname);
			}
		});
		
		/* 전체 플레이리스트 음악 조회 */
		JButton btn4=new JButton("전체 음악 목록 조회");
		jPanel.add(btn4,BorderLayout.CENTER);
		btn4.setVisible(true); btn4.setSize(BTNSIZE, 50); btn4.setLocation(85, 440);
		btn4.setForeground(Color.WHITE); btn4.setBackground(EWHA_GREEN);
		btn4.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Total_musicPlaylist();
			}
		});
		
		/*이전 화면으로 돌아가기*/
		JButton btn5 = new JButton("이전");
		jPanel.add(btn5,BorderLayout.CENTER);
		btn5.setVisible(true); btn5.setSize(100, 50); btn5.setLocation(125, 540);
		btn5.setForeground(Color.WHITE); btn5.setBackground(Color.gray);
		btn5.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame();
				setVisible(false);
			}
		});
	}

}