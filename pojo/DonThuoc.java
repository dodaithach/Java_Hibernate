package pojo;

public class DonThuoc implements java.io.Serializable {
	private int m_madt;
	private int m_soluong;
	private String m_donvi;
	private String m_cachdung;
	private String m_loaithuoc;
	
	// Set
	private PhieuKhamBenh m_pkb;
	
	public DonThuoc() {}
	
	public DonThuoc(int madt, int soluong) {
		m_madt = madt;
		m_soluong = soluong;
	}
	
	public DonThuoc(int soluong, PhieuKhamBenh pkb) {
		m_soluong = soluong;
		m_pkb = pkb;
	}
	
	public DonThuoc(int madt, int soluong, PhieuKhamBenh pkb) {
		m_madt = madt;
		m_soluong = soluong;
		m_pkb = pkb;
	}
	
	public DonThuoc(int madt, int soluong, PhieuKhamBenh pkb, String donvi) {
		m_madt = madt;
		m_soluong = soluong;
		m_pkb = pkb;
		m_donvi = donvi;
	}
	
	public DonThuoc(int soluong, PhieuKhamBenh pkb, String donvi, String cachdung) {
		m_soluong = soluong;
		m_pkb = pkb;
		m_donvi = donvi;
		m_cachdung = cachdung;
	}
	public DonThuoc(int soluong, PhieuKhamBenh pkb, String donvi, String cachdung, String loaithuoc) {
		m_soluong = soluong;
		m_pkb = pkb;
		m_donvi = donvi;
		m_cachdung = cachdung;
		m_loaithuoc = loaithuoc;
	}
	
	public int getMaDT() {
		return m_madt;
	}
	public void setMaDT(int madt) {
		m_madt = madt;
	}
	
	public int getSoLuong() {
		return m_soluong;
	}
	public void setSoLuong(int soluong) {
		m_soluong = soluong;
	}
	
	public PhieuKhamBenh getPKB() {
		return m_pkb;
	}
	public void setPKB(PhieuKhamBenh pkb) {
		m_pkb = pkb;
	}
	
	public String getDonVi() {
		return m_donvi;
	}
	public void setDonVi(String donvi) {
		m_donvi = donvi;
	}
	
	public String getCachDung() {
		return m_cachdung;
	}
	public void setCachDung(String cachdung) {
		m_cachdung = cachdung;
	}
	
	public String getLoaiThuoc() {
		return m_loaithuoc;
	}
	public void setLoaiThuoc(String loaithuoc) {
		m_loaithuoc = loaithuoc;
	}
}
