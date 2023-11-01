package bean;

import java.util.Date;

public class lichsubean {
	private long mahd;
	private String hoten;
	private long slm;
	private int status;
	private Date ngaymua;
	private float tongtien;
	public lichsubean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public lichsubean(long mahd, long slm , float tongtien, Date ngaymua, int status) {
		super();
		this.mahd = mahd;
		this.slm = slm;
		this.tongtien = tongtien;
		this.ngaymua = ngaymua;
		this.status = status;
	}
	public long getMahd() {
		return mahd;
	}
	public void setMahd(long mahd) {
		this.mahd = mahd;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public long getSlm() {
		return slm;
	}
	public void setSlm(long slm) {
		this.slm = slm;
	}
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
//	public boolean isStatus() {
//		return status;
//	}
//	public void setStatus(boolean status) {
//		this.status = status;
//	}
	public Date getNgaymua() {
		return ngaymua;
	}
	public void setNgaymua(Date ngaymua) {
		this.ngaymua = ngaymua;
	}
	public float getTongtien() {
		return tongtien;
	}
	public void setTongtien(float tongtien) {
		this.tongtien = tongtien;
	}
	
	
}
