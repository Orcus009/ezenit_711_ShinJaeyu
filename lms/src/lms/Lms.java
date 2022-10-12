package lms;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lms {
	
	// 멤버 변수는 정의부만 둠 -> 생성자 안에서 초기화 
	private Scanner sc;
	
	private FileManager fm;
	
	private String brand;
	private ArrayList<Student> list;
	private ArrayList<Subject> subjectList;
	
	public Lms(String brand) {
		this.brand = brand;
		init();
	}
	
	private void init() {
		this.sc = new Scanner(System.in);
		this.fm = new FileManager();
		this.list = new ArrayList<Student>();
		this.subjectList = new ArrayList<Subject>();
		
		this.subjectList.add(new Subject("국어"));
		this.subjectList.add(new Subject("영어"));
		this.subjectList.add(new Subject("수학"));
	}
	
	private void printMainMenu() {
		System.out.printf("total : %d\n", this.list.size());
		System.out.println("1. 추가");
		System.out.println("2. 삭제");
		System.out.println("3. 정렬");
		System.out.println("4. 조회");
		System.out.println("5. 저장");
		System.out.println("6. 로드");
		System.out.println("0. 종료");
	}
	
	private void printSubMenuAdd() {
		System.out.println("1) 학생 등록");
		System.out.println("2) 수강 신청");
		System.out.println("3) 성적 입력");
	}
	
	private void printSubMenuDelete() {
		System.out.println("1) 학생 탈퇴");
		System.out.println("2) 수강 취소");
	}
	
	private int input() {
		System.out.print("선택 : ");
		return this.sc.nextInt();
	}
	
	private void printStudentAll() {
		for(int i=0; i<this.list.size(); i++) {
			Student student = this.list.get(i);
			System.out.printf("%d) %d : %s\n", i+1, student.getNumber(), student.getName());
			student.printSubjectAll();
		}
	}
	
	private void addStudent() {
		System.out.print("등록할 학생명 : ");
		String name = this.sc.next();
		int num = randomGenerator();
		
		Student student = new Student(num, name);
		this.list.add(student);
		System.out.printf("%s 학생등록 완료, 학번 : %d\n", name, num);
	}

	private int randomGenerator() {
		Random rn = new Random();
		int rNum = 0;
		while(true) {
			rNum = rn.nextInt(8999) +1000;
			
			boolean dupl = false;
			for(Student student : this.list) {
				if(rNum == student.getNumber())
					dupl = true;
			}
			if(!dupl)
				break;
		}
		return rNum;
	}
	
	private Student getStudent() {
		System.out.print("학번 : ");
		int num = this.sc.nextInt();
		
		for(Student student : this.list) {
			if(student.getNumber() == num)
				return student;
		}
		return null;
	}
	
	private void addSubject() {
		Student student = getStudent();
		
		// 수강신청할 수 있는 과목 목록 출력 
		for(int i=0; i<this.subjectList.size(); i++) {
			Subject subject = this.subjectList.get(i);
			System.out.printf("%d) %s\n", i+1, subject.getTitle());
		}
		// 수강신청 인덱스를 입력받음 
		int idx = input() -1;
		
		// 유효한 선택여부 예외처리 
		if(idx >= 0 && idx < this.subjectList.size()) {
			// 추가할 과목 객체를 따로 생성 
			// 문제점 : 과목 리스트로 둔 객체의 주소가 공유 -> 모든 학생이 한개 객체를 공유
//			Subject subject = this.subjectList.get(idx);   
			String title = this.subjectList.get(idx).getTitle(); // 과목의 타이틀을 확인하는 용도로만 idx를 사용 
			Subject subject = new Subject(title); // 새로운 과목 객체 생성 (주소의 할당 : 인스턴스) 
			student.addSubject(subject);
		}
	}
	
	private void updateScore() {
		Student student = getStudent();
		student.printSubjectAll();
		int idx = input() -1;
		
		if(idx >= 0 && idx < student.getSubjectsSize()) {
			System.out.print("성적 입력 : ");
			int score = this.sc.nextInt();
			
			student.setSubjectScore(idx, score);
		}
	}
	
	private void removeStudent() {
		Student student = getStudent(); // this.list 에서 대상 학생객체 주소 반환
		if(student != null) {
			this.list.remove(student);
			System.out.println("학생탈퇴 완료");
		}
	}
	
	private void removeSubject() {
		Student student = getStudent();
		student.printSubjectAll();
		int delIdx = this.sc.nextInt() -1;
		
		if(delIdx >= 0 && delIdx < student.getSubjectsSize()) {
			student.removeSubject(delIdx);
			System.out.println("수강취소 완료");
		}
	}
	
	private void sortByStudentName() {
		// this.list 
		for(int i=0; i<this.list.size(); i++) {
			Student first = this.list.get(i);
			for(int j=i; j<this.list.size(); j++) {
				Student last = this.list.get(j);
				if(first.getName().compareTo(last.getName()) > 0) {
					this.list.set(i, last);		// ArrayList 의 값 수정 set() 
					this.list.set(j, first);
				}
			}
		}
	}
	
	private String createData() {
		// 학번/이름,수강과목1/성적1,수강과목2/성적2... 
		String data = "";
		for(int i=0; i<this.list.size(); i++) {
			Student student = this.list.get(i);
			data += student.getNumber() + "/";
			data += student.getName();
			
			// 과목 
			for(int j=0; j<student.getSubjectsSize(); j++) {
				Subject subject = student.getSubject(j);
				String title = subject.getTitle();
				int score = subject.getScore();
				
				data += ",";
				data += title + "/" + score;
			}
			
			if(i < this.list.size() -1)
				data += "\n";
		}
		return data;
	}
	
	private void saveData() {
		String data = createData();
		fm.save(data);
	}
	
	private void loadData() {
		this.list = fm.load();
	}
	
	public void run() {
		while(true) {
			printStudentAll();
			printMainMenu();
			int sel = input();
			
			if(sel == 1) {
				printSubMenuAdd();
				sel = input();
				
				if(sel == 1)
					addStudent();
				else if(sel == 2)
					addSubject();
				else if(sel == 3)
					updateScore();
			}
			else if(sel == 2) {
				printSubMenuDelete();
				sel = input();
				
				if(sel == 1)
					removeStudent();
				else if(sel == 2)
					removeSubject();
			}
			else if(sel == 3)
				sortByStudentName();
			else if(sel == 4) 
				printStudentAll();
			else if(sel == 5) 
				saveData();
			else if(sel == 6) 
				loadData();
			else if(sel == 0) {
				System.out.println("시스템을 종료합니다.");
				break;
			}
			
		}
	}
	
}
