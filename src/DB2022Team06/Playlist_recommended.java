package DB2022Team06;

import java.sql.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Playlist_recommended {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";
    private static final String PASSWORD = "DB2022Team06";
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; 
    String topicList[] = {"상황별", "계절별", "나이별"};
    String detailList[][] = { {"여행", "운동", "공부", "출퇴근"}, {"봄", "여름", "가을", "겨울"}, {"10대", "20대", "30대", "40대", "50대", "60대 이상"} };
  
	
    // 주제 (상황, 계절, 나이) 선택용 JPanel 프레임을 init하는 메소드
    public void initJpanel_topic(Container contentpane, MainFrame mainframe, JPanel panel) {
    	Color EWHA_GREEN = new Color(0,70,42);
    	JPanel jpanel = new JPanel();
		JPanel jp1 = new JPanel(); JPanel jp2 = new JPanel(); JPanel jp3 = new JPanel();
		JPanel jpanelDetail[] = {jp1, jp2, jp3};
		JButton situationBtn, seasonBtn, ageBtn;
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
		
		
		// 주제별 (상황, 계절, 나이) 플레이리스트 버튼  
		for(int i=0 ; i<topicList.length; i++) {
			int topicNum = i;
			String btnText = "<HTML><body style='text-align:center;'>" + topicList[i] + "<br>추천 플레이리스트</body></HTML>";
			JButton topicBtn = new JButton(btnText);
			topicBtn.setFont(new Font("고딕", Font.BOLD, 17));
			topicBtn.setLocation(75, 100 + i * 200);
			topicBtn.setSize(300, 100);
			topicBtn.setForeground(Color.WHITE);
			topicBtn.setBackground(EWHA_GREEN);
			jpanel.add(topicBtn);	
			
			topicBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jpanel.setVisible(false);
					contentpane.add(jpanelDetail[topicNum]);
					jpanelDetail[topicNum].setVisible(true);
					jpanelDetail[topicNum].setLayout(null);
					initJpanel_detail(jpanelDetail[topicNum], topicNum);
					
					// 이전 화면으로 돌아가는 버튼
					JButton goBackBtn = new JButton("뒤로가기");
					goBackBtn.setLocation(10,10);
					goBackBtn.setSize(100, 40);
					goBackBtn.setForeground(Color.WHITE);
					goBackBtn.setBackground(Color.GRAY);
					jpanelDetail[topicNum].add(goBackBtn);
					
					// 이전 화면 돌아가기 버튼의 액션
					goBackBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							jpanelDetail[topicNum].setVisible(false);
							jpanel.setVisible(true);
						}
					});
				}
				
			});
			
		}
		
			
    }
    
    
    // 세부 주제 (봄, 여름 등) 선택용 JPanel 프레임을 init하는 메소드
    public void initJpanel_detail(JPanel panel_detail, int topicNum) {
    	Color EWHA_GREEN = new Color(0,70,42);
		for(int j=0 ; j<detailList[topicNum].length ; j++) {
			int detailNum = j;
			JButton detailBtn = new JButton(detailList[topicNum][j]);						
			detailBtn.setFont(new Font("고딕", Font.BOLD, 15));
			detailBtn.setLocation(75, 100 + j*110);
			detailBtn.setSize(300,60);
			detailBtn.setForeground(Color.WHITE); 
			detailBtn.setBackground(EWHA_GREEN);
			panel_detail.add(detailBtn);
			
			detailBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					show_playlist(topicNum, detailList[topicNum][detailNum]);
				}
			});
			
		}
    }
    
    
    // 각각의 플레이리스트를 출력하는 메소드
	public void show_playlist(int topicNum, String detail) {
		String topicList_eng[] = {"situation", "season", "music_age"};
		String query = "SELECT title, singer, songwriter, playtime, likes, genre, release_date "
				+ "FROM db2022_music,  "
				+ "(SELECT music_id, GROUP_CONCAT(singer) AS 'singer' FROM db2022_singer GROUP BY music_id) AS S, "
				+ "(SELECT music_id, GROUP_CONCAT(songwriter) AS 'songwriter' FROM db2022_songwriter GROUP BY music_id) AS SW "
				+ "WHERE ( " + topicList_eng[topicNum] + " = ?) AND (db2022_music.music_id = S.music_id) AND (db2022_music.music_id = SW.music_id)";
		
		
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			PreparedStatement pStmt = conn.prepareStatement(query);) {
			
			pStmt.setString(1, detail);
			ResultSet rset = pStmt.executeQuery();
			
			System.out.println(" **** [" + detail + "] 추천 플레이리스트 ****");
			System.out.println("---------------------------------------------------------------------------------------------");
    		System.out.println("    제목    |    가수    |    작사작곡    |    재생시간    |    좋아요 수    |    장르    |    발매일    ");
    		System.out.println("---------------------------------------------------------------------------------------------");
			
        	
            while(rset.next()) {
            	System.out.println(rset.getString("title") + " | " 
						+ rset.getString("singer") + " | "
						+ rset.getString("songwriter") + " | "
						+ rset.getString("playtime") + " | "
						+ rset.getString("likes") + " | "
						+ rset.getString("genre") + " | "
						+ rset.getString("release_date") + " | ");
            }
            System.out.println();
            
        } catch (SQLException e) {
        	System.out.println("err : " + e);
        }
	}
    
	
	
	
    // Playlist_recommended 생성자
	public Playlist_recommended(Container contentpane, MainFrame mainframe, JPanel panel) {
		initJpanel_topic(contentpane, mainframe, panel);
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
