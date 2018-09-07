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
import vo.UsersVO;

public class Login {
	
	ExDAO ed = new ExDAO();
	PlacesDAO pd = new PlacesDAO();
	LocDAO ld = new LocDAO();
	ReviewsDAO rd = new ReviewsDAO();
	UsersDAO ud = new UsersDAO();
	ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);
	UsersVO us;
	
	JFrame f;
	JLabel title, idL, pwL, liTitle;
	JTextField idTF, pwTF;
	JButton btn;
	
	Login() {
		f = new JFrame("로그인");
		f.setLayout(null);
		
		JPanel tiP = new JPanel();
		tiP.setLayout(null); tiP.setBackground(null);
		title = new JLabel("로그인");
		title.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ft_main.jpg"));
		tiP.add(title);
		title.setBounds(0, 0, 700, 60);
		tiP.setBounds(70, 20, 660, 60);
		f.add(tiP);
		
		JPanel logTP = new JPanel();
		logTP.setLayout(null); logTP.setBackground(Color.WHITE);
		liTitle = new JLabel();
		logTP.add(liTitle); liTitle.setBounds(0, 0, 400, 50);
		liTitle.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\ft_login.jpg"));
		f.add(logTP); logTP.setBounds(170, 180, 400, 50);
		
		JPanel loginP = new JPanel();
		loginP.setLayout(null); loginP.setBackground(Color.WHITE);
		idL = new JLabel("ID");
		loginP.add(idL); idL.setBounds(30, 10, 50, 50);
		idTF = new JTextField();
		loginP.add(idTF); idTF.setBounds(90, 18, 180, 30);
		
		pwL = new JLabel("PW");
		loginP.add(pwL); pwL.setBounds(30, 60, 50, 50);
		pwTF = new JTextField();
		loginP.add(pwTF); pwTF.setBounds(90, 68, 180, 30);
		
		btn = new JButton();
		btn.setIcon(new ImageIcon("C:\\oracle\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\work_08_21\\project\\src\\img\\login.jpg"));
		loginP.add(btn); btn.setBounds(300, 35, 80, 40);
		f.add(loginP); loginP.setBounds(170, 230, 400, 130);
		
		f.setSize(800, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usid = idTF.getText();
				String pw = pwTF.getText();
				
				if (serv.logIn(usid, pw)) {
					ExApp ex = new ExApp(usid);
				}
				else
					JOptionPane.showMessageDialog(null, "아이디/비밀번호를 다시 확인하세요.");
					
			}
		});
		
	}
	
	public void initStyle() {
		idL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		pwL.setFont(new Font("나눔고딕", Font.BOLD, 16));
	}

	public static void main(String[] args) {
		
		Login li = new Login();
		li.initStyle();
	}

}
