package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SelectByMon {

	JFrame f;
	JLabel title;
	JButton[] monBtn = new JButton[5];
	static int month;
	static String usid;
	ViewByMon[] vblArr = new ViewByMon[5];

	SelectByMon(String usid) {
		this.month = month;
		this.usid = usid;
		f = new JFrame("월별 전시");

		JPanel tiP = new JPanel(new BorderLayout());
		tiP.setLayout(null); tiP.setBackground(Color.WHITE);
		title = new JLabel("월별 전시");
		title.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ft_sm.jpg"));
		tiP.add(title);
		title.setBounds(0, 0, 700, 60);
		tiP.setBounds(70, 20, 660, 60);
		f.add(tiP);

		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(Color.WHITE);
		for(int i = 0; i < 5; i++) {
			int month = i + 8;
			monBtn[i] = new JButton();
			p.add(monBtn[i]); monBtn[i].setBounds(150 + (170*(i%3)), 150 + (170*(i/3)), 150, 150);
			monBtn[i].setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse"
					+ "\\work_08_21\\project\\src\\img\\mon_"+(i+8)+".jpg"));
			monBtn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewByMon vbm = new ViewByMon(month, usid);
					vbm.initStyle();
				}
			});
		}
		p.setBounds(70, 100, 600, 600);
		f.add(p);

		f.setSize(800, 600);
		f.setVisible(true);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initStyle() {
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("나눔고딕", Font.BOLD, 24));
	}

	public static void main(String[] args) {
		SelectByMon sbm = new SelectByMon(usid);
		sbm.initStyle();
	}

}

