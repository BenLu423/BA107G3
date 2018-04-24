package com.member.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.ssi.ByteArrayServletOutputStream;

import com.giftDiscount.model.GiftDiscountVO;

import oracle.jdbc.proxy.annotation.Pre;



public class MemberDAO implements MemberDAO_interface{

	/*新增測試*/
	static final String INSERT = "INSERT INTO MEMBER(MEM_NO, MEM_ACCOUNT, MEM_PASSWORD, MEM_JOIN_TIME, MEM_NAME, MEM_GENDER, MEM_BIRTHDAY, MEM_COUNTY, MEM_DEPOSIT, MEM_CONTACT, MEM_EMOTION, MEM_BONUS, MEM_BLOODTYPE, MEM_HEIGHT, MEM_WEIGHT, MEM_INTEREST, MEM_INTRO, MEM_ONLINE, MEM_LONGITUDE, MEM_LATITUDE, MEM_PHONE, MEM_MAIL, MEM_PHOTO, MEM_PROHIBIT, MEM_SETNOTIFY, MEM_TIMENOTIFY) "
			+ "VALUES ('M'||LPAD(to_char(MEMBER_SEQ.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	/*修改測試*/
	static final String UPDATE = "UPDATE MEMBER SET MEM_ACCOUNT = ?, MEM_PASSWORD = ? WHERE MEM_NO = ?";
	
	/*單筆查詢*/
	static final String GETONE = "SELECT * FROM MEMBER WHERE MEM_NO = ?";
	/*查詢全部*/
	static final String GETALL = "SELECT * FROM MEMBER";
	/*查詢帳號是否存在*/
	static final String ISMEMEXSIT = "SELECT MEM_ACCOUNT FROM MEMBER WHERE MEM_ACCOUNT = ?";
	/*會員登入*/
	static final String CHECKLOGIN = "SELECT * FROM MEMBER WHERE MEM_ACCOUNT = ? AND MEM_PASSWORD = ?";
	/*會員註冊*/
	static final String REGISTER = "INSERT INTO MEMBER(MEM_NO, MEM_ACCOUNT, MEM_PASSWORD, MEM_NAME, MEM_GENDER, MEM_BIRTHDAY, MEM_COUNTY, MEM_CONTACT, MEM_EMOTION, MEM_BLOODTYPE, MEM_HEIGHT, MEM_WEIGHT)"
			+ "VALUES ('M'||LPAD(to_char(MEMBER_SEQ.NEXTVAL),3,'0'),? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
	/*測試處理圖片*/
	static final String GETPIC = "SELECT MEM_PHOTO FROM MEMBER WHERE MEM_NO = ?";
	/*修改密碼*/
	static final String UPDATEPASS = "UPDATE MEMBER SET MEM_PASSWORD = ? WHERE MEM_NO = ?";
	/*修改自我介紹*/
	static final String UPDATEINTRO ="UPDATE MEMBER SET MEM_INTRO = ? WHERE MEM_NO = ?";
	/*修改會員資料*/
	static final String UPDATAMEMBERDATA = "UPDATE MEMBER SET MEM_NAME = ?, MEM_PHONE = ?, MEM_MAIL = ?, MEM_HEIGHT = ?, MEM_WEIGHT = ?, MEM_EMOTION = ?, MEM_CONTACT = ?, MEM_INTEREST = ? WHERE MEM_NO = ?";
//	static final String UPDATAMEMBERDATA = "UPDATE MEMBER SET MEM_NAME = ?, MEM_PHONE = ?, MEM_MAIL = ?, MEM_HEIGHT = ? WHERE MEM_NO = ?";
	/*新增大頭貼*/
	static final String INSERTPHOTO = "UPDATE MEMBER SET MEM_PHOTO = ? WHERE MEM_NO = ?";
	/*基本查詢*/
	static final String BLURSEARCH = "SELECT * FROM MEMBER WHERE MEM_NAME LIKE ?";
	/*查詢會員是否為黑名單*/
	static final String ISBAN ="SELECT MEM_PROHIBIT FROM MEMBER WHERE MEM_ACCOUNT = ? AND MEM_PASSWORD = ?";
	/*封鎖會員*/
	static final String BANBANBAN = "UPDATE MEMBER SET MEM_PROHIBIT = ? WHERE MEM_NO = ?";
	
