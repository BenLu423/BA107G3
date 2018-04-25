package android.com.event_participants.model;

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

public class Event_participantsDAO implements Event_participantsDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO event_participants (mem_no , eve_no , evep_sts , evep_qr) VALUES (? , ? , ? , ?)";
	private static final String UPDATE = "UPDATE event_participants set evep_sts = '已報到' where (mem_no = ? and eve_no = ?) ";
	private static final String GETONEEVE = "SELECT * from event_participants where mem_no = ?";

	@Override
	public void insert(Event_participantsVO event_participantsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, event_participantsVO.getMem_no());
			pstmt.setString(2, event_participantsVO.getEve_no());
			pstmt.setString(3, "未報到");
			pstmt.setBytes(4, event_participantsVO.getEvep_qr());
			pstmt.executeUpdate();
		} catch (SQLException se) {
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
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Event_participantsVO event_participantsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, event_participantsVO.getMem_no());
			pstmt.setString(2, event_participantsVO.getEve_no());
			pstmt.executeUpdate();
		} catch (SQLException se) {
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
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<Event_participantsVO> getOneEve(String mem_no) {
		List<Event_participantsVO> list = new ArrayList<>();
		Event_participantsVO event_participantsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETONEEVE);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("=========================");
				event_participantsVO = new Event_participantsVO();
				event_participantsVO.setMem_no(rs.getString("mem_no"));
				event_participantsVO.setEve_no(rs.getString("eve_no"));
				event_participantsVO.setEvep_sts(rs.getString("evep_sts"));
				event_participantsVO.setEvep_qr(rs.getBytes("evep_qr"));
				list.add(event_participantsVO);
			}
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
}