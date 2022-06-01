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

public class MyPage{
	String[] btnList = new String[]{"이름","닉네임","나이","선호장르"};
	JTextField[] textfieldList=new JTextField[4] ;
	
	MyPage(Container contentpane, MainFrame mainframe, JPanel mainmenu){ // 생성자
		Recommend_Model model = new Recommend_Model(); //sql 연결 함수를 사용하기 위한 객체 생성

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
		//위 버튼이 눌렸을 때 메인으로 돌아가는 액션
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
		
		
		
		
		for(int i=0;i<btnList.length;i++) {
			// 닉네임 입력 창, 입력 버튼
			JTextField tf = new JTextField(20);
			tf.setLocation(150, OFFSET+i*100+10);
			tf.setSize(200, 30);
			mypagePannel.add(tf);
			textfieldList[i]=tf;
			
		}
		
		Font font = new Font("돋움", Font.PLAIN, 15);
		JLabel la = new JLabel("제공하는 장르: KPOP,발라드,랩/힙합,POP,트로트,록/메탈");
		la.setFont(font);
		la.setLocation(30,500); 
		la.setSize(450,50); 
		mypagePannel.add(la);
		
		JButton inputBtn = new JButton("회원가입");
		inputBtn.setLocation(150, 600);
		inputBtn.setSize(100, 40);
		inputBtn.setForeground(Color.WHITE);
		inputBtn.setBackground(Color.GRAY);
		mypagePannel.add(inputBtn);
		
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//데이터 입력에 따라 처리
				//model.recommend(nickname.getText()); //입력된 nickname 값을 인자로 넘겨 recommend() 함수 실행
				//System.out.println(nickname.getText());
			}
		});
		
		mypagePannel.setVisible(true);
	}
}