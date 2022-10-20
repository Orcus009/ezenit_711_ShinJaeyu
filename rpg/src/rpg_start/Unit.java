package rpg_start;

// Monster & Hero
// 공통분모만 멤버로 책정
public class Unit {
	public final String NAME;
	public final int MAX_HP;
	
	private int hp;
	private int att;
	private int def;
	public boolean party;
	
	public Unit(String name, int hp, int att, int def) {
		NAME = name;
		MAX_HP = hp;
		this.hp = MAX_HP;
		this.att = att;
		this.def = def;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

}
