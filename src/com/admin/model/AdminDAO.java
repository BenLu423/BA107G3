package com.admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.auth_feature.model.AuthFeatureVO;

public class AdminDAO implements AdminDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_ADMIN = "INSERT INTO ADMIN(ADM_NO, ADM_ACCT, ADM_PWD, ADM_NAME) VALUES ('A'||LPAD(to_char(ADMIN_SEQ.NEXTVAL),3,'0'), ?, ?, ?)";
	private static final String UPDATE_ADMIN = "UPDATE ADMIN SET ADM_ACCT=?,ADM_PWD=?,ADM_NAME=? WHERE ADM_NO=?";
	private static final String DELETE_ADMIN = "DELETE FROM ADMIN WHERE ADM_NO=?";
	private static final String GET_ONE_ADMIN = "SELECT*FROM ADMIN WHERE ADM_NO=?";
	private static final String GET_ALL_ADMIN = "SELECT*FROM ADMIN ORDER BY ADM_NO DESC";
	private static final String GET_ADMIN_AUTHS = "SELECT*FROM AUTH_FEATURE JOIN ADMIN_AUTH ON AUTH_FEATURE.AUTH_NO = ADMIN_AUTH.AUTH_NO WHERE ADM_NO=?";
	private static final String GET_ONE_ByACCT_PWD = "SELECT*FROM ADMIN WHERE ADM_ACCT=? AND ADM_PWD=?";
	
	@Override
	public void insert(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_ADMIN);
			
			pstmt.setString(1, adminVO.getAdm_acct());
			pstmt.setString(2, adminVO.getAdm_pwd());
			pstmt.setString(3, adminVO.getAdm_name());
			
			pstmt.executeUpdate();
			System.out.println("新增完成");
			
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		
	}

	@Override
	public void update(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ADMIN);
			
			pstmt.setString(1, adminVO.getAdm_acct());
			pstmt.setString(2, adminVO.getAdm_pwd());
			pstmt.setString(3, adminVO.getAdm_name());
			pstmt.setString(4, adminVO.getAdm_no());
			
			pstmt.executeUpdate();
			System.out.println("修改完成");
			
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		
	}

	@Override
	public void delete(String admin_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ADMIN);
			
			pstmt.setString(1, admin_no);
			
			pstmt.executeUpdate();
			System.out.println("刪除完成");
			
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		
	}

	@Override
	public AdminVO findByPrimaryKey(String admin_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO admin = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ADMIN);
			
			pstmt.setString(1, admin_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			admin = new AdminVO();
			admin.setAdm_no(rs.getString("adm_no"));
			admin.setAdm_acct(rs.getString("adm_acct"));
			admin.setAdm_pwd(rs.getString("adm_pwd"));
			admin.setAdm_name(rs.getString("adm_name"));
			
			System.out.println("查詢完成");
			
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		return admin;
	}

	@Override
	public List<AdminVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO admin = null;
		List<AdminVO> adminList = new ArrayList<AdminVO>();
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ADMIN);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				admin = new AdminVO();
				admin.setAdm_no(rs.getString("adm_no"));
				admin.setAdm_acct(rs.getString("adm_acct"));
				admin.setAdm_pwd(rs.getString("adm_pwd"));
				admin.setAdm_name(rs.getString("adm_name"));
				adminList.add(admin);
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		return adminList;
	}

	@Override
	public List<AuthFeatureVO> getAdminAuths(String admin_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuthFeatureVO auth = null;
		List<AuthFeatureVO> list = new ArrayList<AuthFeatureVO>();
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ADMIN_AUTHS);
			
			pstmt.setString(1, admin_no);
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		return list;
	}

	
	public Map<String, String> getAllMap() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO admin = null;
		Map<String , String >map = new HashMap<String ,String >();
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ADMIN);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				admin = new AdminVO();
				admin.setAdm_no(rs.getString("adm_no"));
				admin.setAdm_acct(rs.getString("adm_acct"));
				admin.setAdm_pwd(rs.getString("adm_pwd"));
				admin.setAdm_name(rs.getString("adm_name"));
				map.put(rs.getString("adm_acct"), rs.getString("adm_pwd"));
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		return map;
	}

	@Override
	public AdminVO findByAcctAndPwd(String admin_acct, String admin_pws) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO admin = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ByACCT_PWD);
			
			pstmt.setString(1, admin_acct);
			pstmt.setString(2, admin_pws);
			rs = pstmt.executeQuery();
			
			rs.next();
			admin = new AdminVO();
			admin.setAdm_no(rs.getString("adm_no"));
			admin.setAdm_acct(rs.getString("adm_acct"));
			admin.setAdm_pwd(rs.getString("adm_pwd"));
			admin.setAdm_name(rs.getString("adm_name"));
			
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		return admin;
	}
	

}
