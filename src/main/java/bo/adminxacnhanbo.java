package bo;

import java.util.ArrayList;

import bean.adminxacnhanbean;
import dao.adminxnhddao;

public class adminxacnhanbo {
	adminxnhddao xndao = new adminxnhddao();
	
	public ArrayList<adminxacnhanbean> getAllCTHD() throws Exception
	{
		return xndao.getAllCTHD();
	}
	
	public ArrayList<adminxacnhanbean> getListWithPage(int page, int pageSize, int tt) throws Exception {
		return xndao.getListWithPage(page, pageSize, tt);
	}
	
	public int getSoLuong(int tt) throws Exception{
		return xndao.getSoLuong(tt);
	}
	
	public int Sua(long macthd) throws Exception
	{
		return xndao.Sua(macthd);
	}
	
	public long Check(long mhd) throws Exception
	{
		return xndao.Check(mhd);
	}
	
	public void UpdateHD(long mahd) throws Exception
	{
		xndao.UpdateHD(mahd);
	}
}
