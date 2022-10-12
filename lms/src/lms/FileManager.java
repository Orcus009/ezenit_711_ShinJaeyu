package lms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager {
	
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	// 생성자! 
	public FileManager() {
		init();
	}
	
	private void init() {
		this.file = new File("lms.txt");
	}
	
	public void save(String data) {
		try {
			this.fw = new FileWriter(this.file);
			this.fw.write(data);
			this.fw.close();
			System.out.println("파일저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일저장 실패");
		}
	}
	
	public ArrayList<Student> load() {
		ArrayList<Student> result = new ArrayList<Student>();
		
		try {
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			while(this.br.ready()) {
				String[] data = this.br.readLine().split(",");
				
				// 학생 객체 생성 
				String[] info = data[0].split("/");
				int number = Integer.parseInt(info[0]);
				String name = info[1];
				Student student = new Student(number, name);
				
				// 과목 객체 생성 -> student.addSubject() 
				for(int i=1; i<data.length; i++) {
					info = data[i].split("/");
					String title = info[0];
					int score = Integer.parseInt(info[1]);
					Subject subject = new Subject(title, score); // 오버로딩으로 구현된 Subject 생성자 호출 
					
					student.addSubject(subject);
				}
				
				// 완성된 학생 객체 -> result.add() 
				result.add(student);
			}
			this.fr.close();
			this.br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
