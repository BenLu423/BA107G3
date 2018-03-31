package com.auth_feature.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.admin.model.AdminVO;

public class AuthFeatureDAO implements AuthFeatureDAO_interface{
	
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/BA107G3");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_AUTH);
			
			pstmt.setString(1, authVO.getAuth_name());
			
			pstmt.executeUpdate();
			System.out.println("新增成功");
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_AUTH);
			
			pstmt.setString(1, authVO.getAuth_name());
			pstmt.setString(2, authVO.getAuth_no());
			
			pstmt.executeUpdate();
			System.out.println("修改成功");
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_AUTH);
			
			pstmt.setString(1, auth_no);
			
			pstmt.executeUpdate();
			System.out.println("刪除成功");
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_AUTH);
			
			pstmt.setString(1, auth_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			auth.setAuth_no(rs.getString("auth_no"));
			auth.setAuth_name(rs.getString("auth_name"));
			
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_AUTH);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				auth = new AuthFeatureVO();
				auth.setAuth_no(rs.getString("auth_no"));
				auth.setAuth_name(rs.getString("auth_name"));
				list.add(auth);
			}
			
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
			con = ds.getConnection();
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

}
