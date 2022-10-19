package member;

import java.sql.Timestamp;

public class MemberDto {
	
	private int custno;
	private String custname;
	private String phone;
	private String address;
	private Timestamp joindate;
	private String grade;
	private String city;
	
	public MemberDto(int custno, String custname, String phone, String address, Timestamp joindate, String grade, String city) {
		
		this.custno = custno;
		this.custname = custname;
		this.phone = phone;
		this.address = address;
		this.joindate = joindate;
		this.grade = grade;
		this.city = city;
		
	}

	public int getCustno() {
		return custno;
	}

//	public void setCustno(int custno) {
//		this.custno = custno;
//	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getJoindate() {
		return joindate;
	}

	public void setJoindate(Timestamp joindate) {
		this.joindate = joindate;
	}

	public String getGrade() {
		return grade;
	}
	
	public String getGradeString() {
		if(this.grade.equals("A"))
			return "VIP";
		else if(this.grade.equals("B"))
			return "일반";
		return "직원";
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
