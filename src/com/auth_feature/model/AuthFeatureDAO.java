package com.auth_feature.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.admin.model.AdminVO;

import hibernate.util.HibernateUtil;

public class AuthFeatureDAO implements AuthFeatureDAO_interface{

	@Override
	public void insert(AuthFeatureVO authVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AuthFeatureVO authVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String auth_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AuthFeatureVO findByPrimaryKey(String auth_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthFeatureVO> getAll() {
		List<AuthFeatureVO> list = new ArrayList<AuthFeatureVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from AuthFeatureVO order by auth_no desc");
			list = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException re){
			session.getTransaction().rollback();
			throw re;
		}
		
		return list;
	}

	@Override
	public Set<AdminVO> getAdminsByAuthno(String auth_no) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[]args){
//		AuthFeatureDAO_hibernate dao = new AuthFeatureDAO_hibernate();
//		List<AuthFeatureVO> list = dao.getAll();
//		for(AuthFeatureVO auth : list){
//			System.out.println(auth.getAuth_no()+":"+auth.getAuth_name());
//			System.out.println("--------------------");
//		}
	}
	
}
