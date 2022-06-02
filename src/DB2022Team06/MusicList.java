package DB2022Team06;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
 
class SelectMusicList {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "DB2022Team06";//DBMS접속 시 아이디
    private static final String PASSWORD = "DB2022Team06";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/DB2022Team06"; //DBMS접속할 db명 - 로컬 상황에 맞게 바꿔서 사용해주세요
    
    public SelectMusicList(String genre) {

    	try {
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           // System.out.println("success");
            
            Statement stmt = conn.createStatement(); 
            
            try {
            	 ResultSet rset;
            	if(genre=="전체") {
            		   rset = stmt.executeQuery(
            				   "with TMP AS( \r\n"
            		            		+ "select music_id,title,playtime,likes,genre,age_limit,release_date, GROUP_CONCAT(songwriter SEPARATOR ',') as songwriter \r\n"
            		            		+ "from db2022_music natural join db2022_songwriter group by music_id) \r\n"
            		            		+ "SELECT music_id,title,playtime,songwriter,likes,genre,age_limit,release_date,GROUP_CONCAT(singer SEPARATOR ',')\r\n"
            		            		+ "from TMP natural join db2022_singer group by music_id "
                       	);
            		   System.out.println("<전체 음원 목록>\n");
            		   
            			while (rset.next()) {
                			
                			System.out.println(rset.getString("music_id")+" | "+rset.getString("title")+" | "+rset.getTime("playtime")+" | "+rset.getString("GROUP_CONCAT(singer SEPARATOR ',')")+" | "+rset.getString("songwriter")+" | "+rset.getInt("likes")+" | "+rset.getString("genre")+" | "+rset.getDate("release_date"));
                		}
            	}
            	else {
            		 rset = stmt.executeQuery(
                     		"select * from db2022_music where genre=\""+genre+"\""
                     	);
            		 System.out.println("<장르가 "+genre+"인곡 목록>");
            		 System.out.println("id|타이틀|재생시간|장르|좋아요|발매일");
            		 while (rset.next()) {
             			
                 		System.out.println(rset.getInt(1)+" | "+rset.getString("title")+" | "+rset.getTime("playtime")+" | "+rset.getString("genre")+" | "+rset.getInt("likes")+" | "+rset.getDate("release_date"));
                 		}
            		 
            		 }
            	
            	 System.out.println("\n");
            
            }
            catch (SQLException sqle)
            {
            System.out.println("Could not select tuple. " + sqle);
            }
            	
            stmt.close();
            conn.close();
            
        } catch (SQLException e1) {
        	System.out.println("connection fail");
        }
    }
}
 
class SelectMusicListener implements ActionListener{
	 
	int btnNum;  // 어떤 버튼 눌렀는지 

	public SelectMusicListener(int btnNum){
		this.btnNum = btnNum;
	
	}
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
      
        
        switch(btnNum) {
        case 0:
        	new SelectMusicList("전체");//전체 음악 목록 조회
        	break;
        	
        case 1: 
        	new SelectMusicList("KPOP");
        	break;
        
        case 2: 
        	new SelectMusicList("발라드");
        	break;
        	
        case 3: 
        	new SelectMusicList("랩/힙합");
        	break;
        	
        case 4: 
        	new SelectMusicList("록/메탈");
        	break; 
        	
        case 5:
        	new SelectMusicList("POP");
        	break;
        	
        case 6:
        	new SelectMusicList("트로트");
        	break;
        	
     
        }
    }
}
public class MusicList {

   
   String[] btnList=new String[]{"전체음악목록조회","K-POP","발라드","랩/힙합","록/메탈","POP","트로트"};
   
   	public MusicList (MainFrame mainframe,Container contentpane,JPanel mainmenuPanel) {

      JPanel musiclistPanel = new JPanel();
      contentpane.add(musiclistPanel);
      
 
      Color EWHA_GREEN =new Color(0,70,42);
      musiclistPanel.setLayout(new FlowLayout(FlowLayout.CENTER,450,30));
      
         
      
      for(int i=0;i<btnList.length;i++) {
         JButton b=new JButton(btnList[i]);

         b.setForeground(Color.WHITE); //글씨 색상
         b.setBackground(EWHA_GREEN);
         musiclistPanel.add(b);
     	b.setPreferredSize(new Dimension(250, 30));
     	b.addActionListener(new SelectMusicListener(i));

      }
  
      // 메인 화면으로 돌아가기 버튼
 		JButton goMainBtn = new JButton("메인");
 		goMainBtn.setForeground(Color.WHITE); //글씨 색상
 		goMainBtn.setBackground(Color.GRAY);
 		musiclistPanel.add(goMainBtn);
 		goMainBtn.setPreferredSize(new Dimension(250, 30));
        
 		//위 버튼이 눌렸을 때 메인으로 돌아가는 액션
 		goMainBtn.addActionListener((ActionListener) new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				musiclistPanel.setVisible(false);
 				mainmenuPanel.setVisible(true);
 			}
 		});
 		
   
   }
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      // 보이도록하기.

      
   }

}