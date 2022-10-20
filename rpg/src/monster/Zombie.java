package monster;

import util.NameGenerator;

public class Zombie extends Monster {
	
	private final static int MAX_HP = 150;
	private final static int MIN_HP = 100;
	private final static int MAX_ATT = 35;
	private final static int MIN_ATT = 20;
	private final static int MAX_DEF = 15;
	private final static int MIN_DEF = 10;
	
	public Zombie() {
		super(NameGenerator.createName(NameGenerator.MONSTER), 
				(int)Math.floor(Math.random() * (MAX_HP - MIN_HP) + MIN_HP), 
				(int)Math.floor(Math.random() * (MAX_ATT - MIN_ATT) + MIN_ATT), 
				(int)Math.floor(Math.random() * (MAX_DEF - MIN_DEF) + MIN_DEF));
		System.out.printf("Zombie %s의 출현\n", NAME);
		System.out.printf("[hp : %d][att : %d][def : %d]\n", super.getHp(), super.getAtt(), super.getDef());
	}
}
