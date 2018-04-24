package android.com.giftbox.model;

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

public class GiftboxDAO implements GiftboxDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	static final String GETMEMGIFT = "SELECT MEM_NO_SELF,GOD.GIFT_NO, SUM(GIFTR_AMOUNT) AS GIFTR_AMOUNT FROM GIFT_RECEIVE GR "
			+ "JOIN GIFT_ORDER_DETAIL GOD ON GOD.GIFTOD_NO = GR.GIFTOD_NO "
			+ "JOIN GIFT G ON G.GIFT_NO = GOD.GIFT_NO "
			+ "WHERE MEM_NO_OTHER = ? AND GIFT_IS_ON LIKE '%¤W¬[¤¤%' "
			+ "GROUP BY MEM_NO_SELF,MEM_NO_OTHER,GOD.GIFT_NO "
			+ "ORDER BY GOD.GIFT_NO";

	@Override
	public List<GiftboxVO> getByMemGift(String mem_no) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<GiftboxVO> list = new ArrayList<>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETMEMGIFT);
			ps.setString(1, mem_no);
			rs = ps.executeQuery();
			while(rs.next()){
				GiftboxVO giftboxVO = new GiftboxVO();
				giftboxVO.setMem_no_self(rs.getString("MEM_NO_SELF"));
				giftboxVO.setGift_no(rs.getString("GIFT_NO"));
				giftboxVO.setGiftr_amount(rs.getInt("GIFTR_AMOUNT"));
				list.add(giftboxVO);
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
			if (ps != null) {
				try {
					ps.close();
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
}
