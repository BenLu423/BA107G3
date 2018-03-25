package com.admin.model;

import java.util.List;
import java.util.Set;

import com.admin_feature.model.AuthFeatureVO;

public class AdminService {
	
	private AdminDAO_interface dao;
	public AdminService(){
		dao = new AdminDAO();
	}
	
	public AdminVO addAdmin(String adm_acct,String adm_pwd ,String adm_name){
		AdminVO adminVO = new AdminVO();
		adminVO.setAdm_acct(adm_acct);
		adminVO.setAdm_pwd(adm_pwd);
		adminVO.setAdm_name(adm_name);
		dao.insert(adminVO);
		
		return adminVO;
	}
	
	public AdminVO updateAdmin(String adm_no,String adm_acct,String adm_pwd ,String adm_name){
		AdminVO adminVO = new AdminVO();
		adminVO.setAdm_no(adm_no);
		adminVO.setAdm_acct(adm_acct);
		adminVO.setAdm_pwd(adm_pwd);
		adminVO.setAdm_name(adm_name);
		dao.update(adminVO);
		
		return adminVO;
	}
	
	public void deleteAdmin(String adm_no){
		dao.delete(adm_no);
	}
	
	public AdminVO getOneAdmin(String adminAcct,String adminPwd){
		return dao.findByPrimaryKey(adminAcct,adminPwd);
	}
	
	public List<AdminVO>getAll(){
		return dao.getAll();
	}
	
	public Set<AuthFeatureVO>getAdminAuths(String adm_no){
		return dao.getAdminAuths(adm_no);
	}

}
