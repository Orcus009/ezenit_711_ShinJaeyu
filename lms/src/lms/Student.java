package lms;

public class Student {

	private int number;
	private String name;
	
	public Student(int number, String name) {
		this.number = number;
		this.name = name;
	}
	
	// Getter & Setter
	public int getNumber() {
		return this.number;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	int subCnt;
	Subject[] subjects;
	
	void printSubjects() {
		// 과목출력
		for(int i = 0 ; i < this.subCnt ; i ++) {
			Subject subject = this.subjects[i];
			System.out.printf("ㄴ %d) %s (%d점)\n", i + 1, subject.title, subject.score);
		}
	}
	
}
