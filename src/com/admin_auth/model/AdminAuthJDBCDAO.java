package com.admin_auth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminAuthJDBCDAO implements AdminAuthDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TOAST";
	String passwd = "123456";
	
	private static final String INSERT_ADAU ="INSERT INTO ADMIN_AUTH (ADM_NO,AUTH_NO)VALUES(?,?)";
	private static final String DELETE_ADAU = "DELETE FROM ADMIN_AUTH WHERE ADM_NO=? AND AUTH_NO=?";
	private static final String GET_ONE_ADAU = "SELECT*FROM ADMIN_AUTH WHERE ADM_NO=? AND AUTH_NO=?";
	private static final String GET_ALL_ADAU = "SELECT*FROM ADMIN_AUTH";

	@Override
	public void insert(AdminAuthVO asauVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_ADAU);
			
			pstmt.setString(1, asauVO.getAdm_no());
			pstmt.setString(2, asauVO.getAuth_no());
			
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
	public void delete(String adm_no, String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_ADAU);
			
			pstmt.setString(1, adm_no);
			pstmt.setString(2, auth_no);
			
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
	public AdminAuthVO findByPrimaryKey(String adm_no, String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		AdminAuthVO adau = new AdminAuthVO();
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_ADAU);
			
			pstmt.setString(1, adm_no);
			pstmt.setString(2, auth_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			adau.setAdm_no(rs.getString("adm_no"));
			adau.setAuth_no(rs.getString("auth_no"));
			
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_ADAU);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				adau = new AdminAuthVO();
				adau.setAdm_no(rs.getString("adm_no"));
				adau.setAuth_no(rs.getString("auth_no"));
				list.add(adau);
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
	
	public static void main(String []args){
		AdminAuthJDBCDAO adau = new AdminAuthJDBCDAO();
		
		//新增
//		Admin_authVO adauvo1 = new Admin_authVO();
//		adauvo1.setAdm_no("A004");
//		adauvo1.setAuth_no("AF001");
//		adau.insert(adauvo1);
		
		//刪除
//		adau.delete("A004", "AF001");
		
		//get one 
//		Admin_authVO adauvo2 = adau.findByPrimaryKey("A004", "AF006");
//		System.out.println(adauvo2.getAdm_no());
//		System.out.println(adauvo2.getAuth_no());
		
		//get all
		List<AdminAuthVO> list = adau.getAll();
		for(AdminAuthVO adaus : list){
			System.out.println(adaus.getAdm_no());
			System.out.println(adaus.getAuth_no());
			System.out.println("==============");
		}
	}
	

}
