package com.admin.model;

import java.util.*;

import org.hibernate.*;

import hibernate.util.*;

import com.admin_auth.model.AdminAuthVO;
import com.auth_feature.model.AuthFeatureVO;

public class AdminDAO implements AdminDAO_interface{
	
	@Override
	public void insert(AdminVO adminVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(adminVO);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		
	}

	@Override
	public void update(AdminVO adminVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(adminVO);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		
	}

	@Override
	public void delete(String adminNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			AdminVO adminVO = (AdminVO)session.get(AdminVO.class, adminNo);
			session.delete(adminVO);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		
	}

	@Override
	public AdminVO findByPrimaryKey(String admin_no) {
		AdminVO adminVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			adminVO = (AdminVO)session.get(AdminVO.class, admin_no);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		return adminVO;
	}

	@Override
	public List<AdminVO> getAll() {
		List<AdminVO> list = new ArrayList<AdminVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from AdminVO order by adm_no desc");
			list = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		
		return list;
	}

	@Override
	public List<AdminAuthVO> getAdminAuths(String adm_no) {
		List<AdminAuthVO>list = new ArrayList<AdminAuthVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from AdminAuthVO where adm_no=?");
			query.setParameter(0, adm_no);
			list = query.list();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		return list;
	}
	
	

	@Override
	public AdminVO findByAcct(String admin_acct) {
		AdminVO adminVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from AdminVO where adm_acct=?");
			query.setParameter(0, admin_acct);
			List<AdminVO>list = query.list();
			for(AdminVO admin : list){
				adminVO = new AdminVO();
				adminVO.setAdm_acct(admin.getAdm_acct());
				adminVO.setAdm_no(admin.getAdm_no());
				adminVO.setAdm_name(admin.getAdm_name());
				adminVO.setAdm_pwd(admin.getAdm_pwd());
				adminVO.setAdm_mail(admin.getAdm_mail());
			}
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		return adminVO;
	}
	
	
	public static void main(String[]args){
		
//		AdminDAO dao = new AdminDAO();
		
		//新增
//		AdminVO admin = new AdminVO();
//		admin.setAdm_name("test");
//		admin.setAdm_acct("two");
//		admin.setAdm_pwd("two");
//		admin.setAdm_mail("jwes4421@gmail.com");
//		dao.insert(admin);
		
		//修改
//		AdminVO admin = new AdminVO();
//		admin.setAdm_no("A011");
//		admin.setAdm_name("test_update");
//		admin.setAdm_acct("two");
//		admin.setAdm_pwd("two");
//		admin.setAdm_mail("jwes4421@gmail.com");
//		dao.update(admin);
		
		//刪除
//		dao.delete("A011");
		
		//get one
//		AdminVO admin = dao.findByPrimaryKey("A009");
//		System.out.println(admin.getAdm_name());
		
		//get all
//		List<AdminVO> list = dao.getAll();
//		for(AdminVO admins : list){
//			System.out.println(admins.getAdm_name());
//			System.out.println("-----------------");
//		}
		
		//get by acct
//		AdminVO admin = dao.findByAcct("AAA");
//		System.out.println(admin.getAdm_name());
		
		//get admin auths
//		List<AdminAuthVO>list = dao.getAdminAuths("A002");
//		for(AdminAuthVO auth : list){
//			System.out.println(auth.getAuth().getAuth_no());
//			System.out.println("--------------");
//		}
	}

}
