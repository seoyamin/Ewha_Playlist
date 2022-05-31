import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class playlist extends JFrame {
	public playlist() {
		super("나만의 플레이리스트"); //창 이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jPanel=new JPanel();
		add(jPanel);
		
		JButton btn1=new JButton("나만의 플레이리스트");
		jPanel.add(btn1);
		
		btn1.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Nickname();
				setVisible(false);
			}
		});
		
		setSize(500,500);
		setVisible(true);
	}
}
