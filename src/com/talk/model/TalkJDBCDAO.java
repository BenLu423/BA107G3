package com.talk.model;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.friends_list.model.FriendsListVO;

public class TalkJDBCDAO implements TalkDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TOAST";
	String passwd = "123456";
	
	private static final String INSERT_TALK = "INSERT INTO TALK(TALK_NO,MEM_NO_SEND,MEM_NO_GET,TALK_TIME,TALK_CNT)VALUES('T'||LPAD(to_char(TALK_SEQ.NEXTVAL),3,'0'),?,?,?,?)";
	private static final String UPDATE_TALK = "UPDATE TALK SET MEM_NO_SEND=?,MEM_NO_GET=?,TALK_TIME=?,TALK_CNT=? WHERE MEM_NO_SEND=? AND MEM_NO_GET=?";
	private static final String DELETE_TALK = "DELETE TALK WHERE MEM_NO_SEND=? AND MEM_NO_GET=?";
	private static final String FIND_ONE_TALK = "SELECT*FROM TALK WHERE MEM_NO_SEND=? AND MEM_NO_GET=?";
	private static final String GET_ALL = "SELECT*FROM TALK";

	@Override
	public void insert(TalkVO talkvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_TALK);
			
			pstmt.setString(1, talkvo.getMem_no_send());
			pstmt.setString(2,talkvo.getMem_no_get());
			pstmt.setTimestamp(3,talkvo.getTalk_time());
			pstmt.setString(4,talkvo.getTalk_cnt());
			
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
	public void update(TalkVO talkvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_TALK);
			
			pstmt.setString(1, talkvo.getMem_no_send());
			pstmt.setString(2,talkvo.getMem_no_get());
			pstmt.setTimestamp(3,talkvo.getTalk_time());
			pstmt.setString(4,talkvo.getTalk_cnt());
			pstmt.setString(5, talkvo.getMem_no_send());
			pstmt.setString(6,talkvo.getMem_no_get());
			
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
	public void delete(FriendsListVO friends) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_TALK);
			
			pstmt.setString(1, friends.getMem_no_self());
			pstmt.setString(2, friends.getMem_no_other());
			
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
	public TalkVO findTalkByFriends(FriendsListVO friends) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TalkVO talk = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(FIND_ONE_TALK);
			
			pstmt.setString(1, friends.getMem_no_self());
			pstmt.setString(2, friends.getMem_no_other());
			rs = pstmt.executeQuery();
			rs.next();
			talk = new TalkVO();
			talk.setTalk_no(rs.getString("talk_no"));
			talk.setMem_no_send(rs.getString("mem_no_send"));
			talk.setMem_no_get(rs.getString("mem_no_get"));
			talk.setTalk_time(rs.getTimestamp("talk_time"));
			talk.setTalk_cnt(rs.getString("talk_cnt"));
			
			
			
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
		return talk;
	}

	@Override
	public Set<TalkVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Set<TalkVO>set = new LinkedHashSet<TalkVO>();
		TalkVO talk = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while(rs.next()){
				talk = new TalkVO();
				talk.setTalk_no(rs.getString("talk_no"));
				talk.setMem_no_send(rs.getString("mem_no_send"));
				talk.setMem_no_get(rs.getString("mem_no_get"));
				talk.setTalk_time(rs.getTimestamp("talk_time"));
				talk.setTalk_cnt(rs.getString("talk_cnt"));
				set.add(talk);
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
		
		TalkJDBCDAO talk = new TalkJDBCDAO();
		
		//insert
//		java.util.Date today = new java.util.Date();
//		long time = today.getTime();
//		Timestamp date = new Timestamp(time);
//		
//		TalkVO talkvo1 = new TalkVO();
//		talkvo1.setMem_no_send("M002");
//		talkvo1.setMem_no_get("M003");
//		talkvo1.setTalk_time(date);
//		talkvo1.setTalk_cnt("test");
//		talk.insert(talkvo1);
//		
		//update
//		java.util.Date today = new java.util.Date();
//		long time = today.getTime();
//		Timestamp date = new Timestamp(time);
//		TalkVO talkvo2 = new TalkVO();
//		talkvo2.setMem_no_send("M001");
//		talkvo2.setMem_no_get("M002");
//		talkvo2.setTalk_time(date);
//		talkvo2.setTalk_cnt("testupdate");
//		talk.update(talkvo2);
		
		//delete
//		FriendsListVO friends = new FriendsListVO();
//		friends.setMem_no_self("M002");
//		friends.setMem_no_other("M003");
//		talk.delete(friends);
		
		//FIND_ONE_TALK
		
//		FriendsListVO friends2 = new FriendsListVO();
//		friends2.setMem_no_self("M001");
//		friends2.setMem_no_other("M002");
//		TalkVO talkvo = talk.findTalkByFriends(friends2);
//		System.out.println(talkvo.getTalk_no());
//		System.out.println(talkvo.getMem_no_send());
//		System.out.println(talkvo.getMem_no_get());
//		System.out.println(talkvo.getTalk_time());
//		System.out.println(talkvo.getTalk_cnt());
		
		//get all
		Set<TalkVO>set = talk.getAll();
		for(TalkVO talks : set){
			System.out.println(talks.getTalk_no());
			System.out.println(talks.getMem_no_send());
			System.out.println(talks.getMem_no_get());
			System.out.println(talks.getTalk_time());
			System.out.println(talks.getTalk_cnt());
			System.out.println("========");
		}
		
		
	}

}
