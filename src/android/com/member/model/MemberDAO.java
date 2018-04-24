package android.com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.member.model.MemberVO;

import android.com.main.SqlUtil;

public class MemberDAO implements MemberDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	static final String INSERT = "INSERT INTO MEMBER(MEM_NO, MEM_ACCOUNT, MEM_PASSWORD, MEM_JOIN_TIME, MEM_NAME, MEM_GENDER, MEM_BIRTHDAY, MEM_COUNTY, MEM_DEPOSIT, MEM_CONTACT, MEM_EMOTION, MEM_BONUS, MEM_BLOODTYPE, MEM_HEIGHT, MEM_WEIGHT, MEM_INTEREST, MEM_INTRO, MEM_ONLINE, MEM_LONGITUDE, MEM_LATITUDE, MEM_PHONE, MEM_MAIL, MEM_PHOTO, MEM_PROHIBIT, MEM_SETNOTIFY, MEM_TIMENOTIFY) "
			+ "VALUES ('M'||LPAD(to_char(MEMBER_SEQ.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	static final String SEARCHBYACCOUNT = "SELECT * FROM MEMBER WHERE MEM_ACCOUNT = ?";
	
	static final String SEARCHBYMEM_NO = "SELECT * FROM MEMBER WHERE MEM_NO = ?";

	static final String UPDATE = "UPDATE MEMBER SET MEM_ACCOUNT = ?, MEM_PASSWORD = ? WHERE MEM_NO = ?";

	static final String ISMEMBER = "SELECT * FROM MEMBER WHERE MEM_ACCOUNT = ? AND MEM_PASSWORD = ?";

	static final String GETALL = "SELECT * FROM MEMBER";
	
	static final String UPDATE_DEPOSIT_STMT  = "UPDATE MEMBER SET MEM_DEPOSIT=? WHERE MEM_NO=? ";
	
	static final String UPDATE_REC_GIFT_STMT = "UPDATE MEMBER SET MEM_RECEIVE_GIFT=? WHERE MEM_NO=? ";
	
	static final String GETPOPULAR = "SELECT * FROM (SELECT * FROM MEMBER ORDER BY MEM_RECEIVE_GIFT DESC) WHERE ROWNUM <=6";
	
	static String finalSQL;


	@Override
	public Boolean isMember(String account, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isMember = false;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(ISMEMBER);
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();
			isMember = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return isMember;
	}

	@Override
	public void memberUpdate(MemberVO member) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);
			con.setAutoCommit(false);
			ps.setString(1, member.getMem_account());
			ps.setString(2, member.getMem_password());
			ps.setString(3, member.getMem_no());
			ps.executeUpdate();
			con.commit();
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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

	}

	@Override
	public MemberVO getOneByAccount(String account) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvos = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SEARCHBYACCOUNT);
			ps.setString(1, account);

			rs = ps.executeQuery();
			rs.next();
			mvos = new MemberVO();
			mvos.setMem_no(rs.getString("MEM_NO"));
			mvos.setMem_account(rs.getString("MEM_ACCOUNT"));
			mvos.setMem_password(rs.getString("MEM_PASSWORD"));
			mvos.setMem_name(rs.getString("MEM_NAME"));
			mvos.setMem_gender(rs.getString("MEM_GENDER"));
			mvos.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
			mvos.setMem_county(rs.getString("MEM_COUNTY"));
			mvos.setMem_deposit(rs.getInt("MEM_DEPOSIT"));
			mvos.setMem_contact(rs.getString("MEM_CONTACT"));
			mvos.setMem_emotion(rs.getString("MEM_EMOTION"));
			mvos.setMem_bonus(rs.getInt("MEM_BONUS"));
			mvos.setMem_bloodtype(rs.getString("MEM_BLOODTYPE"));
			mvos.setMem_height(rs.getInt("MEM_HEIGHT"));
			mvos.setMem_weight(rs.getInt("MEM_WEIGHT"));
			mvos.setMem_interest(rs.getString("MEM_INTEREST"));
			mvos.setMem_intro(rs.getString("MEM_INTRO"));
			mvos.setMem_online(rs.getString("MEM_ONLINE"));
			mvos.setMem_longitude(rs.getDouble("MEM_LONGITUDE"));
			mvos.setMem_latitude(rs.getDouble("MEM_LATITUDE"));
			mvos.setMem_phone(rs.getString("MEM_PHONE"));
			mvos.setMem_mail(rs.getString("MEM_MAIL"));
			mvos.setMem_photo(rs.getBytes("MEM_PHOTO"));
			mvos.setMem_prohibit(rs.getString("MEM_PROHIBIT"));
			mvos.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));
			
		} catch (

		SQLException se) {
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
		return mvos;
	}
	
	@Override
	public MemberVO getOneByMemNo(String mem_no) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvos = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SEARCHBYMEM_NO);
			ps.setString(1, mem_no);

			rs = ps.executeQuery();
			rs.next();
			mvos = new MemberVO();
			mvos.setMem_no(rs.getString("MEM_NO"));
			mvos.setMem_account(rs.getString("MEM_ACCOUNT"));
			mvos.setMem_password(rs.getString("MEM_PASSWORD"));
			mvos.setMem_name(rs.getString("MEM_NAME"));
			mvos.setMem_gender(rs.getString("MEM_GENDER"));
			mvos.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
			mvos.setMem_county(rs.getString("MEM_COUNTY"));
			mvos.setMem_deposit(rs.getInt("MEM_DEPOSIT"));
			mvos.setMem_contact(rs.getString("MEM_CONTACT"));
			mvos.setMem_emotion(rs.getString("MEM_EMOTION"));
			mvos.setMem_bonus(rs.getInt("MEM_BONUS"));
			mvos.setMem_bloodtype(rs.getString("MEM_BLOODTYPE"));
			mvos.setMem_height(rs.getInt("MEM_HEIGHT"));
			mvos.setMem_weight(rs.getInt("MEM_WEIGHT"));
			mvos.setMem_interest(rs.getString("MEM_INTEREST"));
			mvos.setMem_intro(rs.getString("MEM_INTRO"));
			mvos.setMem_online(rs.getString("MEM_ONLINE"));
			mvos.setMem_longitude(rs.getDouble("MEM_LONGITUDE"));
			mvos.setMem_latitude(rs.getDouble("MEM_LATITUDE"));
			mvos.setMem_phone(rs.getString("MEM_PHONE"));
			mvos.setMem_mail(rs.getString("MEM_MAIL"));
			mvos.setMem_photo(rs.getBytes("MEM_PHOTO"));
			mvos.setMem_prohibit(rs.getString("MEM_PROHIBIT"));
			mvos.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));

		} catch (

		SQLException se) {
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
		return mvos;
	}

	@Override
	public List<MemberVO> getLike(Map<String, String> map) {
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvos = null;
		try {
			con = ds.getConnection();
			finalSQL = "select * from Member " + SqlUtil.get_WhereCondition(map);
		//	System.out.println(finalSQL);
			ps = con.prepareStatement(finalSQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				mvos = new MemberVO();
				mvos.setMem_no(rs.getString("MEM_NO"));
				mvos.setMem_account(rs.getString("MEM_ACCOUNT"));
				mvos.setMem_password(rs.getString("MEM_PASSWORD"));
				mvos.setMem_name(rs.getString("MEM_NAME"));
				mvos.setMem_gender(rs.getString("MEM_GENDER"));
				mvos.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
				mvos.setMem_county(rs.getString("MEM_COUNTY"));
				mvos.setMem_deposit(rs.getInt("MEM_DEPOSIT"));
				mvos.setMem_contact(rs.getString("MEM_CONTACT"));
				mvos.setMem_emotion(rs.getString("MEM_EMOTION"));
				mvos.setMem_bonus(rs.getInt("MEM_BONUS"));
				mvos.setMem_bloodtype(rs.getString("MEM_BLOODTYPE"));
				mvos.setMem_height(rs.getInt("MEM_HEIGHT"));
				mvos.setMem_weight(rs.getInt("MEM_WEIGHT"));
				mvos.setMem_interest(rs.getString("MEM_INTEREST"));
				mvos.setMem_intro(rs.getString("MEM_INTRO"));
				mvos.setMem_online(rs.getString("MEM_ONLINE"));
				mvos.setMem_longitude(rs.getDouble("MEM_LONGITUDE"));
				mvos.setMem_latitude(rs.getDouble("MEM_LATITUDE"));
				mvos.setMem_phone(rs.getString("MEM_PHONE"));
				mvos.setMem_mail(rs.getString("MEM_MAIL"));
				mvos.setMem_photo(rs.getBytes("MEM_PHOTO"));
				mvos.setMem_prohibit(rs.getString("MEM_PROHIBIT"));
				mvos.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));
				
				memberList.add(mvos);
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
		return memberList;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvos = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				mvos = new MemberVO();
				mvos.setMem_no(rs.getString("MEM_NO"));
				mvos.setMem_account(rs.getString("MEM_ACCOUNT"));
				mvos.setMem_password(rs.getString("MEM_PASSWORD"));
				mvos.setMem_name(rs.getString("MEM_NAME"));
				mvos.setMem_gender(rs.getString("MEM_GENDER"));
				mvos.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
				mvos.setMem_county(rs.getString("MEM_COUNTY"));
				mvos.setMem_deposit(rs.getInt("MEM_DEPOSIT"));
				mvos.setMem_contact(rs.getString("MEM_CONTACT"));
				mvos.setMem_emotion(rs.getString("MEM_EMOTION"));
				mvos.setMem_bonus(rs.getInt("MEM_BONUS"));
				mvos.setMem_bloodtype(rs.getString("MEM_BLOODTYPE"));
				mvos.setMem_height(rs.getInt("MEM_HEIGHT"));
				mvos.setMem_weight(rs.getInt("MEM_WEIGHT"));
				mvos.setMem_interest(rs.getString("MEM_INTEREST"));
				mvos.setMem_intro(rs.getString("MEM_INTRO"));
				mvos.setMem_online(rs.getString("MEM_ONLINE"));
				mvos.setMem_longitude(rs.getDouble("MEM_LONGITUDE"));
				mvos.setMem_latitude(rs.getDouble("MEM_LATITUDE"));
				mvos.setMem_phone(rs.getString("MEM_PHONE"));
				mvos.setMem_mail(rs.getString("MEM_MAIL"));
				mvos.setMem_photo(rs.getBytes("MEM_PHOTO"));
				mvos.setMem_prohibit(rs.getString("MEM_PROHIBIT"));
				mvos.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));
				memberList.add(mvos);
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
		return memberList;
	}
	
	@Override
	public void updateDeposit(String mem_no, Integer delDeposit, Connection con) {
		
		PreparedStatement pstmt = null;
		MemberVO memberVO = getOneByMemNo(mem_no);
		Integer oriDeposit = memberVO.getMem_deposit();
		try {
			if(oriDeposit-delDeposit<0)
				throw new SQLException();
			pstmt = con.prepareStatement(UPDATE_DEPOSIT_STMT);
			pstmt.setInt(1, oriDeposit-delDeposit);
			pstmt.setString(2, mem_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if(con != null){
				try {
					System.err.print("Transaction is being ");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured." + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateRecGift(String mem_no, Integer addRecGift, Connection con) {
	
		PreparedStatement pstmt = null;
		//System.out.println(mem_no);
		MemberVO memberVO = getOneByMemNo(mem_no);
		Integer oriRecGift = memberVO.getMem_receive_gift();
		//System.out.println(oriRecGift);
		//System.out.println(addRecGift);
		try {
			pstmt = con.prepareStatement(UPDATE_REC_GIFT_STMT);
			pstmt.setInt(1, oriRecGift+addRecGift);
			pstmt.setString(2, mem_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if(con != null){
				try {
					System.err.print("Transaction is being ");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured." + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<MemberVO> getPopular() {
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvos = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETPOPULAR);
			rs = ps.executeQuery();
			while (rs.next()) {
				mvos = new MemberVO();
				mvos.setMem_no(rs.getString("MEM_NO"));
				mvos.setMem_account(rs.getString("MEM_ACCOUNT"));
				mvos.setMem_password(rs.getString("MEM_PASSWORD"));
				mvos.setMem_name(rs.getString("MEM_NAME"));
				mvos.setMem_gender(rs.getString("MEM_GENDER"));
				mvos.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
				mvos.setMem_county(rs.getString("MEM_COUNTY"));
				mvos.setMem_deposit(rs.getInt("MEM_DEPOSIT"));
				mvos.setMem_contact(rs.getString("MEM_CONTACT"));
				mvos.setMem_emotion(rs.getString("MEM_EMOTION"));
				mvos.setMem_bonus(rs.getInt("MEM_BONUS"));
				mvos.setMem_bloodtype(rs.getString("MEM_BLOODTYPE"));
				mvos.setMem_height(rs.getInt("MEM_HEIGHT"));
				mvos.setMem_weight(rs.getInt("MEM_WEIGHT"));
				mvos.setMem_interest(rs.getString("MEM_INTEREST"));
				mvos.setMem_intro(rs.getString("MEM_INTRO"));
				mvos.setMem_online(rs.getString("MEM_ONLINE"));
				mvos.setMem_longitude(rs.getDouble("MEM_LONGITUDE"));
				mvos.setMem_latitude(rs.getDouble("MEM_LATITUDE"));
				mvos.setMem_phone(rs.getString("MEM_PHONE"));
				mvos.setMem_mail(rs.getString("MEM_MAIL"));
				mvos.setMem_photo(rs.getBytes("MEM_PHOTO"));
				mvos.setMem_prohibit(rs.getString("MEM_PROHIBIT"));
				mvos.setMem_receive_gift(rs.getInt("MEM_RECEIVE_GIFT"));
				memberList.add(mvos);
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
		return memberList;
	}
}
