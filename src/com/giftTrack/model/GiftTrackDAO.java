package com.giftTrack.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gift.model.GiftService;

public class GiftTrackDAO implements GiftTrackDAO_interface{
	
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO GIFT_TRACK(MEM_NO,GIFT_NO,GIFTT_TIME) VALUES (?,?,CURRENT_TIMESTAMP)";
	private static final String DELETE_STMT = "DELETE FROM GIFT_TRACK WHERE MEM_NO=? AND GIFT_NO=?";
	private static final String GET_BY_PK_STMT = "SELECT * FROM GIFT_TRACK WHERE MEM_NO=? AND GIFT_NO=?";
	private static final String GET_ALL_STMT   = "SELECT * FROM GIFT_TRACK ORDER BY GIFTT_TIME DESC";
	private static final String GET_BY_MEMNO   = "SELECT * FROM GIFT_TRACK WHERE MEM_NO=? ORDER BY GIFTT_TIME DESC";
	private static final String GET_BY_GIFTNO  = "SELECT * FROM GIFT_TRACK WHERE GIFT_NO=? ORDER BY GIFTT_TIME DESC";
	
	
	@Override
	public void insert(GiftTrackVO giftTrackVO) {
		/* * * * * * * * * * * * * * * * * * * * * * *
		 * 此新增追蹤會依以下順序進行，若有失敗則rollback		 * 
		 * ------------------------------------------* 
		 * 1. 新增追蹤明細[GIFT_TRACK]					 * 
		 * 2. 新增禮物追蹤數量[GIFT]						 * 
		 * * * * * * * * * * * * * * * * * * * * * * */		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			//1. 新增追蹤明細[GIFT_TRACK]
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, giftTrackVO.getMem_no());
			pstmt.setString(2, giftTrackVO.getGift_no());
			pstmt.executeUpdate();
			
			//2. 新增禮物追蹤數量[GIFT]
			GiftService giftSvc = new GiftService();
			giftSvc.updateGiftTrack(giftTrackVO.getGift_no(), 1, con);
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		} finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String mem_no, String gift_no) {
		/* * * * * * * * * * * * * * * * * * * * * * *
		 * 此刪除追蹤會依以下順序進行，若有失敗則rollback		 * 
		 * ------------------------------------------* 
		 * 1. 刪除追蹤明細[GIFT_TRACK]					 * 
		 * 2. 刪除禮物追蹤數量[GIFT]						 * 
		 * * * * * * * * * * * * * * * * * * * * * * */	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			//1. 刪除追蹤明細[GIFT_TRACK]
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, mem_no);
			pstmt.setString(2, gift_no);
			pstmt.executeUpdate();
			
			//2. 刪除禮物追蹤數量[GIFT]
			GiftService giftSvc = new GiftService();
			giftSvc.updateGiftTrack(gift_no, -1, con);
			
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		} finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public GiftTrackVO getByPrimaryKey(String mem_no, String gift_no) {
		GiftTrackVO giftTrackVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PK_STMT);
			pstmt.setString(1, mem_no);
			pstmt.setString(2, gift_no);
			rs = pstmt.executeQuery();
			rs.next();
			giftTrackVO = new GiftTrackVO();
			giftTrackVO.setMem_no(rs.getString("mem_no"));
			giftTrackVO.setGift_no(rs.getString("gift_no"));
			giftTrackVO.setGiftt_time(rs.getTimestamp("giftt_time"));
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return giftTrackVO;
	}

	@Override
	public List<GiftTrackVO> getAll() {
		List<GiftTrackVO> list = new ArrayList<>();
		GiftTrackVO giftTrackVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				giftTrackVO = new GiftTrackVO();
				giftTrackVO.setMem_no(rs.getString("mem_no"));
				giftTrackVO.setGift_no(rs.getString("gift_no"));
				giftTrackVO.setGiftt_time(rs.getTimestamp("giftt_time"));
				list.add(giftTrackVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<GiftTrackVO> getListByMemNo(String mem_no) {
		List<GiftTrackVO> list = new ArrayList<>();
		GiftTrackVO giftTrackVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMNO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				giftTrackVO = new GiftTrackVO();
				giftTrackVO.setMem_no(rs.getString("mem_no"));
				giftTrackVO.setGift_no(rs.getString("gift_no"));
				giftTrackVO.setGiftt_time(rs.getTimestamp("giftt_time"));
				list.add(giftTrackVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<GiftTrackVO> getListByGiftNo(String gift_no) {
		List<GiftTrackVO> list = new ArrayList<>();
		GiftTrackVO giftTrackVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_GIFTNO);
			pstmt.setString(1, gift_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				giftTrackVO = new GiftTrackVO();
				giftTrackVO.setMem_no(rs.getString("mem_no"));
				giftTrackVO.setGift_no(rs.getString("gift_no"));
				giftTrackVO.setGiftt_time(rs.getTimestamp("giftt_time"));
				list.add(giftTrackVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
