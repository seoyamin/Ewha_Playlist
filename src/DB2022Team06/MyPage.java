package DB2022Team06;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class UpdateGenre {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; //DBMS접속할 db명 - 로컬 상황에 맞게 바꿔서 사용해주세요
    
    public UpdateGenre(String favorite_genre) {
 
    	
    	List<String> list = new ArrayList<>(Arrays.asList("KPOP","발라드","랩/힙합","POP","트로트","록/메탈"));
    	
    	if(!list.contains(favorite_genre)) { //제공하는 장르에 없다면
    		System.out.println("유효한 장르를 입력해주세요 :(");
    		return;
    	}
    	
    	try {
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("connection success");
           
            try {
            	
            PreparedStatement pStmt = conn.prepareStatement( 
            		"UPDATE db2022_user SET favorite_genre=? where user_id=?;");
           
            pStmt.setString(1,favorite_genre );
            pStmt.setInt(2,USERINFO.user_id);
            
            pStmt.executeUpdate();
            
            USERINFO.favorite_genre=favorite_genre;
            pStmt.close();
          
            System.out.println("선호장르가 정상적으로 변경되었습니다." );
            }
           
            catch (SQLException sqle)
            {
            System.out.println("선호장르를 수정할 수 없습니다. (이유 : id존재하지 않음 등)" + sqle);
            }
            	
           
            conn.close();
            
        } catch (SQLException e1) {
        	System.out.println("connection fail");
        }
    }
}


public class MyPage{
	String[] btnList = new String[]{"이름","닉네임","나이","선호장르"};
	JTextField[] textfieldList=new JTextField[4] ;

	
	MyPage(Container contentpane, MainFrame mainframe, JPanel mainmenu){ // 생성자
		
		JPanel mypagePannel = new JPanel();
		mypagePannel.setLayout(null);
		contentpane.add(mypagePannel);
		
		// 메인 화면으로 돌아가기 버튼
		JButton goMainBtn = new JButton("메인");
		goMainBtn.setLocation(10,10);
		goMainBtn.setSize(80, 40);
		goMainBtn.setForeground(Color.WHITE); //글씨 색상
		goMainBtn.setBackground(Color.GRAY);
		mypagePannel.add(goMainBtn);
		
		int OFFSET= 100;
		//버튼이 눌렸을 때 메인으로 돌아가는 액션
		goMainBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mypagePannel.setVisible(false);
				mainmenu.setVisible(true); //메인메뉴 패널
			}
		});
		
		
		for(int i=0;i<btnList.length;i++) {
			
			Font font = new Font("돋움", Font.PLAIN, 15);
			JLabel la = new JLabel(btnList[i]);
			la.setFont(font);
			
			la.setLocation(70,OFFSET+100*i); 

			if(i==3)
				la.setLocation(40,OFFSET+100*i); 
			la.setSize(200,50); 
			mypagePannel.add(la);
		}
		
		
		
		//유저 정보
		
			Font font = new Font("돋움", Font.PLAIN, 15);
			JLabel name = new JLabel(USERINFO.name);
			name.setFont(font);
			name.setLocation(150, OFFSET+10);
			name.setSize(200, 30);
			mypagePannel.add(name);
	
			JLabel nickname = new JLabel(USERINFO.nickname);
			nickname.setFont(font);
			nickname.setLocation(150, OFFSET+110);
			nickname.setSize(200, 30);
			mypagePannel.add(nickname);
			
			
			JLabel age = new JLabel(Integer.toString(USERINFO.age));
			age.setFont(font);
			age.setLocation(150, OFFSET+210);
			age.setSize(200, 30);
			mypagePannel.add(age);
		
			JTextField tf = new JTextField(20);
			tf.setLocation(150, OFFSET+310);
			tf.setSize(150, 30);
			tf.setText(USERINFO.favorite_genre); // 선호장르 초기값 지정
			mypagePannel.add(tf);
			
			JButton inputBtn = new JButton("수정");
			inputBtn.setLocation(300,OFFSET+310);
			inputBtn.setSize(70, 30);
			inputBtn.setForeground(Color.WHITE);
			inputBtn.setBackground(Color.GRAY);
			mypagePannel.add(inputBtn);
			
		
		
		JLabel la = new JLabel("제공하는 장르: KPOP,발라드,랩/힙합,POP,트로트,록/메탈");
		la.setFont(font);
		la.setLocation(30,500); 
		la.setSize(450,50); 
		mypagePannel.add(la);
		
		
	
		
		
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//데이터 입력에 따라 처리
	
				new UpdateGenre(tf.getText());
				
			}
		});
		
		mypagePannel.setVisible(true);
	}
}