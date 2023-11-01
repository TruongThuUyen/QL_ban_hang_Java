package bean;

public class GioHangbean {
	private String masp;
	private String tensp;
	private String anh;
	private long gia;
	private float gg;
	private long sl;
	private float thanhtien;
	public GioHangbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GioHangbean(String masp, String tensp, String anh, long gia, float gg, long sl) {
		super();
		this.masp = masp;
		this.tensp = tensp;
		this.anh = anh;
		this.gia = gia;
		this.gg = gg;
		this.sl = sl;
		this.thanhtien = sl*gia - sl*gia*gg;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public long getGia() {
		return gia;
	}
	public void setGia(long gia) {
		this.gia = gia;
	}
	public float getGg() {
		return gg;
	}
	public void setGg(float gg) {
		this.gg = gg;
	}
	public long getSl() {
		return sl;
	}
	public void setSl(long sl) {
		this.sl = sl;
	}
	
	public float getThanhtien() {
		return sl*gia - sl*gia*gg ;
	}
	public void setThanhtien(float thanhtien) {
		this.thanhtien = thanhtien;
	}
	
	
}
