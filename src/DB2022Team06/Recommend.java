package DB2022Team06;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Recommend{

	Recommend(Container contentpane, MainFrame mainframe, JPanel panel){ // 생성자
		mainframe.setTitle("음악정보관리시스템 이플리 DBMS");
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel recommendPanel = new JPanel();
		recommendPanel.setLayout(null);
		contentpane.add(recommendPanel);
		
		// 메인 화면으로 돌아가기 버튼
		JButton goMainBtn = new JButton("메인");
		goMainBtn.setLocation(10,10);
		goMainBtn.setSize(80, 40);
		goMainBtn.setForeground(Color.WHITE); //글씨 색상
		goMainBtn.setBackground(Color.GRAY);
		recommendPanel.add(goMainBtn);
		
		//위 버튼이 눌렸을 때 메인으로 돌아가는 액션
		goMainBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recommendPanel.setVisible(false);
				panel.setVisible(true);
			}
		});
		
		// 닉네임을 입력하세요 문구
		Font font = new Font("돋움", Font.PLAIN, 20);
		JLabel la = new JLabel("닉네임을 입력하세요");
		la.setFont(font);
		la.setLocation(125,100); 
		la.setSize(300,100); 
		recommendPanel.add(la);
		
		// 닉네임 입력 창, 입력 버튼
		JTextField nickname = new JTextField(20);
		nickname.setLocation(50, 200);
		nickname.setSize(255, 40);
		recommendPanel.add(nickname);
		
		JButton inputBtn = new JButton("입력");
		inputBtn.setLocation(305, 200);
		inputBtn.setSize(80, 40);
		inputBtn.setForeground(Color.WHITE);
		inputBtn.setBackground(Color.GRAY);
		recommendPanel.add(inputBtn);
		
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//데이터 입력에 따라 처리
				//a = nickname.getText();
				System.out.println(nickname.getText());
			}
		});
		
		mainframe.setVisible(true);
	}
}