package bean;

import java.util.Date;

public class ctlichsubean {
	private long mahd;
	private String tenkh;
	private String masp;
	private String tensp;
	private String anh;
	private long gia;
	private float gg;
	private long slm;
	private float thanhtien;
	private Date ngaylap;
	private Date ngayvc;
	private Date ngayht;
	private int status;
	public ctlichsubean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ctlichsubean(long mahd, String tenkh, String masp, String tensp, String anh, long gia, float gg, long slm,
			float thanhtien, Date ngaylap, Date ngayvc, Date ngayht, int status) {
		super();
		this.mahd = mahd;
		this.tenkh = tenkh;
		this.masp = masp;
		this.tensp = tensp;
		this.anh = anh;
		this.gia = gia;
		this.gg = gg;
		this.slm = slm;
		this.thanhtien = thanhtien;
		this.ngaylap = ngaylap;
		this.ngayvc = ngayvc;
		this.ngayht = ngayht;
		this.status = status;
	}
	public long getMahd() {
		return mahd;
	}
	public void setMahd(long mahd) {
		this.mahd = mahd;
	}
	public String getTenkh() {
		return tenkh;
	}
	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
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
	public long getSlm() {
		return slm;
	}
	public void setSlm(long slm) {
		this.slm = slm;
	}
	public float getThanhtien() {
		return thanhtien;
	}
	public void setThanhtien(float thanhtien) {
		this.thanhtien = thanhtien;
	}
	public Date getNgaylap() {
		return ngaylap;
	}
	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}
	public Date getNgayvc() {
		return ngayvc;
	}
	public void setNgayvc(Date ngayvc) {
		this.ngayvc = ngayvc;
	}
	public Date getNgayht() {
		return ngayht;
	}
	public void setNgayht(Date ngayht) {
		this.ngayht = ngayht;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
