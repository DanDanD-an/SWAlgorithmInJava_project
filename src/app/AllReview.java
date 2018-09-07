package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import app.Search.EvtHdlr;
import dao.ExDAO;
import dao.LocDAO;
import dao.PlacesDAO;
import dao.ReviewsDAO;
import dao.UsersDAO;
import service.ExService;
import service.ExServiceImpl;
import vo.ExVO;
import vo.ReviewsVO;

public class AllReview {

	ExDAO ed = new ExDAO();
	PlacesDAO pd = new PlacesDAO();
	LocDAO ld = new LocDAO();
	ReviewsDAO rd = new ReviewsDAO();
	UsersDAO ud = new UsersDAO();
	ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);
	int page = 1;
	static int num, pageNum;

	JFrame f;
	JPanel[] exP;
	JPanel[] pageP;
	JLabel[] grade;
	JLabel[] rev;
	JLabel[] usid;
	JLabel title, toNum, avgGr;

	int gradeN;
	int exid = 1;
	List<ReviewsVO> re = null;

	AllReview(int exid) {

		f = new JFrame("전체 리뷰 보기");
		f.setLayout(null);

		num = serv.getGradeNum(exid);
		pageNum = serv.getGradeNum(exid) / 4 + 1;
		System.out.println(pageNum);
		exP = new JPanel[4];
		pageP = new JPanel[4];
		grade = new JLabel[4];
		rev = new JLabel[4];
		usid = new JLabel[4];

		re = serv.searchRevByExid(exid);
		System.out.println(re);

		JPanel tiP = new JPanel();
		tiP.setLayout(null);
		title = new JLabel(serv.searchExById(exid).getTitle());
		tiP.setBackground(Color.WHITE);
		tiP.add(title); tiP.setBounds(70, 20, 660, 60);
		title.setBounds(150, 0, 700, 60); f.add(tiP);

		// head
		JPanel headP = new JPanel();
		headP.setLayout(null); headP.setBackground(Color.WHITE);

		JLabel gradeImg = new JLabel();
		ImageIcon img = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\gr_" + (int)serv.getAvgGr(exid) + ".jpg").getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH));
		gradeImg.setIcon(img);
		headP.add(gradeImg); gradeImg.setBounds(20, 10, 150, 30);
		avgGr = new JLabel(String.valueOf(serv.getAvgGr(exid)));
		headP.add(avgGr); avgGr.setBounds(170, 10, 50, 30);
		toNum = new JLabel(serv.getGradeNum(exid) + "개의 리뷰");
		headP.add(toNum); toNum.setBounds(230, 10, 100, 30);

		f.add(headP); headP.setBounds(70, 85, 660, 50);


		// review & grade
		pageP[page-1] = addEx(page);

		JPanel btnP = new JPanel();
		btnP.setLayout(null); btnP.setBackground(null);
		JButton prev = new JButton(); prev.setVisible(false);
		prev.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\prev.jpg"));
		f.add(prev); prev.setBounds(10, 300, 50, 50);
		
		//		pageL[page-1] = new JLabel(page + "/ 3 pages");
		//		btnP.add(pageL[page-1]); pageL[page-1].setBounds(300, 20, 150, 50);
		//		pageL[page-1].setFont(new Font("나눔고딕", Font.PLAIN, 14));
		JButton next = new JButton();
		next.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\next.jpg"));
		f.add(next); next.setBounds(740, 300, 50, 50);
		// btnP.setBounds(0, 300, 800, 80); f.add(btnP); 

		f.setSize(800, 600);
		f.setVisible(true);
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageP[page-1].setVisible(false); // pageL[page-1].setVisible(false);
				addEx(page).setVisible(false);
				page++; 
				//				pageL[page-1] = new JLabel(page + "/ 3 pages");
				//				btnP.add(pageL[page-1]); pageL[page-1].setBounds(300, 20, 150, 50);
				//				pageL[page-1].setFont(new Font("나눔고딕", Font.PLAIN, 14));
				pageP[page-1] = addEx(page);

				JLabel nothing2 = new JLabel("");
				f.add(nothing2);

				if(page == 3)
					next.setVisible(false);
				if (page != 1)
					prev.setVisible(true);
			}
		});

		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageP[page-1].setVisible(false); // pageL[page-1].setVisible(false);
				addEx(page).setVisible(false);
				page--;
				//				pageL[page-1] = new JLabel(page + "/ 3 pages");
				//				btnP.add(pageL[page-1]); pageL[page-1].setBounds(300, 20, 150, 50);
				//				pageL[page-1].setFont(new Font("나눔고딕", Font.PLAIN, 14));
				pageP[page-1] = addEx(page);

				JLabel nothing2 = new JLabel("");
				f.add(nothing2);

				if(page != 3)
					next.setVisible(true);
				if (page == 1)
					prev.setVisible(false);
			}
		});


	}

	public JPanel addEx(int page) {
		JPanel listP = new JPanel();
		listP.setLayout(null); listP.setBackground(null);
		for (int i = 0; i < 4; i++) {
			
			if (i+(page-1)*4 >= num) 
				break;
			exP[i] = new JPanel();
			exP[i].setLayout(null); exP[i].setBackground(null);

			JPanel labelsP = new JPanel();
			labelsP.setLayout(null);
			labelsP.setBackground(Color.WHITE);

			grade[i] = new JLabel(String.valueOf(re.get(i+(page-1)*4).getGrade()));
			ImageIcon grimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
					+ "project\\src\\img\\gr_s_" + re.get(i+(page-1)*4).getGrade() + ".jpg").getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH));
			grade[i].setIcon(grimg);
			labelsP.add(grade[i]); grade[i].setBounds(30, 10, 640, 20);

			rev[i] = new JLabel(String.valueOf(re.get(i+(page-1)*4).getReview()));
			labelsP.add(rev[i]); rev[i].setBounds(30, 40, 610, 20);
			usid[i] = new JLabel(String.valueOf(re.get(i+(page-1)*4).getUserid()) + " 님");
			labelsP.add(usid[i]); usid[i].setBounds(30, 65, 640, 20);

			labelsP.setBounds(0, 0, 660, 100);
			exP[i].add(labelsP);

			listP.add(exP[i]); exP[i].setBounds(0, 5 + (i)*100, 660, 95);

			grade[i].setFont(new Font("나눔고딕", Font.BOLD, 16));
			rev[i].setFont(new Font("나눔고딕", Font.PLAIN, 16));
			usid[i].setFont(new Font("나눔고딕", Font.PLAIN, 14));
		}

		listP.setBounds(70, 140, 660, 410);
		f.add(listP);
		return listP;
	}

	void initStyle() {
		title.setFont(new Font("나눔고딕", Font.BOLD, 24));
		title.setHorizontalTextPosition(SwingConstants.CENTER);
		toNum.setFont(new Font("나눔고딕", Font.BOLD, 16));
		avgGr.setFont(new Font("나눔고딕", Font.BOLD, 16));
	}

	public static void main(String[] args) {

		AllReview ar = new AllReview(1);
		ar.initStyle();
	}

}
