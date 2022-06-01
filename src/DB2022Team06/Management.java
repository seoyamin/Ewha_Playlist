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

public class Management{
	String password = "password";
	
	public Management(Container contentpane, MainFrame mainframe, JPanel mainmenuPanel) {
		// TODO Auto-generated constructor stub
		JPanel management1 = new JPanel();

		management1.setLayout(null);
		contentpane.add(management1);
		
		// 메인 화면으로 돌아가기 버튼
		JButton goMainBtn = new JButton("메인");
		goMainBtn.setLocation(10,10);
		goMainBtn.setSize(80, 40);
		goMainBtn.setForeground(Color.WHITE); //글씨 색상
		goMainBtn.setBackground(Color.GRAY);
		management1.add(goMainBtn);
		
		//위 버튼이 눌렸을 때 메인으로 돌아가는 액션
		goMainBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				management1.setVisible(false);
				mainmenuPanel.setVisible(true);
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
					System.out.println("맞음"); //management2로 이동
				}
				else{
					System.out.println("틀림");
				}
			}
		});
		
		
		
		
	}
		
}