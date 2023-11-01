package bo;

import java.util.ArrayList;

import bean.khachhangbean;
import dao.khachhangdao;

public class khachhangbo {
	khachhangdao khdao = new khachhangdao();
	ArrayList<khachhangbean> ds;
	
	public ArrayList<khachhangbean> getDSKH()  throws Exception
	{
		return khdao.getDSKH();
	}
	public khachhangbean KTDK(String un, String pass) throws Exception
	{
		return khdao.KTDN(un, pass);
	}

//	public void ThemKH(String hoten, String diachi, String email, String tendn, String pass) throws Exception {
//		khdao.ThemKH(hoten, diachi, email, tendn, pass);
//	}
//	
	public int UpdateDC(String dc, long makh) throws Exception
	{
		return khdao.UpdateAddress(dc, makh);
	}
	
	public int UpdateSDT(String sdt, long makh) throws Exception
	{
		return khdao.UpdateSDT(sdt, makh);
	}
	
	public int  UpdateTTKH(long makh, String hoten, String diachi, String email, String sodt, String tendn) throws Exception
	{
		return khdao.UpdateTTKH(makh, hoten, diachi, email, sodt, tendn);
	}
}
