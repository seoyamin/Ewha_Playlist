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
	Container contentpane;
	MainFrame mainframe;
	
	String password = "DB2022Team06"; //관리자 비밀번호
	
	JPanel manaPa1; //비밀번호 입력 창
	JPanel manaPa2; //비밀 번호 입력 후 음악을 관리할 수 있는 창
	
	String[] btnList = new String[]{"새로운 음악 추가", "기존 음악 수정", "기존 음악 삭제"};
	int BTNSIZE = 250;
	int OFFSET = 150;
	Color EWHA_GREEN = new Color(0,70,42);
	
	//음원 관리를 눌렀을때 나타나는 창이다. 만약 int i 가 1이면 비밀번호 입력창이 뜨고, int i가 2면 비밀번호 입력 후, 새로운 음악 추가, 기존 음악 수정, 기존 음악 삭제 창이 뜬다.
	public Management1(Container contentpane, MainFrame mainframe, JPanel prevPanel, int i) {
		// TODO Auto-generated constructor stub
		this.contentpane = contentpane;
		this.mainframe = mainframe;
		
		// 이전 화면으로 돌아가기 버튼
		JButton goPrevBtn = new JButton();
		goPrevBtn.setLocation(10,10);
		goPrevBtn.setSize(80, 40);
		goPrevBtn.setForeground(Color.WHITE); //글씨 색상
		goPrevBtn.setBackground(Color.GRAY);
		
		if(i == 1) {
			manaPa1 = new JPanel();
			
			manaPa1.setLayout(null);
			contentpane.add(manaPa1);
			
			//메인으로 가는 버튼
			goPrevBtn.setText("메인");
			
			//메인으로 가는 버튼을 눌렀을때 실행됨
			goPrevBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manaPa1.setVisible(false);
					prevPanel.setVisible(true);
				}
			});		
			
			manaPa1.add(goPrevBtn);
			
			//위 버튼이 눌렸을 때 메인으로 돌아가는 액션
			goPrevBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manaPa1.setVisible(false);
					prevPanel.setVisible(true);
				}
			});
			
			//관리자만 이용 가능한 메뉴입니다. 비밀번호를입력하세요 문구
			Font font = new Font ("돋움", Font.PLAIN, 20);
			JLabel la = new JLabel("<html><body style = 'text-align:center;'>관리자만 이용 가능한 메뉴입니다.<br>비밀번호를 입력하세요.</body></html>", JLabel.CENTER);
			la.setFont(font);
			la.setSize(450,100);
			la.setLocation(0,200);
			manaPa1.add(la);
			
			//비밀번호 입력 창과 입력 버튼
			JTextField pw = new JTextField(20);
			pw.setLocation(25,325);
			pw.setSize(320,40);
			manaPa1.add(pw);
			
			JButton inputBtn = new JButton("입력");
			inputBtn.setLocation(345,325);
			inputBtn.setSize(80,40);
			manaPa1.add(inputBtn);
			
			//비밀번호 입력후 
			inputBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String n = pw.getText();
					if(n.equals(password)) { //만약 비밀번호가 맞으면
						manaPa1.setVisible(false);
						pw.setText("");
						new Management1(contentpane, mainframe, manaPa1, 2);
					}
					else{ //만약 비밀번호가 틀리면
						JOptionPane.showMessageDialog(null,"비밀번호가 아닙니다.","Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		
		if(i == 2) { //비밀번호가 맞았을 때 뜨는 관리자 전용 창
			manaPa2 = new JPanel();
			
			manaPa2.setLayout(null);
			contentpane.add(manaPa2);
			
			//뒤로가기 버튼
			goPrevBtn.setText("뒤로");
			goPrevBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manaPa2.setVisible(false);
					prevPanel.setVisible(true);
				}
			});	
			manaPa2.add(goPrevBtn);
			
			for(int n=0;n<=2;n++) {
				JButton b=new JButton(btnList[n]);
				b.setLocation(100, OFFSET + n*175);
				b.setSize(BTNSIZE,100);
				b.setForeground(Color.WHITE); //글씨 색상
				b.setBackground(EWHA_GREEN);
				b.addActionListener(new MyListener(n)); 
				manaPa2.add(b);
			}
		}
		
	}
	
	//두번째 창(관리자 전용 창)에서 버튼을 눌렀을때 액션
	class MyListener implements ActionListener{
		int btnNum;
		
		public MyListener(int btnNum){
			this.btnNum = btnNum;
		}
		
		public void actionPerformed(ActionEvent e) {
			manaPa2.setVisible(false);
			new Management2(contentpane, mainframe, manaPa2, btnNum);
		    
		}
	}
		
}