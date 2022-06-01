package DB2022Team06;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Management2{
	
	String[] btnList = new String[]{"새로운 음악 추가", "기존 음악 수정", "기존 음악 삭제"};
	int BTNSIZE = 250;
	int OFFSET = 150;
	Color EWHA_GREEN = new Color(0,70,42);
	
	
	public Management2(Container contentpane, MainFrame mainframe, JPanel prevPanel) {
		// TODO Auto-generated constructor stub
		JPanel management2 = new JPanel();
		
		management2.setLayout(null);
		contentpane.add(management2);
		
		
		// 이전 화면으로 돌아가기 버튼
		JButton goBackBtn = new JButton("뒤로 가기");
		goBackBtn.setLocation(10,10);
		goBackBtn.setSize(100, 40);
		goBackBtn.setForeground(Color.WHITE); //글씨 색상
		goBackBtn.setBackground(Color.GRAY);
		management2.add(goBackBtn);
		
		//위 버튼이 눌렸을 때 이전화면으로 돌아가는 액션
		goBackBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				management2.setVisible(false);
				prevPanel.setVisible(true);
			}
		});
		
		for(int i=0;i<=2;i++) {
			JButton b=new JButton(btnList[i]);
			b.setLocation(100, OFFSET + i*175);
			b.setSize(BTNSIZE,100);
			b.setForeground(Color.WHITE); //글씨 색상
			b.setBackground(EWHA_GREEN);
			b.addActionListener(new MyListener(i));
			management2.add(b);
		}
		
	}
	
	class MyListener implements ActionListener{
		int btnNum;
		
		public MyListener(int btnNum){
			this.btnNum = btnNum;
		}
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
		}
	}
}
		