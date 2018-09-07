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
import vo.ExVO;
import vo.ReviewsVO;

public class DetailInfo {
	
	ExDAO ed = new ExDAO();
	PlacesDAO pd = new PlacesDAO();
	LocDAO ld = new LocDAO();
	ReviewsDAO rd = new ReviewsDAO();
	UsersDAO ud = new UsersDAO();
	ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);

	JFrame f;
	JLabel dateL, placeL, priceL, gradeL;
	JLabel title, date, place, price, grade, nothing1, nothing2, nothing3;
	JLabel revL, grade1, rev1, grade2, rev2;
	JButton imgBtn, reserve, write, update, showAllBtn;
	int gradeN;
	int exid = 1;
	ExVO ex;
	List<ReviewsVO> re = null;
	
	DetailInfo(int exid, String usid) {
		f = new JFrame("상세 정보");
		f.setLayout(null); f.setBackground(Color.WHITE);
		
		ex = serv.searchExById(exid);
		re = serv.searchRevByExid(exid);
		
		// title
		JPanel tiP = new JPanel();
		tiP.setLayout(null);
		title = new JLabel(ex.getTitle());
		tiP.setBackground(Color.WHITE);
		tiP.add(title); tiP.setBounds(70, 20, 660, 60);
		title.setBounds(0, 0, 700, 60); f.add(tiP);
		
		
		// Img
		JPanel imgP = new JPanel();
		imgP.setLayout(null);
		imgBtn = new JButton("img");
		//imgBtn.setBackground(null); imgBtn.setBackground(Color.blue);
		ImageIcon img = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\img" +  exid + ".jpg").getImage().getScaledInstance(210, 288, Image.SCALE_SMOOTH));
		imgBtn.setIcon(img);
		imgP.add(imgBtn); imgBtn.setBounds(0, 0, 200, 288);
		imgBtn.setToolTipText("이미지를 클릭하면 해당 사이트로 이동합니다.");
		f.add(imgP); imgP.setBounds(70, 90, 200, 288);
		
		
		// Info
		JPanel labelsP = new JPanel();
		labelsP.setLayout(null); labelsP.setBackground(Color.WHITE);
		
		// date
		dateL = new JLabel("기간");
		labelsP.add(dateL); dateL.setBounds(10, 20, 70, 40);
		date = new JLabel(ex.getStartdate() + " ~ " + ex.getEnddate());
		labelsP.add(date); date.setBounds(120, 20, 350, 40);
		
		// place
		placeL = new JLabel("장소");
		labelsP.add(placeL); placeL.setBounds(10, 60, 70, 40);
		place = new JLabel(serv.getPlacename(ex.getPlaceid()));
		labelsP.add(place); place.setBounds(120, 60, 350, 40);
		
		// price
		priceL = new JLabel("가격");
		labelsP.add(priceL); priceL.setBounds(10, 100, 70, 40);
		price = new JLabel(String.valueOf(ex.getPrice()));
		labelsP.add(price); price.setBounds(120, 100, 350, 40);
		
		// grade
		gradeL = new JLabel("평점");
		labelsP.add(gradeL); gradeL.setBounds(10, 140, 70, 40);
		grade = new JLabel(String.valueOf(serv.getAvgGr(exid)));
		labelsP.add(grade); grade.setBounds(120, 140, 350, 40);
		ImageIcon agrimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\gr_" + (int)serv.getAvgGr(exid) + ".jpg").getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH));
		grade.setIcon(agrimg);
		// grade만큼 별표 보여주는 코드 추가
		
		// buttons
		reserve = new JButton();
		labelsP.add(reserve); reserve.setBounds(50, 210, 100, 50);
		reserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserve res = new Reserve(ex.getExid(), usid);
				res.initStyle();
			}
		});
		ImageIcon resimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\res.jpg").getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH));
		reserve.setIcon(resimg);
		
		write = new JButton();
		labelsP.add(write); write.setBounds(165, 210, 100, 50);
		write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteRev wr = new WriteRev(exid, usid);
				wr.initStyle();
			}
		});
		ImageIcon revimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\rev.jpg").getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH));
		write.setIcon(revimg);
		// labelsP.add(update); update.setBounds(275, 210, 100, 50);
		
		labelsP.setBounds(290, 90, 440, 290); f.add(labelsP);
		
		
		// Review & Grade
		JPanel revP = new JPanel();
		revP.setLayout(null); revP.setBackground(Color.WHITE);
		revL = new JLabel("평점/리뷰");
		revP.add(revL); revL.setBounds(20, 10, 100, 20);
		
		
		// showAll
		showAllBtn = new JButton();
		revP.add(showAllBtn); showAllBtn.setBounds(550, 10, 100, 15);
		showAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllReview ar = new AllReview(exid);
				ar.initStyle();
			}
		});
		ImageIcon saimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\showAllbtn.jpg").getImage().getScaledInstance(100, 15, Image.SCALE_SMOOTH));
		showAllBtn.setIcon(saimg);
		
		
			// rev1
			JPanel rev1P = new JPanel();
			rev1P.setBackground(Color.WHITE); rev1P.setLayout(null);
			if (re.size() > 0) {
			grade1 = new JLabel(String.valueOf(re.get(0).getGrade()));
			ImageIcon grimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
					+ "project\\src\\img\\gr_s_" + re.get(0).getGrade() + ".jpg").getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH));
			grade1.setIcon(grimg);
			rev1 = new JLabel(String.valueOf(re.get(0).getReview()));
			rev1P.add(grade1); grade1.setBounds(10, 5, 150, 20);
			rev1P.add(rev1); rev1.setBounds(10, 35, 640, 15);
			}
			else {
				grade1 = new JLabel();
				rev1 = new JLabel();
				rev1P.add(grade1); grade1.setBounds(10, 5, 100, 20);
				rev1P.add(rev1); rev1.setBounds(10, 40, 640, 15);				
			}
			revP.add(rev1P); rev1P.setBounds(10, 35, 640, 50);
			nothing2 = new JLabel();
			revP.add(nothing2);
			
			// rev2
			JPanel rev2P = new JPanel();
			rev2P.setBackground(Color.WHITE); rev2P.setLayout(null);
			if (re != null && re.size() > 1) {
			grade2 = new JLabel(String.valueOf(re.get(1).getGrade()));
			ImageIcon grimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
					+ "project\\src\\img\\gr_s_" + re.get(1).getGrade() + ".jpg").getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH));
			grade2.setIcon(grimg);
			rev2 = new JLabel(String.valueOf(re.get(1).getReview())); 
			rev2P.add(grade2); grade2.setBounds(10, 5, 150, 20);
			rev2P.add(rev2); rev2.setBounds(10, 35, 640, 15);
			}
			else {
				grade2 = new JLabel(); 
				rev2 = new JLabel();
				rev2P.add(grade2); grade2.setBounds(10, 5, 100, 20);
				rev2P.add(rev2); rev2.setBounds(10, 30, 640, 15);
			}
			revP.add(rev2P); rev2P.setBounds(10, 90, 640, 50);
		
		
		f.add(revP);
		revP.setBounds(70, 390, 660, 160);
		
		f.setSize(800, 600);
		f.setVisible(true);
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initStyle() {
		
		title.setHorizontalAlignment(SwingConstants.CENTER);
		dateL.setHorizontalAlignment(SwingConstants.CENTER);
		placeL.setHorizontalAlignment(SwingConstants.CENTER);
		priceL.setHorizontalAlignment(SwingConstants.CENTER);
		gradeL.setHorizontalAlignment(SwingConstants.CENTER);
//		date.setHorizontalAlignment(SwingConstants.CENTER);
//		place.setHorizontalAlignment(SwingConstants.CENTER);
//		price.setHorizontalAlignment(SwingConstants.CENTER);
//		grade.setHorizontalAlignment(SwingConstants.CENTER);
		
		title.setFont(new Font( "나눔고딕", Font.BOLD, 24));
		dateL.setFont(new Font( "나눔고딕", Font.BOLD, 16));
		date.setFont(new Font( "나눔고딕", Font.PLAIN, 16));
		placeL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		place.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		priceL.setFont(new Font( "나눔고딕", Font.BOLD, 16));
		price.setFont(new Font( "나눔고딕", Font.PLAIN, 16));
		gradeL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		grade.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		
		revL.setFont(new Font( "나눔고딕", Font.BOLD, 20));
		grade1.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		rev1.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		grade2.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		rev2.setFont(new Font("나눔고딕", Font.PLAIN, 14));
	}
	
	public static void main(String[] args) {
		
		DetailInfo di = new DetailInfo(1, "dd");
		di.initStyle();
	}
}
