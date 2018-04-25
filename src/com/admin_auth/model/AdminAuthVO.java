package com.admin_auth.model;

import com.admin.model.AdminVO;
import com.auth_feature.model.AuthFeatureVO;

public class AdminAuthVO implements java.io.Serializable{

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((auth == null) ? 0 : auth.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminAuthVO other = (AdminAuthVO) obj;
		
		AdminVO selfAdmin = this.getAdmin();
		AdminVO otherAdmin = other.getAdmin();
		AuthFeatureVO selfAuth = this.getAuth();
		AuthFeatureVO otherAuth = other.getAuth();

		if(((selfAdmin.getAdm_no()).equals(otherAdmin.getAdm_no()))&&((selfAuth.getAuth_no()).equals(otherAuth.getAuth_no()))){
			return true;
		}else{
			return false;
		}
//		if (admin == null) {
//			if (other.admin != null)
//				return false;
//		} else if (!admin.equals(other.admin))
//			return false;
//		if (auth == null) {
//			if (other.auth != null)
//				return false;
//		} else if (!auth.equals(other.auth))
//			return false;
//		return true;
	}

	private AdminVO admin;
	private AuthFeatureVO auth;
	
	public AdminAuthVO(){
		
	}
	public AdminAuthVO(AdminVO admin,AuthFeatureVO auth){
		this.admin=admin;
		this.auth=auth;
	}

	public AdminVO getAdmin() {
		return admin;
	}

	public void setAdmin(AdminVO admin) {
		this.admin = admin;
	}

	public AuthFeatureVO getAuth() {
		return auth;
	}

	public void setAuth(AuthFeatureVO auth) {
		this.auth = auth;
	}
	
	

}
