package DB2022Team06;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MusicList {

   
   String[] btnList=new String[]{"전체음악목록조회(최신순)","K-POP","발라드","힙합","음악 검색","R&B","POP","OST"};
   
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

      }
  
      // 메인 화면으로 돌아가기 버튼
 		JButton goMainBtn = new JButton("메인");
 		goMainBtn.setForeground(Color.WHITE); //글씨 색상
 		goMainBtn.setBackground(Color.GRAY);
 		goMainBtn.setLocation(10,10);
		goMainBtn.setSize(80, 40);
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
      // // 보이도록하기.
      
      
      
   }

}