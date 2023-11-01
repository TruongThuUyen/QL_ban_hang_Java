package bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bean.admindsmhbean;
import bean.hoadonbean;
import bean.lichsubean;
import dao.hoadondao;

public class hoadonbo {
	hoadondao hddao = new hoadondao();
	
	public long getMa(long makh) throws Exception
	{
		return hddao.getMaHD(makh);
	}
	
	//Lay hoadon theo ma so trang thai
	public ArrayList<hoadonbean> getHDByID(long ma, int tt) throws Exception
	{
		return hddao.getHDByID(ma, tt);
	}
	
	// get List with page HoaDon
	public ArrayList<hoadonbean> getListWithPageHD(int page, int pageSize, int tt) throws Exception {
		return hddao.getListWithPageHD(page, pageSize, tt);
	}
	
	// get List with page HoaDon dv moi khach hang
	public ArrayList<hoadonbean> getListWithPageHDKH(int page, int pageSize, int tt, long mkh) throws Exception {
		return hddao.getListWithPageHDKH(page, pageSize, tt, mkh);
	}
	
	public void ThemHD(long makh, long sl, float tt) throws Exception
	{
		hddao.ThemHD(makh, sl, tt);
	}
	
	//GET SO LUONG HOADON DA DUOC DUOC DUYET
	public int getSoLuongHD(int tt) throws Exception{
		return hddao.getSoLuongHD(tt);
	}
	
	// GET SO LUONG HOADON DA DUOC DUOC DUYET DOI VOI MOI KHACH HANG
	public int getSoLuongHDKH(int tt, long makh) throws Exception {
		return hddao.getSoLuongHDKH(tt, makh);
	}
	
	//Khi hoa don duoc duyet hoac dang duoc ship
	public int UpdateHD(long mahd, int status) throws Exception
	{
		return hddao.UpdateHD(mahd, status);
	}
	
	//Tim HD theo ngay
	public ArrayList<hoadonbean> TimHDTheoNgay(ArrayList<hoadonbean> dshd, String key, int tt) throws Exception
	{
		ArrayList<hoadonbean> temp1 = new ArrayList<hoadonbean>();
		java.text.DateFormat  dateFormat = null;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		for(hoadonbean hd: dshd)
		{
			String date = String.valueOf(dateFormat.format(hd.getNgaymua()));
			if( (date.contains(key)) && hd.isDamua() == tt)
				temp1.add(hd);
		}
		
		return temp1;
	}
	
	//Hoan thanh hoa don
	public int UpdateHDHT(long mahd, int status) throws Exception
	{
		return hddao.UpdateHDHT(mahd, status);
	}
	
	public int XoaHD() throws Exception
	{
		return hddao.XoaHD();
	}

}
