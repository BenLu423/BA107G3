package com.talk_picture.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import com.friends_list.model.FriendsListVO;

public class TalkPictureJDBCDAO implements TalkPictureDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TOAST";
	String passwd = "123456";
	
	private static final String INSERT_TALKP = "INSERT INTO TALK_PICTURE (TALKP_NO,TALK_NO,TALKP_PIC)VALUES('TP'||LPAD(to_char(TALK_PICTURE_SEQ.NEXTVAL),3,'0'),?,?)";
	private static final String UPDATE_TALKP = "";
	private static final String DELETE_TALKP = "DELETE FROM TALK_PICTURE WHERE TALK_NO=?";
	private static final String GET_ONE_PIC = "SELECT*FROM TALK_PICTURE WHERE TALKP_NO=?";
	private static final String GET_PIC_ByFRIENDS = 
			"SELECT TALKP_NO,talk.TALK_NO,TALKP_PIC FROM TALK_PICTURE JOIN TALK ON TALK_PICTURE.TALK_NO=TALK.TALK_NO WHERE MEM_NO_SEND=? and MEM_NO_GET=?";
	

	@Override
	public void insert(TalkPictureVO pic) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_TALKP);
			
			pstmt.setString(1, pic.getTalk_no());
			pstmt.setBytes(2,pic.getTalkp_pic());
			
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		
	}

	@Override
	public void update(TalkPictureVO pic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String talk_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_TALKP);
			
			pstmt.setString(1, talk_no);
			
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
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}
		
	}

	@Override
	public TalkPictureVO findByPrimaryKey(String talkp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TalkPictureVO pic= null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_PIC);			
			pstmt.setString(1, talkp_no);
			rs = pstmt.executeQuery();
			rs.next();
			pic = new TalkPictureVO();
			pic.setTalk_no(rs.getString("talk_no"));
			pic.setTalkp_no(rs.getString("talkp_no"));
			pic.setTalkp_pic(rs.getBytes("talkp_pic"));
			
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
		return pic;
	}

	@Override
	public List<TalkPictureVO> getPicByTalkNo(FriendsListVO friends) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TalkPictureVO pic= null;
		List<TalkPictureVO>list = new ArrayList<TalkPictureVO>();
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_PIC_ByFRIENDS);			
			pstmt.setString(1, friends.getMem_no_self());
			pstmt.setString(2, friends.getMem_no_other());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				pic = new TalkPictureVO();
				pic.setTalk_no(rs.getString("talk_no"));
				pic.setTalkp_no(rs.getString("talkp_no"));
				pic.setTalkp_pic(rs.getBytes("talkp_pic"));
				list.add(pic);
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
	
	public static void main(String[]args) throws IOException{
		TalkPictureJDBCDAO pic = new TalkPictureJDBCDAO();
		
		//insert
//		TalkPictureVO picvo = new TalkPictureVO();
//		byte[] picture = getPicture("pic/p1.gif");
//		
//		picvo.setTalk_no("T003");
//		picvo.setTalkp_pic(picture);
//		pic.insert(picvo);

		//delete
//		pic.delete("T003");
		
		//get one pic
//		TalkPictureVO picvo2 = pic.findByPrimaryKey("TP003");
//		System.out.println(picvo2.getTalk_no());
//		System.out.println(picvo2.getTalkp_no());
//		System.out.println(picvo2.getTalkp_pic());
	
		
		//GET_PIC_ByFRIENDS
		FriendsListVO friends = new FriendsListVO();
		friends.setMem_no_self("M001");
		friends.setMem_no_other("M002");
		
		List<TalkPictureVO> list = pic.getPicByTalkNo(friends);
		for(TalkPictureVO pics : list){
			System.out.println(pics.getTalk_no());
			System.out.println(pics.getTalkp_no());
			System.out.println(pics.getTalkp_pic());
		}
		
	}
	
	
	
	public static byte[] getPicture(String path) throws IOException{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024*3];
		int i ;
		while((i = fis.read(buffer)) != -1){
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		
		return baos.toByteArray();
	}

}
