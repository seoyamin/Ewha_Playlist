package DB2022Team06;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MyListener implements ActionListener{
	 
	int btnNum;  // 어떤 버튼 눌렀는지 
	Container contentpane; // 현재 활성화된 메인 컨텐트 팬
	MainFrame mainframe;
	JPanel panel; // mainframe의 모든 컴포넌트가 부착된 panel
	
	public MyListener(int btnNum, Container contentpane, MainFrame mainframe, JPanel panel){
		this.btnNum = btnNum;
		this.contentpane = contentpane;
		this.mainframe = mainframe;
		this.panel = panel;
	}
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        
        if(btnNum!=1)
        panel.setVisible(false); // 메인화면 내용 보이지 않게 하기
        
        switch(btnNum) {
        case 0:
        	new MusicList(mainframe,contentpane,  panel); // 전체음악 목록 창 열기
        	break;
        	
        case 1: 
        	new TOP10(); 
        	break;
        
        case 2: 
        	//나만의 플레이 리스트
        	new Nickname(contentpane, mainframe, panel);
        	break;
        	
        case 3: 
        	//추천 플레이 리스트
        	new Playlist_recommended(contentpane, mainframe, panel);
        	break;
        	
        case 4: 
        	//음악 검색
        	new Search(contentpane, mainframe, panel);
        	break; 
        	
        case 5:
        	new Recommend(contentpane, mainframe, panel);
        	break;
        	
        case 6:
        	new Management1(contentpane, mainframe, panel,1);
        	break;
        	
        case 7:
        	if(USERINFO.user_id==-1) { //회원가입 안한 상태라면
        		new SigninPage(contentpane, mainframe, panel); //회원 가입 창으로
        	}
        	else { //회원가입 한 상태라면
        		//개인정보 조회 및 수정 창으로 
        	new MyPage(contentpane, mainframe, panel);
        	}
        	
        	break;
        }
    }
}

public class MainFrame extends JFrame{
	String[] btnList = new String[]{"전체음악 목록","TOP 10","<HTML>나만의<br>플레이 리스트</HTML>","<HTML>추천<br>플레이 리스트</HTML>","음악 검색","음악 추천","음원 관리","마이페이지"};
	public MainFrame() { //생성자
		setTitle("음악정보관리시스템 이플리 DBMS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentpane = getContentPane();
		JPanel panel = new JPanel();
		contentpane.add(panel);
		
		int BTNSIZE = 250;
		int OFFSET = 70;
		Color EWHA_GREEN = new Color(0,70,42);
		panel.setLayout(null); // 배치관리자 해제
		
		// NullContainer는 컴포넌트의 절대적 크기,위치를 지정해주어야함.
		// 지정해주지 않을시 화면에 출력 X
		
		var la = new JLabel("음악정보관리시스템 이플리 DBMS");
		la.setLocation(125,0); 
		la.setSize(250,100); 
		panel.add(la);
		
		// 상단 버튼 두개 (전체음악목록, TOP10)
		for(int i=0;i<=1;i++) {
			JButton b=new JButton(btnList[i]);
			b.setLocation(100,OFFSET+i*100);
			b.setSize(BTNSIZE,50);
			b.setForeground(Color.WHITE); //글씨 색상
			b.setBackground(EWHA_GREEN);
			b.addActionListener(new MyListener(i, contentpane, this, panel));
			panel.add(b);
		}
		
		//중간 플레이 리스트 버튼 두개
		for(int i=2;i<=3;i++) {
			JButton b=new JButton(btnList[i]);
			b.setLocation(100+(i-2)*130,OFFSET+190);
			b.setSize(120,100);
			b.setForeground(Color.WHITE); //글씨 색상
			b.setBackground(EWHA_GREEN); 
			b.addActionListener(new MyListener(i, contentpane, this, panel));
			panel.add(b);
		}
		
		//하단 나머지 버튼 세개 (검색/추천/관리)
		for(int i=4;i<=6;i++) {
			JButton b=new JButton(btnList[i]);
			b.setLocation(100,400+(i-4)*100);
			b.setSize(BTNSIZE,50);
			b.setForeground(Color.WHITE); //글씨 색상
			b.setBackground(EWHA_GREEN);
			b.addActionListener(new MyListener(i, contentpane, this, panel));
			panel.add(b);
		}
	
		//마이페이지 버튼
		JButton myPageBtn=new JButton(btnList[7]);
		myPageBtn.setLocation(300,700);
		myPageBtn.setSize(100,50);
		myPageBtn.setForeground(Color.WHITE); //글씨 색상
		myPageBtn.setBackground(EWHA_GREEN);
		myPageBtn.addActionListener(new MyListener(7, contentpane, this, panel));
		panel.add(myPageBtn);
		
		setSize(450,800); //contentpane의 크기
		setVisible(true); // 보이도록 하기
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame(); // 프레임 실행
	}
}