package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.*;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExApp {

	JFrame f;
	JLabel title, dateL, placeL, priceL, gradeL;
	JLabel date, titleL, place, price, grade;
	JButton menu, search, viewLoc, viewMon, res;
	JPanel[] exP = new JPanel[5];
	JButton[] imgBtnArr = new JButton[5];
	boolean menuOn = false;
	List<ExVO> exArr1;
	List<ExVO> exArr2;
	String usid;
	static int id;

	ExDAO ed = new ExDAO();
	PlacesDAO pd = new PlacesDAO();
	LocDAO ld = new LocDAO();
	ReviewsDAO rd = new ReviewsDAO();
	UsersDAO ud = new UsersDAO();
	ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);
	
	ExApp(String usid) {
		this.usid = usid;
		f = new JFrame("main");
		f.setLayout(null);
		JPanel menuP = new JPanel(); menuP.setVisible(false);
		menuP.setLayout(null); menuP.setBackground(Color.WHITE);
		
		search = new JButton();
		menuP.add(search); search.setBounds(5, 0, 220, 70);
		ImageIcon seimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21"
				+ "\\project\\src\\img\\search.jpg").getImage().getScaledInstance(220, 70, Image.SCALE_SMOOTH));
		search.setIcon(seimg);
		viewLoc = new JButton();
		menuP.add(viewLoc); viewLoc.setBounds(5, 70, 220, 70);
		ImageIcon locimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21"
				+ "\\project\\src\\img\\loc.jpg").getImage().getScaledInstance(220, 70, Image.SCALE_SMOOTH));
		viewLoc.setIcon(locimg);
		viewMon = new JButton();
		menuP.add(viewMon); viewMon.setBounds(5, 140, 220, 70);
		ImageIcon monimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21"
				+ "\\project\\src\\img\\mon.jpg").getImage().getScaledInstance(220, 70, Image.SCALE_SMOOTH));
		viewMon.setIcon(monimg);
		res = new JButton();
		menuP.add(res); res.setBounds(5, 210, 220, 70);
		ImageIcon resimg = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21"
				+ "\\project\\src\\img\\chk.jpg").getImage().getScaledInstance(220, 70, Image.SCALE_SMOOTH));
		res.setIcon(resimg);
		menuP.setBounds(500, 80, 230, 280);
		f.add(menuP);
		
		JPanel tiP = new JPanel();
		tiP.setLayout(null); tiP.setBackground(null);
		title = new JLabel("main");
		title.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ft_main.jpg"));
		tiP.add(title);
		title.setBounds(0, 0, 660, 60);
		menu = new JButton(); menu.setBackground(null);
		f.add(menu); menu.setBounds(650, 30, 50, 55);
		menu.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\menuicon.png"));
		tiP.setBounds(70, 20, 660, 60);
		f.add(tiP);

		// 평점순 
		exArr1 = serv.getBestBr();
		addEx(1);
		// 예매순 
		exArr2 = serv.getBestRes();
		addEx(2);
		
		JLabel nothing = new JLabel();
		f.add(nothing);

		f.setSize(800, 600);
		f.setVisible(true);
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (menuOn) {
					menuP.setVisible(false);
					menuOn = false;
				}
				else {
					menuP.setVisible(true);
					menuOn = true;
				}
			}
		});
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search s = new Search(usid);
				s.initStyle();
				s.eventProc();
				menuP.setVisible(false);
			}
		});
		viewLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectByLoc s = new SelectByLoc(usid);
				// s.initStyle();
				menuP.setVisible(false);
			}
		});
		viewMon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectByMon s = new SelectByMon(usid);
				s.initStyle();
				menuP.setVisible(false);
			}
		});
		res.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckRes s = new CheckRes(usid);
				s.initStyle();
				menuP.setVisible(false);
			}
		});
	}


	public JPanel addEx(int num) {
		JPanel listP = new JPanel();
		listP.setLayout(null); listP.setBackground(Color.WHITE);
		List<ExVO> exArr = null;
		
		JLabel pTitle = new JLabel();
		if (num == 1) {
			pTitle.setText("평점 높은 전시");
			exArr = exArr1;
		}
		else {
			pTitle.setText("예매율 높은 전시");
			exArr = exArr2;
		}
		pTitle.setFont(new Font("나눔고딕", Font.BOLD, 16));
		listP.add(pTitle); pTitle.setBounds(10, 0, 300, 40);
		
		
		for (int i = 0; i < 3; i++) {
			int id = exArr.get(i).getExid();
			exP[i] = new JPanel();
			exP[i].setLayout(null); exP[i].setBackground(Color.WHITE);
			imgBtnArr[i] = new JButton("img" + (i+(num-1)*6+1));
			exP[i].add(imgBtnArr[i]); imgBtnArr[i].setBounds(10, 0, 72, 100);
			ImageIcon img = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
					+ "project\\src\\img\\img" + id + ".jpg").getImage().getScaledInstance(83, 100, Image.SCALE_SMOOTH));
			imgBtnArr[i].setIcon(img);
			imgBtnArr[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DetailInfo di = new DetailInfo(id, usid);
					di.initStyle();
				}
			});

			JPanel labelsP = new JPanel();
			labelsP.setLayout(null);
			labelsP.setBackground(Color.WHITE);

			// title
			//			titleL = new JLabel(exArr.get(i).getTitle());
			titleL = new JLabel(exArr.get(i).getTitle());
			exP[i].add(titleL);
			titleL.setBounds(90, 30, 180, 20);

			// date
			date = new JLabel(exArr.get(i).getStartdate() +
					" ~ " + exArr.get(i).getEnddate());
			labelsP.add(date);
			date.setBounds(10, 10, 180, 20);

			// place
			place = new JLabel(serv.getPlacename(exArr.get(i).getPlaceid()));
			labelsP.add(place);
			place.setBounds(10, 35, 180, 20);

			labelsP.setBounds(10, 110, 200, 70);
			exP[i].add(labelsP);

			listP.add(exP[i]); exP[i].setBounds(10 + (i)*200, 40, 200, 230);

			titleL.setFont(new Font("나눔고딕", Font.BOLD, 16));
			date.setFont(new Font("나눔고딕", Font.PLAIN, 14));
			place.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		}

		listP.setBounds(70, 90 + (240*(num-1)), 660, 230);
		f.add(listP);
		return listP;
	}

	public void initStyle() {
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("나눔고딕", Font.BOLD, 24));
		titleL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		date.setFont(new Font( "나눔고딕", Font.PLAIN, 16));
		place.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		
		search.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		viewLoc.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		viewMon.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		res.setFont(new Font("나눔고딕", Font.PLAIN, 16));
	}

	public static void main(String[] args) {

		ExApp ex = new ExApp("ex");
		ex.initStyle();
	}

}

