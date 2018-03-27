package com.diary_message_report.model;

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

public class DiaryMessageReportDAO implements DiaryMessageReportDAO_interface {
	
	
	private static final String INSERT = "INSERT INTO DIARY_MESSAGE_REPORT(MEM_NO, DIAM_NO, DIAMR_REASON, DIAMR_CNT) VALUES(?,?,?,?)";
	private static final String UPDATE = "UPDATE DIARY_MESSAGE_REPORT SET DIAMR_PERMIT = ? WHERE MEM_NO = ? AND DIAM_NO = ?";
	private static final String SEARCH = "SELECT * FROM DIARY_MESSAGE_REPORT WHERE DIAMR_PERMIT = ?";
	private static final String SEARCH2 = "SELECT * FROM DIARY_MESSAGE_REPORT";
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
	public void diaryMessageReportVOAdd(DiaryMessageReportVO dmrrep) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			ps.setString(1, dmrrep.getMem_no());
			ps.setString(2, dmrrep.getDiam_no());
			ps.setString(3, dmrrep.getDiamr_reason());
			ps.setString(4, dmrrep.getDiamr_cnt());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void diaryMessageReportVOUpdate(DiaryMessageReportVO dmrrep) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);
			ps.setString(1, dmrrep.getDiamr_permit());
			ps.setString(2, dmrrep.getMem_no());
			ps.setString(3, dmrrep.getDiam_no());

			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<DiaryMessageReportVO> findStatement(String dmrrep) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DiaryMessageReportVO dmrvo = null;
		List<DiaryMessageReportVO> list = new ArrayList<>();
			
		try{
		con = ds.getConnection();
		ps = con.prepareStatement(SEARCH);
		ps.setString(1, dmrrep);
		rs = ps.executeQuery();
		while(rs.next()){
			dmrvo = new DiaryMessageReportVO();
			dmrvo.setMem_no(rs.getString("MEM_NO"));
			dmrvo.setDiam_no(rs.getString("DIAM_NO"));
			dmrvo.setDiamr_reason(rs.getString("DIAMR_REASON"));
			dmrvo.setDiamr_cnt(rs.getString("DIAMR_CNT"));
			dmrvo.setDiamr_time(rs.getDate("DIAMR_TIME"));
			dmrvo.setDiamr_permit(rs.getString("DIAMR_PERMIT"));
			list.add(dmrvo);
		}
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return list;
	}

	@Override
	public List<DiaryMessageReportVO> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DiaryMessageReportVO dmrvo = null;
		List<DiaryMessageReportVO> list = new ArrayList<>();
			
		try{
		con = ds.getConnection();
		ps = con.prepareStatement(SEARCH2);
		rs = ps.executeQuery();
		while(rs.next()){
			dmrvo = new DiaryMessageReportVO();
			dmrvo.setMem_no(rs.getString("MEM_NO"));
			dmrvo.setDiam_no(rs.getString("DIAM_NO"));
			dmrvo.setDiamr_reason(rs.getString("DIAMR_REASON"));
			dmrvo.setDiamr_cnt(rs.getString("DIAMR_CNT"));
			dmrvo.setDiamr_time(rs.getDate("DIAMR_TIME"));
			dmrvo.setDiamr_permit(rs.getString("DIAMR_PERMIT"));
			list.add(dmrvo);
		}
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return list;
	}
	
}
