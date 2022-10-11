package lms;

import java.io.*;

public class Filemanager {

	private FileWriter fw;
	private File file;
	private FileReader fr;
	private BufferedReader br;

	void saveData() {
		// 학번/학생명,수강과목1/,수강과목2/성적2
		String data = "";
		
		for(int i = 0 ; i < this.cnt ; i ++) {
			Student student = this.students[i];
			data += student.number + "/";
			data += student.name;
			
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
	
}

