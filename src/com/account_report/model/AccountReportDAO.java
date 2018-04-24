package com.account_report.model;

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



public class AccountReportDAO implements AccountReportDAO_interface{

	
	private static final String INSERTREP = "INSERT INTO ACCOUNT_REPORT(MEM_NO_SELF,MEM_NO_OTHER,ACCREP_REASON,ACCREP_CNT) VALUES (?,?,?,?) ";
	private static final String UPDATE_REP_STATEMENTS = "UPDATE ACCOUNT_REPORT SET ACCREP_PERMIT = ? WHERE MEM_NO_SELF = ? AND MEM_NO_OTHER = ?";
	
	private static final String SEARCH_STATEMENT = "SELECT * FROM ACCOUNT_REPORT WHERE ACCREP_PERMIT = ?";
	private static final String GETALL = "SELECT * FROM ACCOUNT_REPORT";
	private static final String GETONE = "SELECT * FROM ACCOUNT_REPORT WHERE MEM_NO_SELF = ? AND MEM_NO_OTHER = ?"; 
	private static final String DELPREHIBIT = "DELETE FROM ACCOUNT_REPORT WHERE MEM_NO_SELF = ? AND MEM_NO_OTHER = ?";
	private static final String GETALLOTHER = "SELECT * FROM ACCOUNT_REPORT WHERE MEM_NO_OTHER = ?";
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/BA107G3");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	
	/*新增檢舉*/
	@Override
	public void accountReportAdd(AccountReportVO memrep) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERTREP);
			ps.setString(1, memrep.getMem_no_self());
			ps.setString(2, memrep.getMem_no_other());
			ps.setString(3, memrep.getAccrep_reason());
			ps.setString(4, memrep.getAccrep_cnt());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
					
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	/*修改檢舉*/
	@Override
	public void accountReportUpdate(List<AccountReportVO> memrep) {
		Connection con = null;
		PreparedStatement ps = null;
	
		try{
		con = ds.getConnection();
		ps = con.prepareStatement(UPDATE_REP_STATEMENTS);
		for(AccountReportVO list : memrep){
			ps.setString(1, list.getAccrep_permit());
			ps.setString(2, list.getMem_no_self());
			ps.setString(3, list.getMem_no_other());
			ps.addBatch();
		}
		ps.executeBatch();
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	/*查看檢舉狀態*/
	@Override
	public List<AccountReportVO> findStatement(String memrep) {
		List<AccountReportVO> listmemrep = new ArrayList<AccountReportVO>();
		AccountReportVO mrvo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SEARCH_STATEMENT);
			ps.setString(1, memrep);
			rs = ps.executeQuery();
			while(rs.next()){
				mrvo = new AccountReportVO();
				mrvo.setMem_no_self(rs.getString("MEM_NO_SELF"));
				mrvo.setMem_no_other(rs.getString("MEM_NO_OTHER"));
				mrvo.setAccrep_reason(rs.getString("ACCREP_REASON"));
				mrvo.setAccrep_cnt(rs.getString("ACCREP_CNT"));
				mrvo.setAccrep_time(rs.getDate("ACCREP_TIME"));
				mrvo.setAccrep_permit(rs.getString("ACCREP_PERMIT"));
				listmemrep.add(mrvo);
			}
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		} finally{
			
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return listmemrep;
	}
	/*查看全部*/
	@Override
	public List<AccountReportVO> getAll() {
		List<AccountReportVO> listmemrep = new ArrayList<AccountReportVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AccountReportVO mrvo = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			
			while(rs.next()){
				mrvo = new AccountReportVO();
				mrvo.setMem_no_self(rs.getString("MEM_NO_SELF"));
				mrvo.setMem_no_other(rs.getString("MEM_NO_OTHER"));
				mrvo.setAccrep_reason(rs.getString("ACCREP_REASON"));
				mrvo.setAccrep_cnt(rs.getString("ACCREP_CNT"));
				mrvo.setAccrep_time(rs.getDate("ACCREP_TIME"));
				mrvo.setAccrep_permit(rs.getString("ACCREP_PERMIT"));
				listmemrep.add(mrvo);
			}
					
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return listmemrep;
	}
	/*查看被檢舉人*/
	public List<AccountReportVO> getAll(String other, String permit){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AccountReportVO arvo = null;
		List<AccountReportVO> list = new ArrayList<AccountReportVO>();
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(GETALLOTHER);
			ps.setString(1, other);
			rs = ps.executeQuery();
			while(rs.next()){
				arvo = new AccountReportVO();
				arvo.setMem_no_self(rs.getString("MEM_NO_SELF"));
				arvo.setMem_no_other(rs.getString("MEM_NO_OTHER"));
				arvo.setAccrep_permit(permit);
				list.add(arvo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	/*查看檢舉是否存在*/
	public boolean getOne(String self, String other){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Boolean isExist = false;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(GETONE);
			ps.setString(1, self);
			ps.setString(2, other);
			rs = ps.executeQuery();
			while(rs.next()){
				isExist = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return isExist;
	}
	/*刪除檢舉*/
	 public void accountReportDelete(AccountReportVO memrep){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(DELPREHIBIT);
			ps.setString(1, memrep.getMem_no_self());
			ps.setString(2, memrep.getMem_no_other());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	 /*查看個人*/
	 public AccountReportVO getOneMem(String self,String other){
		 	Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			AccountReportVO arvo = null;
			try{
				con = ds.getConnection();
				ps = con.prepareStatement(GETONE);
				ps.setString(1, self);
				ps.setString(2, other);
				rs = ps.executeQuery();
				while(rs.next()){
					arvo = new AccountReportVO();
					arvo.setMem_no_self(rs.getString("MEM_NO_SELF"));
					arvo.setMem_no_other(rs.getString("MEM_NO_OTHER"));
					arvo.setAccrep_reason(rs.getString("ACCREP_REASON"));
					arvo.setAccrep_cnt(rs.getString("ACCREP_CNT"));
					arvo.setAccrep_time(rs.getDate("ACCREP_TIME"));
					arvo.setAccrep_permit(rs.getString("ACCREP_PERMIT"));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(con != null){
					try{
						con.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
			return arvo;
	 }
}
