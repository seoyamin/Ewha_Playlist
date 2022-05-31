import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class MyListener implements ActionListener{
	 
	int btnNum; // 어떤 버튼 눌렀는지 
	Container contentpane; // 활성화 된 컨텐트팬 
	
	public MyListener(int btnNum,Container contentpane){
		this.btnNum=btnNum;
		this.contentpane=contentpane;
		
	}
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button  = (JButton)e.getSource();
        contentpane.setVisible(false);

        switch(btnNum) {
        case 0:
        	//new musicList(); // 전체음악 목록 창 열기
        	break;
        	
        case 1: 
        	//TOP 10 
        	break;
        
        case 2: 
        	//나만의 플레이 리스트
        	break;
        	
        case 3: 
        	//추천 플레이 리스트
        	break;
        	
        case 4: 
        	//음악 검색
        	break; 
        	
        case 5:
        	//음악 추천
        	break;
        	
        case 6:
        	//음원 관리
        	break;
        	
        case 7:
        	//마이페이지
        	break;
        }
    }
    
}
public class MainFrame extends JFrame{
	
	String[] btnList=new String[]{"전체음악 목록","TOP 10","<HTML>나만의<br>플레이 리스트</HTML>","<HTML>추천<br>플레이 리스트</HTML>","음악 검색","음악 추천","음원 관리","마이페이지"};
	
	
	public MainFrame () { //생성자
		
		setTitle("음악정보관리시스템 이플리 DBMS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentpane=getContentPane();
		
		int BTNSIZE=250;
		int OFFSET =70;
		Color EWHA_GREEN =new Color(0,70,42);
		contentpane.setLayout(null);// 배치관리자 해제
		
		// NullContainer는 컴포넌트의 절대적 크기,위치를 지정해주어야함.
		// 지정해주지 않을시 화면에 출력 X
		
		var la = new JLabel("음악정보관리시스템 이플리 DBMS");
		la.setLocation(125,0); 
		la.setSize(250,100); 
		contentpane.add(la);
		
		// 상단 버튼 두개 (전체음악목록, TOP10)
		for(int i=0;i<=1;i++) {
			JButton b=new JButton(btnList[i]);
			b.setLocation(100,OFFSET+i*100);
			b.setSize(BTNSIZE,50);
			b.setForeground(Color.WHITE); //글씨 색상
			b.setBackground(EWHA_GREEN);
			contentpane.add(b);
		}
		
		//중간 플레이 리스트 버튼 두개
		for(int i=2;i<=3;i++) {
			JButton b=new JButton(btnList[i]);
			b.setLocation(100+(i-2)*130,OFFSET+200);
			b.setSize(120,100);
			b.setForeground(Color.WHITE); //글씨 색상
			b.setBackground(EWHA_GREEN); 
			
			contentpane.add(b);
		}
		
		
		//하단 나머지 버튼 세개 (검색/추천/관리)
		for(int i=4;i<=6;i++) {
			JButton b=new JButton(btnList[i]);
			b.setLocation(100,400+(i-4)*100);
			b.setSize(BTNSIZE,50);
			b.setForeground(Color.WHITE); //글씨 색상
			b.setBackground(EWHA_GREEN);
			b.addActionListener(new MyListener(i,contentpane));
		
			contentpane.add(b);
		}
		
		
		//마이페이지 버튼
		JButton myPageBtn=new JButton(btnList[7]);
		myPageBtn.setLocation(300,700);
		myPageBtn.setSize(100,50);
		myPageBtn.setForeground(Color.WHITE); //글씨 색상
		myPageBtn.setBackground(EWHA_GREEN);
		contentpane.add(myPageBtn);
		
		setSize(450,800); //contentpane의 크기
		setVisible(true); // 보이도록하기.
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame(); // 프레임 실행
		
	}

}