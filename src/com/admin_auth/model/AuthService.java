package com.admin_auth.model;

import java.util.List;

public class AuthService {
	private AdminAuthDAO_interface dao;

	public AuthService() {
		dao = new AdminAuthDAO();
	}

	public void addAuth(String adm_no,String auth_no){
		AdminAuthVO authVO = new AdminAuthVO();
		authVO.setAdm_no(adm_no);
		authVO.setAuth_no(auth_no);
		dao.insert(authVO);
	}
	
	public void addAuth(String admin_no, String[] auths) {
		AdminAuthVO authVO = null;

		for (int i = 0; i < auths.length; i++) {
			authVO = new AdminAuthVO();
			authVO.setAdm_no(admin_no);
			authVO.setAuth_no(auths[i]);
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
