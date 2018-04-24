package android.com.friends_list.model;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;

public class FriendsListDAO implements FriendsListDAO_interface {

	private static final String INSERT_FRIENDS = "INSERT INTO FRIENDS_LIST (MEM_NO_SELF,MEM_NO_OTHER)VALUES(?,?)";
	private static final String ADD_FRIENDS = "UPDATE FRIENDS_LIST SET FRILIST_MODIFY='是',FRILIST_NOTICE='已通知' WHERE (MEM_NO_SELF=? AND MEM_NO_OTHER=?) OR (MEM_NO_SELF=? AND MEM_NO_OTHER=?)";

	@Override
	public void insert(FriendsListVO frilistVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery(INSERT_FRIENDS);
			query.setParameter(0, frilistVO.getMemVO_self().getMem_no());
			query.setParameter(1, frilistVO.getMemVO_other().getMem_no());
			System.out.println("新增的筆數=" + query.executeUpdate());
			session.getTransaction().commit();

		} catch (RuntimeException se) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public void update(FriendsListVO frilistVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(frilistVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void add(FriendsListVO frilistVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery(ADD_FRIENDS);
			query.setParameter(0, frilistVO.getMemVO_self().getMem_no());
			query.setParameter(1, frilistVO.getMemVO_other().getMem_no());
			query.setParameter(2, frilistVO.getMemVO_other().getMem_no());
			query.setParameter(3, frilistVO.getMemVO_self().getMem_no());
			System.out.println("新增的筆數=" + query.executeUpdate());
			session.getTransaction().commit();

		} catch (RuntimeException se) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public void delete(String mem_no_self, String mem_no_other) {
		System.out.println(mem_no_self);
		System.out.println(mem_no_other);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Query query = session.createQuery("delete FriendsListVO where (mem_no_self=? and mem_no_other=?) or (mem_no_self=? and mem_no_other=?)");
			query.setParameter(0, mem_no_self);
			query.setParameter(1, mem_no_other);
			query.setParameter(2, mem_no_other);
			query.setParameter(3, mem_no_self);
			System.out.println("刪除的筆數=" + query.executeUpdate());

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured." + ex.getMessage());
		}
	}

	@Override
	public FriendsListVO findByPrimaryKey(String mem_no_self, String mem_no_other) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from FriendsListVO where mem_no_self=? and mem_no_other=?");
			query.setParameter(0, mem_no_self);
			query.setParameter(1, mem_no_other);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException se) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return (FriendsListVO) list.get(0);
	}
		

	@Override
	public List<FriendsListVO> getAll() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<FriendsListVO> list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from FriendsListVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
		return list;
	}

	@Override
	public List<FriendsListVO> getMemberFriends(String mem_no) {
		List<FriendsListVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from FriendsListVO where mem_no_self=? and frilist_modify not in('待審核')");
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
	public Boolean havewait(String mem_no_self, String mem_no_other) {
		System.out.println(mem_no_self);
		System.out.println(mem_no_other);
		List<FriendsListVO> list = null;
		boolean havewait = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from FriendsListVO where mem_no_self=? and mem_no_other=? and frilist_modify in('待審核')");
			query.setParameter(0, mem_no_self);
			query.setParameter(1, mem_no_other);
			list = query.list();
			if(list.size()!=0)
				havewait = true;
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + ex.getMessage());
		}
		return havewait;
	}
}
