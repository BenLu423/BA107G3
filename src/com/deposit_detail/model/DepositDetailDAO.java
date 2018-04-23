package com.deposit_detail.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class DepositDetailDAO implements DepositDetail_interface {

	private static final String INSERT_DEPOSITDETAIL = "INSERT INTO DEPOSIT_DETAIL(DEPOD_NO, MEM_NO, DEPO_NO) VALUES ('DD'||LPAD(to_char(DEPOSIT_DETAIL_SEQ.NEXTVAL),3,'0'),?,?)";

	@Override
	public void insert(DepositDetailVO depositDetailVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery(INSERT_DEPOSITDETAIL);
			query.setParameter(0, depositDetailVO.getMemberVO().getMem_no());
			query.setParameter(1, depositDetailVO.getDepositVO().getDepo_no());
			System.out.println("新增的筆數=" + query.executeUpdate());
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
	}

	@Override
	public DepositDetailVO getByPrimaryKey(String depod_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from DepositDetailVO where depod_no=? order by depod_no desc");
			query.setParameter(0, depod_no);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException se) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return (DepositDetailVO) list.get(0);
	}

	@Override
	public List<DepositDetailVO> getAll() {
		List<DepositDetailVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from DepositDetailVO order by depod_no desc");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
		return list;
	}

	public static void main(String[] args) {

		DepositDetailDAO dao = new DepositDetailDAO();

//		 //● 新增
//		 com.member.model.MemberVO memberVO = new com.member.model.MemberVO();
//		 // 會員POJO
//		 memberVO.setMem_no("M012");
//		 com.deposit.model.DepositVO depositVO = new
//		 com.deposit.model.DepositVO(); // 禮物POJO
//		 depositVO.setDepo_no("D001");
//		 DepositDetailVO depositDetailVO1 = new DepositDetailVO();
//		 depositDetailVO1.setMemberVO(memberVO);
//		 depositDetailVO1.setDepositVO(depositVO);
//		 dao.insert(depositDetailVO1);

		// //● 查詢-findByPrimaryKey (多方giftTtrack.hbm.xml必須設為lazy="false")(優!)
		// DepositDetailVO depositDetailVO3 = dao.getByPrimaryKey("DD013");
		// System.out.print(depositDetailVO3.getDepod_no() + ",");
		// System.out.println(depositDetailVO3.getDepod_time() + ",");
		// MemberVO memberVO3 = depositDetailVO3.getMemberVO();
		// System.out.print(memberVO3.getMem_no() + ",");
		// System.out.println(memberVO3.getMem_name() + ",");
		// DepositVO depositVO3 = depositDetailVO3.getDepositVO();
		// System.out.print(depositVO3.getDepo_no() + ",");
		// System.out.print(depositVO3.getDepo_name() + ",");
		// System.out.print(depositVO3.getDepo_value() + ",");
		// System.out.print(depositVO3.getDepo_percent() + ",");
		// System.out.println();

		// //● 查詢-getAll (多方giftTtrack.hbm.xml必須設為lazy="false")(優!)
		// List<DepositDetailVO> list = dao.getAll();
		// for (DepositDetailVO vo : list) {
		// System.out.print(vo.getDepod_no() + ",");
		// System.out.println(vo.getDepod_time() + ",");
		// MemberVO memberVO = vo.getMemberVO();
		// System.out.print(memberVO.getMem_no() + ",");
		// System.out.println(memberVO.getMem_name() + ",");
		// DepositVO depositVO = vo.getDepositVO();
		// System.out.print(depositVO.getDepo_no() + ",");
		// System.out.print(depositVO.getDepo_name() + ",");
		// System.out.print(depositVO.getDepo_value() + ",");
		// System.out.print(depositVO.getDepo_percent() + ",");
		// System.out.println();
		// }

	}

}
