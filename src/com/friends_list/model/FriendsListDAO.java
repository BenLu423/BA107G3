package com.friends_list.model;

import java.sql.Connection;
import java.sql.Date;
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

public class FriendsListDAO implements FriendsListDAO_interface{
	
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/BA107G3");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
//	private static final String INSERT_FRIENDS = "INSERT INTO FRIENDS_LIST (MEM_NO_SELF,MEM_NO_OTHER)VALUES(?,?)";
	private static final String INSERT_FRIENDS = "INSERT INTO FRIENDS_LIST (MEM_NO_SELF,MEM_NO_OTHER,FRILIST_MODIFY)VALUES(?,?,?)";
	private static final String UPDATE_FRIENDS = "UPDATE FRIENDS_LIST SET MEM_NO_SELF=?,MEM_NO_OTHER=?,FRILIST_MODIFY=?,FRILIST_TIME=?,FRILIST_NOTICE=? WHERE MEM_NO_SELF=? AND MEM_NO_OTHER=?";
	private static final String DELETE_FRIENDS = "DELETE FROM FRIENDS_LIST WHERE MEM_NO_SELF=? AND MEM_NO_OTHER=?";
	private static final String GET_ONE_LIST = "SELECT*FROM FRIENDS_LIST WHERE MEM_NO_SELF=? AND MEM_NO_OTHER=?";
	private static final String GET_ALL = "SELECT*FROM FRIENDS_LIST";
	private static final String GET_LIST_ByMEMEBERNO = "SELECT*FROM FRIENDS_LIST WHERE MEM_NO_SELF=? AND (FRILIST_MODIFY='是' OR FRILIST_MODIFY='黑名單')";

