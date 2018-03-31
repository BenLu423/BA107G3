package com.admin.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.auth_feature.model.AuthFeatureVO;

public class AdminJDBCDAO implements AdminDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TOAST";
	String passwd = "123456";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_ADMIN,PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, adminVO.getAdm_acct());
			pstmt.setString(2, adminVO.getAdm_pwd());
			pstmt.setString(3, adminVO.getAdm_name());
			
			pstmt.executeUpdate();
			System.out.println("新增完成");
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_ADMIN);
			
			pstmt.setString(1, adminVO.getAdm_acct());
			pstmt.setString(2, adminVO.getAdm_pwd());
			pstmt.setString(3, adminVO.getAdm_name());
			pstmt.setString(4, adminVO.getAdm_no());
			
			pstmt.executeUpdate();
			System.out.println("修改完成");
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_ADMIN);
			
			pstmt.setString(1, admin_no);
			
			pstmt.executeUpdate();
			System.out.println("刪除完成");
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_ADMIN);
			
			pstmt.setString(1, admin_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			admin = new AdminVO();
			admin.setAdm_no(rs.getString("adm_no"));
			admin.setAdm_acct(rs.getString("adm_acct"));
			admin.setAdm_pwd(rs.getString("adm_pwd"));
			admin.setAdm_name(rs.getString("adm_name"));
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ADMIN_AUTHS);
			
			pstmt.setString(1, admin_no);
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		return list;
	}
	

	
	public static void main(String[]args){
		AdminJDBCDAO admin = new AdminJDBCDAO();
		
		//新增
//		AdminVO adminvo1 = new AdminVO();
//		adminvo1.setAdm_acct("fff");
//		adminvo1.setAdm_pwd("fff");
//		adminvo1.setAdm_name("測試");
//		admin.insert(adminvo1);
		
		//修改
//		AdminVO adminvo2 = new AdminVO();
//		adminvo2.setAdm_no("A006");
//		adminvo2.setAdm_acct("test");
//		adminvo2.setAdm_pwd("test");
//		adminvo2.setAdm_name("testupdate");
//		admin.update(adminvo2);
		
		//刪除
//		admin.delete("A006");
		
		//以帳號密碼查詢
//		AdminVO adminvo3 = admin.findByPrimaryKey("A001");
//		System.out.println("adminvo_no="+adminvo3.getAdm_no());
//		System.out.println("adminvo_acct="+adminvo3.getAdm_acct());
//		System.out.println("adminvo_pwd="+adminvo3.getAdm_pwd());
//		System.out.println("adminvo_name"+adminvo3.getAdm_name());
		
		//getAll
		List<AdminVO> list = admin.getAll();
		for(AdminVO adminvo4 : list){
			System.out.println("adminvo_no="+adminvo4.getAdm_no());
			System.out.println("adminvo_acct="+adminvo4.getAdm_acct());
			System.out.println("adminvo_pwd="+adminvo4.getAdm_pwd());
			System.out.println("adminvo_name="+adminvo4.getAdm_name());
			System.out.println("================================");
		}
		
		//get admin's auths
//		List<AuthFeatureVO> auth = admin.getAdminAuths("A001");
//		for(AuthFeatureVO auths : auth){
//			System.out.println("auth_no:"+auths.getAuth_no());
//			System.out.println("auth_name:"+auths.getAuth_name());
//		}
		
//		AdminVO adminvo = admin.findByPrimaryKey("A008");
//		System.out.println(adminvo.getAdm_acct());
//		
		
	}

	
	public Map<String, String> getAllMap() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO admin = null;
		List<AdminVO> adminList = new ArrayList<AdminVO>();
		Map<String , String >map = new HashMap<String ,String >();
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		return admin;
	}

}