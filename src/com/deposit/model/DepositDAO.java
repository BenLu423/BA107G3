package com.deposit.model;

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

public class DepositDAO implements DepositDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_DEPOSIT = "INSERT INTO DEPOSIT(DEPO_NO, DEPO_NAME, DEPO_VALUE, DEPO_PERCENT) VALUES ('D'||LPAD(to_char(DEPOSIT_SEQ.NEXTVAL),3,'0'),?,?,?)";
	private static final String UPDATE_DEPOSIT = "UPDATE DEPOSIT SET DEPO_NAME=?,DEPO_VALUE=?,DEPO_PERCENT=? WHERE DEPO_NO=?";
	private static final String GET_ALL = "SELECT * FROM DEPOSIT";

	@Override
	public void insert(DepositVO depositVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_DEPOSIT);
			con.setAutoCommit(false);

			pstmt.setString(1, depositVO.getDepo_name());
			pstmt.setInt(2, depositVO.getDepo_value());
			pstmt.setDouble(3, depositVO.getDepo_percent());

			pstmt.executeUpdate();
			con.commit();

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
	public void update(DepositVO depositVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_DEPOSIT);
			con.setAutoCommit(false);

			pstmt.setString(1, depositVO.getDepo_name());
			pstmt.setInt(2, depositVO.getDepo_value());
			pstmt.setDouble(3, depositVO.getDepo_percent());
			pstmt.setString(4, depositVO.getDepo_no());

			pstmt.executeUpdate();
			con.commit();

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
	public List<DepositVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DepositVO> list = new ArrayList<>();
		DepositVO depositVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				depositVO = new DepositVO();
				depositVO.setDepo_no(rs.getString("DEPO_NO"));
				depositVO.setDepo_name(rs.getString("DEPO_NAME"));
				depositVO.setDepo_value(rs.getInt("DEPO_VALUE"));
				depositVO.setDepo_percent(rs.getDouble("DEPO_PERCENT"));
				list.add(depositVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
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
					con.setAutoCommit(true);
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
//	public static void main(String[] args) {
//		DepositDAO depositDAO = new DepositDAO();
//		
////		·s¼W
//		DepositVO depositVO1 = new DepositVO();
//		depositVO1.setDepo_name("¦R¥q1000");
//		depositVO1.setDepo_value(1000);
//		depositVO1.setDepo_percent(0.4);
//		depositDAO.insert(depositVO1);
//		
////		­×§ï”¹
//		DepositVO depositVO2 = new DepositVO();
//		depositVO2.setDepo_no("D005");
//		depositVO2.setDepo_name("¦R¥q1000");
//		depositVO2.setDepo_value(1000);
//		depositVO2.setDepo_percent(0.3);
//		depositDAO.update(depositVO2);
//		
////		¬d¸ß¥þ³¡
//		List<DepositVO> list = depositDAO.getAll();
//		for (DepositVO depositVO : list) {
//			System.out.print(depositVO.getDepo_no() + ",");
//			System.out.print(depositVO.getDepo_name() + ",");
//			System.out.print(depositVO.getDepo_value() + ",");
//			System.out.println(depositVO.getDepo_percent());
//			System.out.println("--------------------");
//		}
//	}
}