	private static final String CHECK_IS_FRIENDS = "SELECT * FROM FRIENDS_LIST WHERE MEM_NO_SELF = ? AND MEM_NO_OTHER = ?";
	private static final String UPDATE_FRIEND = "UPDATE FRIENDS_LIST SET FRILIST_MODIFY = ? WHERE MEM_NO_SELF = ? AND MEM_NO_OTHER = ?";
	private static final String GET_FRIENDS_LIST = "SELECT * FROM FRIENDS_LIST WHERE MEM_NO_SELF = ? AND FRILIST_MODIFY = ?";
	@Override
	public void insert(String self , String other) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_FRIENDS);
			
			pstmt.setString(1, self);
			pstmt.setString(2, other);
			
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
	public void update(FriendsListVO frilistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FRIENDS);
			
			pstmt.setString(1, frilistVO.getMem_no_self());
			pstmt.setString(2, frilistVO.getMem_no_other());
			pstmt.setString(3, frilistVO.getFrilist_modify());
			pstmt.setDate(4, (Date) frilistVO.getFrilist_time());
			pstmt.setString(5, frilistVO.getFrilist_notice());
			pstmt.setString(6, frilistVO.getMem_no_self());
			pstmt.setString(7, frilistVO.getMem_no_other());
			
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
	public void delete(String mem_no_self,String mem_no_other) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_FRIENDS);
			
			pstmt.setString(1, mem_no_self);
			pstmt.setString(2, mem_no_other);
			
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
	public FriendsListVO findByPrimaryKey(String mem_no_self, String mem_no_other) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FriendsListVO friendsVO = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_LIST);
			
			pstmt.setString(1, mem_no_self);
			pstmt.setString(2, mem_no_other);
			rs = pstmt.executeQuery();
			rs.next();
			friendsVO = new FriendsListVO();
			friendsVO.setMem_no_self(rs.getString("mem_no_self"));
			friendsVO.setMem_no_other(rs.getString("mem_no_other"));
			friendsVO.setFrilist_modify(rs.getString("frilist_modify"));
			friendsVO.setFrilist_time(rs.getDate("frilist_time"));
			friendsVO.setFrilist_notice(rs.getString("frilist_notice"));
			
			
			
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
		return friendsVO;
	}

	@Override
	public List<FriendsListVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FriendsListVO friendsVO = null;
		List<FriendsListVO> list = new ArrayList<FriendsListVO>();
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				friendsVO = new FriendsListVO();
				friendsVO.setMem_no_self(rs.getString("mem_no_self"));
				friendsVO.setMem_no_other(rs.getString("mem_no_other"));
				friendsVO.setFrilist_modify(rs.getString("frilist_modify"));
				friendsVO.setFrilist_time(rs.getDate("frilist_time"));
				friendsVO.setFrilist_notice(rs.getString("frilist_notice"));
				list.add(friendsVO);
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
	public List<FriendsListVO> getMemberFriends(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FriendsListVO friendsVO = null;
		List<FriendsListVO> list = new ArrayList<FriendsListVO>();
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_ByMEMEBERNO);
			pstmt.setString(1, mem_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				friendsVO = new FriendsListVO();
				friendsVO.setMem_no_self(rs.getString("mem_no_self"));
				friendsVO.setMem_no_other(rs.getString("mem_no_other"));
				friendsVO.setFrilist_modify(rs.getString("frilist_modify"));
				friendsVO.setFrilist_time(rs.getDate("frilist_time"));
				friendsVO.setFrilist_notice(rs.getString("frilist_notice"));
				list.add(friendsVO);
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
	/*瀏覽個人頁面加好友*/
	public void webAddFriends(String self,String other){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_FRIENDS);
			/*新增修改成一次新增兩筆*/
			pstmt.setString(1, self);
			pstmt.setString(2, other);
			pstmt.setString(3, "被審核");
			pstmt.addBatch();
			pstmt.setString(1, other);
			pstmt.setString(2, self);
			pstmt.setString(3, "待審核");
			pstmt.addBatch();
			pstmt.executeBatch();
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
	/*是否為好友*/
	public Boolean isFriend(String self,String other,Integer count){
		Boolean isfri = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK_IS_FRIENDS);
			pstmt.setString(1, self);
			pstmt.setString(2, other);
			rs = pstmt.executeQuery();
			while(rs.next()){
				isfri = true;
			}
			count++;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if((count != 2) && (isfri == true)){
				isFriend(other,self,count);
			}
		}
		
		return isfri;
	}
	/*通知好友確定加入*/
	public void webUpdateFrinds(String modify,String self, String other){
		Connection con = null;
		PreparedStatement ps = null;	
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_FRIEND);
			ps.setString(1, modify);
			ps.setString(2, self);
			ps.setString(3, other);
			ps.addBatch();
			ps.setString(1, modify);
			ps.setString(2, other);
			ps.setString(3, self);
			ps.addBatch();
			ps.executeBatch();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}	
	}
	/*拒絕好友和刪除好友*/
	public void webDeleteFriends(String self,String other){
		Connection con = null;
		PreparedStatement ps = null;	
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE_FRIENDS);
			ps.setString(1, self);
			ps.setString(2, other);
			ps.addBatch();
			ps.setString(1, other);
			ps.setString(2, self);
			ps.addBatch();
			ps.executeBatch();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}	
	}
	public List<FriendsListVO> webFriendsList(String modify,String self){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<FriendsListVO> list = new ArrayList<FriendsListVO>();
		FriendsListVO flvo = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(GET_FRIENDS_LIST);
			ps.setString(1, self);
			ps.setString(2, modify);
			rs = ps.executeQuery();
			while(rs.next()){
				flvo = new FriendsListVO();
				flvo.setMem_no_self(rs.getString("MEM_NO_SELF"));
				flvo.setMem_no_other(rs.getString("MEM_NO_OTHER"));
				flvo.setFrilist_modify(rs.getString("FRILIST_MODIFY"));
				flvo.setFrilist_time(rs.getDate("FRILIST_TIME"));
				flvo.setFrilist_notice(rs.getString("FRILIST_NOTICE"));
				list.add(flvo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
}
