package rpg_start;

import java.util.ArrayList;
import java.util.Random;

import monster.Monster;

public class Game {
	
	// 전투메뉴안에 배치할 것
	private ArrayList<Monster> monsters;
	private String[] monList = {"Orc", "Wolf", "Zombie"}; // 클래스 이름의 목록
	
	private void initFight() {
		this.monsters = new ArrayList<Monster>();
		
		Random rand = new Random();
		
		int monCnt = rand.nextInt(3) + 3;
		for(int i = 0 ; i < monCnt ; i ++) {
			// 랜덤하게 몬스터 발생
			// 제네릭(클래스 타입을 사용할 때 정하는 것) 클래스 활용
			
			try {
				int rIdx = rand.nextInt(monList.length);
				Class<?> cla = Class.forName("monster." + this.monList[rIdx]);
				Object obj = cla.newInstance();// = new 클래스명() // 인스턴스 할당
				if(obj instanceof Monster)
					this.monsters.add((Monster) obj); // 형변환 후 add()
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void run() {
		// 전투 메뉴 진입
		// 랜덤한 몬스터 배치
		initFight();
		
	}
}
