package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import error.ErrorLog;
import pojo.BenhNhan;
import util.HibernateUtil;

public class BenhNhanDAO {
	public static List<BenhNhan> layDSBenhNhan() {
		List<BenhNhan> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "select bn from BenhNhan bn";
			Query query = session.createQuery(hql);
			ds = query.list();
			
			ErrorLog.log("Lay danh sach BenhNhan mac dinh");
		}
		catch (Exception ex) {
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return ds;
	}
	
	public static List<BenhNhan> layDSBenhNhan(String hoten) {
		List<BenhNhan> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "select bn from BenhNhan bn where bn.m_hoten like :hoten";
			Query query = session.createQuery(hql);
			query.setString("hoten", "%" + hoten + "%");
			ds = query.list();
			
			ErrorLog.log("Lay danh sach BenhNhan theo HoTen");
		}
		catch (Exception ex) {
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return ds;
	}
	
	public static boolean themBenhNhan(BenhNhan bn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean res = true;
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(bn);
			transaction.commit();
			
			ErrorLog.log("Them benh nhan");
		}
		catch (Exception ex) {
			transaction.rollback();
			res = false;
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return res;
	}
	
	public static boolean capNhatBenhNhan(BenhNhan bn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean res = true;
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(bn);
			transaction.commit();
			
			ErrorLog.log("Cap nhat benh nhan");
		}
		catch (Exception ex) {
			transaction.rollback();
			res = false;
			ErrorLog.log(ex.getMessage());
		}
		finally {
			session.close();
		}
		
		return res;
	}
}
