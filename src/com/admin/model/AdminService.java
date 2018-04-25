package com.admin.model;

import java.util.*;

import com.admin_auth.model.AdminAuthVO;
import com.auth_feature.model.AuthFeatureVO;

public class AdminService {
	
	private AdminDAO_interface dao;
	public AdminService(){
		dao = new AdminDAO();
	}
	
	public AdminVO addAdmin(String adm_acct,String adm_pwd ,String adm_name,String adm_mail){
		AdminVO adminVO = new AdminVO();
		adminVO.setAdm_acct(adm_acct);
		adminVO.setAdm_pwd(adm_pwd);
		adminVO.setAdm_name(adm_name);
		adminVO.setAdm_mail(adm_mail);
		dao.insert(adminVO);
		
		return adminVO;
	}
	
	public AdminVO updateAdmin(String adm_no,String adm_acct,String adm_mail ,String adm_name){
		AdminVO adminVO = dao.findByPrimaryKey(adm_no);
		adminVO.setAdm_no(adm_no);
		adminVO.setAdm_acct(adm_acct);
		adminVO.setAdm_mail(adm_mail);
		adminVO.setAdm_name(adm_name);
		dao.update(adminVO);
		
		return adminVO;
	}
	
	public void deleteAdmin(String adm_no){
		dao.delete(adm_no);
	}
	
	public AdminVO getOneAdmin(String admin_no){
		return dao.findByPrimaryKey(admin_no);
	}
	
	public AdminVO getOneByAcct(String adm_acct){
		return dao.findByAcct(adm_acct);
	}
	
	public List<AdminVO>getAll(){
		return dao.getAll();
	}
	
	public List<AdminAuthVO>getAdminAuths(String adm_no){
		return dao.getAdminAuths(adm_no);
	}
	
	
}
