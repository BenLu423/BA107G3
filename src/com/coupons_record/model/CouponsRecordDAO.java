package com.coupons_record.model;

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


public class CouponsRecordDAO implements CouponsRecordDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String UPDATE_COUPONSRECORD = "UPDATE COUPONS_RECORD SET COUPR_IS_USE=? WHERE MEM_NO=? AND COUP_NO=?";
	private static final String GET_ONEMEM = "SELECT MEM_NO,COUP_NO,COUPR_IS_USE FROM COUPONS_RECORD WHERE MEM_NO=?";
	private static final String GET_ALLMEM = "SELECT * FROM COUPONS_RECORD ORDER BY MEM_NO";
	private static final String GET_MEMNO = "SELECT MEM_NO FROM MEMBER";
	private static final String INSERT_COUPONSRECORD = "INSERT INTO COUPONS_RECORD(MEM_NO, COUP_NO, COUPR_IS_USE) VALUES (?,?,?)";
	
	@Override
	public void insert(Connection con, String nextCoup_NO) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> allMem = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(GET_MEMNO);
			rs = pstmt.executeQuery();
			while (rs.next())
				allMem.add(rs.getString("MEM_NO"));
			pstmt = con.prepareStatement(INSERT_COUPONSRECORD);
			for (int i = 0; i < allMem.size(); i++) {
				pstmt.setString(1, allMem.get(i));
				pstmt.setString(2, nextCoup_NO);
				pstmt.setString(3, "ゼㄏノ");
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	@Override
	public void update(CouponsRecordVO couponsRecordVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_COUPONSRECORD);
			con.setAutoCommit(false);

			pstmt.setString(1, couponsRecordVO.getCoupr_is_use());
			pstmt.setString(2, couponsRecordVO.getMem_no());
			pstmt.setString(3, couponsRecordVO.getCoup_no());
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
	public List<CouponsRecordVO> findByMem(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CouponsRecordVO couponsRecordVO = null;
		List<CouponsRecordVO> list = new ArrayList<>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEMEM);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				couponsRecordVO = new CouponsRecordVO();
				couponsRecordVO.setMem_no(rs.getString("MEM_NO"));
				couponsRecordVO.setCoup_no(rs.getString("COUP_NO"));
				couponsRecordVO.setCoupr_is_use(rs.getString("COUPR_IS_USE"));
				list.add(couponsRecordVO);
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

	@Override
	public List<CouponsRecordVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CouponsRecordVO couponsRecordVO = null;
		List<CouponsRecordVO> list = new ArrayList<>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLMEM);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				couponsRecordVO = new CouponsRecordVO();
				couponsRecordVO.setMem_no(rs.getString("MEM_NO"));
				couponsRecordVO.setCoup_no(rs.getString("COUP_NO"));
				couponsRecordVO.setCoupr_is_use(rs.getString("COUPR_IS_USE"));
				list.add(couponsRecordVO);
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
//		CouponsRecordDAO couponsRecordDAO = new CouponsRecordDAO();
//		
////		э敼
//		CouponsRecordVO couponsRecordVO = new CouponsRecordVO();
//		couponsRecordVO.setCoup_no("C001");
//		couponsRecordVO.setMem_no("M001");
//		couponsRecordVO.setCoupr_is_use("ㄏノ");
//		couponsRecordDAO.update(couponsRecordVO);
//		
////		琩高琘穦ч基ㄩ魁
//		List<CouponsRecordVO> list1 = couponsRecordDAO.findByMem("M003");
//		for (CouponsRecordVO couponsRecordVO2 : list1) {
//			System.out.print(couponsRecordVO2.getMem_no()+ ",");
//			System.out.print(couponsRecordVO2.getCoup_no() + ",");
//			System.out.println(couponsRecordVO2.getCoupr_is_use());
//			System.out.println("--------------");
//		}
//		
////		琩高┮Τ穦ч基ㄩ魁
//		List<CouponsRecordVO> list2 = couponsRecordDAO.getAll();
//		for (CouponsRecordVO couponsRecordVO3 : list2) {
//			System.out.print(couponsRecordVO3.getMem_no()+ ",");
//			System.out.print(couponsRecordVO3.getCoup_no() + ",");
//			System.out.println(couponsRecordVO3.getCoupr_is_use());
//			System.out.println("--------------");
//		}
//	}
}
