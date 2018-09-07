package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.ExDAO;
import dao.LocDAO;
import dao.PlacesDAO;
import dao.ReviewsDAO;
import dao.UsersDAO;
import service.ExService;
import service.ExServiceImpl;
import vo.ExVO;

public class CheckRes {
	
	ExDAO ed = new ExDAO();
	PlacesDAO pd = new PlacesDAO();
	LocDAO ld = new LocDAO();
	ReviewsDAO rd = new ReviewsDAO();
	UsersDAO ud = new UsersDAO();
	ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);
	
	JFrame f;
	JLabel title, resL, titleL, dateL, placeL, date, place;
	JButton imgBtn;
	String userid = "ex";
	ExVO ex;
	
	CheckRes(String userid) {
		f = new JFrame("예매 내역 확인");
		f.setLayout(null);
		
		ex = serv.chkRes(userid);
		
		JPanel tiP = new JPanel();
		tiP.setLayout(null); tiP.setBackground(Color.GRAY);
		title = new JLabel("");
		title.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ft_res.jpg"));
		tiP.add(title);
		title.setBounds(0, 0, 660, 60);
		tiP.setBounds(70, 20, 660, 60);
		f.add(tiP);
		
		JPanel resP = new JPanel();
		resP.setLayout(null); resP.setBackground(null);
		
		JPanel chkres = new JPanel();
		chkres.setLayout(null); chkres.setBackground(Color.WHITE);
		resL = new JLabel(userid + " 님의 예매 내역");
		chkres.add(resL); resL.setBounds(30, 10, 660, 30);
		resP.add(chkres); chkres.setBounds(0, 0, 660, 50);
		
		JPanel con = new JPanel();
		con.setLayout(null); con.setBackground(Color.WHITE);
		imgBtn = new JButton("img");
		con.add(imgBtn); imgBtn.setBounds(30, 30, 128, 184);
		ImageIcon img = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\img" +  ex.getExid() + ".jpg").getImage().getScaledInstance(140, 184, Image.SCALE_SMOOTH));
		imgBtn.setIcon(img);
		titleL = new JLabel(ex.getTitle());
		con.add(titleL); titleL.setBounds(190, 30, 350, 50);
		date = new JLabel(ex.getStartdate() + " ~ " + ex.getEnddate());
		con.add(date); date.setBounds(190, 90, 350, 50);
		place = new JLabel(serv.getPlacename(ex.getPlaceid()));
		con.add(place); place.setBounds(190, 150, 350, 50);
		
		resP.add(con); con.setBounds(0, 60, 660, 370);
		
		f.add(resP); resP.setBounds(70, 90, 660, 350);
		
		
		f.setSize(800, 600);
		f.setVisible(true);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initStyle() {
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("나눔고딕", Font.BOLD, 24));

		resL.setFont(new Font("나눔고딕", Font.BOLD, 20));
		titleL.setFont(new Font("나눔고딕", Font.BOLD, 20));
		date.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		place.setFont(new Font("나눔고딕", Font.PLAIN, 16));
	}

	public static void main(String[] args) {
		
		CheckRes s = new CheckRes("ex");
		s.initStyle();
	}

}

