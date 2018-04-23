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

//		 //�� �s�W-2(���ݭncascade="save-update" �� cascade="all"���]�w)(�o�O�g�`�n�Ψ쪺�@��s�W)
//		 DepositVO depositVO = new DepositVO(); // �x��POJO
//		 depositVO.setDepo_name("�x7����");
//		 depositVO.setDepo_value(666);
//		 depositVO.setDepo_percent(0.55);
//		 dao.insert(depositVO);

//		 //�� �ק�-2(���ݳ]�wcascade="save-update" �� cascade="all")(�o�O�g�`�n�Ψ쪺�@��ק�)
//		 DepositVO depositVO2 = dao.getByPrimaryKey("D001");
//		 depositVO2.setDepo_value(500);
//		 dao.update(depositVO2);

//		 //�� �d��-findByPrimaryKey (�u�q!) (�@��dept2.hbm.xml�����]��lazy="false")
//		 DepositVO depositVO3 = dao.getByPrimaryKey("D002");
//		 System.out.print(depositVO3.getDepo_no() + ",");
//		 System.out.print(depositVO3.getDepo_name() + ",");
//		 System.out.print(depositVO3.getDepo_value() + ",");
//		 System.out.print(depositVO3.getDepo_percent() + ",");

//		 //�� �d��-getAll-2 (�u�q!!!) (�@��dept2.hbm.xml�����]��lazy="false")
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
