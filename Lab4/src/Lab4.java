import java.util.Scanner;

public class Lab4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Hash table iin hemjeeg oruulna uu: ");
		int hemjee = sc.nextInt();
		MyHashTable hashTable = new MyHashTable(hemjee);
		
		while (true) {
			System.out.println("");
			System.out.println("");
			System.out.println("1. Hevleh");
			System.out.println("2. Key: Element nemeh");
			System.out.println("3. Hailt hiih");
			System.out.println("4. Element update hiih");
			System.out.println("5. Key update hiih");
			System.out.println("6. Key: Element ustgah");
			System.out.println("9. EXIT program");
			
			int songolt = sc.nextInt();
			if (songolt == 1) {
				hashTable.printHashTable();
			} else if (songolt == 2) {
				
				sc.nextLine();
				
				System.out.println("Key oruulna uu: ");
				String key = sc.nextLine();
				
				System.out.println("Element oruulna uu: ");
				int element = sc.nextInt();
				
				try {
					hashTable.put(key, element);
				} catch (IllegalArgumentException e) {
					System.out.println("Hashtable duursen baina.");
				}
			} else if (songolt == 3) {
				System.out.println("Haih elementiin tulhuuriig oruulna uu: ");
				sc.nextLine();
				String key = sc.nextLine();
				hashTable.searchElement(key);
			} else if (songolt == 4) {
				System.out.println("Update hiih elementiinhaa tulhuuriig oruulna uu: ");
				sc.nextLine();
				String key = sc.nextLine();
				System.out.println("Elementiig oruulna uu: ");
				int element = sc.nextInt();
				
				hashTable.updateElement(key, element);
				
			} else if (songolt == 5) {
				System.out.println("Update hiih elementiinhaa tulhuuriig oruulna uu: ");
				sc.nextLine();
				String key = sc.nextLine();
				System.out.println("Shine tulhuuriig oruulna uu: ");
				String newKey = sc.nextLine();
				
				hashTable.updateKey(key, newKey);
			} else if (songolt == 6) {
				System.out.println("Ustgah elementiin tulhuuriig oruulna uu: ");
				sc.nextLine();
				String key = sc.nextLine();
				hashTable.delete(key);
			} else if (songolt == 9) {
				break;
			} else {
				System.out.println("Buruu songolt hiisen baina.");
			}
			
		}
		sc.close();
	}

}
