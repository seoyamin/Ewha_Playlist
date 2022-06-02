package DB2022Team06;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Nickname {
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06";
    
    Color EWHA_GREEN = new Color(0,70,42);
    int BTNSIZE = 250;

	Nickname(Container contentpane, MainFrame mainframe, JPanel panel) {
		
		mainframe.setTitle("닉네임 입력화면");
		
		/* Panel 추가 */
		JPanel jPanel=new JPanel(); 
		mainframe.add(jPanel);
		mainframe.setVisible(true);
		mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jPanel.setLayout(null);// 배치관리자 해제
		Font font = new Font("돋움", Font.PLAIN, 20);
		
		/*닉네임 입력 안내문구*/
		JLabel text=new JLabel("닉네임을 입력하세요"); text.setFont(font);
		jPanel.add(text);
		text.setVisible(true);
		text.setLocation(145, 60);
		text.setSize(250,180);
		
		/*닉네임 입력 box*/
		JTextField name=new JTextField();
		jPanel.add(name);
		name.setVisible(true);
		name.setBounds(130, 200, 190, 40);
		
		
		JButton btn1=new JButton("입력");
		jPanel.add(btn1);
		btn1.setVisible(true);
		btn1.setSize(BTNSIZE,50);
		btn1.setForeground(Color.WHITE); //글씨 색상
		btn1.setBackground(EWHA_GREEN);
		btn1.setLocation(100, 260);
		
		btn1.addActionListener(new ActionListener() {
			
			String user_nickname;
			String nickname;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				user_nickname= name.getText();
				
				try {
					Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					Statement stmt = conn.createStatement(); 
		            //stmt.executeQuery("use DB2022Team06");
		            ResultSet rs = stmt.executeQuery("select nickname from db2022_user");
		            
		            while(rs.next()) {
		            	nickname=rs.getString(1);
		            	
		            	if(user_nickname.equals(nickname)) { //회원정보 = 닉네임
		    				new playlist_main(nickname); //플레이리스트 창으로 이동
		    				mainframe.setVisible(false);
		    			}
		            	
		            	else { //회원 정보가 없다면 
		            		JLabel popup=new JLabel("<html><body style='text-align:center;'><body>회원 정보가 없습니다.<br>회원가입 후 다시 이용해주세요</body></html>");
		            		jPanel.add(popup); popup.setFont(font);
		            		popup.setVisible(true);
		            		popup.setBounds(100, 330, 500, 100);
		            		
		            		JButton btn2=new JButton("메인"); 
		            		jPanel.add(btn2);
		            		btn2.setVisible(true);
		            		btn2.setForeground(Color.WHITE); //글씨 색상
		            		btn2.setBackground(Color.gray);
		            		btn2.setSize(BTNSIZE,50);
		            		btn2.setLocation(100, 430);
		            		
		            		btn2.addActionListener((ActionListener) new ActionListener() {
		    					public void actionPerformed(ActionEvent e) {
		    						new MainFrame(); //회원가입 창으로 변경하기
		    						mainframe.setVisible(false);
		    					}
		    				});
		            	}
		            }
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}
	
}	
	