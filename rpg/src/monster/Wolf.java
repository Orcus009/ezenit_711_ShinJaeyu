package monster;

import util.NameGenerator;

public class Wolf extends Monster {
	
	private final static int MAX_HP = 110;
	private final static int MIN_HP = 70;
	private final static int MAX_ATT = 20;
	private final static int MIN_ATT = 10;
	private final static int MAX_DEF = 8;
	private final static int MIN_DEF = 5;
	
	public Wolf() {
		super(NameGenerator.createName(NameGenerator.MONSTER), 
				(int)Math.floor(Math.random() * (MAX_HP - MIN_HP) + MIN_HP), 
				(int)Math.floor(Math.random() * (MAX_ATT - MIN_ATT) + MIN_ATT), 
				(int)Math.floor(Math.random() * (MAX_DEF - MIN_DEF) + MIN_DEF));
		System.out.printf("Wolf %s의 출현\n", NAME);
		System.out.printf("[hp : %d][att : %d][def : %d]\n", super.getHp(), super.getAtt(), super.getDef());
	}
}
