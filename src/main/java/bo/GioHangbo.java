package bo;

import java.util.ArrayList;

import bean.GioHangbean;

public class GioHangbo {
	public ArrayList<GioHangbean> ds = new ArrayList<GioHangbean>();
	
	public void Them(String masp, String tensp, String anh, long gia, float gg, long sl) throws Exception
	{
		for(GioHangbean h:ds)
			if(h.getMasp().equals(masp))
			{
				h.setSl(h.getSl() + sl);
				return;
			}
		GioHangbean h = new GioHangbean(masp, tensp, anh, gia, gg, sl);
		ds.add(h);
	}
	
	public void Xoa (String masp)  throws Exception
	{
		for(GioHangbean h:ds)
			if(h.getMasp().equals(masp))
			{
				ds.remove(h);
				return;
			}
	}
	
	public void Sua(String masp, long slmoi) throws Exception
	{
		int n = ds.size();
		for(int i = 0; i < n; i++)
		{
			if(ds.get(i).getMasp().toLowerCase().trim().equals(masp.toLowerCase().trim()))
			{
				ds.get(i).setSl(slmoi);
				return;
			}
		}
	}
	
	public long TongSP() {
		long tong = 0;
		for(GioHangbean gio: ds) {
			tong += gio.getSl();
		}
		return tong;
	}
	
	public float TongTien() {
		int n = ds.size();
		float s = 0;
		for(GioHangbean h:ds)
			s+= h.getThanhtien();
		return s;
	}
	

}
