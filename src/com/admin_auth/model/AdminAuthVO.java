package com.admin_auth.model;

public class AdminAuthVO implements java.io.Serializable{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adm_no == null) ? 0 : adm_no.hashCode());
		result = prime * result + ((auth_no == null) ? 0 : auth_no.hashCode());
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
		if (adm_no == null) {
			if (other.adm_no != null)
				return false;
		} else if (!adm_no.equals(other.adm_no))
			return false;
		if (auth_no == null) {
			if (other.auth_no != null)
				return false;
		} else if (!auth_no.equals(other.auth_no))
			return false;
		return true;
	}

	private String adm_no;
	private String auth_no;
	
	public AdminAuthVO(){
		
	}
	public AdminAuthVO(String adm_no,String auth_no){
		this.adm_no=adm_no;
		this.auth_no=auth_no;
	}

	public String getAdm_no() {
		return adm_no;
	}

	public void setAdm_no(String adm_no) {
		this.adm_no = adm_no;
	}

	public String getAuth_no() {
		return auth_no;
	}

	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}
	
	

}
