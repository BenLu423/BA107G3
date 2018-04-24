package com.giftTrack.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.gift.model.GiftVO;

import hibernate.util.HibernateUtil;

public class GiftTrackDAO implements GiftTrackDAO_interface{
	
	@Override
	public void insert(GiftTrackVO giftTrackVO) {
		/* * * * * * * * * * * * * * * * * * * * * * *
		 * 此新增追蹤會依以下順序進行，若有失敗則rollback		 * 
		 * ------------------------------------------* 
		 * 1. 新增追蹤明細[GIFT_TRACK]					 * 
		 * 2. 新增禮物追蹤數量[GIFT]						 * 
		 * * * * * * * * * * * * * * * * * * * * * * */		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();

			//1. 新增追蹤明細[GIFT_TRACK]
			Query query = session.createSQLQuery("insert into gift_track(mem_no,gift_no) values (?,?)");
			query.setParameter(0, giftTrackVO.getMemberVO().getMem_no());
			query.setParameter(1, giftTrackVO.getGiftVO().getGift_no());
			System.out.println("更新的筆數=" + query.executeUpdate());
			
			//2. 新增禮物追蹤數量[GIFT]
			GiftVO giftVO = (GiftVO) session.get(GiftVO.class, giftTrackVO.getGiftVO().getGift_no());
			Integer new_qty = giftVO.getGift_track_qty() + 1;
			giftVO.setGift_track_qty(new_qty);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured." + ex.getMessage());
		}
	}

	@Override
	public void delete(String mem_no, String gift_no) {
		/* * * * * * * * * * * * * * * * * * * * * * *
		 * 此刪除追蹤會依以下順序進行，若有失敗則rollback		 * 
		 * ------------------------------------------* 
		 * 1. 刪除追蹤明細[GIFT_TRACK]					 * 
		 * 2. 刪除禮物追蹤數量[GIFT]						 * 
		 * * * * * * * * * * * * * * * * * * * * * * */	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//1. 刪除追蹤明細[GIFT_TRACK]
			Query query = session.createQuery("delete GiftTrackVO where mem_no=? and gift_no=?");
			query.setParameter(0, mem_no);
			query.setParameter(1, gift_no);
			System.out.println("刪除的筆數=" + query.executeUpdate());
			
			//2. 刪除禮物追蹤數量[GIFT]
			GiftVO giftVO = (GiftVO) session.get(GiftVO.class, gift_no);
			Integer new_qty = giftVO.getGift_track_qty() - 1;
			giftVO.setGift_track_qty(new_qty);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured." + ex.getMessage());
		}
	}

	@Override
	public GiftTrackVO getByPrimaryKey(String mem_no, String gift_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List list = null;
		try {
			session.beginTransaction();
//GiftTrackVO = (GiftTrackVO) session.get(GiftTrackVO.class, new String(mem_no,gift_no));
			Query query = session.createQuery("from GiftTrackVO where mem_no=? and gift_no=?");
			query.setParameter(0, mem_no);
			query.setParameter(1, gift_no);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
		if(list!=null)
			return (GiftTrackVO) list.get(0);
		else
			return null;
	}

	@Override
	public List<GiftTrackVO> getAll() {
		List<GiftTrackVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();		
		try {
			session.beginTransaction();
			Query query = session.createQuery("from GiftTrackVO order by giftt_time desc");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
		return list;
	}

	@Override
	public List<String> getGiftListByMemNo(String mem_no) {
		List<GiftTrackVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();		
		try {
			session.beginTransaction();
			Query query = session.createQuery("from GiftTrackVO where mem_no=? order by giftt_time desc");
			query.setParameter(0, mem_no);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
		List<String> giftNoList = new ArrayList<String>();
		for(GiftTrackVO giftTrackVO : list){
			giftNoList.add(giftTrackVO.getGiftVO().getGift_no());
		}
		return giftNoList;
	}

	@Override
	public List<GiftTrackVO> getListByMemNo(String mem_no) {
		List<GiftTrackVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();		
		try {
			session.beginTransaction();
			Query query = session.createQuery("from GiftTrackVO where mem_no=? order by giftt_time desc");
			query.setParameter(0, mem_no);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
		return list;
	}

	@Override
	public List<GiftTrackVO> getListByGiftNo(String gift_no) {
		List<GiftTrackVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();		
		try {
			session.beginTransaction();
			Query query = session.createQuery("from GiftTrackVO where gift_no=? order by giftt_time desc");
			query.setParameter(0, gift_no);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
		return list;
	}
	
	public static void main(String[] args) {

		GiftTrackDAO dao = new GiftTrackDAO();

//		//● 新增
//		com.member.model.MemberVO memberVO = new com.member.model.MemberVO(); // 會員POJO
//		memberVO.setMem_no("M013");
//		com.gift.model.GiftVO giftVO = new com.gift.model.GiftVO(); // 禮物POJO
//		giftVO.setGift_no("G003");
//
//		GiftTrackVO giftTrackVO1 = new GiftTrackVO();
//		giftTrackVO1.setMemberVO(memberVO);
//		giftTrackVO1.setGiftVO(giftVO);
//		dao.insert(giftTrackVO1);

//		//● 刪除(小心cascade - 多方emp2.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除)
//		dao.delete("M002", "G001");

		
		//● 查詢-findByPrimaryKey (多方giftTtrack.hbm.xml必須設為lazy="false")(優!)
		GiftTrackVO giftTrackVO3 = dao.getByPrimaryKey("M001", "G002");
		System.out.print(giftTrackVO3.getMemberVO().getMem_no() + ",");
		System.out.print(giftTrackVO3.getGiftVO().getGift_no() + ",");
		System.out.print(giftTrackVO3.getGiftt_time() + ",");
		System.out.println();


//		//● 查詢-getAll (多方giftTtrack.hbm.xml必須設為lazy="false")(優!)
//		List<GiftTrackVO> list = dao.getAll();
//		for (GiftTrackVO atrack : list) {
//			System.out.print(atrack.getMemberVO().getMem_no() + ",");
//			System.out.print(atrack.getGiftVO().getGift_no() + ",");
//			System.out.print(atrack.getGiftt_time() + ",");
//			System.out.println();
//		}
		
//		//● 查詢-getListByMemNo (多方giftTtrack.hbm.xml必須設為lazy="false")(優!)
//		List<GiftTrackVO> list = dao.getListByMemNo("M001");
//		for (GiftTrackVO atrack : list) {
//			System.out.print(atrack.getMemberVO().getMem_no() + ",");
//			System.out.print(atrack.getGiftVO().getGift_no() + ",");
//			System.out.print(atrack.getGiftt_time() + ",");
//			System.out.println();
//		}
		
//		//● 查詢-getListByGiftNo (多方giftTtrack.hbm.xml必須設為lazy="false")(優!)
//		List<GiftTrackVO> list = dao.getListByGiftNo("G001");
//		for (GiftTrackVO atrack : list) {
//			System.out.print(atrack.getMemberVO().getMem_no() + ",");
//			System.out.print(atrack.getGiftVO().getGift_no() + ",");
//			System.out.print(atrack.getGiftt_time() + ",");
//			System.out.println();
//		}
	}

}
