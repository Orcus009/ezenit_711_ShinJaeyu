package rpg_start;

import java.util.Scanner;
import hero.HeroList;

class StartMenu {
	static Scanner scan = new Scanner(System.in);
	
	public void Start() {
		while(true) {
			System.out.println("1.마을");
			System.out.println("2.던전");
			System.out.println("3.레이드");
			System.out.print("목적지 : ");
			int menu = scan.nextInt();
			System.out.println();
			
			if(menu == 1) {
				System.out.println("1.여관");
				System.out.println("2.신전");
				System.out.println("3.돌아가기");
				System.out.println("목적지 : ");
				int inCity = scan.nextInt();
				System.out.println();
				
				if(inCity == 1) {
					System.out.println("여관에서 휴식");
					System.out.println("y/n");
					char rest = scan.next().charAt(0);
					
					if(rest == 'y') {
						
					}
					
					else if(rest == 'n')
						continue;
				}
				
				else if(inCity == 2) {
					System.out.println("영웅 소환");
					System.out.println("소환 y/n");
					char call = scan.next().charAt(0);
					System.out.println();
					
					if(call == 'y') {
						HeroList list = new HeroList();
						list.callUnit();
					}
				}
				
				else if(inCity == 3)
					break;
			}
			
			else if(menu == 2) {
				Game game = new Game();
				game.run();
			}
			
			else if(menu == 3) {
				
			}
		}
	}
}

public class _Main {

	public static void main(String[] args) {
		StartMenu startmenu = new StartMenu();
		startmenu.Start();
		
	}

}
