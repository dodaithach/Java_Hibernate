package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import error.ErrorLog;
import pojo.DonThuoc;
import util.HibernateUtil;

public class DonThuocDAO {
	public static List<DonThuoc> layDSThuoc() {
		List<DonThuoc> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "select dt from DonThuoc dt";
			Query query = session.createQuery(hql);
			ds = query.list();
			
			ErrorLog.log("Lay danh sach DonThuoc mac dinh");
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
	
	public static boolean themDonThuoc(DonThuoc dt) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean res = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(dt);
			transaction.commit();
			
			ErrorLog.log("Them DonThuoc");
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
