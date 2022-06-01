package DB2022Team06;

import java.sql.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Search {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";
    private static final String PASSWORD = "DB2022Team06";
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; 
    
    public void initJPanel(Container contentpane, MainFrame mainframe, JPanel panel) {
    	Color EWHA_GREEN = new Color(0,70,42);
    	JPanel jpanel = new JPanel();
    	
    	contentpane.add(jpanel);
    	jpanel.setLayout(null);
    	
    	
    	// 메인 화면으로 돌아가기 버튼
		JButton goMainBtn = new JButton("메인");
		goMainBtn.setLocation(10,10);
		goMainBtn.setSize(80, 40);
		goMainBtn.setForeground(Color.WHITE); //글씨 색상
		goMainBtn.setBackground(Color.GRAY);
		jpanel.add(goMainBtn);
		
		//위 버튼이 눌렸을 때 메인으로 돌아가는 액션
		goMainBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel.setVisible(false);
				panel.setVisible(true);
			}
		});
		
		
		// 제목으로 검색하기
		JLabel titleLabel = new JLabel("제목으로 검색하기", JLabel.CENTER);
		titleLabel.setFont(new Font("고딕", Font.BOLD, 17));
		titleLabel.setLocation(120, 90);
		titleLabel.setSize(200,200);
		jpanel.add(titleLabel);
		
		JTextField titleTf = new JTextField(30);
		titleTf.setLocation(90, 220);
		titleTf.setSize(200, 40);
		jpanel.add(titleTf);
		
		JButton titleBtn = new JButton("검색");
		titleBtn.setLocation(290, 220);
		titleBtn.setSize(60, 40);
		titleBtn.setForeground(Color.WHITE);
		titleBtn.setBackground(EWHA_GREEN);
		jpanel.add(titleBtn);
		titleBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = titleTf.getText();
				show_title(title);
			}
		});
		
		
		// 가수명으로 검색하기
		JLabel singerLabel = new JLabel("가수명으로 검색하기", JLabel.CENTER);
		singerLabel.setFont(new Font("고딕", Font.BOLD, 17));
		singerLabel.setLocation(120, 290);
		singerLabel.setSize(200,200);
		jpanel.add(singerLabel);
		
		JTextField singerTf = new JTextField(30);
		singerTf.setLocation(90, 420);
		singerTf.setSize(200, 40);
		jpanel.add(singerTf);
		
		JButton singerBtn = new JButton("검색");
		singerBtn.setLocation(290, 420);
		singerBtn.setSize(60, 40);
		singerBtn.setForeground(Color.WHITE);
		singerBtn.setBackground(EWHA_GREEN);
		jpanel.add(singerBtn);
		singerBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String singer = singerTf.getText();
				show_singer(singer);
			}
		});
		
    }
    
    
    
    // 제목 검색 결과 출력하는 메소드
    public void show_title(String title) {
		String query = "SELECT title, playtime FROM db2022_music WHERE title = \"" + title + "\"";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement stmt = conn.createStatement();) {
			
			System.out.println("------------------------------------------");
			System.out.println("**** [" + title + "] 제목 검색 결과 ****");
			System.out.println("------------------------------------------");
        	
			stmt.executeUpdate("use db2022team06");
            ResultSet rset = stmt.executeQuery(query);
            
            if(!rset.next()) {
            	System.out.println("해당 제목을 가진 음악이 없습니다.");
            }
            else {
            	do {
            		System.out.println(rset.getString("title") + " (" + rset.getString("playtime") + ")");
            	}
            	while(rset.next());
            	System.out.println();
            }
            
            
            
        } catch (SQLException e) {
        	System.out.println("err: " + e);
        }
	}
	
	
    
	// 가수명 검색 결과 출력하는 메소드
	public void show_singer(String singer) {
		String query = "SELECT M.title, M.playtime FROM db2022_music as M, db2022_singer as S WHERE S.singer = \"" + singer + "\" AND S.music_id = M.music_id";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement stmt = conn.createStatement();) {
			
			System.out.println("------------------------------------------");
			System.out.println("**** [" + singer + "] 가수 검색 결과 ****");
			System.out.println("------------------------------------------");
        	
			stmt.executeUpdate("use db2022team06");
            ResultSet rset = stmt.executeQuery(query);
            
            if(!rset.next()) {
            	System.out.println("해당 가수명을 가진 음악이 없습니다.");
            }
            else {
            	do {
            		System.out.println(rset.getString("title") + " (" + rset.getString("playtime") + ")");
            	}
            	while(rset.next());
            	System.out.println();
            }
            
        } catch (SQLException e) {
        	System.out.println("err: " + e);
        }
	}
    
    
	
    // Search 생성자
    public Search(Container contentpane, MainFrame mainframe, JPanel panel) {
    	initJPanel(contentpane, mainframe, panel);
    }
}
