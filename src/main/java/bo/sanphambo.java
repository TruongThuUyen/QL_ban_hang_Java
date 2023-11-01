package bo;

import java.util.ArrayList;
import bean.sanphambean;
import dao.sanphamdao;

public class sanphambo {
	sanphamdao spdao = new sanphamdao();
	ArrayList<sanphambean> temp = new ArrayList<sanphambean>();
	
	public String getMaSP() throws Exception{
		return spdao.getMaSP();
	}
	
	public ArrayList<sanphambean> getSP() throws Exception
	{
		return spdao.getSanPham();
	}
	
	public long SetSL(String masp, long sl) throws Exception
	{
		long tam = 0;
		for(sanphambean s: spdao.getSanPham())
		{
			if(s.getMasp().equals(masp))
			{
				s.setSl(sl - 1);
				System.out.print("sluong" + s.getSl() + "\n");
				tam = s.getSl();
				return tam;
			}
		}
		return 0;
	}
	
	//Kiem tra ma san pham 
//	public String TimMSP(ArrayList<sanphambean> ds, String ma) throws Exception
//	{
//		for(sanphambean s: ds)
//		{
//			if(s.getMasp().equals(ma))
//				return s.getMasp();
//		}
//		return null;
//	}
//	
//	//Get so luong tu ds tam 
//	public long GetSLdsTam(ArrayList<sanphambean> ds, String ma) throws Exception
//	{
//		for(sanphambean s: ds)
//		{
//			if(s.getMasp().equals(ma))
//				return s.getSl();
//		}
//		return -1;
//	}
	

	
	public long GetSLBean(String masp) throws Exception
	{
		for(sanphambean s: spdao.getSanPham())
		{
			if(s.getMasp().equals(masp))
			{
				return s.getSl();
			}
		}
		return 0;
	}
	

	//Get soluong cua moi sp trong bang sanpham
	public long getSL(String masp) throws Exception
	{
		return spdao.getSL(masp);
	}
	
	//Update lai so luong khi ban hang
	public int UpdateSL(long sl, String masp) throws Exception
	{
		return spdao.UpdateSL(sl, masp);
	}
	
	//Tim thong tin cua sp tu ma sp -> Select
	public sanphambean Tim(String masp) throws Exception
	{
		ArrayList<sanphambean> temp = spdao.getSanPham();
		for(sanphambean sp: temp)
		{
			if(sp.getMasp().equals(masp))
			{
				return sp;
			}
		}
		return null;
	}
	
	//get list with page
	public ArrayList<sanphambean> getListWithPage(int page, int pageSize, String ktim) throws Exception {
		return spdao.getListWithPage(page, pageSize, ktim);
	}
	
	//get list by MaLoai
	public ArrayList<sanphambean> getListWithPageByML(int page, int pageSize, String ktim) throws Exception {
		return spdao.getListWithPageByML(page, pageSize, ktim);
	}
	
	//get soluong
	public int getSoLuong(String key) throws Exception{
		return spdao.getSoLuong( key);
	}
	
	
	// Tim kiem theo: ma loai, masp, tensp
	public ArrayList<sanphambean> TimKiem(String key) throws Exception {
		ArrayList<sanphambean> tam = spdao.getSanPham();
		ArrayList<sanphambean> dstk = new ArrayList<sanphambean>();
		for (sanphambean s : tam) {
			if (s.getMaloai().toLowerCase().trim().contains(key.toLowerCase().trim()) || 
				s.getMasp().toLowerCase().trim().contains(key.toLowerCase().trim()) ||
				s.getTensp().toLowerCase().trim().contains(key.toLowerCase().trim()) 
				)
				dstk.add(s);
		}
		return dstk;
	}
	
	public int ThemSP(String ma, String tensp, String anh, long gia, float gg, long soluong, String maloai) throws Exception
	{
		return spdao.ThemSP(ma, tensp, anh, gia, gg, soluong, maloai);
	}
	
	public int SuaSP(String ma, String tensp, String anh, long gia, float gg, long soluong, String maloai) throws Exception
	{
		return spdao.SuaSP(ma, tensp, anh, gia, gg, soluong, maloai);
	}
	
	public int Xoa(String msp) throws Exception
	{
		return spdao.Xoa(msp);
	}

	
	// Tim kiem theo ma loai
	public ArrayList<sanphambean> TimMa( ArrayList<sanphambean> ds,  String maloai) throws Exception {		
		for(sanphambean s: ds)
		{
			if(s.getMaloai().toLowerCase().trim().equals(maloai.toLowerCase().trim()))
				temp.add(s);
		}
		return temp;
	}

	// Tim kiem theo tensp 
	public ArrayList<sanphambean> Tim(ArrayList<sanphambean> ds, String key) throws Exception {		
		for(sanphambean s: ds)
		{
			if(s.getTensp().toLowerCase().trim().contains(key.toLowerCase().trim()))
				temp.add(s);
		}
		return temp;
	}
	

}
