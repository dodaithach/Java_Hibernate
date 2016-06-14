package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import error.ErrorLog;
import pojo.BenhNhan;
import pojo.PhieuKhamBenh;
import util.HibernateUtil;

public class PhieuKhamBenhDAO {
	public static List<PhieuKhamBenh> layDSPKB() {
		List<PhieuKhamBenh> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "select p from PhieuKhamBenh p";
			Query query = session.createQuery(hql);
			ds = query.list();
			
			ErrorLog.log("Lay danh sach PhieuKhamBenh mac dinh");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return ds;
	}
	
	public static List<PhieuKhamBenh> layDSPKB(Date ngay) {
		List<PhieuKhamBenh> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "select p from PhieuKhamBenh p where p.m_ngaykham = :ngay";
			Query query = session.createQuery(hql);
			query.setDate("ngay", ngay);
			ds = query.list();
			
			ErrorLog.log("Lay danh sach PhieuKhamBenh theo NgayKham");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return ds;
	}
	
	public static List<PhieuKhamBenh> layDSPKBTheoTrieuChung(String trieuchung) {
		List<PhieuKhamBenh> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "select p from PhieuKhamBenh p where p.m_trieuchung like :trieuchung";
			Query query = session.createQuery(hql);
			query.setString("trieuchung", "%" + trieuchung + "%");
			ds = query.list();
			ErrorLog.log("Lay danh sach PhieuKhamBenh theo TrieuChung");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return ds;
	}
	
	public static List<PhieuKhamBenh> layDSPKBTheoBenh(String benh) {
		List<PhieuKhamBenh> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "select p from PhieuKhamBenh p where p.m_tenbenh like :tenbenh";
			Query query = session.createQuery(hql);
			query.setString("tenbenh", "%" + benh + "%");
			ds = query.list();
			ErrorLog.log("Lay danh sach PhieuKhamBenh theo TenBenh");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return ds;
	}
	
	public static boolean themPhieuKhamBenh(PhieuKhamBenh pkb) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean res = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(pkb);
			transaction.commit();
			ErrorLog.log("Them PhieuKhamBenh");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			res = false;
			transaction.rollback();
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return res;
	}
	
	public static boolean capNhatPhieuKhamBenh(PhieuKhamBenh pkb) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean res = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(pkb);
			transaction.commit();
			
			ErrorLog.log("Cap nhat PhieuKhamBenh");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			res = false;
			transaction.rollback();
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return res;
	}
	
	public static boolean xoaPhieuKhamBenh(PhieuKhamBenh pkb) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean res = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.delete(pkb);
			transaction.commit();
			
			ErrorLog.log("Xoa PhieuKhamBenh");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			res = false;
			transaction.rollback();
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return res;
	}
}
