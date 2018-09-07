package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import dao.ExDAO;
import dao.LocDAO;
import dao.PlacesDAO;
import dao.ReviewsDAO;
import dao.UsersDAO;
import service.ExService;
import service.ExServiceImpl;
import vo.ReviewsVO;

public class WriteRev {
	
	ExDAO ed = new ExDAO();
	PlacesDAO pd = new PlacesDAO();
	LocDAO ld = new LocDAO();
	ReviewsDAO rd = new ReviewsDAO();
	UsersDAO ud = new UsersDAO();
	ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);
	int exid = 1;
	String usid = "ex";
	ReviewsVO re = null;
	
	Integer[] num = new Integer [5];
	
	JFrame f;
	JLabel title;
	JLabel tiL, grL, revL, ti;
	JComboBox grCB;
	TextArea revTA;
	JButton write;
	
	
	WriteRev(int exid, String usid) {
		f = new JFrame("상세 정보");
		f.setLayout(null); f.setBackground(Color.WHITE);
		
		// title
		JPanel tiP = new JPanel();
		tiP.setLayout(null);
		title = new JLabel("리뷰작성");
		tiP.setBackground(Color.BLUE);
		tiP.add(title); tiP.setBounds(70, 20, 660, 60);
		title.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ft_write.jpg"));
		title.setBounds(0, 0, 700, 60); f.add(tiP);
		
		// Write review
		JPanel wrP = new JPanel();
		wrP.setLayout(null); wrP.setBackground(Color.WHITE);
		
		tiL = new JLabel("제목");
		wrP.add(tiL); tiL.setBounds(40, 30, 50, 30);
		ti = new JLabel(serv.getTitle(exid));
		wrP.add(ti); ti.setBounds(100, 30, 570, 30);
		
		grL = new JLabel("평점");
		wrP.add(grL); grL.setBounds(40, 90, 50, 30);
		for (int i = 0; i < 5; i++)
			num[i] = i+1;
		grCB = new JComboBox<Integer>(num);
		wrP.add(grCB); grCB.setBounds(100, 90, 70, 30);
		
		revL = new JLabel("리뷰");
		wrP.add(revL); revL.setBounds(40, 150, 50, 30);
		revTA = new TextArea(10, 100);
		wrP.add(revTA); revTA.setBounds(100, 150, 490, 180);
		
		write = new JButton();
		wrP.add(write); write.setBounds(490, 370, 100, 30);
		write.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\save.PNG"));
		write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					serv.insertRev(exid, usid, grCB.getSelectedIndex(), revTA.getText());
					JOptionPane.showMessageDialog(null, "리뷰가 저장되었습니다.");
					f.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		f.add(wrP); wrP.setBounds(70, 90, 660, 450);
		
		f.setSize(800, 600);
		f.setVisible(true);
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	void initStyle() {
		tiL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		ti.setFont(new Font("나눔고딕", Font.BOLD, 16));
		grL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		revL.setFont(new Font("나눔고딕", Font.BOLD, 16));
	}

	public static void main(String[] args) {
		WriteRev wr = new WriteRev(1, "ex");
		wr.initStyle();
	}

}
