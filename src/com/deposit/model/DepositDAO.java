package com.deposit.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.giftTrack.model.GiftTrackVO;

import hibernate.util.HibernateUtil;

public class DepositDAO implements DepositDAO_interface {

	@Override
	public void insert(DepositVO depositVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(depositVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(DepositVO depositVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(depositVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<DepositVO> getAll() {
		List<DepositVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from DepositVO order by depo_no desc");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public DepositVO getByPrimaryKey(String depo_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from DepositVO where depo_no=?");
			query.setParameter(0, depo_no);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
		return (DepositVO) list.get(0);
	}

	public static void main(String[] args) {

		 DepositDAO dao = new DepositDAO();

//		 //● 新增-2(不需要cascade="save-update" 或 cascade="all"的設定)(這是經常要用到的一般新增)
//		 DepositVO depositVO = new DepositVO(); // 儲值POJO
//		 depositVO.setDepo_name("儲7號機");
//		 depositVO.setDepo_value(666);
//		 depositVO.setDepo_percent(0.55);
//		 dao.insert(depositVO);

//		 //● 修改-2(不需設定cascade="save-update" 或 cascade="all")(這是經常要用到的一般修改)
//		 DepositVO depositVO2 = dao.getByPrimaryKey("D001");
//		 depositVO2.setDepo_value(500);
//		 dao.update(depositVO2);

//		 //● 查詢-findByPrimaryKey (優秀!) (一方dept2.hbm.xml必須設為lazy="false")
//		 DepositVO depositVO3 = dao.getByPrimaryKey("D002");
//		 System.out.print(depositVO3.getDepo_no() + ",");
//		 System.out.print(depositVO3.getDepo_name() + ",");
//		 System.out.print(depositVO3.getDepo_value() + ",");
//		 System.out.print(depositVO3.getDepo_percent() + ",");

//		 //● 查詢-getAll-2 (優秀!!!) (一方dept2.hbm.xml必須設為lazy="false")
//		 List<DepositVO> list2 = dao.getAll();
//		 for (DepositVO depositVO : list2) {
//			 System.out.print(depositVO.getDepo_no() + ",");
//			 System.out.print(depositVO.getDepo_name() + ",");
//			 System.out.print(depositVO.getDepo_value() + ",");
//			 System.out.print(depositVO.getDepo_percent() + ",");
//			 System.out.println();
//		 }

	}
}
