package lms;

import java.io.*;
import java.util.*;

public class Lms {

	private ArrayList<Student> list;
	
	public ArrayList<Student> getList() {
		return this.list;
	}
	
	public void setList(ArrayList<Student> list) {
		this.list = list;
	}

	Scanner scan;
	
	File file;
	FileWriter fw;
	FileReader fr;
	BufferedReader br;
	
	String brand;
	
	int cnt;
	
	Lms(String brand){
		this.scan = new Scanner(System.in);
		this.file = new File(brand);
		this.brand = brand;
	}
	
	int printMenu() {
		
		System.out.println("1.추가");
		System.out.println("2.삭제");
		System.out.println("3.정렬");
		System.out.println("4.출력");
		System.out.println("5.저장");
		System.out.println("6.로드\n");
		System.out.print("입력 : ");
		int sel = scan.nextInt();
		return sel;
		
	}
	
	int printAddSubMenu() {
		System.out.println("1) 학생 등록 : ");
		System.out.println("2) 수강 신청 : ");
		System.out.println("3) 성적 입력 : ");
		System.out.print("선택 : ");
		int sel = this.scan.nextInt();
		return sel;
	}
	
	int printDeleteSubMenu() {
		System.out.println("1) 학생 탈퇴 : ");
		System.out.println("2) 수강 취소 : ");
		System.out.print("선택 : ");
		int sel = this.scan.nextInt();
		return sel;
	}
	
	void addStudent() {
		System.out.print("등록할 학생명 : ");
		String name = this.scan.next();
		
		int num = randomGenerator();
		
		Student[] temp = this.students;
		this.students = new Student[this.cnt + 1];
		for(int i = 0 ; i < this.cnt ; i ++)
			this.students[i] = temp[i];
		
		Student student = new Student(num, name);
		this.students[cnt] = student;
		this.cnt ++;
		
		System.out.printf("학번 : %d, 학생명 : %s  등록완료\n", num, name);
	}
	
	int randomGenerator() {
		Random rand = new Random();
		int rNum = 0;
		
		while(true) {
			rNum = rand.nextInt(8999) + 1000;
			boolean dupl = false;
			for(int i = 0 ; i < cnt ; i ++) {
				Student student = this.students[i];
				if(rNum == student.getNumber())
					dupl = true;
			}
			if(!dupl) 
				break;
		}
		return rNum;
	}
	
	void printStudentAll() {
		System.out.println("total : " + this.cnt);
		for(int i = 0 ; i < this.cnt ; i ++) {
			Student student = this.students[i];
			System.out.printf("%d) %s\n", student.getNumber(), student.getName());
			student.printSubjects();
		}
	}
	
	void printSubjectTitles() {
		for(int i = 0 ; i < this.subTitles.length ; i ++)
			System.out.printf("%d) %s\n", i + 1, this.subTitles[i]);
	}
	
	Student getStudent(int number) {
		System.out.print("학번 입력 : ");
		int num = this.scan.nextInt();
		
		for(int i = 0 ; i < this.cnt ; i ++) {
			Student student = this.students[i];
			if(student.getNumber() == num)
				return student;
		}
		return null;
	}
	
	void addSubject() {
		Student student = getStudent(cnt);
		
		if(student != null) {
			printSubjectTitles();
			System.out.print("과목 선택 : ");
			int idx = this.scan.nextInt() - 1;
			
			if(idx >= 0 && idx < this.subTitles.length) {
				Subject[] temp = student.subjects;
				
				boolean dupl = false;
				for(int i = 0 ; i < student.subCnt ; i ++) {
					Subject subject = temp[i];
					if(subject.title.equals(this.subTitles[idx]))
						dupl = true;
				}
				
				if(!dupl) {
					student.subjects = new Subject[student.subCnt + 1];
					for(int i = 0 ; i < student.subCnt ; i ++)
						student.subjects[i] = temp[i];
					
					student.subjects[student.subCnt] = new Subject(this.subTitles[idx]);
					student.subCnt ++;
					
					System.out.println("수강신청 완료");
				}
				
				else
					System.out.println("이미 신청한 과목");
			}
		}
	}
	
	void updateScore() {
		Student student = getStudent(cnt);
		
		if(student != null) {
			student.printSubjects();
			System.out.print("수정할 과목 : ");
			int idx = this.scan.nextInt() - 1;
			
			if(idx >= 0 && idx < student.subCnt) {
				Subject subject = student.subjects[idx];
				
				System.out.printf("%s 성적 : ", subject.title);
				int score = scan.nextInt();
				
				if(score >= 0 && score <= 100) {
					subject.score = score;
					System.out.println("성적이 반영됨");
				}
				
				else
					System.out.println("유효하지 않은 성적 값");
			}
		}
	}
	
	void removeStudent() {
		Student student = getStudent(cnt);
		if(student != null) {
			Student[] temp = this.students;
			this.students = new Student[this.cnt - 1];
			
			int idx = 0;
			for(int i = 0 ; i < this.cnt ; i ++) {
				if(student.getNumber() != temp[i].getNumber()) {
					this.students[idx] = temp[i];
					idx ++;
				}
			}
			this.cnt --;
			System.out.println("학생 탈퇴 완료");
		}
	}
	
	void removeSubject() {
		Student student = getStudent(cnt);
		student.printSubjects();
		System.out.print("삭제할 과목 : ");
		int delIdx = this.scan.nextInt() - 1;
		
		if(delIdx >= 0 && delIdx < student.subCnt) {
			Subject[] temp = student.subjects;
			student.subjects = new Subject[student.subCnt - 1];
			
			int idx = 0;
			for(int i = 0 ; i < student.subCnt ; i ++) {
				if(delIdx != i) {
					student.subjects[idx] = temp[i];
					idx ++;
				}
			}
			student.subCnt --;
			System.out.println("수강취소 완료");
		}
	}
	
	void sortStudent() { 
		for(int i = 0 ; i < this.cnt ; i ++) {
			Student student = this.students[i];
			String first = this.students[i].getName();
			
			for(int j = i ; j < this.cnt ; j ++) {
				Student student2 = this.students[j];
				
				if(first.compareTo(student2.getName()) > 0) {
					this.students[i] = student2;
					this.students[j] = student;
				}
			}
		}
	}
	
	void run() {
		
		while(true) {
			int sel = printMenu();
			
			if(sel == 1) {
				sel = printAddSubMenu();
				if(sel == 1) {
					addStudent();
				}
				
				else if(sel == 2) {
					addStudent();
				}
				
				else if(sel == 3) {
					updateScore();
				}
			}
			
			else if(sel == 2) {
				sel = printDeleteSubMenu();
				if(sel == 1) {
					removeStudent();
				}
				
				else if(sel == 2) {
					removeSubject();
				}
			}
			
			else if(sel == 3) {
				sortStudent();	
			}
						
			else if(sel == 4) {
				printStudentAll();
			}
						
			else if(sel == 5) {
				saveData();
			}
			
			else if(sel == 6) {
				loadData();
			}
		}
	}
}
