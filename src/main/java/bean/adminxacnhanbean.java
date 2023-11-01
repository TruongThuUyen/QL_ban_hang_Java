package bean;

import java.util.Date;

public class adminxacnhanbean {
	private long macthd;
	private long mahd;
	private String hoten;
	private String tensp;
	private long gia;
	private float mucgiam;
	private long soluong; 
	private float thanhtien;
	private int trangthai;
	private Date ngaymua;
	public adminxacnhanbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public adminxacnhanbean(long macthd, long mahd, String hoten, String tensp, long gia, float mucgiam, long soluong,
			float thanhtien, int trangthai, Date ngaymua) {
		super();
		this.macthd = macthd;
		this.mahd = mahd;
		this.hoten = hoten;
		this.tensp = tensp;
		this.gia = gia;
		this.mucgiam = mucgiam;
		this.soluong = soluong;
		this.thanhtien = thanhtien;
		this.trangthai = trangthai;
		this.ngaymua = ngaymua;
	}
	public long getMacthd() {
		return macthd;
	}
	public void setMacthd(long macthd) {
		this.macthd = macthd;
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
	public float getMucgiam() {
		return mucgiam;
	}
	public void setMucgiam(float mucgiam) {
		this.mucgiam = mucgiam;
	}
	public long getSoluong() {
		return soluong;
	}
	public void setSoluong(long soluong) {
		this.soluong = soluong;
	}
	public float getThanhtien() {
		return thanhtien;
	}
	public void setThanhtien(float thanhtien) {
		this.thanhtien = thanhtien;
	}
	public int isTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	public Date getNgaymua() {
		return ngaymua;
	}
	public void setNgaymua(Date ngaymua) {
		this.ngaymua = ngaymua;
	}
	
	
	
}
