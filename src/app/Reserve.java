package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RescaleOp;

import javax.swing.*;

import dao.ExDAO;
import dao.LocDAO;
import dao.PlacesDAO;
import dao.ReviewsDAO;
import dao.UsersDAO;
import service.ExService;
import service.ExServiceImpl;
import vo.ExVO;

public class Reserve {
	
	ExDAO ed = new ExDAO();
	PlacesDAO pd = new PlacesDAO();
	LocDAO ld = new LocDAO();
	ReviewsDAO rd = new ReviewsDAO();
	UsersDAO ud = new UsersDAO();
	ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);

	JFrame f;
	JLabel dateL, placeL, priceL, gradeL;
	JLabel title, date, place, price, grade;
	JLabel resL, numL, cntL, prL, toprL, wonL;
	JLabel userInfoL, useridL, userid, nameL, emailL;
	JTextField numTF, toprTF, nameTF, emailTF;
	JButton imgBtn, plusBtn, subBtn, reserve;
	int gradeN;
	ExVO ex;

	Reserve(int exid, String usid) {
		f = new JFrame("예매");
		f.setLayout(null); f.setBackground(Color.WHITE);
		
		ex = serv.searchExById(exid);

		// title
		JPanel tiP = new JPanel();
		tiP.setLayout(null);
		title = new JLabel(ex.getTitle());
		tiP.setBackground(Color.WHITE);
		tiP.add(title); title.setBounds(0, 0, 700, 60);
		tiP.setBounds(70, 20, 660, 60); f.add(tiP);

		// Img
		JPanel imgP = new JPanel();
		imgP.setLayout(null);
		imgBtn = new JButton("img");
		//imgBtn.setBackground(null);
		imgBtn.setBackground(Color.GRAY);
		imgBtn.setBounds(0, 0, 200, 288); imgP.add(imgBtn);
		ImageIcon img = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\img" +  exid + ".jpg").getImage().getScaledInstance(210, 288, Image.SCALE_SMOOTH));
		imgBtn.setIcon(img);
		imgBtn.setToolTipText("이미지를 클릭하면 해당 사이트로 이동합니다.");
		imgP.setBounds(70, 90, 200, 288); f.add(imgP);

		// Info
		JPanel labelsP = new JPanel();
		labelsP.setLayout(null); labelsP.setBackground(Color.WHITE);

		// date
		dateL = new JLabel("기간");
		labelsP.add(dateL); dateL.setBounds(10, 20, 40, 20);
		date = new JLabel(ex.getStartdate() + " ~ " + ex.getEnddate());
		labelsP.add(date); date.setBounds(60, 20, 100, 20);

		// place
		placeL = new JLabel("장소");
		labelsP.add(placeL); placeL.setBounds(10, 50, 40, 20);
		place = new JLabel(serv.getPlacename(ex.getPlaceid()));
		labelsP.add(place);	place.setBounds(60, 50, 100, 20);

		// price
		priceL = new JLabel("가격");
		labelsP.add(priceL); priceL.setBounds(10, 80, 40, 20);
		price = new JLabel(String.valueOf(ex.getPrice()));
		labelsP.add(price); price.setBounds(60, 80, 100, 20);

		// grade
		gradeL = new JLabel("평점");
		labelsP.add(gradeL); gradeL.setBounds(10, 110, 40, 20);
		grade = new JLabel(String.valueOf(serv.getAvgGr(exid)));
		labelsP.add(grade); grade.setBounds(60, 110, 100, 20);
		ImageIcon agrimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\gr_s_" + (int)serv.getAvgGr(exid) + ".jpg").getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH));
		grade.setIcon(agrimg);
		// grade만큼 별표 보여주는 코드 추가

		labelsP.setBounds(70, 390, 200, 160); f.add(labelsP);

		
		// reservation
		JPanel resvaP = new JPanel();
		resvaP.setLayout(null); resvaP.setBackground(Color.WHITE); 
		JPanel resP = new JPanel();
		resP.setLayout(null); resP.setBackground(Color.WHITE); 
		
		resL = new JLabel("예매하기");
		resP.add(resL); resL.setBounds(40, 10, 100, 50);
		
		numL = new JLabel("매수 선택");
		resP.add(numL); numL.setBounds(40, 70, 100, 20);
		
		prL = new JLabel("13000원");
		resP.add(prL); prL.setBounds(40, 110, 100, 30);
		plusBtn = new JButton();
		plusBtn.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21"
		+ "\\project\\src\\img\\plus.jpg"));
		resP.add(plusBtn); plusBtn.setBounds(150, 110, 30, 30);
		numTF = new JTextField("0");
		resP.add(numTF); numTF.setBounds(190, 110, 100, 30);
		subBtn = new JButton();
		subBtn.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21"
				+ "\\project\\src\\img\\sub.jpg"));
		resP.add(subBtn); subBtn.setBounds(300, 110, 30, 30);
		cntL = new JLabel("매");
		resP.add(cntL); cntL.setBounds(360, 110, 20, 30);
		
		toprL = new JLabel("결제 예정 금액");
		resP.add(toprL); toprL.setBounds(40, 180, 100, 20);
		
		toprTF = new JTextField();
		resP.add(toprTF); toprTF.setBounds(250, 210, 100, 30);
		wonL = new JLabel("원");
		resP.add(wonL); wonL.setBounds(360, 210, 20, 30);
		
		resvaP.add(resP); resP.setBounds(0, 0, 440, 260);
		
		// userInfo
		JPanel userP = new JPanel();
		userP.setLayout(null); userP.setBackground(Color.WHITE); 
		
		userInfoL = new JLabel("예매자 정보");
		userP.add(userInfoL); userInfoL.setBounds(40, 0, 100, 30);
		
		useridL = new JLabel("예매자 ID");
		userP.add(useridL); useridL.setBounds(40, 50, 100, 20);
		userid = new JLabel("예매자 ID");
		userP.add(userid); userid.setBounds(160, 50, 100, 20);
		
		nameL = new JLabel("예매자 성함");
		userP.add(nameL); nameL.setBounds(40, 90, 100, 20);
		nameTF = new JTextField();
		userP.add(nameTF); nameTF.setBounds(160, 90, 100, 25);
		
		emailL = new JLabel("e-mail");
		userP.add(emailL); emailL.setBounds(40, 130, 100, 20);
		emailTF = new JTextField();
		userP.add(emailTF); emailTF.setBounds(160, 130, 100, 25);
		
		userP.setBounds(0, 260, 440, 160);
		resvaP.add(userP); 
		
		reserve = new JButton();
		resvaP.add(reserve); reserve.setBounds(310, 420, 100, 30);
		reserve.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\reserv.jpg"));
		
		resvaP.setBounds(290, 90, 440, 460);
		f.add(resvaP);
		
		
		f.setSize(800, 600);
		f.setVisible(true);
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		plusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(numTF.getText());
				num++;
				numTF.setText(String.valueOf(num));
				toprTF.setText(String.valueOf(num*(ex.getPrice())));
			}
		});
		
		subBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(numTF.getText());
				if (num == 0)
					numTF.setText(String.valueOf(0));
				else {
					num--;
					numTF.setText(String.valueOf(num));
				}
				toprTF.setText(String.valueOf(num*(ex.getPrice())));
			}
		});
		
		reserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!numTF.getText().equals("0")) && !nameTF.getText().equals("") &&
					!emailTF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "예매되었습니다!");
					try {
						serv.reserve(usid, ex.getExid());
						f.setVisible(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if (numTF.getText().equals("0"))
					JOptionPane.showMessageDialog(null, "매수를 선택해주세요.");
				else if (nameTF.getText().equals(""))
					JOptionPane.showMessageDialog(null, "예매자 성함을 입력해주세요.");
				else if (emailTF.getText().equals(""))
					JOptionPane.showMessageDialog(null, "예매자 이메일 주소를 입력해주세요.");
			}
		});
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
		dateL.setFont(new Font( "나눔고딕", Font.BOLD, 13));
		date.setFont(new Font( "나눔고딕", Font.PLAIN, 13));
		placeL.setFont(new Font("나눔고딕", Font.BOLD, 13));
		place.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		priceL.setFont(new Font( "나눔고딕", Font.BOLD, 13));
		price.setFont(new Font( "나눔고딕", Font.PLAIN, 13));
		gradeL.setFont(new Font("나눔고딕", Font.BOLD, 13));
		grade.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		
		resL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		numL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		prL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		toprL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		wonL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		
		userInfoL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		useridL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		userid.setFont(new Font("나눔고딕", Font.BOLD, 14));
		nameL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		emailL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		
		numTF.setFont(new Font("나눔고딕", Font.BOLD, 14));
	}

	public static void main(String[] args) {

		Reserve di = new Reserve(1, "dd");
		di.initStyle();
	}

}
