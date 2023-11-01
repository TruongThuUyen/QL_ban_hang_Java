package bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bean.lichsubean;
import dao.lichsudao;


public class lichsubo {
	lichsudao lsdao = new lichsudao();
	ArrayList<lichsubean> temp1 = new ArrayList<lichsubean>();
	ArrayList<lichsubean> temp2 = new ArrayList<lichsubean>();
	ArrayList<lichsubean> temp3 = new ArrayList<lichsubean>();
	
	public ArrayList<lichsubean> getHDDTT(long makh) throws Exception
	{
		return lsdao.getHDDTT(makh);
	}
	
	public ArrayList<lichsubean> getHDCTT(long makh) throws Exception
	{
		return lsdao.getHDCTT(makh);
	}
	
	
	//hoa don da hoan thanh
	public ArrayList<lichsubean> getHDDaHT(long mkh) throws Exception
	{
		return lsdao.getHDDaHT(mkh);
	}
	
	//K dung
	public ArrayList<lichsubean> TimHDChuaTT(String key, long makh) throws Exception
	{
		java.text.DateFormat  dateFormat = null;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		for(lichsubean hd: lsdao.getHDCTT(makh))
		{
			String date = String.valueOf(dateFormat.format(hd.getNgaymua()));
			if( (date.contains(key)))
				temp1.add(hd);
		}
		
		return temp1;
	}
	
	//Khong dung
	public ArrayList<lichsubean> TimHDDaTT(String key, long makh) throws Exception
	{
		java.text.DateFormat  dateFormat = null;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		for(lichsubean hd: lsdao.getHDDTT(makh))
		{
			String date = String.valueOf(dateFormat.format(hd.getNgaymua()));
			if( (date.contains(key)))
				temp2.add(hd);
		}
		
		return temp2;
	}
	
	public ArrayList<lichsubean> TimHDDaHT(String key, long makh) throws Exception
	{
		java.text.DateFormat  dateFormat = null;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		for(lichsubean hd: lsdao.getHDDaHT(makh))
		{
			String date = String.valueOf(dateFormat.format(hd.getNgaymua()));
			if( (date.contains(key)))
				temp3.add(hd);
		}
		
		return temp2;
	}

}
