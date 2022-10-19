package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DBManager;

public class MemberDao {

	private String url;
	private String user;
	private String password;
	
	// Singleton Pattern
	private MemberDao() {
		this.url = "jdbc:oracle:thin:@localhost:1521:xe";
		this.user = "C##system";
		this.password = "1234";
	}
	
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	
	// Methods
	// 1. Create
	public void addMember(MemberDto memberDto) {
		Connection conn = DBManager.getConnection(this.url, this.user, this.password);
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO member_tbl_02 VALUES(?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberDto.getCustno());
			pstmt.setString(2, memberDto.getCustname());
			pstmt.setString(3, memberDto.getPhone());
			pstmt.setString(4, memberDto.getAddress());
			pstmt.setTimestamp(5, memberDto.getJoindate());
			pstmt.setString(6, memberDto.getGrade());
			pstmt.setString(7, memberDto.getCity());
			
			pstmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 2. READ
	// 2-1. 한 개 조회
	public MemberDto getMemberByCustno(int custno) {
		MemberDto member = null;
		
		Connection conn = DBManager.getConnection(this.url, this.user, this.password);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM member_tbl_02 WHERE custno = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt(1);
				String custname = rs.getString(2);
				String phone = rs.getString(3);
				String address = rs.getString(4);
				Timestamp joindate = rs.getTimestamp(5);
				String grade = rs.getString(6);
				String city = rs.getString(7);
				
				member = new MemberDto(no, custname, phone, address, joindate, grade, city);
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
		return member;
	}
	
	// 2-2. 모두 조회
	public ArrayList<MemberDto> getMemberAll(){
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		
		Connection conn = DBManager.getConnection(this.url, this.user, this.password);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM member_tbl_02";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				System.out.println(no);
				String custname = rs.getString(2);
				String phone = rs.getString(3);
				String address = rs.getString(4);
				Timestamp joindate = rs.getTimestamp(5);
				String grade = rs.getString(6);
				String city = rs.getString(7);
				
				MemberDto member = new MemberDto(no, custname, phone, address, joindate, grade, city);
				list.add(member);
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
		return list;
	}
	
	// 3. Update
	public void setMember(int custno, MemberDto memberDto) {
		Connection conn = DBManager.getConnection(this.url, this.user, this.password);
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE member_tbl_02 SET custname = ?, phone = ?, address = ?, joindate = ?, grade = ?, city = ? WHERE = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getCustname());
			pstmt.setString(2, memberDto.getPhone());
			pstmt.setString(3, memberDto.getAddress());
			pstmt.setTimestamp(4, memberDto.getJoindate());
			pstmt.setString(5, memberDto.getGrade());
			pstmt.setString(6, memberDto.getCity());
			pstmt.setInt(7, memberDto.getCustno());
			
			pstmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 4. Delete
	public void removeMember(int custno) {
		Connection conn = DBManager.getConnection(this.url, this.user, this.password);
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM member_tbl_02 WHERE custno = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custno);
			pstmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
