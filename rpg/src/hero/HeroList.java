package hero;

import java.util.ArrayList;
import java.util.Random;

import rpg_start.Unit;
import util.NameGenerator;

public class HeroList {
	final int PARTY_SIZE = 4;
	ArrayList<Unit> heroList = new ArrayList<>();
	Unit[] partyList;
	
	public void setParty() {

		for (int i = 0; i < 4; i++) {
			heroList.get(i).party = true;
		}
		partyList = new Unit[PARTY_SIZE];
		int n = 0;
		for (int i = 0; i < heroList.size(); i++) {
			if (heroList.get(i).party == true) {
				partyList[n] = heroList.get(i);
				n += 1;
			}
		}
	}
	
	public Unit getHeroUnit(int num) {
		return heroList.get(num);
	}
	
	public void callUnit() {
		
		Random rand = new Random();
		
		int ran = rand.nextInt(8) + 2;
		String name = NameGenerator.createName(1);
		int hp = ran * 11;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, hp, att, def);

		System.out.println("=====================================");
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [체력 : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.print("[공격력 : " + att + "]");
		System.out.println(" [방어력 : " + def + "]");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		heroList.add(temp);
	}
}
