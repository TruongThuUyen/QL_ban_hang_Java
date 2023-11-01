package bean;

import java.util.Date;

public class hoadonbean {
	private long mahd;
	private long makh;
	private Date ngaymua;
	private long sl;
	private int damua;
	private float tongtien;
	public hoadonbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public hoadonbean(long mahd, long makh, Date ngaymua, long sl, int damua, float tongtien) {
		super();
		this.mahd = mahd;
		this.makh = makh;
		this.ngaymua = ngaymua;
		this.sl = sl;
		this.damua = damua;
		this.tongtien = tongtien;
	}
	public long getMahd() {
		return mahd;
	}
	public void setMahd(long mahd) {
		this.mahd = mahd;
	}
	public long getMakh() {
		return makh;
	}
	public void setMakh(long makh) {
		this.makh = makh;
	}
	public Date getNgaymua() {
		return ngaymua;
	}
	public void setNgaymua(Date ngaymua) {
		this.ngaymua = ngaymua;
	}
	public long getSl() {
		return sl;
	}
	public void setSl(long sl) {
		this.sl = sl;
	}
	public int isDamua() {
		return damua;
	}
	public void setDamua(int damua) {
		this.damua = damua;
	}
	public float getTongtien() {
		return tongtien;
	}
	public void setTongtien(float tongtien) {
		this.tongtien = tongtien;
	}
	
	
}
