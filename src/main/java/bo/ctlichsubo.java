package bo;

import java.util.ArrayList;

import bean.ctlichsubean;
import dao.ctlichsudao;

public class ctlichsubo {
	ctlichsudao  ctdao = new ctlichsudao();
	ArrayList<ctlichsubean> ds;
	ArrayList<ctlichsubean> temp = new ArrayList<ctlichsubean>();
	
	public ArrayList<ctlichsubean> getHD(long mahd) throws Exception
	{
		ds = ctdao.getHD(mahd);
		return ds;
	} 
	
	public ArrayList<ctlichsubean> TimKiem(String tensp) throws Exception
	{
		for(ctlichsubean s: ds)
		{
			if(s.getTensp().toLowerCase().trim().contains(tensp.toLowerCase().trim()))
				temp.add(s);
		}
		return temp;
	}
}
