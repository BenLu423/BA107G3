package com.admin.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.auth_feature.model.AuthFeatureVO;

public interface AdminDAO_interface {
	public void insert(AdminVO adminVO);
	public void update(AdminVO adminVO);
	public void delete(String adminNo);
	public AdminVO findByPrimaryKey(String admin_no);
	public List<AdminVO> getAll();
	//�H���u�s���j�M�ӭ��u�v��
	public List<AuthFeatureVO> getAdminAuths(String admin_no);
	
	//�H�b���K�X�j�M���u
	public AdminVO findByAcctAndPwd(String admin_acct,String admin_pws);
	

}
