package app;

import java.util.*;
import javax.swing.JOptionPane;
import dao.ExDAO;
import dao.LocDAO;
import dao.PlacesDAO;
import dao.ReviewsDAO;
import dao.UsersDAO;
import service.ExService;
import service.ExServiceImpl;
import vo.ExVO;

public class AppTest {

	public static void main(String[] args) {
		
		ExDAO ed = new ExDAO();
		PlacesDAO pd = new PlacesDAO();
		LocDAO ld = new LocDAO();
		ReviewsDAO rd = new ReviewsDAO();
		UsersDAO ud = new UsersDAO();
		ExService serv = new ExServiceImpl(ed, pd, ld, ud, rd);

		//String msg1 = JOptionPane.showInputDialog("title/price 입력하세요.");
		//String re[] = msg1.split("/");
		
		
		//System.out.println("2018-09-28" - "2018-08-28");
//		Scanner sc = new Scanner(System.in);
//		int num = Integer.parseInt(sc.nextLine());
		//System.out.println(re[0]);
		System.out.println(serv.getBestBr());
//		System.out.println(re[0]);
//		System.out.println(serv.searchExByDate(re[0], re[1], re[2], re[3]));
	}

}
