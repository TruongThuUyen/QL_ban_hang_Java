package bo;

import java.util.ArrayList;

import bean.admindsmhbean;
import dao.admindsmhdao;

public class admindsmhbo {
	admindsmhdao dsmhdao = new admindsmhdao();
	
	public ArrayList<admindsmhbean> getDSDaXN() throws Exception
	{
		return dsmhdao.getDSDaXN();
	}
	
	public ArrayList<admindsmhbean> getDSChuaXN() throws Exception
	{
		return dsmhdao.getDSChuaXN();
	}
	
	public ArrayList<admindsmhbean> getListWithPage(int page, int pageSize, int tt) throws Exception {
		return dsmhdao.getListWithPage(page, pageSize, tt);
	}
	
	public int getSoLuong(int tt) throws Exception{
		return dsmhdao.getSoLuong(tt);
	}
}
