package com.admin_auth.model;

import java.util.List;

import com.admin.model.AdminVO;
import com.auth_feature.model.AuthFeatureVO;

public class AuthService {
	private AdminAuthDAO_interface dao;

	public AuthService() {
		dao = new AdminAuthDAO();
	}

	public void addAuth(AdminVO adm,AuthFeatureVO auth){
		AdminAuthVO authVO = new AdminAuthVO();
		authVO.setAdmin(adm);
		authVO.setAuth(auth);
		dao.insert(authVO);
	}
	
	public void addAuth(AdminVO admin, String[] auths) {
		AdminAuthVO authVO = null;

		for (int i = 0; i < auths.length; i++) {
			authVO = new AdminAuthVO();
			authVO.setAdmin(admin);
			AuthFeatureVO af = new AuthFeatureVO();
			af.setAuth_no(auths[i]);
			authVO.setAuth(af);
			dao.insert(authVO);

		}
	}

	public void deleteAuth(String adm_no, String auth_no) {
		dao.delete(adm_no, auth_no);
	}

	public AdminAuthVO getOne(String adm_no, String auth_no) {
		return dao.findByPrimaryKey(adm_no, auth_no);
	}
	
	public List<AdminAuthVO> getAll(){
		return dao.getAll();
	}

}
