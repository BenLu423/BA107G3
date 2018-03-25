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

public class FriendsListJDBCDAO implements FriendsListDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TOAST";
	String passwd = "123456";
	
	private static final String INSERT_FRIENDS = "INSERT INTO FRIENDS_LIST (MEM_NO_SELF,MEM_NO_OTHER)VALUES(?,?)";
	private static final String UPDATE_FRIENDS = "UPDATE FRIENDS_LIST SET MEM_NO_SELF=?,MEM_NO_OTHER=?,FRILIST_MODIFY=?,FRILIST_TIME=?,FRILIST_NOTICE=? WHERE MEM_NO_SELF=? AND MEM_NO_OTHER=?";
	private static final String DELETE_FRIENDS = "DELETE FROM FRIENDS_LIST WHERE MEM_NO_SELF=? AND MEM_NO_OTHER=?";
	private static final String GET_ONE_LIST = "SELECT*FROM FRIENDS_LIST WHERE MEM_NO_SELF=? AND MEM_NO_OTHER=?";
	private static final String GET_ALL = "SELECT*FROM FRIENDS_LIST";
	private static final String GET_LIST_ByMEMEBERNO = "SELECT*FROM FRIENDS_LIST WHERE (MEM_NO_SELF=? OR MEM_NO_OTHER =?) AND FRILIST_MODIFY='是'";

	@Override
	public void insert(FriendsListVO frilistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_FRIENDS);
			
			pstmt.setString(1, frilistVO.getMem_no_self());
			pstmt.setString(2, frilistVO.getMem_no_other());
			
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
	public void update(FriendsListVO frilistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
	public void delete(String mem_no_self,String mem_no_other) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_FRIENDS);
			
			pstmt.setString(1, mem_no_self);
			pstmt.setString(2, mem_no_other);
			
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
	public FriendsListVO findByPrimaryKey(String mem_no_self, String mem_no_other) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FriendsListVO friendsVO = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
	public Set<FriendsListVO> getMemberFriends(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FriendsListVO friendsVO = null;
		Set<FriendsListVO> set = new LinkedHashSet<FriendsListVO>();
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_LIST_ByMEMEBERNO);
			pstmt.setString(1, mem_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				friendsVO = new FriendsListVO();
				friendsVO.setMem_no_self(rs.getString("mem_no_self"));
				friendsVO.setMem_no_other(rs.getString("mem_no_other"));
				friendsVO.setFrilist_modify(rs.getString("frilist_modify"));
				friendsVO.setFrilist_time(rs.getDate("frilist_time"));
				friendsVO.setFrilist_notice(rs.getString("frilist_notice"));
				set.add(friendsVO);
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
		return set;
	}
	
	public static void main(String []args){
		FriendsListJDBCDAO friends = new FriendsListJDBCDAO();
		
		//insert
//		FriendsListVO friends1 = new FriendsListVO();
//		friends1.setMem_no_self("M012");
//		friends1.setMem_no_other("M011");
//		friends.insert(friends1);
		
		//update
//		FriendsListVO friends2 = new FriendsListVO();
//		java.util.Date today = new java.util.Date();
//		long time = today.getTime();
//		Date date = new Date(time);
//		
//		friends2.setMem_no_self("M012");
//		friends2.setMem_no_other("M011");
//		friends2.setFrilist_modify("是");
//		friends2.setFrilist_time(date);
//		friends2.setFrilist_notice("已通知");
//		friends.update(friends2);
		
		//delete
//		friends.delete("M012", "M011");
		
		//GET ONE LIST
//		FriendsListVO friends3 = friends.findByPrimaryKey("M004", "M005");
//		System.out.println("mem no self:"+friends3.getMem_no_self());
//		System.out.println("mem no other:"+friends3.getMem_no_other());
//		System.out.println("modify:"+friends3.getFrilist_modify());
//		System.out.println("time:"+friends3.getFrilist_time());
//		System.out.println("notice:"+friends3.getFrilist_notice());
		
		//get all
//		List<FriendsListVO> list = friends.getAll();
//		for(FriendsListVO fr : list){
//			System.out.println(fr.getMem_no_self());
//			System.out.println(fr.getMem_no_other());
//			System.out.println(fr.getFrilist_modify());
//			System.out.println(fr.getFrilist_time());
//			System.out.println(fr.getFrilist_notice());
//			System.out.println("==============");
//		}
		
		//get member friends
		Set<FriendsListVO> set = friends.getMemberFriends("M002");
		for(FriendsListVO fr : set){
			if(fr.getMem_no_other().equals("M002")){
				System.out.println(fr.getMem_no_self());
			}else{
				System.out.println(fr.getMem_no_other());
			}
			System.out.println("======");
		}
		
		
	}

}
