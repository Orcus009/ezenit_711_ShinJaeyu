package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBManager;

public class UserDao {
	
	private String url;
	private String user;
	private String password;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private UserDao() {
		this.url = "jdbc:mysql://localhost:3306/my_rental_car";
		this.user = "root";
		this.password = "root";
		
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}
	
	private static UserDao instance = new UserDao();
	
	public static UserDao getInstance() {
		return instance;
	}
	
	// CRUD
	// Create
	public void createUser(UserDto user) {
		String sql = "insert into user values(?, ?, ?, ?, ?, ?)";
		
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt = this.conn.prepareStatement(sql);
			
			this.pstmt.setString(1, user.getId());
			this.pstmt.setString(2, user.getPw());
			this.pstmt.setString(3, user.getName());
			this.pstmt.setString(4, user.getPhNum());
			this.pstmt.setString(5, user.getAddress());
			this.pstmt.setString(6, user.getDriveCode());
			
			this.pstmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Read
	public UserDto getUserById(String id) {
		UserDto user = null;
		String sql = "select * from user where id = ?";
		
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, id);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				String pw = this.rs.getString(2);
				String name = this.rs.getString(3);
				String phNum = this.rs.getString(4);
				String address = this.rs.getString(5);
				String driveCode = this.rs.getString(6);
				
				user = new UserDto(id, pw, name, phNum, address, driveCode);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	// Update
	public void updateUser(UserDto user) {
		
		String sql = "update user set pw = ?, name = ?, phNum = ?, address = ? where id = ?";
		
		String id = user.getId();
		String pw = user.getPw();
		String name = user.getName();
		String phNum = user.getPhNum();
		String address = user.getAddress();
		
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, pw);
			this.pstmt.setString(2, name);
			this.pstmt.setString(3, phNum);
			this.pstmt.setString(4, address);
			this.pstmt.setString(5, id);
			this.pstmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Delete
	public void deleteUser(UserDto user) {
		
		String sql = "delete user where id = ?";
		 
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt = this.conn.prepareStatement(sql);
			
			this.pstmt.setString(1, user.getId());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
