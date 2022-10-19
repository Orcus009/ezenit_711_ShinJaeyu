package money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import member.MemberDao;
import member.MemberDto;
import util.DBManager;

public class MoneyDao {

	private String url;
	private String user;
	private String password;
	
	// Singleton Pattern
	private MoneyDao() {
		this.url = "jdbc:oracle:thin:@localhost:1521:xe";
		this.user = "C##system";
		this.password = "1234";
	}
	
	private static MoneyDao instance = new MoneyDao();
	
	public static MoneyDao getInstance() {
		return instance;
	}
	
	// Methods
	
	// 1. Create
	// 2. Read
	public ArrayList<MoneyDto> getMoneyAll(){
		ArrayList<MoneyDto> list = new ArrayList<MoneyDto>();
		
		Connection conn = DBManager.getConnection(this.url, this.user, this.password);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM money_tbl_02";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int custno = rs.getInt(1);
				int salenol = rs.getInt(2);
				int pcost = rs.getInt(3);
				int amount = rs.getInt(4);
				int price = rs.getInt(5);
				String pcode = rs.getString(6);
				Timestamp sdate = rs.getTimestamp(7);
				
				MoneyDto money = new MoneyDto(custno, salenol, pcost, amount, price, pcode, sdate);
				list.add(money);
			}
			System.out.println("list.size() : " + list.size());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 3. Update
	// 4. Delete
	
	// 5. 회원별 누적 판매계
	public ArrayList<ArrayList<String>> getSalesInquiry(){
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		MemberDao memberDao = MemberDao.getInstance();
		
		Connection conn = DBManager.getConnection(this.url, this.user, this.password);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT custno, SUM(price) as price FROM money_tbl_02 GROUP BY custno ORDER BY price DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ArrayList<String> data = new ArrayList<String>();
				
				int custno = rs.getInt(1);
				MemberDto member = memberDao.getMemberByCustno(custno);
				
				data.add(rs.getString(1));
				data.add(member.getCustname());
				data.add(member.getGrade());
				data.add(rs.getString(2));
				
				result.add(data);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
