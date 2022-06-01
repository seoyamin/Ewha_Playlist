package DB2022Team06;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Management1{
	String password = "pw"; //"DB2022Team06";
	
	JPanel management1;
	JPanel management2;
	
	String[] btnList = new String[]{"새로운 음악 추가", "기존 음악 수정", "기존 음악 삭제"};
	int BTNSIZE = 250;
	int OFFSET = 150;
	Color EWHA_GREEN = new Color(0,70,42);
	
	
	public Management1(Container contentpane, MainFrame mainframe, JPanel prevPanel, int i) {
		// TODO Auto-generated constructor stub
		// 이전 화면으로 돌아가기 버튼
		JButton goPrevBtn = new JButton();
		goPrevBtn.setLocation(10,10);
		goPrevBtn.setSize(80, 40);
		goPrevBtn.setForeground(Color.WHITE); //글씨 색상
		goPrevBtn.setBackground(Color.GRAY);
		
	
		if(i == 1) {
			management1 = new JPanel();
			
			management1.setLayout(null);
			contentpane.add(management1);
			
			goPrevBtn.setText("메인");
			
			goPrevBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					management1.setVisible(false);
					prevPanel.setVisible(true);
				}
			});		
			
			management1.add(goPrevBtn);
			
			//위 버튼이 눌렸을 때 메인으로 돌아가는 액션
			goPrevBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					management1.setVisible(false);
					prevPanel.setVisible(true);
				}
			});
			
			//관리자만 이용 가능한 메뉴입니다. 비밀번호를입력하세요 문구
			Font font = new Font ("돋움", Font.PLAIN, 20);
			JLabel la = new JLabel("<html><body style = 'text-align:center;'>관리자만 이용 가능한 메뉴입니다.<br>비밀번호를 입력하세요.</body></html>", JLabel.CENTER);
			la.setFont(font);
			la.setSize(450,100);
			la.setLocation(0,200);
			management1.add(la);
			/*label1.setOpaque(true); 
		    label1.setBackground(Color.RED); //위치 확인용으로*/
			
			//비밀번호 입력 창, 입력 버튼
			JTextField pw = new JTextField(20);
			pw.setLocation(25,325);
			pw.setSize(320,40);
			management1.add(pw);
			
			JButton inputBtn = new JButton("입력");
			inputBtn.setLocation(345,325);
			inputBtn.setSize(80,40);
			management1.add(inputBtn);
			
			inputBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String n = pw.getText();
					if(n.equals(password)) {
						management1.setVisible(false);
						pw.setText("");
						new Management1(contentpane, mainframe, management1, 2);
					}
					else{
						JOptionPane.showMessageDialog(null,"비밀번호가 아닙니다.","Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		
		if(i == 2) {
			management2 = new JPanel();
			
			management2.setLayout(null);
			contentpane.add(management2);
			
			goPrevBtn.setText("뒤로");
			goPrevBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					management2.setVisible(false);
					prevPanel.setVisible(true);
				}
			});	
			management2.add(goPrevBtn);
			
			for(int n=0;n<=2;n++) {
				JButton b=new JButton(btnList[n]);
				b.setLocation(100, OFFSET + n*175);
				b.setSize(BTNSIZE,100);
				b.setForeground(Color.WHITE); //글씨 색상
				b.setBackground(EWHA_GREEN);
				b.addActionListener(new MyListener(n));
				management2.add(b);
			}
		
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