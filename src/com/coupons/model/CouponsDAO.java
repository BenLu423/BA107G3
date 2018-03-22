package com.coupons.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coupons_record.model.CouponsRecordDAO;

public class CouponsDAO implements CouponsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Toast";
	String passwd = "Toast";

	private static final String INSERT_COUPONS = "INSERT INTO COUPONS (COUP_NO, COUP_NAME, COUP_SERIAL, COUP_VALUE, COUP_THR, COUP_START, COUP_END) VALUES ('C'||LPAD(to_char(COUPONS_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?)";
	private static final String UPDATE_COUPOUS = "UPDATE COUPONS SET COUP_NAME=?,COUP_SERIAL=?,COUP_VALUE=?, COUP_THR=?, COUP_START=?, COUP_END=? WHERE COUP_NO=?";
	private static final String FINDBYDATE = "SELECT COUP_NO, COUP_NAME, COUP_SERIAL, COUP_VALUE, COUP_THR, COUP_START, COUP_END FROM COUPONS WHERE COUP_END > ?";
	private static final String GET_ALL = "SELECT * FROM COUPONS ORDER BY COUP_NO DESC";

	@Override
	public void insert(CouponsVO couponsvo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String nextCoup_NO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String[] s = { "COUP_NO" };
			pstmt = con.prepareStatement(INSERT_COUPONS, s);
			con.setAutoCommit(false);

			pstmt.setString(1, couponsvo.getCoup_name());
			pstmt.setString(2, couponsvo.getCoup_serial());
			pstmt.setInt(3, couponsvo.getCoup_value());
			pstmt.setInt(4, couponsvo.getCoup_thr());
			pstmt.setDate(5, couponsvo.getCoup_start());
			pstmt.setDate(6, couponsvo.getCoup_end());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			rs.next();
			nextCoup_NO = rs.getString(1);
			CouponsRecordDAO couponsRecordDAO = new CouponsRecordDAO();
			couponsRecordDAO.insert(con, nextCoup_NO);
			con.commit();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(CouponsVO couponsvo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_COUPOUS);
			con.setAutoCommit(false);

			pstmt.setString(1, couponsvo.getCoup_name());
			pstmt.setString(2, couponsvo.getCoup_serial());
			pstmt.setInt(3, couponsvo.getCoup_value());
			pstmt.setInt(4, couponsvo.getCoup_thr());
			pstmt.setDate(5, couponsvo.getCoup_start());
			pstmt.setDate(6, couponsvo.getCoup_end());
			pstmt.setString(7, couponsvo.getCoup_no());

			pstmt.executeUpdate();
			con.commit();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<CouponsVO> findByDate(Date date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CouponsVO couponsVO = null;
		List<CouponsVO> list = new ArrayList<>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FINDBYDATE);
			pstmt.setDate(1, date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponsVO = new CouponsVO();
				couponsVO.setCoup_no(rs.getString("COUP_NO"));
				couponsVO.setCoup_name(rs.getString("COUP_NAME"));
				couponsVO.setCoup_serial(rs.getString("COUP_SERIAL"));
				couponsVO.setCoup_value(rs.getInt("COUP_VALUE"));
				couponsVO.setCoup_thr(rs.getInt("COUP_THR"));
				couponsVO.setCoup_start(rs.getDate("COUP_START"));
				couponsVO.setCoup_end(rs.getDate("COUP_END"));
				list.add(couponsVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
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
	public List<CouponsVO> getAll() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CouponsVO couponsVO = null;
		List<CouponsVO> list = new ArrayList<>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponsVO = new CouponsVO();
				couponsVO.setCoup_no(rs.getString("COUP_NO"));
				couponsVO.setCoup_name(rs.getString("COUP_NAME"));
				couponsVO.setCoup_serial(rs.getString("COUP_SERIAL"));
				couponsVO.setCoup_value(rs.getInt("COUP_VALUE"));
				couponsVO.setCoup_thr(rs.getInt("COUP_THR"));
				couponsVO.setCoup_start(rs.getDate("COUP_START"));
				couponsVO.setCoup_end(rs.getDate("COUP_END"));
				list.add(couponsVO);
				break;
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
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
	
	public static void main(String[] args) {
		CouponsDAO couponsDAO = new CouponsDAO();

//		新增
		CouponsVO couponsVO1 = new CouponsVO();
		couponsVO1.setCoup_name("滿一百折十");
		couponsVO1.setCoup_serial("A010");
		couponsVO1.setCoup_value(10);
		couponsVO1.setCoup_thr(100);
		couponsVO1.setCoup_start(Date.valueOf("2018-03-03"));
		couponsVO1.setCoup_end(Date.valueOf("2018-03-04"));
		couponsDAO.insert(couponsVO1);

//		修改
		CouponsVO couponsVO2 = new CouponsVO();
		couponsVO2.setCoup_no("C012");
		couponsVO2.setCoup_name("滿一百折十");
		couponsVO2.setCoup_serial("A009");
		couponsVO2.setCoup_value(10);
		couponsVO2.setCoup_thr(100);
		couponsVO2.setCoup_start(Date.valueOf("2018-03-10"));
		couponsVO2.setCoup_end(Date.valueOf("2018-03-20"));
		couponsDAO.update(couponsVO2);

//		用日期查詢
		List<CouponsVO> list1 = couponsDAO.findByDate(Date.valueOf("2018-01-01"));
		for (CouponsVO couponsVO : list1) {
			System.out.print(couponsVO.getCoup_no() + ",");
			System.out.print(couponsVO.getCoup_name() + ",");
			System.out.print(couponsVO.getCoup_serial() + ",");
			System.out.print(couponsVO.getCoup_value() + ",");
			System.out.print(couponsVO.getCoup_thr() + ",");
			System.out.print(couponsVO.getCoup_start() + ",");
			System.out.println(couponsVO.getCoup_end());
			System.out.println("----------------------------------------------------");

		}

//		查詢全部
		List<CouponsVO> list2 = couponsDAO.getAll();
		for (CouponsVO couponsVO : list2) {
			System.out.print(couponsVO.getCoup_no() + ",");
			System.out.print(couponsVO.getCoup_name() + ",");
			System.out.print(couponsVO.getCoup_serial() + ",");
			System.out.print(couponsVO.getCoup_value() + ",");
			System.out.print(couponsVO.getCoup_thr() + ",");
			System.out.print(couponsVO.getCoup_start() + ",");
			System.out.println(couponsVO.getCoup_end());
			System.out.println("----------------------------------------------------");
		}
	}
}
