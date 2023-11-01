package bean;

import java.util.Date;

public class admindsmhbean {
	private long mahd;
	private String tenkh;
	private String tensp;
	private long gia;
	private long slm;
	private float ck;
	private int status;
	private float tongtien;
	private Date ngaymua;
	public admindsmhbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public admindsmhbean(long mahd, String tenkh, String tensp, long gia, long slm, float ck, int status,
			float tongtien, Date ngaymua) {
		super();
		this.mahd = mahd;
		this.tenkh = tenkh;
		this.tensp = tensp;
		this.gia = gia;
		this.slm = slm;
		this.ck = ck;
		this.status = status;
		this.tongtien = tongtien;
		this.ngaymua = ngaymua;
	}
	public long getMahd() {
		return mahd;
	}
	public void setMahd(long makh) {
		this.mahd = makh;
	}
	public String getTenkh() {
		return tenkh;
	}
	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public long getGia() {
		return gia;
	}
	public void setGia(long gia) {
		this.gia = gia;
	}
	public long getSlm() {
		return slm;
	}
	public void setSlm(long slm) {
		this.slm = slm;
	}
	public float getCk() {
		return ck;
	}
	public void setCk(float ck) {
		this.ck = ck;
	}
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getTongtien() {
		return tongtien;
	}
	public void setTongtien(float tongtien) {
		this.tongtien = tongtien;
	}
	public Date getNgaymua() {
		return ngaymua;
	}
	public void setNgaymua(Date ngaymua) {
		this.ngaymua = ngaymua;
	}
	
	
}
