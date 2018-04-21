package com.giftOrderView.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gift.model.GiftVO;

public class GiftOrderViewDAO implements GiftOrderViewDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		}
	}

	private static final String GET_BY_PK_STMT = "SELECT * FROM GIFT_ORDER_VIEW WHERE GIFTR_NO=?";
	private static final String GET_ALL_STMT = "SELECT * FROM GIFT_ORDER_VIEW ORDER BY GIFTR_NO DESC";
	private static final String GET_ALL_BY_MEM_STMT = "SELECT * FROM GIFT_ORDER_VIEW WHERE MEM_NO_SELF=? AND (GIFTR_TIME BETWEEN ? AND ? ) ORDER BY GIFTR_NO DESC"; 
	private static final String GET_PIC_STMT = "SELECT * FROM GIFT_ORDER_VIEW WHERE GIFTR_NO=?";
	private static final String GET_ALL_BY_MEMSELF_STMT = "SELECT * FROM GIFT_ORDER_VIEW WHERE MEM_NO_SELF=? ORDER BY GIFTR_NO DESC";

	@Override
	public GiftOrderViewVO getByPrimaryKey(String giftr_no) {
		GiftOrderViewVO giftOrderViewVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PK_STMT);
			pstmt.setString(1, giftr_no);

			rs = pstmt.executeQuery();
			rs.next();
			giftOrderViewVO = new GiftOrderViewVO();
			giftOrderViewVO.setGiftr_no(rs.getString("giftr_no"));
			giftOrderViewVO.setMem_no_self(rs.getString("mem_no_self"));
			giftOrderViewVO.setMem_name_self(rs.getString("mem_name_self"));
			giftOrderViewVO.setMem_photo_self(rs.getBytes("mem_photo_self"));
			giftOrderViewVO.setMem_no_other(rs.getString("mem_no_other"));
			giftOrderViewVO.setMem_name_other(rs.getString("mem_name_other"));
			giftOrderViewVO.setMem_photo_other(rs.getBytes("mem_photo_other"));
			giftOrderViewVO.setGiftr_amount(rs.getInt("giftr_amount"));
			giftOrderViewVO.setGiftr_time(rs.getTimestamp("giftr_time"));
			giftOrderViewVO.setGifto_no(rs.getString("gifto_no"));
			giftOrderViewVO.setGifto_time(rs.getTimestamp("gifto_time"));
			giftOrderViewVO.setGifto_remark(rs.getString("gifto_remark"));
			giftOrderViewVO.setCoup_no(rs.getString("coup_no"));
			giftOrderViewVO.setCoup_name(rs.getString("coup_name"));
			giftOrderViewVO.setCoup_value(rs.getInt("coup_value"));
			giftOrderViewVO.setGiftod_no(rs.getString("giftod_no"));
			giftOrderViewVO.setGift_no(rs.getString("gift_no"));
			giftOrderViewVO.setGift_name(rs.getString("gift_name"));
			giftOrderViewVO.setGift_cnt(rs.getString("gift_cnt"));
			giftOrderViewVO.setGift_price(rs.getInt("gift_price"));
			giftOrderViewVO.setGift_pic(rs.getBytes("gift_pic"));
			giftOrderViewVO.setGiftod_amount(rs.getInt("giftod_amount"));
			giftOrderViewVO.setGiftod_money(rs.getInt("giftod_money"));
			giftOrderViewVO.setGiftod_inventory(rs.getInt("giftod_inventory"));
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return giftOrderViewVO;
	}

	@Override
	public List<GiftOrderViewVO> getAll() {
		List<GiftOrderViewVO> list = new ArrayList<>();
		GiftOrderViewVO giftOrderViewVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giftOrderViewVO = new GiftOrderViewVO();
				giftOrderViewVO.setGiftr_no(rs.getString("giftr_no"));
				giftOrderViewVO.setMem_no_self(rs.getString("mem_no_self"));
				giftOrderViewVO.setMem_name_self(rs.getString("mem_name_self"));
				giftOrderViewVO.setMem_photo_self(rs.getBytes("mem_photo_self"));
				giftOrderViewVO.setMem_no_other(rs.getString("mem_no_other"));
				giftOrderViewVO.setMem_name_other(rs.getString("mem_name_other"));
				giftOrderViewVO.setMem_photo_other(rs.getBytes("mem_photo_other"));
				giftOrderViewVO.setGiftr_amount(rs.getInt("giftr_amount"));
				giftOrderViewVO.setGiftr_time(rs.getTimestamp("giftr_time"));
				giftOrderViewVO.setGifto_no(rs.getString("gifto_no"));
				giftOrderViewVO.setGifto_time(rs.getTimestamp("gifto_time"));
				giftOrderViewVO.setGifto_remark(rs.getString("gifto_remark"));
				giftOrderViewVO.setCoup_no(rs.getString("coup_no"));
				giftOrderViewVO.setCoup_name(rs.getString("coup_name"));
				giftOrderViewVO.setCoup_value(rs.getInt("coup_value"));
				giftOrderViewVO.setGiftod_no(rs.getString("giftod_no"));
				giftOrderViewVO.setGift_no(rs.getString("gift_no"));
				giftOrderViewVO.setGift_name(rs.getString("gift_name"));
				giftOrderViewVO.setGift_cnt(rs.getString("gift_cnt"));
				giftOrderViewVO.setGift_price(rs.getInt("gift_price"));
				giftOrderViewVO.setGift_pic(rs.getBytes("gift_pic"));
				giftOrderViewVO.setGiftod_amount(rs.getInt("giftod_amount"));
				giftOrderViewVO.setGiftod_money(rs.getInt("giftod_money"));
				giftOrderViewVO.setGiftod_inventory(rs.getInt("giftod_inventory"));
				list.add(giftOrderViewVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
				;
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
	public List<GiftOrderViewVO> getAll(String mem_no_self, Date start, Date end) {
		List<GiftOrderViewVO> list = new ArrayList<>();
		GiftOrderViewVO giftOrderViewVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEM_STMT);
			pstmt.setString(1, mem_no_self);
			pstmt.setDate(2, start);
			pstmt.setDate(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giftOrderViewVO = new GiftOrderViewVO();
				giftOrderViewVO.setGiftr_no(rs.getString("giftr_no"));
				giftOrderViewVO.setMem_no_self(rs.getString("mem_no_self"));
				giftOrderViewVO.setMem_name_self(rs.getString("mem_name_self"));
				giftOrderViewVO.setMem_photo_self(rs.getBytes("mem_photo_self"));
				giftOrderViewVO.setMem_no_other(rs.getString("mem_no_other"));
				giftOrderViewVO.setMem_name_other(rs.getString("mem_name_other"));
				giftOrderViewVO.setMem_photo_other(rs.getBytes("mem_photo_other"));
				giftOrderViewVO.setGiftr_amount(rs.getInt("giftr_amount"));
				giftOrderViewVO.setGiftr_time(rs.getTimestamp("giftr_time"));
				giftOrderViewVO.setGifto_no(rs.getString("gifto_no"));
				giftOrderViewVO.setGifto_time(rs.getTimestamp("gifto_time"));
				giftOrderViewVO.setGifto_remark(rs.getString("gifto_remark"));
				giftOrderViewVO.setCoup_no(rs.getString("coup_no"));
				giftOrderViewVO.setCoup_name(rs.getString("coup_name"));
				giftOrderViewVO.setCoup_value(rs.getInt("coup_value"));
				giftOrderViewVO.setGiftod_no(rs.getString("giftod_no"));
				giftOrderViewVO.setGift_no(rs.getString("gift_no"));
				giftOrderViewVO.setGift_name(rs.getString("gift_name"));
				giftOrderViewVO.setGift_cnt(rs.getString("gift_cnt"));
				giftOrderViewVO.setGift_price(rs.getInt("gift_price"));
				giftOrderViewVO.setGift_pic(rs.getBytes("gift_pic"));
				giftOrderViewVO.setGiftod_amount(rs.getInt("giftod_amount"));
				giftOrderViewVO.setGiftod_money(rs.getInt("giftod_money"));
				giftOrderViewVO.setGiftod_inventory(rs.getInt("giftod_inventory"));
				list.add(giftOrderViewVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
				;
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
	public List<GiftOrderViewVO> getAll(Map<String, String[]> map) {
		List<GiftOrderViewVO> list = new ArrayList<>();
		GiftOrderViewVO giftOrderViewVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String finalSQL = GiftOrderView_CompositeQuery.get_WhereCondition(map);
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				giftOrderViewVO = new GiftOrderViewVO();
				giftOrderViewVO.setGiftr_no(rs.getString("giftr_no"));
				giftOrderViewVO.setMem_no_self(rs.getString("mem_no_self"));
				giftOrderViewVO.setMem_name_self(rs.getString("mem_name_self"));
				giftOrderViewVO.setMem_photo_self(rs.getBytes("mem_photo_self"));
				giftOrderViewVO.setMem_no_other(rs.getString("mem_no_other"));
				giftOrderViewVO.setMem_name_other(rs.getString("mem_name_other"));
				giftOrderViewVO.setMem_photo_other(rs.getBytes("mem_photo_other"));
				giftOrderViewVO.setGiftr_amount(rs.getInt("giftr_amount"));
				giftOrderViewVO.setGiftr_time(rs.getTimestamp("giftr_time"));
				giftOrderViewVO.setGifto_no(rs.getString("gifto_no"));
				giftOrderViewVO.setGifto_time(rs.getTimestamp("gifto_time"));
				giftOrderViewVO.setGifto_remark(rs.getString("gifto_remark"));
				giftOrderViewVO.setCoup_no(rs.getString("coup_no"));
				giftOrderViewVO.setCoup_name(rs.getString("coup_name"));
				giftOrderViewVO.setCoup_value(rs.getInt("coup_value"));
				giftOrderViewVO.setGiftod_no(rs.getString("giftod_no"));
				giftOrderViewVO.setGift_no(rs.getString("gift_no"));
				giftOrderViewVO.setGift_name(rs.getString("gift_name"));
				giftOrderViewVO.setGift_cnt(rs.getString("gift_cnt"));
				giftOrderViewVO.setGift_price(rs.getInt("gift_price"));
				giftOrderViewVO.setGift_pic(rs.getBytes("gift_pic"));
				giftOrderViewVO.setGiftod_amount(rs.getInt("giftod_amount"));
				giftOrderViewVO.setGiftod_money(rs.getInt("giftod_money"));
				giftOrderViewVO.setGiftod_inventory(rs.getInt("giftod_inventory"));
				list.add(giftOrderViewVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
				;
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
	public List<GiftOrderViewVO> getAll(String mem_no_self) {
		List<GiftOrderViewVO> list = new ArrayList<>();
		GiftOrderViewVO giftOrderViewVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMSELF_STMT);
			pstmt.setString(1, mem_no_self);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giftOrderViewVO = new GiftOrderViewVO();
				giftOrderViewVO.setGiftr_no(rs.getString("giftr_no"));
				giftOrderViewVO.setMem_no_self(rs.getString("mem_no_self"));
				giftOrderViewVO.setMem_name_self(rs.getString("mem_name_self"));
				giftOrderViewVO.setMem_photo_self(rs.getBytes("mem_photo_self"));
				giftOrderViewVO.setMem_no_other(rs.getString("mem_no_other"));
				giftOrderViewVO.setMem_name_other(rs.getString("mem_name_other"));
				giftOrderViewVO.setMem_photo_other(rs.getBytes("mem_photo_other"));
				giftOrderViewVO.setGiftr_amount(rs.getInt("giftr_amount"));
				giftOrderViewVO.setGiftr_time(rs.getTimestamp("giftr_time"));
				giftOrderViewVO.setGifto_no(rs.getString("gifto_no"));
				giftOrderViewVO.setGifto_time(rs.getTimestamp("gifto_time"));
				giftOrderViewVO.setGifto_remark(rs.getString("gifto_remark"));
				giftOrderViewVO.setCoup_no(rs.getString("coup_no"));
				giftOrderViewVO.setCoup_name(rs.getString("coup_name"));
				giftOrderViewVO.setCoup_value(rs.getInt("coup_value"));
				giftOrderViewVO.setGiftod_no(rs.getString("giftod_no"));
				giftOrderViewVO.setGift_no(rs.getString("gift_no"));
				giftOrderViewVO.setGift_name(rs.getString("gift_name"));
				giftOrderViewVO.setGift_cnt(rs.getString("gift_cnt"));
				giftOrderViewVO.setGift_price(rs.getInt("gift_price"));
				giftOrderViewVO.setGift_pic(rs.getBytes("gift_pic"));
				giftOrderViewVO.setGiftod_amount(rs.getInt("giftod_amount"));
				giftOrderViewVO.setGiftod_money(rs.getInt("giftod_money"));
				giftOrderViewVO.setGiftod_inventory(rs.getInt("giftod_inventory"));
				list.add(giftOrderViewVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
				;
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
	public byte[] getPic(String giftr_no, String columnName) {
		byte[] pic = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PIC_STMT);
			pstmt.setString(1, giftr_no);
			rs = pstmt.executeQuery();

			rs.next();
			if ("MEM_PHOTO_SELF".equals(columnName))
				pic = rs.getBytes("MEM_PHOTO_SELF");
			else if ("MEM_PHOTO_OTHER".equals(columnName))
				pic = rs.getBytes("MEM_PHOTO_OTHER");
			else if ("GIFT_PIC".equals(columnName))
				pic = rs.getBytes("GIFT_PIC");
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
				;
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
		return pic;
	}

}
