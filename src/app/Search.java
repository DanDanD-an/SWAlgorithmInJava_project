package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
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

public class Search {
	
	ExDAO ed = new ExDAO();
	PlacesDAO pd = new PlacesDAO();
	LocDAO ld = new LocDAO();
	ReviewsDAO rd = new ReviewsDAO();
	UsersDAO ud = new UsersDAO();
	ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);

	JFrame f;
	JLabel title, dateL, placeL, priceL, gradeL;
	JLabel date, titleL, place, price, grade;
	JLabel dateSL, plSL, titleSL, smL, sdL, emL, edL;
	JTextField titleTF, plTF;
	JComboBox<Integer> scbM, scbD, ecbM, ecbD;
	JButton search;
	JPanel[] exP = new JPanel[6];
	JButton[] imgBtnArr = new JButton[6];
	JPanel[] pageP = new JPanel[3];
	JLabel[] pageL = new JLabel[3];
	static int page = 1;
	List<ExVO> exArr;
	ExVO ex;
	static int num;
	static String usid;

	int [] lastDays = {
			31, 28, 31, 30, 31, 30,
			31, 31, 30, 31, 30, 31
	};

	Search(String usid) {
		this.usid = usid;
		f = new JFrame("전시회 찾기");
		f.setLayout(null);
		
		JPanel tiP = new JPanel();
		tiP.setLayout(null); tiP.setBackground(null);
		title = new JLabel("");
		title.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ft_s.jpg"));
		tiP.add(title);
		title.setBounds(0, 0, 660, 60);
		tiP.setBounds(70, 20, 660, 60);
		f.add(tiP);

		exArr = serv.getEx();
		

		JPanel searchP = new JPanel();
		searchP.setLayout(null); searchP.setBackground(Color.WHITE);

		titleSL = new JLabel("전시명");
		searchP.add(titleSL); titleSL.setBounds(10, 10, 100, 30);
		titleTF = new JTextField("title");
		searchP.add(titleTF); titleTF.setBounds(90, 10, 250, 30);

		dateSL = new JLabel("전시 기간");
		searchP.add(dateSL); dateSL.setBounds(10, 40, 100, 30);
		Integer [] dal = new Integer [12]; // Object 배열만 가능
		for (int i = 0; i < dal.length; i++) {
			dal[i] = i+1;
		}
		scbM = new JComboBox(dal); scbD = new JComboBox();
		searchP.add(scbM); scbM.setBounds(90, 40, 80, 30);
		smL = new JLabel("월"); searchP.add(smL); smL.setBounds(180, 40, 30, 30);
		searchP.add(scbD); scbD.setBounds(210, 40, 80, 30);
		sdL = new JLabel("일"); searchP.add(sdL); sdL.setBounds(300, 40, 30, 30);
		
		JLabel btw = new JLabel("~"); searchP.add(btw); btw.setBounds(350, 40, 30, 30);
		ecbM = new JComboBox(dal); ecbD = new JComboBox();
		searchP.add(ecbM); ecbM.setBounds(390, 40, 80, 30);
		emL = new JLabel("월"); searchP.add(emL); emL.setBounds(480, 40, 30, 30);
		searchP.add(ecbD); ecbD.setBounds(510, 40, 80, 30);
		edL = new JLabel("일"); searchP.add(edL); edL.setBounds(600, 40, 30, 30);
		
		plSL = new JLabel("전시 장소");
		searchP.add(plSL); plSL.setBounds(10, 70, 100, 30);
		plTF = new JTextField("place");
		searchP.add(plTF); plTF.setBounds(90, 70, 250, 30);
		
		pageP[page-1] = addEx(page);
		
		search = new JButton();
		search.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ser.jpg"));
		searchP.add(search); search.setBounds(560, 75, 80, 30);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < page; i++) {
					pageP[i].setVisible(false);
					f.remove(pageP[i]);
				}
				
				String title = null;
				String sm = null; String sd = null;
				String em = null; String ed = null;
				String pl = null;
				
				if (titleTF.getText().equals("title"))
					title = "%";
				else
					title = titleTF.getText();
				
				
				if (scbM.getSelectedIndex() < 9)
					sm = "0" + String.valueOf(scbM.getSelectedIndex()+1);
				else
					sm = String.valueOf(scbM.getSelectedIndex()+1);
				
				if (scbD.getSelectedIndex() < 9)
					sd = "0" + String.valueOf(scbD.getSelectedIndex()+1);
				else
					sd = String.valueOf(scbD.getSelectedIndex()+1);
				
				if (ecbM.getSelectedIndex() < 9)
					em = "0" + String.valueOf(ecbM.getSelectedIndex()+1);
				else
					em = String.valueOf(ecbM.getSelectedIndex()+1);
				
				if (ecbD.getSelectedIndex() < 9)
					ed = "0" + String.valueOf(ecbD.getSelectedIndex()+1);
				else
					ed = String.valueOf(ecbD.getSelectedIndex()+1);
				
				if (plTF.getText().equals("place"))
					pl = "%";
				else
					pl = plTF.getText();
				
				exArr = serv.search(title, sm, sd, em, ed, pl);
				pageP[page-1] = addEx(page); //pageP[page-1].setVisible(true);
			}
		});

		
		
		f.add(searchP); searchP.setBounds(70, 90, 660, 110);

		JPanel btnP = new JPanel();
		btnP.setLayout(null); btnP.setBackground(null);
		JButton prev = new JButton(); prev.setVisible(false);
		prev.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\prev.jpg"));
		f.add(prev); prev.setBounds(20, 350, 50, 50);
		//		pageL[page-1] = new JLabel(page + "/ 3 pages");
		//		btnP.add(pageL[page-1]); pageL[page-1].setBounds(300, 20, 150, 50);
		//		pageL[page-1].setFont(new Font("나눔고딕", Font.PLAIN, 14));
		JButton next = new JButton();
		next.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\next.jpg"));
		f.add(next); next.setBounds(730, 350, 50, 50);
		// btnP.setBounds(0, 300, 800, 80); f.add(btnP); 


		f.setSize(800, 620);
		f.setVisible(true);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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
		listP.setLayout(null); listP.setBackground(Color.WHITE);
		for (int i = 0; i < 6; i++) {
			int num = i+(page-1)*6;
			if (num >= exArr.size()) 
				break;
			exP[i] = new JPanel();
			exP[i].setLayout(null); exP[i].setBackground(Color.WHITE);
			
			imgBtnArr[i] = new JButton("img" + (i+(page-1)*6+1));
			exP[i].add(imgBtnArr[i]); imgBtnArr[i].setBounds(10, 0, 72, 100);
			ImageIcon img = new ImageIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
					+ "project\\src\\img\\img" + exArr.get(num).getExid() + ".jpg").getImage().getScaledInstance(83, 100, Image.SCALE_SMOOTH));
			imgBtnArr[i].setIcon(img);
			imgBtnArr[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DetailInfo di = new DetailInfo(exArr.get(num).getExid(), usid);
					di.initStyle();
				}
			});
			
			JPanel labelsP = new JPanel();
			labelsP.setLayout(null);
			labelsP.setBackground(Color.WHITE);

			// title
			//			titleL = new JLabel(exArr.get(i).getTitle());
			titleL = new JLabel(exArr.get(i+(page-1)*6).getTitle());
			
			labelsP.add(titleL);
			titleL.setBounds(10, 10, 180, 20);

			// date
			date = new JLabel(exArr.get(i+(page-1)*6).getStartdate() +
					" ~ " + exArr.get(i+(page-1)*6).getEnddate());
			labelsP.add(date);
			date.setBounds(10, 35, 180, 20);

			// place
			place = new JLabel(serv.getPlacename(exArr.get(i+(page-1)*4).getPlaceid()));
			labelsP.add(place);
			place.setBounds(10, 60, 180, 20);

			labelsP.setBounds(90, 0, 200, 100);
			exP[i].add(labelsP);

			listP.add(exP[i]); exP[i].setBounds(10 + (i%2)*320, 10 + (i/2)*110, 320, 100);

			titleL.setFont(new Font("나눔고딕", Font.BOLD, 16));
			date.setFont(new Font("나눔고딕", Font.PLAIN, 14));
			place.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		}

		listP.setBounds(70, 210, 660, 340);
		f.add(listP);
		return listP;
	}

	public void eventProc() {
		EvtHdlr eh = new EvtHdlr();
		scbM.addActionListener(eh);
		ecbM.addActionListener(eh);
	}

	class EvtHdlr implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Object evt = e.getSource();

			if (evt == scbM)
				getDate1();
			else if (evt == ecbM)
				getDate2();
		}

	}

	void getDate1() {

		// 월 정보 저장
		int m = scbM.getSelectedIndex();

		// 날짜 설정
		scbD.removeAllItems();
		for (int i = 1; i <= lastDays[m]; i++) {
			scbD.addItem(i);
		}
	}
	
	void getDate2() {

		// 월 정보 저장
		int m = ecbM.getSelectedIndex();

		// 날짜 설정
		ecbD.removeAllItems();
		for (int i = 1; i <= lastDays[m]; i++) {
			ecbD.addItem(i);
		}
	}
	
		public void initStyle() {
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setVerticalAlignment(SwingConstants.CENTER);
			title.setFont(new Font("나눔고딕", Font.BOLD, 24));

			titleSL.setFont(new Font("나눔고딕", Font.PLAIN, 16));
			dateSL.setFont(new Font("나눔고딕", Font.PLAIN, 16));
			plSL.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		}


		public static void main(String[] args) {
			
			Search s = new Search(usid);
			s.initStyle();
			s.eventProc();
		}
	}

