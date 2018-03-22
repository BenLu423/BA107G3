package com.deposit_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deposit.model.DepositVO;

public class DepositDetailDAO implements DepositDetail_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Toast";
	String passwd = "Toast";

	private static final String INSERT_DEPOSITDETAIL = 
			"INSERT INTO DEPOSIT_DETAIL(DEPOD_NO, MEM_NO, DEPO_NO, DEPOD_TIME) VALUES ('DD'||LPAD(to_char(DEPOSIT_DETAIL_SEQ.NEXTVAL),3,'0'),?,?, TO_DATE('2018-03-10 08:08:00','yyyy/mm/dd hh24:mi:ss'))";
	private static final String GET_ONEMEM = 
			"SELECT DEPOD_NO,MEM_NO,DEPO_NO,DEPOD_TIME FROM DEPOSIT_DETAIL WHERE MEM_NO=?";
	private static final String GET_ALLMEM = 
			"SELECT * FROM DEPOSIT_DETAIL ORDER BY MEM_NO";
	@Override
	public void insert(DepositDetailVO depositDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_DEPOSITDETAIL);
			con.setAutoCommit(false);

			pstmt.setString(1, depositDetailVO.getMem_no());
			pstmt.setString(2, depositDetailVO.getDepo_no());

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
	public List<DepositDetailVO> findByPrimaryKey(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DepositDetailVO depositDetailVO = null;
		List<DepositDetailVO> list = new ArrayList<>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONEMEM);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				depositDetailVO = new DepositDetailVO();
				depositDetailVO.setDepod_no(rs.getString("DEPOD_NO"));
				depositDetailVO.setMem_no(rs.getString("MEM_NO"));
				depositDetailVO.setDepo_no(rs.getString("DEPO_NO"));
				depositDetailVO.setDepod_time(rs.getTime("DEPOD_TIME"));
				list.add(depositDetailVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	@Override
	public List<DepositDetailVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DepositDetailVO depositDetailVO = null;
		List<DepositDetailVO> list = new ArrayList<>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALLMEM);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				depositDetailVO = new DepositDetailVO();
				depositDetailVO.setDepod_no(rs.getString("DEPOD_NO"));
				depositDetailVO.setMem_no(rs.getString("MEM_NO"));
				depositDetailVO.setDepo_no(rs.getString("DEPO_NO"));
				depositDetailVO.setDepod_time(rs.getTime("DEPOD_TIME"));
				list.add(depositDetailVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	public static void main(String[] args) {
		DepositDetailDAO depositDetailDAO = new DepositDetailDAO();
		
//		新增
		DepositDetailVO depositDetailVO = new DepositDetailVO();
		depositDetailVO.setMem_no("M003");
		depositDetailVO.setDepo_no("D003");
		depositDetailDAO.insert(depositDetailVO);
		
//		查詢某會員儲值明細
		List<DepositDetailVO> list1 = depositDetailDAO.findByPrimaryKey("M003");
		for (DepositDetailVO depositDetailVO2 : list1) {
			System.out.print(depositDetailVO2.getDepod_no() + ",");
			System.out.print(depositDetailVO2.getMem_no() + ",");
			System.out.print(depositDetailVO2.getDepo_no() + ",");
			System.out.println(depositDetailVO2.getDepod_time());
			System.out.println("----------------------------------------------------");
		}
		
//		查詢全部會員儲值明細
		List<DepositDetailVO> list2 = depositDetailDAO.getAll();
		for (DepositDetailVO depositDetailVO3 : list2) {
			System.out.print(depositDetailVO3.getDepod_no() + ",");
			System.out.print(depositDetailVO3.getMem_no() + ",");
			System.out.print(depositDetailVO3.getDepo_no() + ",");
			System.out.println(depositDetailVO3.getDepod_time());
			System.out.println("----------------------------------------------------");
		}
	}

}
