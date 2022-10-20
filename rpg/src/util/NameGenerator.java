package util;

import java.util.Random;

public class NameGenerator {
	
	public static final int HERO = 1;
	public static final int MONSTER = 2;
	
	public static String createName(int type) {
		String name = "";
		
		Random rand = new Random();
		
		if(type == 1) {
			char[] a = {'타','녹','아','키','모','제','티'};
			char[] b = {'이','터','인','아','라','다','리'};
			char[] c = {'버','널','즈','라','스','이','엘'};
			name += a[rand.nextInt(a.length)];
			name += b[rand.nextInt(b.length)];
			name += c[rand.nextInt(c.length)];
		}
		
		if(type == 2) {
			char[] a = {'나','소','오','알','알','모','몰'};
			char[] b = {'미','나','그','비','두','스','락'};
			char[] c = {'라','벨','마','온','인','라','발'};
			name += a[rand.nextInt(a.length)];
			name += b[rand.nextInt(b.length)];
			name += c[rand.nextInt(c.length)];
		}
		return name;
	}
	
}
