package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.ExDAO;
import dao.LocDAO;
import dao.PlacesDAO;
import dao.ReviewsDAO;
import dao.UsersDAO;
import service.ExService;
import service.ExServiceImpl;
import vo.ExVO;

public class ViewByLoc {
	
	ExDAO ed = new ExDAO();
	PlacesDAO pd = new PlacesDAO();
	LocDAO ld = new LocDAO();
	ReviewsDAO rd = new ReviewsDAO();
	UsersDAO ud = new UsersDAO();
	ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);

	JFrame f;
	JLabel title, dateL, placeL, priceL, gradeL;
	JLabel date, titleL, place, price, grade;
	JPanel[] exP = new JPanel[6];
	JButton[] imgBtnArr = new JButton[6];
	JPanel[] pageP = new JPanel[3];
	JLabel[] pageL = new JLabel[3];
	List<ExVO> exArr;
	int locid = 1;
	static int page = 1;
	String usid;

	ViewByLoc(int locid, String usid) {
		this.locid = locid;
		this.usid = usid;
		f = new JFrame(" 전시");
		f.setLayout(null);

		JPanel tiP = new JPanel();
		tiP.setLayout(null); tiP.setBackground(null);
		title = new JLabel(locid + " 전시");
		title.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ft_vl_" + locid +".jpg"));
		tiP.add(title);
		title.setBounds(0, 0, 700, 60);
		tiP.setBounds(70, 20, 660, 60);
		f.add(tiP);
		
		exArr = serv.searchExByLoc(serv.getLocname(locid));
		
		pageP[page-1] = addEx(page);
		
		JPanel btnP = new JPanel();
		btnP.setLayout(null); btnP.setBackground(Color.WHITE);
		JButton prev = new JButton(); prev.setVisible(false);
		btnP.add(prev); prev.setBounds(100, 20, 100, 50);
		prev.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\pre.jpg"));
		pageL[page-1] = new JLabel(page + "/ 3 pages");
		btnP.add(pageL[page-1]); pageL[page-1].setBounds(300, 20, 150, 50);
		pageL[page-1].setFont(new Font("나눔고딕", Font.PLAIN, 14));
		JButton next = new JButton();
		btnP.add(next); next.setBounds(460, 20, 100, 50);
		next.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\"
				+ "project\\src\\img\\nex.jpg"));
		btnP.setBounds(70, 460, 660, 80); f.add(btnP); 
		
		JLabel nothing = new JLabel("");
		f.add(nothing);

		f.setSize(800, 600);
		f.setVisible(true);
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageP[page-1].setVisible(false); pageL[page-1].setVisible(false);
				// addEx(page).setVisible(false);
				page++; 
				pageL[page-1] = new JLabel(page + "/ 3 pages");
				btnP.add(pageL[page-1]); pageL[page-1].setBounds(300, 20, 150, 50);
				pageL[page-1].setFont(new Font("나눔고딕", Font.PLAIN, 14));
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
				pageP[page-1].setVisible(false); pageL[page-1].setVisible(false);
				addEx(page).setVisible(false);
				page--;
				pageL[page-1] = new JLabel(page + "/ 3 pages");
				btnP.add(pageL[page-1]); pageL[page-1].setBounds(300, 20, 150, 50);
				pageL[page-1].setFont(new Font("나눔고딕", Font.PLAIN, 14));
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

	public void initStyle() {
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("나눔고딕", Font.BOLD, 24));
		titleL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		date.setFont(new Font( "나눔고딕", Font.PLAIN, 16));
		place.setFont(new Font("나눔고딕", Font.PLAIN, 16));
	}
	
	public JPanel addEx(int page) {
		JPanel listP = new JPanel();
		listP.setLayout(null); listP.setBackground(Color.WHITE);
		for (int i = 0; i < 6; i++) {
			int num = i + (page-1)*6;
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
			//labelsP.setBackground(Color.BLUE);

			// title
			titleL = new JLabel(exArr.get(i+(page-1)*6).getTitle());
			labelsP.add(titleL);
			titleL.setBounds(10, 10, 180, 20);

			// date
			date = new JLabel(exArr.get(i+(page-1)*6).getStartdate() +
					" ~ " + exArr.get(i+(page-1)*6).getEnddate());
			labelsP.add(date);
			date.setBounds(10, 35, 180, 20);

			// place
			place = new JLabel(serv.getPlacename(exArr.get(i+(page-1)*6).getPlaceid()));
			labelsP.add(place);
			place.setBounds(10, 60, 180, 20);

			labelsP.setBounds(90, 0, 200, 110);
			exP[i].add(labelsP);

			listP.add(exP[i]); exP[i].setBounds(10 + (i%2)*320, 10 + (i/2)*120, 320, 110);
			
			titleL.setFont(new Font("나눔고딕", Font.BOLD, 16));
			date.setFont(new Font("나눔고딕", Font.PLAIN, 14));
			place.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		}
		
		listP.setBounds(70, 90, 660, 380);
		f.add(listP);
		return listP;
	}

	public static void main(String[] args) {

//		ViewByLoc ex = new ViewByLoc(locid);
//		ex.initStyle();
	}
}
