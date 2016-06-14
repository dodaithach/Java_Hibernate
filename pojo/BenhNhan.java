package pojo;

import java.util.HashSet;
import java.util.Set;

public class BenhNhan implements java.io.Serializable {
	private int m_mabn;
	private String m_hoten;
	private String m_gioitinh;
	private int m_namsinh;
	private String m_diachi;
	
	// Set
	private Set<PhieuKhamBenh> m_pkb = new HashSet<PhieuKhamBenh>(0);
	
	public BenhNhan() {}
	
	public BenhNhan(String hoten, String gioitinh, int namsinh, String diachi) {
		m_hoten = hoten;
		m_gioitinh = gioitinh;
		m_namsinh = namsinh;
		m_diachi = diachi;
	}
	
	public BenhNhan(int mabn, String hoten, String gioitinh, int namsinh, String diachi) {
		m_mabn = mabn;
		m_hoten = hoten;
		m_gioitinh = gioitinh;
		m_namsinh = namsinh;
		m_diachi = diachi;
	}
	
	public BenhNhan(int mabn, String hoten, String gioitinh, int namsinh, String diachi
					, Set<PhieuKhamBenh> pkb) {
		m_mabn = mabn;
		m_hoten = hoten;
		m_gioitinh = gioitinh;
		m_namsinh = namsinh;
		m_diachi = diachi;
		m_pkb = pkb;
	}
	
	public int getMaBN() {
		return m_mabn;
	}
	public void setMaBN(int mabn) {
		m_mabn = mabn;
	}
	
	public String getHoTen() {
		return m_hoten;
	}
	public void setHoTen(String hoten) {
		m_hoten = hoten;
	}
	
	public String getGioiTinh() {
		return m_gioitinh;
	}
	public void setGioiTinh(String gioitinh) {
		m_gioitinh = gioitinh;
	}
	
	public int getNamSinh() {
		return m_namsinh;
	}
	public void setNamSinh(int namsinh) {
		m_namsinh = namsinh;
	}
	
	public String getDiaChi() {
		return m_diachi;
	}
	public void setDiaChi(String diachi) {
		m_diachi = diachi;
	}
	
	public Set<PhieuKhamBenh> getPKB() {
		return m_pkb;
	}
	public void setPKB(Set<PhieuKhamBenh> pkb) {
		m_pkb = pkb;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		BenhNhan bn = (BenhNhan) obj;
		
		boolean res = false;
		if (m_hoten.compareTo(bn.getHoTen()) == 0
				&& m_gioitinh.compareTo(bn.getGioiTinh()) == 0
				&& m_namsinh == bn.getNamSinh()
				&& m_diachi.compareTo(bn.getDiaChi()) == 0)
			res = true;
		
		return res;
	}
	
	
}
