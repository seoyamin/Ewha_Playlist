import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Nickname extends JFrame {
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06";
    
   /* public void user_Info() {
    	try {
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement stmt = conn.createStatement(); 
	            //stmt.executeQuery("use DB2022Team06");
	            ResultSet rs = stmt.executeQuery("select nickname, age from db2022_user");
	            String nickname=rs.getString(1);
    	}
    		catch (SQLException e) {
				e.printStackTrace();
			}
    } */

	public Nickname() {
		
		super("닉네임 입력화면");
	
		/* Panel 추가 */
		JPanel jPanel=new JPanel(); 
		add(jPanel);
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		/*닉네임 입력 안내문구*/
		JLabel text=new JLabel("닉네임을 입력하세요");
		jPanel.add(text);
		text.setVisible(true);
		text.setBounds(100, 100, 250, 100);
		
		/*닉네임 입력 box*/
		JTextField name=new JTextField();
		jPanel.add(name);
		name.setVisible(true);
		name.setBounds(130, 100, 200, 30);
		
		JButton btn1=new JButton("입력");
		jPanel.add(btn1);
		btn1.setVisible(true);
		btn1.setBounds(230, 200,200, 40);
		
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
		            	
		            	if(user_nickname.equals(nickname)) {
		    				new playlist_main(nickname);
		    				setVisible(false);
		    			}
		            	else {
		            		JLabel popup=new JLabel("회원 정보가 없습니다. 회원가입 후 다시 이용해주세요");
		            		jPanel.add(popup);
		            		popup.setVisible(true);
		            		popup.setBounds(100, 100, 450, 100);
		            	}
		            }
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}
	
		 public static void main(String[] args) {
			new Nickname();
		}
}	
	