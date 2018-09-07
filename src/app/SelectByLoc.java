package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SelectByLoc {

	JFrame f;
	JButton sBtn, kBtn, pBtn, oBtn;
	static int locid;
	static String usid;

	SelectByLoc(String usid) {
		this.usid = usid;
		f = new JFrame("지역별로 보기");
		sBtn = new JButton();
		sBtn.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse"
				+ "\\work_08_21\\project\\src\\img\\seo.jpg"));
		kBtn = new JButton();
		kBtn.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse"
				+ "\\work_08_21\\project\\src\\img\\gye.jpg"));
		pBtn = new JButton();
		pBtn.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse"
				+ "\\work_08_21\\project\\src\\img\\bu.jpg"));
		oBtn = new JButton();
		oBtn.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse"
				+ "\\work_08_21\\project\\src\\img\\ex.jpg"));
		
		JPanel tiP = new JPanel(new BorderLayout());
		// tiP.setBackground(Color.WHITE);
		JLabel title = new JLabel();
		title.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ft_sl.jpg"));
		tiP.setLayout(null);
		tiP.setBounds(70, 20, 660, 60);
		title.setBounds(0, 0, 780, 60);
		tiP.add(title);
		f.add(tiP);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(Color.WHITE);
		p.add(sBtn);
		sBtn.setBounds(180, 90, 200, 200);
		p.add(kBtn);
		kBtn.setBounds(400, 90, 200, 200);
		p.add(pBtn);
		pBtn.setBounds(180, 310, 200, 200);
		p.add(oBtn);
		oBtn.setBounds(400, 310, 200, 200);
		
		p.setBounds(100, 100, 600, 600);
		f.add(p);
		
		f.setSize(800, 600);
		f.setVisible(true);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		sBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ViewByLoc vbl = new ViewByLoc(1, usid);
			}
		});
		
		kBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewByLoc vbl = new ViewByLoc(9, usid);
			}
		});
		
		pBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewByLoc vbl = new ViewByLoc(2, usid);
			}
		});
		
		oBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewByLoc vbl = new ViewByLoc(3, usid);
			}
		});
	}
	public static void main(String[] args) {

		SelectByLoc sbl = new SelectByLoc(usid);
	}
}
