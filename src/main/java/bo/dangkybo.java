package bo;

import java.util.ArrayList;

import bean.khachhangbean;
import dao.khachhangdao;

public class dangkybo {
	khachhangdao khdao = new khachhangdao();
	ArrayList<khachhangbean> ds;
	
	public khachhangbean ktdke(String email) throws Exception
	{
		return khdao.KTDKE(email);
	}
	
	public khachhangbean ktdkun(String un) throws Exception
	{
		return khdao.KTDKUN(un);
	}
	
	
	public void ThemKH(String hoten, String email, String tendn, String pass) throws Exception {
		khdao.ThemKH(hoten, email, tendn, pass);
	}
}
