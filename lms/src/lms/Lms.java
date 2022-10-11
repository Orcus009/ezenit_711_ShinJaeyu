package lms;

import java.io.*;
import java.util.*;

public class Lms {

	private ArrayList<Student> list;

	Scanner scan;
	
	File file;
	FileWriter fw;
	FileReader fr;
	BufferedReader br;
	
	String brand;
	
	int size;
	
	int cnt;
	Student[] students;
	
	String[] subTitles = {"국어", "수학", "영어"};
	
	// Lms 클래스의 생성자
	Lms(String brand){
		this.scan = new Scanner(System.in); // 생성단계에서 -> 멤버 초기화
		this.file = new File(brand);
		this.brand = brand;
	}
	
	void addStudent(String name) {
		int num = randomGenerator();
		Student student = new Student(0, name);
		
		Student[] stuTemp = this.students;
		this.students = new Student[size + 1];
		for(int i = 0 ; i < size ; i ++)
			this.students[i] = stuTemp[i]; // 클래스 주소값으로 옮김
		this.students[size] = student;
		size ++;
	}
	
	int printMenu() {
		
		// 1.추가
		// 1-1.학생
		// 1-2.과목
		// 1-3.성적
		// 2.삭제
		// 3.정렬
		// 4.출력
		// 5.저장
		// 6.로드
		
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
		
		int num = randomGenerator(); // 랜덤 학번 발행
		
		Student[] temp = this.students;
		this.students = new Student[this.cnt + 1];
		for(int i = 0 ; i < this.cnt ; i ++)
			this.students[i] = temp[i];
		
		Student student = new Student(num, name); // 새로운 학생객체 생성
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
			for(int i = 0 ; i < size ; i ++) {
				Student student = this.students[i];
				if(rNum == student.getNumber())
					dupl = true;
			}
			if(!dupl) 
				break;
		}
		return rNum; // 중복되지 않은 랜덤 학번 -> 리턴
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
				return student; // return 키워드를 만나면 -> 메소드가 즉시 소멸
		}
		return null;
	}
	
	void addSubject() {
		Student student = getStudent(cnt); // 학번을 통한 Student 객체를 얻어오기
		
		if(student != null) { // 학생이 존재하면
			printSubjectTitles();
			System.out.print("과목 선택 : ");
			int idx = this.scan.nextInt() - 1;
			
			if(idx >= 0 && idx < this.subTitles.length) {
				Subject[] temp = student.subjects;
				
				// 중복 수강신청 예외처리
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
		Student student = getStudent(cnt); // 학번을 통한 Student 객체를 얻어오기
		
		if(student != null) {
			student.printSubjects(); // 신청한 과목 목록 출력
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
	
	void sortStudent() { // 이름순으로 정렬
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
	
	void saveData() {
		// 학번/학생명,수강과목1/,수강과목2/성적2
		String data = "";
		
		for(int i = 0 ; i < this.cnt ; i ++) {
			Student student = this.students[i];
			data += student.getNumber() + "/";
			data += student.getName();
			
			// 과목 붙이기
			for(int j = 0 ; j < student.subCnt ; j ++) {
				Subject subject = student.subjects[j];
				
				data += ",";
				data += subject.title + "/";
				data += subject.score;
			}
			if(i < this.cnt - 1)
				data += "\n";
		}
		
		try {
			fw = new FileWriter(this.file);
			fw.write(data);
			fw.close();
			System.out.println("파일저장 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("파일저장 실패");
		}
	}
	
	void loadData() {
		saveData(); // 자동처리 후, 로드
		
		this.cnt = 0;
		this.students = null;
		try {
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			while(this.br.ready()) {
				String[] data = this.br.readLine().split(",");
				
				String[] info = data[0].split("/");
				int number = Integer.parseInt(data[0]);
				String name = info[1];
				
				Student student = new Student(number, name);
				
				if(data.length > 1) { // 수강신청 내역이 존재함
					student.subCnt = data.length - 1;
					student.subjects = new Subject[data.length - 1];
					
					for(int i = 1 ; i < data.length ; i ++) {
						info = data[i].split("/");
						
						Subject subject = new Subject(info[0]);
						subject.score = Integer.parseInt(info[1]);
						student.subjects[i - 1] = subject;
					}
				}
				Student[] temp = this.students;
				this.students = new Student[this.cnt + 1];
				for(int i = 0 ; i < this.cnt ; i ++)
					this.students[i] = temp[i];
				this.students[this.cnt] = student;
				this.cnt ++;
			}
				
			this.fr.close();
			this.br.close();
			System.out.println("파일로드 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("파일로드 실패");
		}
	}
	
	// Lms 클래스가 가진 멤버 메소드
	void run() {
		
		while(true) {
			printStudentAll(); // 검수용
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
