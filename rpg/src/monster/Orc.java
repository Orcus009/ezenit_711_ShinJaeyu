package monster;

import util.NameGenerator;

public class Orc extends Monster {

	private final static int MAX_HP = 80;
	private final static int MIN_HP = 50;
	private final static int MAX_ATT = 10;
	private final static int MIN_ATT = 5;
	private final static int MAX_DEF = 5;
	private final static int MIN_DEF = 1;
	
	public Orc() {
		super(NameGenerator.createName(NameGenerator.MONSTER), 
				(int)Math.floor(Math.random() * (MAX_HP - MIN_HP) + MIN_HP), 
				(int)Math.floor(Math.random() * (MAX_ATT - MIN_ATT) + MIN_ATT), 
				(int)Math.floor(Math.random() * (MAX_DEF - MIN_DEF) + MIN_DEF));
		System.out.printf("Orc %s의 출현\n", NAME);
		System.out.printf("[hp : %d][att : %d][def : %d]\n", super.getHp(), super.getAtt(), super.getDef());
	}
	
}
