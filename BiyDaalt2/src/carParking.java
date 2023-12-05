import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class carParking extends ArrayStack {
	
	private ArrayStack temppark;
	
	public carParking() {
		super(10);
		temppark = new ArrayStack(10);
	}
	
	public void input(String number) {
		if (this.top == this.stack.length - 1) {
			System.out.println("Park duursen uchir " + number + " dugaartai mashiniig oruulah bolomjgui.");
		} else {
			this.push(number);
			System.out.println(number + " dugaartai mashiniig amjilttai oruulav.");
		}
	}
	
	public void output(String number) {
		if (this.empty()) {
			System.out.println("Park hoosen baina. " + number + " dugaartai mashin zogsoold baihgui. ");
		} else {
			this.process(number);
		}
	}
	
	public void process(String number) {
		int k = 0;
		while (!this.empty()) {
			if (!this.peek().equals(number)) {
				temppark.push(this.pop());
				k++;
			} else {
				break;
			}
		}
		
		if (this.empty()) {
			System.out.println(number + " dugaartai mashin zogsoold baihgui. ");
		} else {
			System.out.println(k + " mashin tur gargasnii daraa " + this.pop() + " dugaartai mashin zogsooloos garlaa. ");
		}
		
		while (!temppark.empty()) {
			this.push(temppark.pop());
		}
		
		if (k > 0 && k <= this.top) {
			System.out.println(k + " mashiniig butsaaj park ruu oruulav.");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		carParking cp = new carParking();
		
		try {
			File file = new File("cars.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				String [] inputline = sc.nextLine().split(" ");
				if (inputline[0].equals("A")) {
					cp.input(inputline[1]);
				} else {
					cp.output(inputline[1]);
				}
			}
			sc.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: " + ex.getMessage());
			System.exit(0);
		}
	}

}
