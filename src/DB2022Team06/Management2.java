package DB2022Team06;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Management2{
	
	JPanel pa1, pa2, pa3;
	Management_Model model = new Management_Model();
	
	public Management2(Container contentpane, MainFrame mainframe, JPanel prevPanel, int btnNum) {
		
		JButton goPrevBtn = new JButton("뒤로");
		
		goPrevBtn.setLocation(10,10);
		goPrevBtn.setSize(80,40);     
		goPrevBtn.setForeground(Color.WHITE); //글씨 색상
		goPrevBtn.setBackground(Color.GRAY);
		
		
		if(btnNum == 0) {
			pa1 = new JPanel();
			pa1.setLayout(null);
			
			goPrevBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pa1.setVisible(false);
					prevPanel.setVisible(true);
				}
			});
			pa1.add(goPrevBtn);
			
			//가수, 제목, playtime, likes, genre, age_limit, release_date, situation, season, music_age
			JLabel La = new JLabel("추가하고 싶은 노래의 정보를 입력하세요.", JLabel.CENTER);
			La.setFont(new Font("고딕", Font.BOLD, 17));
			La.setSize(450,15);
			La.setLocation(0, 60);
			pa1.add(La);
			
			//가수 입력받기
			JLabel singer = new JLabel("가수");
			singer.setFont(new Font("고딕", Font.PLAIN, 15));
			singer.setSize(40,80);
			singer.setLocation(20,80);
			pa1.add(singer);
			
			JTextField singerTf = new JTextField(45);
			singerTf.setLocation(100, 105);
			singerTf.setSize(200, 30);
			pa1.add(singerTf);
			
			//작곡가 입력받기
			JLabel writer = new JLabel("작곡가");
			writer.setFont(new Font("고딕", Font.PLAIN, 15));
			writer.setSize(80,80);
			writer.setLocation(20,120);
			pa1.add(writer);
			
			JTextField writerTf = new JTextField(45);
			writerTf.setLocation(100, 145);
			writerTf.setSize(200, 30);
			pa1.add(writerTf);
			
			//제목 입력받기
			JLabel title = new JLabel("제목");
			title.setFont(new Font("고딕", Font.PLAIN, 15));
			title.setSize(40,80);
			title.setLocation(20,160);
			pa1.add(title);
			
			JTextField titleTf = new JTextField(45);
			titleTf.setLocation(100, 185);
			titleTf.setSize(200, 30);
			pa1.add(titleTf);
			
			//playtime 입력받기
			JLabel playtime = new JLabel("재생 시간");
			playtime.setFont(new Font("고딕", Font.PLAIN, 15));
			playtime.setSize(80,80);
			playtime.setLocation(20,200);
			pa1.add(playtime);
			
			JTextField playtimeTf = new JTextField("hh:mm:ss 형식으로 입력",30);
			playtimeTf.setLocation(100, 225);
			playtimeTf.setSize(200, 30);
			pa1.add(playtimeTf);
			
			//likes 입력받기
			JLabel likes = new JLabel("좋아요 수");
			likes.setFont(new Font("고딕", Font.PLAIN, 15));
			likes.setSize(80,80);
			likes.setLocation(20,240);
			pa1.add(likes);
			
			JTextField likesTf = new JTextField(30);
			likesTf.setLocation(100, 265);
			likesTf.setSize(200, 30);
			pa1.add(likesTf);
			
			//genre 입력받기
			String [] genres = {"랩/힙합","POP","KPOP","트로트","발라드","록/메탈","없음"};
			JLabel genre = new JLabel("장르");
			genre.setFont(new Font("고딕", Font.PLAIN, 15));
			genre.setSize(40,80);
			genre.setLocation(20,280);
			pa1.add(genre);
			
			JComboBox<String> genreCb = new JComboBox<String>(genres);
			genreCb.setLocation(100, 305);
			genreCb.setSize(200, 30);
			pa1.add(genreCb);
			
			//age_limit
			JLabel al = new JLabel("연령 제한이 있습니까?");
			al.setFont(new Font("고딕", Font.PLAIN, 15));
			al.setSize(200,80);
			al.setLocation(20,320);
			pa1.add(al);
			
			JRadioButton yes = new JRadioButton("예");
			JRadioButton no = new JRadioButton("아니오", true);
			yes.setLocation(200, 345);
			no.setLocation(300, 345);
			yes.setSize(100, 30);
			no.setSize(100, 30);
			pa1.add(no);
			pa1.add(yes);
			
			//release_date 입력 
			JLabel release_date = new JLabel("발매일");
			release_date.setFont(new Font("고딕", Font.PLAIN, 15));
			release_date.setSize(80,80);
			release_date.setLocation(20,360);
			pa1.add(release_date);
			
			JTextField rdTf = new JTextField("yyyy-mm-dd 형식으로 입력",30);
			rdTf.setLocation(100, 385);
			rdTf.setSize(200, 30);
			pa1.add(rdTf);
			
			//situation 입력 
			String [] situations = {"운동","출퇴근","공부","여행"};
			JLabel situation = new JLabel("상황");
			situation.setFont(new Font("고딕", Font.PLAIN, 15));
			situation.setSize(40,80);
			situation.setLocation(20,400);
			pa1.add(situation);
			
			JComboBox<String> situationCb = new JComboBox<String>(situations);
			situationCb.setLocation(100, 425);
			situationCb.setSize(200, 30);
			pa1.add(situationCb);
			
			//season 입력 
			String [] seasons = {"봄","여름","가을","겨울"};
			JLabel season = new JLabel("상황");
			season.setFont(new Font("고딕", Font.PLAIN, 15));
			season.setSize(40,80);
			season.setLocation(20, 440);
			pa1.add(season);
			
			JComboBox<String> seasonCb = new JComboBox<String>(seasons);
			seasonCb.setLocation(100, 465);
			seasonCb.setSize(200, 30);
			pa1.add(seasonCb);
			
			//music_age 입력
			Integer [] music_ages = {10, 20, 30, 40, 50, 60};
			JLabel music_age = new JLabel("나이");
			music_age.setFont(new Font("고딕", Font.PLAIN, 15));
			music_age.setSize(40,80);
			music_age.setLocation(20, 480);
			pa1.add(music_age);
			
			JComboBox<Integer> maCb = new JComboBox<Integer>(music_ages);
			maCb.setLocation(100, 505);
			maCb.setSize(200, 30);
			pa1.add(maCb);
			
			JButton insertBtn = new JButton("확인");
			insertBtn.setLocation(300,650);
			insertBtn.setSize(80,40);
			
			insertBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String singerInfo;
					String writerInfo;
					String titleInfo;
					String genreInfo;
					String situInfo;
					String seasonInfo;
					
					int maInfo;
					int likesInfo;
					
					/*오류 잡기용*/
					SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
					String pt = playtimeTf.getText();
					Date playtimeInfo;
					
					SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-mm-dd");
					String rd = rdTf.getText();
					Date rdInfo;
					
					boolean limitInfo;
					
					try {
						singerInfo = singerTf.getText();
						writerInfo = writerTf.getText();
						titleInfo = titleTf.getText();
						genreInfo = genreCb.getSelectedItem().toString();
						situInfo = situationCb.getSelectedItem().toString();
						seasonInfo = seasonCb.getSelectedItem().toString();
						maInfo = (int) maCb.getSelectedItem();
						
						if(yes.isSelected()) {limitInfo = true;} else {limitInfo = false;}
						
						likesInfo = Integer.parseInt(likesTf.getText());
						
						
						
						playtimeInfo = formatter.parse(pt);
						rdInfo = formatter2.parse(rd);
						
						model.insert(playtimeInfo, titleInfo, likesInfo, genreInfo, limitInfo, rdInfo, situInfo, seasonInfo, maInfo, writerInfo, singerInfo);
						
					}catch (ParseException e1) { //date 관련
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"입력 형식이 맞지 않습니다(재생 시간, 발매일)","Message", JOptionPane.ERROR_MESSAGE);
					}catch (NumberFormatException e2) {//string->int 관련
						JOptionPane.showMessageDialog(null,"좋아요 수 항목은 정수 값으로 입력해주세요","Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			pa1.add(insertBtn);
			
			contentpane.add(pa1);
		}
		
		else if(btnNum == 1){
			pa2 = new JPanel();
			pa2.setLayout(null);
			
			goPrevBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pa2.setVisible(false);
					prevPanel.setVisible(true);
				}
			});
			
			
			pa2.add(goPrevBtn);
			
			contentpane.add(pa2);
		}
		else if(btnNum == 2){
			pa3 = new JPanel();
			pa3.setLayout(null);
			
			goPrevBtn.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pa3.setVisible(false);
					prevPanel.setVisible(true);
				}
			});
			pa3.add(goPrevBtn);
			contentpane.add(pa3);
		}
		

		
		
	}
}
		