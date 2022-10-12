package lms;

import java.util.ArrayList;

public class Student {

	// 모든 멤버변수는 private 처리  
	private int number;
	private String name;
	
	private ArrayList<Subject> subjects;
	
	public Student(int number, String name) {
		this.number = number;
		this.name = name;
		this.subjects = new ArrayList<Subject>(); // empty 
	}
	
	// 모든 Getter & Setter는 public 처리 
	public int getNumber() { // 조회만 가능 
		return this.number;
	}
	
//	public void setNumber(int number) { // 기발급된 학번 수정을 허용X  
//		this.number = number;
//	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// 그 외 기능 메소드는 용도에 맞게 public 또는 private 처리 
	
	// 학생의 수강신청 내역 리스트에 접근할 수 있도록 퍼블릭 메소드 제공 
	public void addSubject(Subject subject) {
		boolean dupl = false;
		for(Subject sub : this.subjects) {
			if(sub.getTitle().equals(subject.getTitle()))
				dupl = true;
		}
		if(!dupl) {
			this.subjects.add(subject);
			System.out.println("수강신청 완료");
		}
		else
			System.out.println("이미 신청한 과목입니다.");
	}
	
//	public ArrayList<Subject> getSubjects() { // 외부에서 해당 객체(ArrayList)의 모든 메소드에 접근 가능 -> 쓰지 말자 
//		return this.subjects;
//	}
	
	// 대안 : 전체 과목에 대한 조회 용도로만 public 메소드를 제공 
	public void printSubjectAll() {
		for(int i=0; i<this.subjects.size(); i++) {
			Subject subject = this.subjects.get(i);
			System.out.printf("ㄴ %d) %s : %d점\n", i+1, subject.getTitle(), subject.getScore());
		}
	}
	
	public int getSubjectsSize() {
		return this.subjects.size();
	}
	
	public void setSubjectScore(int idx, int score) {
		Subject subject = this.subjects.get(idx); // 수정 대상 과목 
		subject.setScore(score);
		System.out.println("성적입력 완료 ");
	}
	
	public Subject getSubject(int idx) {
		return this.subjects.get(idx);
	}
	
	public void removeSubject(int idx) {
		this.subjects.remove(idx);
	}
	
	
	
}
