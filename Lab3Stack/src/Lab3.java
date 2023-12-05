import java.util.Scanner;


public class Lab3 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		// TODO Auto-generated method stub
		MyStack mainStack = null;
		Object [] list = null;
		
		
		while (true) {
			System.out.println("1. Shine stack uusgeh");
			System.out.println("2. Stack ruu element nemeh");
			System.out.println("3. Stack aas element hasah (pop)");
			System.out.println("4. Stack-iig list ruu shiljuuleh");
			System.out.println("5. Stack-iin elementuudiig randomoor bairluulah");
			System.out.println("6. Stack-iin elentuudiin davhtsaliig arilgah");
			System.out.println("7. Stack deer list nemeh");
			System.out.println("8. Exit program");
			System.out.println("9. Stack hevleh");
			int songolt = scanner.nextInt();
			
			if (songolt == 1) {
				System.out.println("1. Stack-iin hemjee ogoh");
				System.out.println("2. Default hemjeegeer neeh (10)");
				int dedSongolt = scanner.nextInt();
				if (dedSongolt == 1) {
					System.out.println("Hemjeegee oruulna uu: ");
					int hemjee = scanner.nextInt();
					if (hemjee > 0) {
						mainStack = new MyStack(hemjee);
						System.out.println(hemjee + " hemjeetei stack uusgelee.");
					} else {
						System.out.println("Hemjee 0 ees ih baih ystoi");
					}
				} else if (dedSongolt == 2) {
					mainStack = new MyStack();
					System.out.println("Default hemjeegeer Stack-iig uusgelee");
				} else {
					System.out.println("Buruu utga oruulsan baina.");
				}
			} else if (songolt == 2) {
				if (mainStack == null) {
					System.out.println("Stack uusgeegui baina");
				} else {
					System.out.println("Nemeh elementiig oruulna uu");
					int element = scanner.nextInt();
					mainStack.push((Object)element);
					System.out.println("Elementiig nemsen.");
				}
 			} else if (songolt == 3) {
 				if (mainStack == null) {
					System.out.println("Stack uuseegui baina.");
				} else {
					System.out.println("Popped element: " + mainStack.pop());
				}
				
			} else if (songolt == 4) {
				if (mainStack == null) {
					System.out.println("Stack uuseegui baina.");
				} else {
					list = mainStack.toArray();
					
					System.out.println(list.toString());
				}
			} else if (songolt == 5) {
				if (mainStack == null) {
					System.out.println("Stack uuseegui baina.");
				} else {
					
					mainStack = new MyStack(mainStack.rand());
					System.out.println("Random stack: " + mainStack.toString());
				}
				
			} else if (songolt == 6) {
				if (mainStack == null) {
					System.out.println("Stack uuseegui baina.");
				} else {
					mainStack = new MyStack(mainStack.unique());
					System.out.println("Unique stack: " + mainStack.toString());
				}
				
			} else if (songolt == 7) {
				if (mainStack == null) {
					System.out.println("Stack uuseegui baina.");
				} else {
					System.out.println("Nemeh listiin hemjeeg oruulna: ");
					int hemjee = scanner.nextInt();
					Object [] elements = new Object[hemjee];
					for(int i = 0; i < hemjee; i++) {
						System.out.println((i + 1) + " elementiig oruulna uu");
						elements[i] = (Object)scanner.nextInt();
					}
					mainStack.addRange(elements);
					System.out.println("Listiig nemsen." + mainStack.toString());
					
				}
				
			} else if (songolt == 8) {
				break;
			} else if (songolt == 9) {
				if (mainStack == null) {
					System.out.println("Stack uuseegui baina.");
				} else {
					System.out.println(mainStack.toString());
				}
			}
			
			else {
				System.out.println("Buruu utga oruulsan baina.");
			}
			
		}
		scanner.close();
		
		
	}

}
