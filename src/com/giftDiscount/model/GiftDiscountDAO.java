package com.giftDiscount.model;

import java.sql.*;
import java.util.*;

import javax.management.RuntimeErrorException;
import javax.naming.*;
import javax.sql.DataSource;

import com.gift.model.GiftVO;
import com.gift.model.Gift_CompositeQuery;

public class GiftDiscountDAO implements GiftDiscountDAO_interface{
	
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO GIFT_DISCOUNT(GIFTD_NO,GIFT_NO,GIFTD_START,GIFTD_END,GIFTD_PERCENT,GIFTD_AMOUNT) VALUES ('GD'||LPAD(to_char(GIFT_DISCOUNT_SEQ.NEXTVAL),3,'0'),?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE GIFT_DISCOUNT SET GIFT_NO=?,GIFTD_START=?,GIFTD_END=?,GIFTD_PERCENT=?,GIFTD_AMOUNT=? WHERE GIFTD_NO=?";
	private static final String DELETE_STMT = "DELETE FROM GIFT_DISCOUNT WHERE GIFTD_NO=?";
	private static final String CURRENT_VALID_GIFT_STMT = "SELECT * FROM GIFT_DISCOUNT WHERE GIFT_NO=? AND (CURRENT_TIMESTAMP BETWEEN GIFTD_START AND GIFTD_END)";
	private static final String FIND_BY_PK_STMT = "SELECT * FROM GIFT_DISCOUNT WHERE GIFTD_NO=?";
	private static final String GET_ALL_STMT 	= "SELECT * FROM GIFT_DISCOUNT WHERE (CURRENT_TIMESTAMP BETWEEN GIFTD_START AND GIFTD_END) ORDER BY GIFTD_NO DESC";
	
	@Override
	public void insert(GiftDiscountVO giftDiscountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, giftDiscountVO.getGift_no());
			pstmt.setTimestamp(2, giftDiscountVO.getGiftd_start());
			pstmt.setTimestamp(3, giftDiscountVO.getGiftd_end());
			pstmt.setDouble(4, giftDiscountVO.getGiftd_percent());
			pstmt.setInt(5, giftDiscountVO.getGiftd_amount());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public void update(GiftDiscountVO giftDiscountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, giftDiscountVO.getGift_no());
			pstmt.setTimestamp(2, giftDiscountVO.getGiftd_start());
			pstmt.setTimestamp(3, giftDiscountVO.getGiftd_end());
			pstmt.setDouble(4, giftDiscountVO.getGiftd_percent());
			pstmt.setInt(5, giftDiscountVO.getGiftd_amount());
			pstmt.setString(6, giftDiscountVO.getGiftd_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public void delete(String giftd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, giftd_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public GiftDiscountVO getCurrentValidGift(String gift_no) {
		//尋找當前禮物是否存在於限時優惠中
		GiftDiscountVO giftDiscountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CURRENT_VALID_GIFT_STMT);
			pstmt.setString(1, gift_no);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				giftDiscountVO = new GiftDiscountVO();
				giftDiscountVO.setGiftd_no(rs.getString("giftd_no"));
				giftDiscountVO.setGift_no(rs.getString("gift_no"));
				giftDiscountVO.setGiftd_start(rs.getTimestamp("giftd_start"));
				giftDiscountVO.setGiftd_end(rs.getTimestamp("giftd_end"));
				giftDiscountVO.setGiftd_percent(rs.getDouble("giftd_percent"));
				giftDiscountVO.setGiftd_amount(rs.getInt("giftd_amount"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
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
		return giftDiscountVO;
	}

	@Override
	public GiftDiscountVO getByPrimaryKey(String giftd_no) {
		GiftDiscountVO giftDiscountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK_STMT);
			pstmt.setString(1, giftd_no);
			
			rs = pstmt.executeQuery();
			rs.next();
			giftDiscountVO = new GiftDiscountVO();
			giftDiscountVO.setGiftd_no(rs.getString("giftd_no"));
			giftDiscountVO.setGift_no(rs.getString("gift_no"));
			giftDiscountVO.setGiftd_start(rs.getTimestamp("giftd_start"));
			giftDiscountVO.setGiftd_end(rs.getTimestamp("giftd_end"));
			giftDiscountVO.setGiftd_percent(rs.getDouble("giftd_percent"));
			giftDiscountVO.setGiftd_amount(rs.getInt("giftd_amount"));
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
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
		return giftDiscountVO;
	}

	@Override
	public List<GiftDiscountVO> getAll() {
		List<GiftDiscountVO> list = new ArrayList<>();
		GiftDiscountVO giftDiscountVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				giftDiscountVO = new GiftDiscountVO();
				giftDiscountVO.setGiftd_no(rs.getString("giftd_no"));
				giftDiscountVO.setGift_no(rs.getString("gift_no"));
				giftDiscountVO.setGiftd_start(rs.getTimestamp("giftd_start"));
				giftDiscountVO.setGiftd_end(rs.getTimestamp("giftd_end"));
				giftDiscountVO.setGiftd_percent(rs.getDouble("giftd_percent"));
				giftDiscountVO.setGiftd_amount(rs.getInt("giftd_amount"));
				list.add(giftDiscountVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally{
			if(rs != null){
				try {
						rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				};
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	@Override
	public List<GiftDiscountVO> getAll(Map<String, String[]> map) {
		List<GiftDiscountVO> list = new ArrayList<>();
		GiftDiscountVO giftDiscountVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			String finalSQL = GiftDiscount_CompositeQuery.get_WhereCondition(map);
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				giftDiscountVO = new GiftDiscountVO();
				giftDiscountVO.setGiftd_no(rs.getString("giftd_no"));
				giftDiscountVO.setGift_no(rs.getString("gift_no"));
				giftDiscountVO.setGiftd_start(rs.getTimestamp("giftd_start"));
				giftDiscountVO.setGiftd_end(rs.getTimestamp("giftd_end"));
				giftDiscountVO.setGiftd_percent(rs.getDouble("giftd_percent"));
				giftDiscountVO.setGiftd_amount(rs.getInt("giftd_amount"));
				list.add(giftDiscountVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally{
			if(rs != null){
				try {
						rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				};
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
}