	static final String UPDATE_DEPOSIT_STMT  = "UPDATE MEMBER SET MEM_DEPOSIT=? WHERE MEM_NO=? ";
	static final String UPDATE_REC_GIFT_STMT = "UPDATE MEMBER SET MEM_RECEIVE_GIFT=? WHERE MEM_NO=? ";

	/********驅動載入********/									
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/BA107G3");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	/********新增資料********/
	@Override
	public void memberAdd(MemberVO member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
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

	/*測試處理圖片*/
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
	

	
	/********修改資料********/
	@Override
	public void memberUpdate(MemberVO member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
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
	/********單筆查詢********/
	@Override
	public MemberVO getOne(String mem_no) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvos = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETONE);
			ps.setString(1, mem_no);
			
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
				mvos.setMem_time_notify(rs.getDate("MEM_TIMENOTIFY"));
				mvos.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));
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
	/********查詢全部********/
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvos = null;
		try {
			con = ds.getConnection();
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
				mvos.setMem_time_notify(rs.getDate("MEM_TIMENOTIFY"));
				mvos.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));
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
	
	
	
	/********確認登入********/
	public MemberVO checkLogin(String mem_account,String mem_password){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mem = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(CHECKLOGIN);
			ps.setString(1, mem_account);
			ps.setString(2, mem_password);
			rs = ps.executeQuery();
			while(rs.next()){
				mem = new MemberVO();
				mem.setMem_no(rs.getString("MEM_NO"));
				mem.setMem_account(rs.getString("MEM_ACCOUNT"));
				mem.setMem_password(rs.getString("MEM_PASSWORD"));
				mem.setMem_join_time(rs.getDate("MEM_JOIN_TIME"));
				mem.setMem_name(rs.getString("MEM_NAME"));
				mem.setMem_gender(rs.getString("MEM_GENDER"));
				mem.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
				mem.setMem_county(rs.getString("MEM_COUNTY"));
				mem.setMem_deposit(rs.getInt("MEM_DEPOSIT"));
				mem.setMem_contact(rs.getString("MEM_CONTACT"));
				mem.setMem_emotion(rs.getString("MEM_EMOTION"));
				mem.setMem_bonus(rs.getInt("MEM_BONUS"));
				mem.setMem_bloodtype(rs.getString("MEM_BLOODTYPE"));
				mem.setMem_height(rs.getInt("MEM_HEIGHT"));
				mem.setMem_weight(rs.getInt("MEM_WEIGHT"));
				mem.setMem_interest(rs.getString("MEM_INTEREST"));
				mem.setMem_intro(rs.getString("MEM_INTRO"));
				mem.setMem_online(rs.getString("MEM_ONLINE"));
				mem.setMem_longitude(rs.getDouble("MEM_LONGITUDE"));
				mem.setMem_latitude(rs.getDouble("MEM_LATITUDE"));
				mem.setMem_phone(rs.getString("MEM_PHONE"));
				mem.setMem_mail(rs.getString("MEM_MAIL"));
				mem.setMem_photo(rs.getBytes("MEM_PHOTO"));
				mem.setMem_prohibit(rs.getString("MEM_PROHIBIT"));
				mem.setMem_set_notify(rs.getString("MEM_SETNOTIFY"));
				mem.setMem_time_notify(rs.getDate("MEM_TIMENOTIFY"));	
				mem.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));
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

	
	
	
	
	/********會員註冊********/
	public void memberRegister(MemberVO member){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = ds.getConnection();
//			con.setAutoCommit(false);
			ps = con.prepareStatement(REGISTER);
			ps.setString(1, member.getMem_account());
			ps.setString(2, member.getMem_password());
			ps.setString(3, member.getMem_name());
			ps.setString(4, member.getMem_gender());
			ps.setDate(5, member.getMem_birthday());
			ps.setString(6, member.getMem_county());
			ps.setString(7, member.getMem_contact());
			ps.setString(8, member.getMem_emotion());
			ps.setString(9, member.getMem_bloodtype());
			ps.setInt(10, member.getMem_height());
			ps.setInt(11, member.getMem_weight());
			ps.executeUpdate();
//			con.commit();
//			con.setAutoCommit(true);	
		}catch(SQLException e){
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
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
	}
	
	
	
	
	
	/********是否為會員********/
	public boolean isMember(String mem_account){
		boolean ismem = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(ISMEMEXSIT);
			ps.setString(1, mem_account);
			rs = ps.executeQuery();
			while(rs.next()){
				
				ismem = true;
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
		return ismem;
	}
	
	/********會員修改密碼********/
	public void memUpdatePassword(String mem_password,String mem_no){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATEPASS);
			ps.setString(1, mem_password);
			ps.setString(2, mem_no);
			ps.executeQuery();
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
		
		
	}
	/********修改自我介紹********/
	public void memIntro(MemberVO member){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATEINTRO);
			ps.setString(1, member.getMem_intro());
			ps.setString(2, member.getMem_no());
			ps.executeUpdate();
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
	}
	/*******修改個人資料********/
	public void memModify(MemberVO member){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATAMEMBERDATA);
			ps.setString(1, member.getMem_name());
			System.out.println("member.getMem_name() = "+member.getMem_name());
			ps.setString(2, member.getMem_phone());
			System.out.println("member.getMem_phone() = "+ member.getMem_phone());
			ps.setString(3, member.getMem_mail());
			ps.setInt(4, member.getMem_height());
			ps.setInt(5, member.getMem_weight());
			ps.setString(6, member.getMem_emotion());
			ps.setString(7, member.getMem_contact());
			ps.setString(8, member.getMem_interest());
			ps.setString(9, member.getMem_no());
			System.out.println("member.getMem_interest() =" + member.getMem_interest());
			ps.executeUpdate();
			System.out.println("修改成功!");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
			}
		}
	}
	/***********上傳大頭貼***********/
	public void memInsertPic(MemberVO member){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(INSERTPHOTO);
			ps.setBytes(1, member.getMem_photo());
			ps.setString(2, member.getMem_no());
			ps.executeUpdate();
			System.out.println("ok");
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
	}
	
	/********基本搜尋(模糊查詢)********/
	public List<MemberVO> blurSearch(String mem_name){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO memvo = null;
		List<MemberVO> memberList = new ArrayList<MemberVO>(); 
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(BLURSEARCH);
			ps.setString(1, "%"+mem_name+"%");
			System.out.println(BLURSEARCH);
			System.out.println(mem_name);
			rs = ps.executeQuery();
			while(rs.next()){
				memvo = new MemberVO();
				memvo.setMem_no(rs.getString("MEM_NO"));
				memvo.setMem_account(rs.getString("MEM_ACCOUNT"));
				memvo.setMem_password(rs.getString("MEM_PASSWORD"));
				memvo.setMem_join_time(rs.getDate("MEM_JOIN_TIME"));
				memvo.setMem_name(rs.getString("MEM_NAME"));
				memvo.setMem_gender(rs.getString("MEM_GENDER"));
				memvo.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
				memvo.setMem_county(rs.getString("MEM_COUNTY"));
				memvo.setMem_deposit(rs.getInt("MEM_DEPOSIT"));
				memvo.setMem_contact(rs.getString("MEM_CONTACT"));
				memvo.setMem_emotion(rs.getString("MEM_EMOTION"));
				memvo.setMem_bonus(rs.getInt("MEM_BONUS"));
				memvo.setMem_bloodtype(rs.getString("MEM_BLOODTYPE"));
				memvo.setMem_height(rs.getInt("MEM_HEIGHT"));
				memvo.setMem_weight(rs.getInt("MEM_WEIGHT"));
				memvo.setMem_interest(rs.getString("MEM_INTEREST"));
				memvo.setMem_intro(rs.getString("MEM_INTRO"));
				memvo.setMem_online(rs.getString("MEM_ONLINE"));
				memvo.setMem_longitude(rs.getDouble("MEM_LONGITUDE"));
				memvo.setMem_latitude(rs.getDouble("MEM_LATITUDE"));
				memvo.setMem_phone(rs.getString("MEM_PHONE"));
				memvo.setMem_mail(rs.getString("MEM_MAIL"));
				memvo.setMem_photo(rs.getBytes("MEM_PHOTO"));
				memvo.setMem_prohibit(rs.getString("MEM_PROHIBIT"));
				memvo.setMem_set_notify(rs.getString("MEM_SETNOTIFY"));
				memvo.setMem_time_notify(rs.getDate("MEM_TIMENOTIFY"));
				memvo.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));
				memberList.add(memvo);
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
		
		return memberList;
	}
	/********進階搜尋(複合查詢)********/
	public List<MemberVO> getAll(Map<String,String[]> map){
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memvo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			String preciseSQL = "SELECT * FROM MEMBER"
			+  get_WhereCondition(map);
			System.out.println("preciseSQL = " + preciseSQL);
			ps = con.prepareStatement(preciseSQL);
			rs = ps.executeQuery();
			while(rs.next()){
				memvo= new MemberVO();
				memvo.setMem_no(rs.getString("MEM_NO"));
				memvo.setMem_account(rs.getString("MEM_ACCOUNT"));
				memvo.setMem_password(rs.getString("MEM_PASSWORD"));
				memvo.setMem_join_time(rs.getDate("MEM_JOIN_TIME"));
				memvo.setMem_name(rs.getString("MEM_NAME"));
				memvo.setMem_gender(rs.getString("MEM_GENDER"));
				memvo.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
				memvo.setMem_county(rs.getString("MEM_COUNTY"));
				memvo.setMem_deposit(rs.getInt("MEM_DEPOSIT"));
				memvo.setMem_contact(rs.getString("MEM_CONTACT"));
				memvo.setMem_emotion(rs.getString("MEM_EMOTION"));
				memvo.setMem_bonus(rs.getInt("MEM_BONUS"));
				memvo.setMem_bloodtype(rs.getString("MEM_BLOODTYPE"));
				memvo.setMem_height(rs.getInt("MEM_HEIGHT"));
				memvo.setMem_weight(rs.getInt("MEM_WEIGHT"));
				memvo.setMem_interest(rs.getString("MEM_INTEREST"));
				memvo.setMem_intro(rs.getString("MEM_INTRO"));
				memvo.setMem_online(rs.getString("MEM_ONLINE"));
				memvo.setMem_longitude(rs.getDouble("MEM_LONGITUDE"));
				memvo.setMem_latitude(rs.getDouble("MEM_LATITUDE"));
				memvo.setMem_phone(rs.getString("MEM_PHONE"));
				memvo.setMem_mail(rs.getString("MEM_MAIL"));
				memvo.setMem_photo(rs.getBytes("MEM_PHOTO"));
				memvo.setMem_prohibit(rs.getString("MEM_PROHIBIT"));
				memvo.setMem_set_notify(rs.getString("MEM_SETNOTIFY"));
				memvo.setMem_time_notify(rs.getDate("MEM_TIMENOTIFY"));
				memvo.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));
				list.add(memvo);
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
		
		return list;
	}
	
	public static String get_aCondition_For_Oracle(String columnName,String value){
		String aCondition = null;
		if("mem_gender".equals(columnName) || "mem_county".equals(columnName)){
			aCondition = columnName + " = " +"'" + value + "'";
			System.out.println(1);
		}else if("mem_age".equals(columnName)){
			System.out.println(2);
			columnName = "mem_birthday";
			int mem_age = Integer.valueOf(value);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String str_mem_date = df.format(new Date());
			Integer int_mem_date = Integer.valueOf(str_mem_date);

			
			if(mem_age == 20){
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd') >'" + (int_mem_date - 20)+"-01-01" + "'";
			}else if(mem_age == 30){
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd') >'" + (int_mem_date - 30)+"-01-01" + "'"
							  +" AND to_char(" + columnName + ",'yyyy-mm-dd') <'" + (int_mem_date - 21)+"-01-01" + "'";
			}else if(mem_age == 40){
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd') >'" + (int_mem_date - 40)+"-01-01" + "'"
						  +" AND to_char(" + columnName + ",'yyyy-mm-dd') <'" + (int_mem_date - 31)+"-01-01" + "'";
			}else if(mem_age == 50){
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd') <'" + (int_mem_date - 40)+"-01-01" + "'";
			}else{
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd') >'" + (int_mem_date)+"-01-01" + "'";
			}
			
			
		}else if(columnName.startsWith("mem_height")){
			String str_h1 = null;
			String str_h2 = null;
			if("mem_height1".equals(columnName)){
				str_h1 = columnName;
				String col = columnName.substring(0,10);
				aCondition = col + " >= " + value;
			}else if("mem_height2".equals(columnName)){
				String col = columnName.substring(0,10);
				aCondition = col + " <= " + value;
			}
			
		}else if(columnName.startsWith("mem_weight")){
				String str_w1 = null;
				String str_w2 = null;
			if("mem_weight1".equals(columnName)){
				str_w1 = columnName;
				String col = columnName.substring(0,10);
				aCondition = col + " >= " + value;
			}else if("mem_weight2".equals(columnName)){
				str_w2 = columnName;
				String col = columnName.substring(0,10);
				aCondition = col + " <= " + value;
			}
			
		}else if(columnName.startsWith("mem_emotion") || columnName.startsWith("mem_contact")){
			String col = columnName.substring(0, 11);
			aCondition = col + " = " +"'" + value + "'";
		}

		return aCondition.toUpperCase() + " ";
	}
	
	
	public static String get_WhereCondition(Map<String,String[]> map){
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		int mem_emotion_sum = 0;
		int mem_contact_sum = 0;
		int mem_emotion_count = 0;
		int mem_contact_count = 0;
		int judge_where_emotion_key = 0;
		int judge_where_contact_key = 0;
		int c = 2;
		int d = 2;
	

		
		
		mem_emotion_count = getStateCount(keys, mem_emotion_count,"mem_emotion");
		mem_contact_count = getStateCount(keys, mem_contact_count,"mem_contact");
	

		System.out.println("mem_emotion_count = " + mem_emotion_count);
		System.out.println("mem_contact_count = " + mem_contact_count);
		
		for(String key :keys){
		try{

			String value = map.get(key)[0];
			if(value != null && value.trim().length() != 0 && !"action".equals(key)){
			count++;
			String aCondition = get_aCondition_For_Oracle(key,value.trim());

				if("mem_gender".equals(key) || "mem_county".equals(key) || "mem_age".equals(key)){
					mem_emotion_sum = 0;
					mem_contact_sum = 0;
				}else if("mem_height".equals(key.substring(0,10)) || "mem_weight".equals(key.substring(0,10))){
					mem_emotion_sum = 0;
					mem_contact_sum = 0;
				}else if("mem_emotion".equals(key.substring(0, 11))){
					mem_contact_sum = 0;
					mem_emotion_sum++;					
				}else if("mem_contact".equals(key.substring(0,11))){
					mem_emotion_sum = 0;
					mem_contact_sum++;
				}
				
				if(count == 1){
					whereCondition.append(" WHERE " + aCondition);
					if(key.startsWith("mem_emotion")){
						judge_where_emotion_key++;	
					}else if(key.startsWith("mem_contact")){
						judge_where_contact_key++;
					}
				}else if(mem_emotion_sum > 1){						
						if(c == mem_emotion_count && judge_where_emotion_key == 0 && mem_emotion_count != 1){	
							whereCondition.append("OR " + aCondition + ")");
							c = 2;
						}else{
							whereCondition.append("OR " + aCondition);
							c++;
						}
						System.out.println("c = "+c);
				}else if(mem_contact_sum > 1){
					if(d == mem_contact_count && judge_where_contact_key == 0){	
						whereCondition.append("OR " + aCondition + ")");
						d = 2;
					}else{
						whereCondition.append("OR " + aCondition);
						d++;
					}

				}else{
					if(key.length() >= 11){
							if(("mem_emotion".equals(key.substring(0, 11))) && mem_emotion_count > 1){
								whereCondition.append("AND " + "("+ aCondition);	
							}else if(("mem_emotion".equals(key.substring(0, 11))) && mem_emotion_count == 1){
								whereCondition.append("AND " + "("+ aCondition + ")");
							}else if("mem_contact".equals(key.substring(0,11)) && mem_contact_count > 1){
								whereCondition.append("AND " + "("+ aCondition);
							}else if("mem_contact".equals(key.substring(0,11)) && mem_contact_count == 1){
								whereCondition.append("AND " + "("+ aCondition + ")");
							}else{
								whereCondition.append("AND " + aCondition);
							}
					}else{
							whereCondition.append("AND " + aCondition);	
						}
					
				}
				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		System.out.println();
		return whereCondition.toString();
	}
	
	public static int getStateCount(Set<String> keys,int count, String colname){
		count = 0;
			for(String key: keys){
				try{
					if(colname.equals(key.substring(0,11))){
						count++;
					}
				}catch(StringIndexOutOfBoundsException e){
				}
			}
		return count;
	}
	
	/*會員是否為黑單*/
	public Boolean memIsBan(String mem_account,String mem_password){
		Boolean isBan = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(ISBAN);
			ps.setString(1, mem_account);
			ps.setString(2, mem_password);
			rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getString("MEM_PROHIBIT").equals("是")){
					isBan = true;
				}
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
		return isBan;
	}
	/*封鎖會員*/
	public void memBan(String mem_no, String mem_prohibit){
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(BANBANBAN);
			ps.setString(1, mem_prohibit);
			ps.setString(2, mem_no);
			ps.executeUpdate();
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
	
	//紹永[禮物訂單使用]
	@Override
	public void updateDeposit(String mem_no, Integer delDeposit, Connection con) {
		/* con從GiftOrderDAO的insert()傳遞過來  */
		PreparedStatement pstmt = null;
		MemberVO memberVO = getOne(mem_no);
		Integer oriDeposit = memberVO.getMem_deposit();
		try {
			pstmt = con.prepareStatement(UPDATE_DEPOSIT_STMT);
			pstmt.setInt(1, oriDeposit-delDeposit);
			pstmt.setString(2, mem_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if(con != null){
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-MemberDAO updateDeposit時");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured." + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateRecGift(String mem_no, Integer addRecGift, Connection con) {
		/* con從GiftOrderDAO的insert()傳遞過來  */
		PreparedStatement pstmt = null;
		MemberVO memberVO = getOne(mem_no);
		Integer oriRecGift = memberVO.getMem_receive_gift();
		try {
			pstmt = con.prepareStatement(UPDATE_REC_GIFT_STMT);
			pstmt.setInt(1, oriRecGift+addRecGift);
			pstmt.setString(2, mem_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if(con != null){
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-GiftReceiveDAO updateDeposit時");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured." + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
}
