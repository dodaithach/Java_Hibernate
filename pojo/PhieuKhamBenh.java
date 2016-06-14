package pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PhieuKhamBenh implements java.io.Serializable {
	private int m_maphieu;
	private Date m_ngaykham;
	private String m_trieuchung;
	private String m_tenbenh;
	
	// Set
	private Set<DonThuoc> m_donthuoc = new HashSet<DonThuoc>(0);
	private BenhNhan m_benhnhan;
	
	public PhieuKhamBenh() {}
	
	public PhieuKhamBenh(Date ngaykham, String trieuchung, String tenbenh) {
		m_ngaykham = ngaykham;
		m_trieuchung = trieuchung;
		m_tenbenh = tenbenh;
	}
	
	public PhieuKhamBenh(int maphieu, Date ngaykham, String trieuchung, String tenbenh) {
		m_maphieu = maphieu;
		m_ngaykham = ngaykham;
		m_trieuchung = trieuchung;
		m_tenbenh = tenbenh;
	}
	
	public PhieuKhamBenh(int maphieu, Date ngaykham, String trieuchung, String tenbenh
							, Set<DonThuoc> donthuoc) {
		m_maphieu = maphieu;
		m_ngaykham = ngaykham;
		m_trieuchung = trieuchung;
		m_tenbenh = tenbenh;
		m_donthuoc = donthuoc;
	}
	
	public PhieuKhamBenh(int maphieu, Date ngaykham, String trieuchung, String tenbenh
							, Set<DonThuoc> donthuoc, BenhNhan benhnhan) {
		m_maphieu = maphieu;
		m_ngaykham = ngaykham;
		m_trieuchung = trieuchung;
		m_tenbenh = tenbenh;
		m_donthuoc = donthuoc;
		m_benhnhan = benhnhan;
	}
	
	public int getMaPKB() {
		return m_maphieu;
	}
	public void setMaPKB(int maphieu) {
		m_maphieu = maphieu;
	}
	
	public Date getNgayKham() {
		return m_ngaykham;
	}
	public void setNgayKham(Date ngaykham) {
		m_ngaykham = ngaykham;
	}
	
	public String getTrieuChung() {
		return m_trieuchung;
	}
	public void setTrieuChung(String trieuchung) {
		m_trieuchung = trieuchung;
	}
	
	public String getTenBenh() {
		return m_tenbenh;
	}
	public void setTenBenh(String tenbenh) {
		m_tenbenh = tenbenh;
	}
	
	public Set<DonThuoc> getDonThuoc() {
		return m_donthuoc;
	}
	public void setDonThuoc(Set<DonThuoc> donthuoc) {
		m_donthuoc = donthuoc;
	}
	
	public BenhNhan getBenhNhan() {
		return m_benhnhan;
	}
	public void setBenhNhan(BenhNhan benhnhan) {
		m_benhnhan = benhnhan;
	}
}
