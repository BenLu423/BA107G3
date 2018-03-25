package com.admin_auth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdminAuthDAO implements AdminAuthDAO_interface{
	
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/BA107G3");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_ADAU ="INSERT INTO ADMIN_AUTH (ADM_NO,AUTH_NO)VALUES(?,?)";
	private static final String DELETE_ADAU = "DELETE FROM ADMIN_AUTH WHERE ADM_NO=? AND AUTH_NO=?";
	private static final String GET_ONE_ADAU = "SELECT*FROM ADMIN_AUTH WHERE ADM_NO=? AND AUTH_NO=?";
	private static final String GET_ALL_ADAU = "SELECT*FROM ADMIN_AUTH";

	@Override
	public void insert(AdminAuthVO asauVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_ADAU);
			
			pstmt.setString(1, asauVO.getAdm_no());
			pstmt.setString(2, asauVO.getAuth_no());
			
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
	public void delete(String adm_no, String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ADAU);
			
			pstmt.setString(1, adm_no);
			pstmt.setString(2, auth_no);
			
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
	public AdminAuthVO findByPrimaryKey(String adm_no, String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		AdminAuthVO adau = new AdminAuthVO();
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ADAU);
			
			pstmt.setString(1, adm_no);
			pstmt.setString(2, auth_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			adau.setAdm_no(rs.getString("adm_no"));
			adau.setAuth_no(rs.getString("auth_no"));
			
			
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
		return adau;
	}

	@Override
	public List<AdminAuthVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		AdminAuthVO adau = null;
		ResultSet rs = null;
		List<AdminAuthVO> list = new ArrayList<AdminAuthVO>();
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ADAU);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				adau = new AdminAuthVO();
				adau.setAdm_no(rs.getString("adm_no"));
				adau.setAuth_no(rs.getString("auth_no"));
				list.add(adau);
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

}
