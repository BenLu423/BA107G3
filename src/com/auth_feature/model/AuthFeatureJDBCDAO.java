package com.auth_feature.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.admin.model.AdminVO;

public class AuthFeatureJDBCDAO implements AuthFeatureDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TOAST";
	String passwd = "123456";
	
	private static final String INSERT_AUTH = "INSERT INTO AUTH_FEATURE (AUTH_NO,AUTH_NAME)VALUES('AF'||LPAD(to_char(AUTH_FEATURE_SEQ.NEXTVAL),3,'0'), ?)";
	private static final String UPDATE_AUTH = "UPDATE AUTH_FEATURE SET AUTH_NAME=? WHERE AUTH_NO=?";
	private static final String DELETE_AUTH = "DELETE FROM AUTH_FEATURE WHERE AUTH_NO=?";
	private static final String GET_ONE_AUTH = "SELECT*FROM AUTH_FEATURE WHERE AUTH_NO=?";
	private static final String GET_ALL_AUTH = "SELECT*FROM AUTH_FEATURE";
	private static final String GET_ADMINS_ByAUTHNO = "SELECT*FROM ADMIN JOIN ADMIN_AUTH ON ADMIN.ADM_NO = ADMIN_AUTH.ADM_NO AND AUTH_NO=?";
	

	@Override
	public void insert(AuthFeatureVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_AUTH);
			
			pstmt.setString(1, authVO.getAuth_name());
			
			pstmt.executeUpdate();
			System.out.println("新增成功");
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(AuthFeatureVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_AUTH);
			
			pstmt.setString(1, authVO.getAuth_name());
			pstmt.setString(2, authVO.getAuth_no());
			
			pstmt.executeUpdate();
			System.out.println("修改成功");
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_AUTH);
			
			pstmt.setString(1, auth_no);
			
			pstmt.executeUpdate();
			System.out.println("刪除成功");
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public AuthFeatureVO findByPrimaryKey(String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		AuthFeatureVO auth = new AuthFeatureVO();
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_AUTH);
			
			pstmt.setString(1, auth_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			auth.setAuth_no(rs.getString("auth_no"));
			auth.setAuth_name(rs.getString("auth_name"));
			
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		return auth;
	}

	@Override
	public List<AuthFeatureVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		AuthFeatureVO auth = null;
		List<AuthFeatureVO> list = new ArrayList<AuthFeatureVO>();
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_AUTH);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				auth = new AuthFeatureVO();
				auth.setAuth_no(rs.getString("auth_no"));
				auth.setAuth_name(rs.getString("auth_name"));
				list.add(auth);
			}
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public Set<AdminVO> getAdminsByAuthno(String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO admin = null;
		Set<AdminVO> set = new LinkedHashSet<AdminVO>();
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ADMINS_ByAUTHNO);
			pstmt.setString(1, auth_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				admin = new AdminVO();
				admin.setAdm_no(rs.getString("adm_no"));
				admin.setAdm_acct(rs.getString("adm_acct"));
				admin.setAdm_pwd(rs.getString("adm_pwd"));
				admin.setAdm_name(rs.getString("adm_name"));
				set.add(admin);
			}
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		return set;
	}
	
	public static void main(String []args){
		AuthFeatureJDBCDAO auth = new AuthFeatureJDBCDAO();
		
		//新增
//		Auth_featureVO auth1 = new Auth_featureVO();
//		auth1.setAuth_name("test");
//		auth.insert(auth1);
//		
		//修改
//		Auth_featureVO auth2 = new Auth_featureVO();
//		auth2.setAuth_no("AF008");
//		auth2.setAuth_name("testUpdate");
//		auth.update(auth2);
		
		//刪除
//		auth.delete("AF008");
		
		//依權限編號查詢權限功能
//		Auth_featureVO auth3 = auth.findByPrimaryKey("AF007");
//		System.out.println("auth_no="+auth3.getAuth_no());
//		System.out.println("auth_name="+auth3.getAuth_name());
		
		//getAll
		List<AuthFeatureVO>list = auth.getAll();
		for(AuthFeatureVO auth4 : list){
			System.out.println("auth_no="+auth4.getAuth_no());
			System.out.println("auth_name="+auth4.getAuth_name());
			System.out.println("=======================");
		}
		
		//get admin by auth_no
//		Set<AdminVO> set = auth.getAdminsByAuthno("AF001");
//		for(AdminVO admin : set){
//			System.out.println("admin_no:"+admin.getAdm_no());
//			System.out.println("admin_acct:"+admin.getAdm_acct());
//			System.out.println("admin_pws:"+admin.getAdm_pwd());
//			System.out.println("admin_name:"+admin.getAdm_name());
//			System.out.println("======================");
//		}
		
	}

}
