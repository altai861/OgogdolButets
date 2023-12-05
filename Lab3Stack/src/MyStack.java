import dataStructures.ArrayStack;
import dataStructures.ArrayLinearList;

import java.util.Arrays;
import java.util.Random;

public class MyStack extends ArrayStack {
	public MyStack() {
		super();
	}
	
	public MyStack(int initialCapacity) {
		super(initialCapacity);
	}
	public MyStack(MyStack ms) {
		super(ms.size());
		MyStack temp = new MyStack();
		while (!ms.empty()) {
			temp.push(ms.pop());
		}
		while (!temp.empty()) {
			Object k = temp.pop();
			
			this.push(k);
			ms.push(k);
		}
	}
	
	public Object[] toArray() {
		Object [] arr = new Object[this.size()];
		MyStack temp = new MyStack();
		int i = 0;
		while (!this.empty()) {
			Object x = this.pop();
			temp.push(x);
			arr[i++] = x;
		}
		while (!temp.empty()) {
			this.push(temp.pop());
		}
		return arr;
	}
	
	public MyStack rand() {
		Object [] arr = this.toArray();
		MyStack ms = new MyStack();
		Random r = new Random();
		ArrayLinearList al = new ArrayLinearList();
		for (int i = 0; i < arr.length; i++) {
			al.add(i,  arr[i]);
		}
		while (!al.isEmpty()) {
			int k = r.nextInt(al.size());
			ms.push(al.get(k));
			al.remove(k);
		}
		return ms;
	}
	
	public MyStack unique() {
		Object [] arr = this.toArray();
		Arrays.sort(arr);
		MyStack temp = new MyStack();
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] != arr[i + 1]) {
				temp.push(arr[i]);
			}
		}
		temp.push(arr[arr.length - 1]);
		return temp;
	}
	
	public void addRange(Object [] elements) {
		
		for (int i = 0; i < elements.length; i++) {
			this.push(elements[i]);
		}
	}
	
	
	public boolean exists(Object element) {
		boolean exists = false;
		MyStack temp = new MyStack();
		while (!this.empty()) {
			Object popped = this.pop();
			if (popped == element) {
				exists = true;
			}
			temp.push(popped);
		}
		while (!temp.empty()) {
			this.push(temp.pop());
		}
		return exists;
	}
}
