package com.admin_auth.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.admin.model.AdminVO;
import com.auth_feature.model.AuthFeatureVO;

import hibernate.util.HibernateUtil;

public class AdminAuthDAO  implements AdminAuthDAO_interface{

	@Override
	public void insert(AdminAuthVO asauVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(asauVO);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		
	}

	@Override
	public void delete(String adm_no, String auth_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("delete from AdminAuthVO where adm_no=? and auth_no=?");
			query.setParameter(0, adm_no);
			query.setParameter(1, auth_no);
			int deletecount = query.executeUpdate();
			System.out.println("§R°£¤ñ¼Æ"+deletecount);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		
	}

	@Override
	public AdminAuthVO findByPrimaryKey(String adm_no, String auth_no) {
		AdminAuthVO adminauth = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from AdminAuthVO where adm_no=? and auth_no=?");
			query.setParameter(0, adm_no);
			query.setParameter(1,auth_no);
			List<AdminAuthVO> list = query.list();
			adminauth = list.get(0);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		return adminauth;
	}

	@Override
	public List<AdminAuthVO> getAll() {
		List<AdminAuthVO> list = new ArrayList<AdminAuthVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from AdminAuthVO order by adm_no desc");
			list = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		
		return list;
	}
	
	public static void main(String [] args){
//		AdminAuthDAO_hibernate dao = new AdminAuthDAO_hibernate();
		
		//insert
//		AdminAuthVO auth = new AdminAuthVO();
//		AdminVO admin = new AdminVO();
//		admin.setAdm_no("A009");
//		auth.setAdmin(admin);
//		AuthFeatureVO fea = new AuthFeatureVO();
//		fea.setAuth_no("AF002");
//		auth.setAuth(fea);
//		dao.insert(auth);
		
		//delete
//		dao.delete("A009", "AF002");
		
		//get one
//		AdminAuthVO vo = dao.findByPrimaryKey("A009", "AF005");
//		System.out.println(vo.getAdmin().getAdm_name());
//		System.out.println(vo.getAuth().getAuth_no());
		
		//get all
//		List<AdminAuthVO> list = dao.getAll();
//		for(AdminAuthVO vo : list){
//			System.out.println(vo.getAdmin().getAdm_no());
//			System.out.println(vo.getAuth().getAuth_no());
//			System.out.println("------------------------------");
//		}
	}

}
