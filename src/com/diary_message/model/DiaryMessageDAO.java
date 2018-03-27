package com.diary_message.model;

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

public class DiaryMessageDAO implements DiaryMessageDAO_interface {

	private static final String INSERT = "INSERT INTO DIARY_MESSAGE (DIAM_NO, MEM_NO, DIA_NO, DIAM_CNT, DIAM_LIKE,DIAM_RESTIME, DIAM_RESCNT, DIAM_RESLIKE)"
			+ "VALUES('DM'||LPAD(to_char(DIARY_MESSAGE_SEQ.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?, ?) ";
	private static final String UPDATE = "UPDATE DIARY_MESSAGE SET DIAM_CNT = ? WHERE DIAM_NO = ?";
	private static final String SEARCH = "SELECT * FROM DIARY_MESSAGE WHERE DIAM_NO = ?";
	private static final String SERACH2 = "SELECT * FROM DIARY_MESSAGE";
	
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/BA107G3");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	@Override
	public void DiaryMessageAdd(DiaryMessageVO diam) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			ps.setString(1, diam.getMem_no());
			ps.setString(2,diam.getDia_no());
			ps.setString(3, diam.getDiam_cnt());
			ps.setInt(4, diam.getDiam_like());
			ps.setDate(5, diam.getDiam_time());
			ps.setString(6, diam.getDiam_rescnt());
			ps.setInt(7, diam.getDiam_reslike());
			
			ps.executeQuery();
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

	@Override
	public void accountReportUpdate(DiaryMessageVO diam) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);
			ps.setString(1, diam.getDiam_cnt());
			ps.setString(2, diam.getDiam_no());
			ps.executeQuery();
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

	@Override
	public List<DiaryMessageVO> findStatement(String diam) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DiaryMessageVO> list = new ArrayList<>();
		DiaryMessageVO dmvo = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SEARCH);
			ps.setString(1, diam);
			rs = ps.executeQuery();
			while(rs.next()){
				dmvo = new DiaryMessageVO();
				dmvo.setDiam_no(rs.getString("DIAM_NO"));
				dmvo.setMem_no(rs.getString("MEM_NO"));
				dmvo.setDia_no(rs.getString("DIA_NO"));
				dmvo.setDiam_time(rs.getDate("DIAM_TIME"));
				dmvo.setDiam_cnt(rs.getString("DIAM_CNT"));
				dmvo.setDiam_like(rs.getInt("DIAM_LIKE"));
				dmvo.setDiam_restime(rs.getDate("DIAM_RESTIME"));
				dmvo.setDiam_rescnt(rs.getString("DIAM_RESCNT"));
				dmvo.setDiam_reslike(rs.getInt("DIAM_RESLIKE"));
				list.add(dmvo);
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
		return list;
	}

	@Override
	public List<DiaryMessageVO> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DiaryMessageVO> list = new ArrayList<>();
		DiaryMessageVO dmvo = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SERACH2);
			rs = ps.executeQuery();
			while(rs.next()){
				dmvo = new DiaryMessageVO();
				dmvo.setDiam_no(rs.getString("DIAM_NO"));
				dmvo.setMem_no(rs.getString("MEM_NO"));
				dmvo.setDiam_no(rs.getString("DIA_NO"));
				dmvo.setDiam_time(rs.getDate("DIAM_TIME"));
				dmvo.setDiam_cnt(rs.getString("DIAM_CNT"));
				dmvo.setDiam_like(rs.getInt("DIAM_LIKE"));
				dmvo.setDiam_restime(rs.getDate("DIAM_RESTIME"));
				dmvo.setDiam_cnt(rs.getString("DIAM_RESCNT"));
				dmvo.setDiam_reslike(rs.getInt("DIAM_RESLIKE"));
				list.add(dmvo);
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
		return list;
	}

}
