package bo;

import dao.chitiethddao;

public class chitiethdbo {
	chitiethddao cthddao = new chitiethddao();
	
	public void ThemCTHD(String masp, long soluong, long mahd, float gg, float tt) throws Exception
	{
		cthddao.ThemCTHD(masp, soluong, mahd, gg, tt);
	}
	
	public int UpdateCTHD(long mhd) throws Exception
	{
		return cthddao.UpdateCTHD(mhd);
	}

	//Update CTHD voi status khac 0 va 1
	public int UpdateCTHD(long mahd, int status) throws Exception
	{
		return cthddao.UpdateCTHD(mahd, status);
	}
	
	//Truy xuat mahd tu cthd
	public long GetMaHD(long macthd) throws Exception
	{
		return cthddao.GetMaHD(macthd);
	}
	
	//Dem so cthd da thanh toan co cung mahd
	public long DemCTHDDaTT(long mhd)throws Exception {
		return cthddao.DemCTHDDaTT(mhd);
	}
	
	//Dem so chitiethoadon co cung mahd 
	public long DemCTHD(long mhd)throws Exception {
		return cthddao.DemCTHD(mhd);
	}
	
	
	//Xoa chitethoadon tren 15 ngay
	public int XoaCTHD(long mahd) throws Exception
	{
		return cthddao.XoaCTHD(mahd);
	}
}
