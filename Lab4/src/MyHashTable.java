import dataStructures.HashTable;
import java.util.Scanner;

public class MyHashTable extends HashTable {
	public MyHashTable(int divisor) {
		super(divisor);
	}
	
	public void printHashTable() {
		for (int i = 0; i < this.table.length; i++) {
			if (table[i] == null) {
				System.out.println("Null");
			} else {
				System.out.println(((HashEntry)table[i]).key + ": " + ((HashEntry)table[i]).element);
			}
		}
	}
	
	public void searchElement(Object key) {
		for (int i = 0; i < this.table.length; i++) {
			if (table[i] == null) {
				continue;
			}
			else if (table[i].key.equals(key)) {
				System.out.println(key + ": " + table[i].element);
				return;
			}
		}
		System.out.println("Tiim tulhuurtei element alga baina. ");
		return;
		
	}
	
	public void printElement(int index) {
		System.out.println(table[index].key + ": " + table[index].element);
	}
	
	public void updateElement(Object theKey, Object theElement) {
		for (int i = 0; i < this.table.length; i++) {
			if (table[i] == null) {
				continue;
			}
			else if (table[i].key.equals(theKey)) {
				table[i].element = theElement;
				System.out.println("Elementiig zassan.");
				return;
			}
		}
		System.out.println("Tanii oruulsan tulhuurtei element baihgui baina.");
	}
	
	public void updateKey(Object theKey, Object theNewKey) {
		for (int i = 0; i < this.table.length; i++) {
			if (table[i] == null) {
				continue;
			}
			else if (table[i].key.equals(theKey)) {
				table[i].key = theNewKey;
				System.out.println("Elementiig zassan.");
				return;
			}
		}
		System.out.println("Tanii oruulsan tulhuurtei element baihgui baina.");
	}
	
	public void delete(Object theKey) {
		Object element = this.get(theKey);
		if (element != null) {
			int b = this.search(theKey);
			this.table[b] = null;
			this.size--;
			System.out.println(theKey + " tulhuurtei element ustgagdlaa.");
			
		} else {
			System.out.println(theKey + " tulhuurtei element oldsongui.");
		}
	}
	
}
