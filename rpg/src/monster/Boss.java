package monster;

import util.NameGenerator;

public class Boss extends Monster{
	
	private final static int MAX_HP = 200;
	private final static int MIN_HP = 150;
	private final static int MAX_ATT = 50;
	private final static int MIN_ATT = 30;
	private final static int MAX_DEF = 30;
	private final static int MIN_DEF = 20;
	
	public Boss() {
		super(NameGenerator.createName(NameGenerator.MONSTER), 
				(int)Math.floor(Math.random() * (MAX_HP - MIN_HP) + MIN_HP), 
				(int)Math.floor(Math.random() * (MAX_ATT - MIN_ATT) + MIN_ATT), 
				(int)Math.floor(Math.random() * (MAX_DEF - MIN_DEF) + MIN_DEF));
		System.out.printf("BOSS %s의 출현\n", NAME);
		System.out.printf("[hp : %d][att : %d][def : %d]\n", super.getHp(), super.getAtt(), super.getDef());
	}
}
