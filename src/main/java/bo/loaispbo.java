package bo;

import java.util.ArrayList;

import bean.loaibean;
import dao.loaispdao;

public class loaispbo {
	loaispdao ldao = new loaispdao();
	ArrayList<loaibean> ds = new ArrayList<loaibean>();
	
	//Get all Loai
	public ArrayList<loaibean> getLoai() throws Exception
	{
		ds = ldao.getloai();
		return ds;
	}
	
	//Get list Loai by id
	public ArrayList<loaibean> getLoaiById(String ml) throws Exception{
		return ldao.getLoaiById(ml);
	}
	
	//Check: ma loai nay da co san pham nao chua
	public int Check(String ml) throws Exception{
		return ldao.Check(ml);
	}
	
	public ArrayList<loaibean> getListWithPage(int page, int pageSize, String ktim) throws Exception {
		ds = ldao.getListWithPage(page, pageSize, ktim);
		return ds;
	}
	
	//getsl
	public int getSoLuong(String key) throws Exception{
		return ldao.getSoLuong(key);
	}
	
	public int ThemLoai(String ml, String tl) throws Exception
	{
		for(loaibean loai:ds)
			if(loai.getMaloai().equals(ml))
				return 0;
			return ldao.ThemLoai(ml, tl);
	}
	
	
	public String Tim(String maloai) throws Exception
	{
		ArrayList<loaibean> temp = ldao.getloai();
		for(loaibean loai: temp)
		{
			if(loai.getMaloai().equals(maloai))
				return loai.getTenloai();
		}
		return null;
	}
	
	public int Sua(String mlmoi, String tl, String ml) throws Exception
	{
		return ldao.Sua(mlmoi, tl, ml);
	}
	
	public int Xoa(String ml) throws Exception
	{
		return ldao.Xoa(ml);
	}
}
