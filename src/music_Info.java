import javax.swing.*;
import java.awt.event.*;

public class music_Info extends JFrame {
	
	public music_Info() {
		
		super("곡명, 가수명 검색");
		JPanel jPanel = new JPanel();
		add(jPanel);
		setVisible(true);
		setSize(500, 500);
		
		JLabel music=new JLabel("곡명");
		JLabel singer=new JLabel("가수명");
		jPanel.add(music);
		jPanel.add(singer);
		music.setBounds(50, 50, 30, 10);
		singer.setBounds(50, 60, 30, 10);
		music.setVisible(true);
		singer.setVisible(true);
		
		JTextField box1=new JTextField();
		JTextField box2=new JTextField();
		
		jPanel.add(box1);
		jPanel.add(box2);
		box1.setVisible(true);
		box2.setVisible(true);
		box1.setBounds(90,50,100,19);
		box2.setBounds(100,30, 100, 19);
		
		JButton btn1=new JButton("이전");
		jPanel.add(btn1);
		btn1.setVisible(true);
		btn1.setSize(100, 30);
		
		/*btn1.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new playlist_main(nickname);
				setVisible(false);
			}
		});*/
	}
}