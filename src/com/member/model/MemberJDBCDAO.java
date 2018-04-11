package com.member.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.ssi.ByteArrayServletOutputStream;

public class MemberJDBCDAO implements MemberDAO_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "BA107G3";
	private static final String PASS = "BA107G3";
	
	static final String INSERT = "INSERT INTO MEMBER(MEM_NO, MEM_ACCOUNT, MEM_PASSWORD, MEM_JOIN_TIME, MEM_NAME, MEM_GENDER, MEM_BIRTHDAY, MEM_COUNTY, MEM_DEPOSIT, MEM_CONTACT, MEM_EMOTION, MEM_BONUS, MEM_BLOODTYPE, MEM_HEIGHT, MEM_WEIGHT, MEM_INTEREST, MEM_INTRO, MEM_ONLINE, MEM_LONGITUDE, MEM_LATITUDE, MEM_PHONE, MEM_MAIL, MEM_PHOTO, MEM_PROHIBIT, MEM_SETNOTIFY, MEM_TIMENOTIFY) "
			+ "VALUES ('M'||LPAD(to_char(MEMBER_SEQ.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	static final String UPDATE = "UPDATE MEMBER SET MEM_ACCOUNT = ?, MEM_PASSWORD = ? WHERE MEM_NO = ?";
	
	static final String SINGLE_SEARCH = "SELECT MEM_NAME,MEM_PASSWORD FROM MEMBER WHERE MEM_NO = ?";
	
	static final String GETALL = "SELECT * FROM MEMBER";
	
	static final String CHECKLOGIN = "SELECT * FROM MEMBER WHERE MEM_ACCOUNT = ? AND MEM_PASSWORD = ?";
	/*驅動載入*/									
	static{
		try {
			Class.forName(DRIVER);
			System.out.println("載入成功!!");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	/*新增資料*/
	@Override
	public void memberAdd(MemberVO member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASS);
			ps = con.prepareStatement(INSERT);
			System.out.println("新增");
			ps.setString(1, member.getMem_account());
			ps.setString(2, member.getMem_password());
			ps.setDate(3, member.getMem_join_time());
			ps.setString(4, member.getMem_name());
			ps.setString(5, member.getMem_gender());
			ps.setDate(6, member.getMem_birthday());
			ps.setString(7, member.getMem_county());
			ps.setInt(8, member.getMem_deposit());
			ps.setString(9, member.getMem_contact());
			ps.setString(10, member.getMem_emotion());
			ps.setInt(11, member.getMem_bonus());
			ps.setString(12, member.getMem_bloodtype());
			ps.setInt(13, member.getMem_height());
			ps.setInt(14, member.getMem_weight());
			ps.setString(15, member.getMem_interest());
			ps.setString(16, member.getMem_intro());
			ps.setString(17, member.getMem_online());
			ps.setDouble(18, member.getMem_longitude());
			ps.setDouble(19, member.getMem_latitude());
			ps.setString(20, member.getMem_phone());
			ps.setString(21, member.getMem_mail());
			ps.setBytes(22, member.getMem_photo());
			ps.setString(23, member.getMem_prohibit());
			ps.setString(24, member.getMem_set_notify());
			ps.setDate(25, member.getMem_time_notify());
	
			ps.executeUpdate();
			System.out.println("新增成功!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	/*處理圖片*/
	public byte[] getPic(String pic){
		File file = null;
		FileInputStream fis = null;
		ByteArrayServletOutputStream basos = null;
//		ByteArrayOutputStream baos = null;
		try{
			file = new File(pic);
			fis = new FileInputStream(file);
			basos = new ByteArrayServletOutputStream();
			byte[] buff = new byte[fis.available()];
			fis.read(buff);
			basos.write(buff,0,buff.length);
			
		}catch(IOException e){
			System.out.println("圖片上傳失敗");
		}
		return basos.toByteArray();
	}
	

	
	/*修改資料*/
	@Override
	public void memberUpdate(MemberVO member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASS);
			ps = con.prepareStatement(UPDATE);
			System.out.println("修改");
			ps.setString(1, member.getMem_account());
			ps.setString(2, member.getMem_password());
			ps.setString(3, member.getMem_no());
		
	
			ps.executeUpdate();
			System.out.println("修改成功!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	/*單筆查詢*/
	@Override
	public MemberVO getOne(String name) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvos = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASS);
			ps = con.prepareStatement(SINGLE_SEARCH);
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			while(rs.next()){
				mvos = new MemberVO();
				mvos.setMem_account(rs.getString("MEM_NAME"));
				mvos.setMem_password(rs.getString("MEM_PASSWORD"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return mvos;
	}
	/*查詢全部*/
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvos = null;
		try {
			con = DriverManager.getConnection(URL,USER,PASS);
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			while(rs.next()){
				mvos = new MemberVO();
				mvos.setMem_no(rs.getString("MEM_NO"));
				mvos.setMem_account(rs.getString("MEM_ACCOUNT"));
				mvos.setMem_password(rs.getString("MEM_PASSWORD"));
				mvos.setMem_join_time(rs.getDate("MEM_JOIN_TIME"));
				mvos.setMem_name(rs.getString("MEM_NAME"));
				mvos.setMem_gender(rs.getString("MEM_GENDER"));
				mvos.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
				mvos.setMem_county(rs.getString("MEM_COUNTY"));
				mvos.setMem_deposit(rs.getInt("MEM_DEPOSIT"));
				mvos.setMem_contact(rs.getString("MEM_CONTACT"));
				mvos.setMem_emotion(rs.getString("MEM_EMOTION"));
				mvos.setMem_bonus(rs.getInt("MEM_BONUS"));
				mvos.setMem_bloodtype(rs.getString("MEM_BLOODTYPE"));
				mvos.setMem_height(rs.getInt("MEM_HEIGHT"));
				mvos.setMem_weight(rs.getInt("MEM_WEIGHT"));
				mvos.setMem_interest(rs.getString("MEM_INTEREST"));
				mvos.setMem_intro(rs.getString("MEM_INTRO"));
				mvos.setMem_online(rs.getString("MEM_ONLINE"));
				mvos.setMem_longitude(rs.getDouble("MEM_LONGITUDE"));
				mvos.setMem_latitude(rs.getDouble("MEM_LATITUDE"));
				mvos.setMem_phone(rs.getString("MEM_PHONE"));
				mvos.setMem_mail(rs.getString("MEM_MAIL"));
				mvos.setMem_photo(rs.getBytes("MEM_PHOTO"));
				mvos.setMem_prohibit(rs.getString("MEM_PROHIBIT"));
				mvos.setMem_set_notify(rs.getString("MEM_SETNOTIFY"));
				mvos.setMem_time_notify(rs.getDate("MEM_TIMENOTIFY"));;
				memberList.add(mvos);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally{
			
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return memberList;
	}
	public MemberVO checkLogin(String account,String password){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean checkmem = false;
		MemberVO mem = null;
		try{
			con = DriverManager.getConnection(URL,USER,PASS);
			ps = con.prepareStatement(CHECKLOGIN);
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()){
				mem = new MemberVO();
				mem.setMem_account(rs.getString("MEM_ACCOUNT"));
				mem.setMem_password(rs.getString("MEM_PASSWORD"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return mem;
	}

	@Override
	public void memberRegister(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMember(String mem_account) {
		// TODO Auto-generated method stub
		return false;
	}

	public MemberVO memberFindByPK(String mem_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> blurSearch(String mem_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void memIntro(MemberVO member) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void memModify(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDeposit(String mem_no, Integer delDeposit, Connection con) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRecGift(String mem_no, Integer addRecGift, Connection con) {
		// TODO Auto-generated method stub
		
	}


	
}
