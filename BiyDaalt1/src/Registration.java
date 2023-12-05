import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
import dataStructures.ArrayLinearList;
import dataStructures.Chain;

public class Registration {
	
	public ArrayLinearList studentList;
	public ArrayLinearList subjectList;
	public ArrayLinearList majorList;
	
	public Registration() {
		studentList = new ArrayLinearList();
		subjectList = new ArrayLinearList();
		majorList = new ArrayLinearList();
	}
	
	public void readSubjectList() {
		try {
			
			File file = new File("Subjects.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				Subject sj = new Subject(sc.nextLine());
				subjectList.add(subjectList.size(), sj);
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void printSubjects() {
		Subject.displayHead();
		for (int i = 0; i < subjectList.size(); i++) {
			((Subject)subjectList.get(i)).display();
		}
	}
	
	public void readMajorList() {
		try {
			
			File file = new File("Professions.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				Major sj = new Major(sc.nextLine());
				majorList.add(majorList.size(), sj);
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void printMajors() {
		Major.displayHead();
		for (int i = 0; i < majorList.size(); i++) {
			((Major)majorList.get(i)).display();
		}
	}
	
	public void printStudents() {
		Student.displayHeader();
		for (int i = 0; i < studentList.size(); i++) {
			((Student)studentList.get(i)).displayGPA();
		}
	}
	
	public int checkStudent(String sc) {
		int ind = -1;
		for (int i = 0; i < this.studentList.size(); i++) {
			if (((Student)this.studentList.get(i)).getCode().equals(sc)) {
				ind = i;
			}
		}
		return ind;
	}
	public int checkMajor(String sc) {
		int ind = -1;
		for (int i = 0; i < this.majorList.size(); i++) {
			if (((Major)this.majorList.get(i)).getCode().equals(sc)) {
				ind = i;
			}
		}
		return ind;
	}
	public int checkSubject(String sc) {
		int ind = -1;
		for (int i = 0; i < this.subjectList.size(); i++) {
			if (((Subject)this.subjectList.get(i)).getCode().equals(sc)) {
				ind = i;
			}
		}
		return ind;
	}
	
	public void readStudents() {
		studentList = new ArrayLinearList();
		
		try {
			File file = new File("Exams.txt");
			Scanner sc = new Scanner(file);
			
			int k = 0;
			while (sc.hasNext()) {
				String ss = sc.nextLine();
				String [] strArr = ss.split("/");
				int index = checkStudent(strArr[0]);
				if (index == -1) {
					int majorIndex = checkMajor(strArr[0].substring(0, 2));
					Student newStudent = new Student(strArr[0], ((Major)majorList.get(majorIndex)));
					int subjectIndex = checkSubject(strArr[1]);
					newStudent.addLesson(((Subject)subjectList.get(subjectIndex)), Integer.parseInt(strArr[2]));
					studentList.add(k, newStudent);
					k++;
					
				} else {
					
					int subjectIndex = checkSubject(strArr[1]);
					((Student)studentList.get(index)).addLesson(((Subject)subjectList.get(subjectIndex)), Integer.parseInt(strArr[2]));
					
				}
			}
			
			sc.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void tripleF() {
		for (int i = 0; i < studentList.size(); i++) {
			int countOfF = 0;
			Chain lessons = ((Student)studentList.get(i)).getLessons();
			for (int j = 0; j < lessons.size(); j++) {
				if (((Lesson)lessons.get(j)).score < 60) {
					countOfF++;
				}
			}
			if (countOfF >= 3) {
				((Student)studentList.get(i)).displayGPA();
			}
		}
	}
	
	public void printAll() {
		for (int i = 0; i < majorList.size(); i++) {
			try {
				File file = new File("Exams.txt");
				Scanner sc = new Scanner(file);
				while (sc.hasNext()) {
					String line = sc.nextLine();
					String [] strArr = line.split("/");
					
					if (((Major)majorList.get(i)).getCode().equals(strArr[0].substring(0, 2))) {
						System.out.println(strArr[0] + " " + strArr[1] + " " + strArr[2]);
					}
				}
				sc.close();
			} catch(FileNotFoundException e) {
				System.out.println("Error: " + e.getMessage());
				System.exit(0);
			}
			System.out.println("\n");
		}
		
	}
	
	public void printAllHicheel() {
		for (int i = 0; i < subjectList.size(); i++) {
			try {
				File file = new File("Exams.txt");
				Scanner sc = new Scanner(file);
				while (sc.hasNext()) {
					String line = sc.nextLine();
					String [] strArr = line.split("/");
					
					if (((Subject)subjectList.get(i)).getCode().equals(strArr[1])) {
						System.out.println(strArr[0] + " " + strArr[1] + " " + strArr[2]);
					}
				}
				sc.close();
			} catch(FileNotFoundException e) {
				System.out.println("Error: " + e.getMessage());
				System.exit(0);
			}
			System.out.println("\n");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Registration rr = new Registration();
		rr.readSubjectList();
		rr.printSubjects();
		
		rr.readMajorList();
		rr.printMajors();
		
		rr.readStudents();
		rr.printStudents();
		
		
		while (true) {
			System.out.println("1. Нийт хичээлүүдийн жагсаалтыг харуулах");
			System.out.println("2. Нийт мэргэжлүүдийн жагсаалтыг харуулах");
			System.out.println("3. Нийт оюутны дундаж голч дүнг харуулах");
			System.out.println("4. 3-аас дээш хичээлд \"F\" үнэлгээ авсан хасагдах оюутны жагсаалт хэвлэх");
			System.out.println("5. Хичээл бүрээр оюутнуудын дүнгийн жагсаалтыг хэвлэх");
			System.out.println("6. Мэргэжил бүрээр оюутнуудын дүнгийн жагсаалтыг харуулах");
			System.out.println("7. Exit");

			int songolt = scanner.nextInt();
			
			if (songolt == 1) {
				rr.printSubjects();
			} else if (songolt == 2) {
				rr.printMajors();
			} else if (songolt == 3) {
				rr.printStudents();
			} else if (songolt == 4) {
				rr.tripleF();
			} else if (songolt == 5) {
				while (true) {
					rr.printSubjects();
					System.out.println("Аль хичээлийн дүнгүүдийг харах вэ? хичээлийн кодыг оруулна уу.");
					System.out.println("Бүх хичээлүүдийн дүнгүүдийг цувуулах бол all гэж бичнэ үү");
					System.out.println("Гарах бол exit гэж бичнэ үү.");
					
					String hicheel = scanner.nextLine();
					
					if (hicheel.equals("exit")) {
						break;
					} else if (hicheel.equals("all")) {
						//END ALL IIG BICHNE.
						rr.printAllHicheel();
					} else {
						try {
							
							File file = new File("Exams.txt");
							Scanner sc = new Scanner(file);
							while (sc.hasNext()) {
								String[] strArr = sc.nextLine().split("/");
								if (strArr[1].equals(hicheel)) {
									System.out.println(strArr[0] + "  " + strArr[1] + "  " + strArr[2]);
								}
								
							}
							sc.close();
							
						} catch (FileNotFoundException e) {
							System.out.println("Error: " + e.getMessage());
							System.exit(0);
						}
					}
				}
				
			} else if (songolt == 6) {
				while (true) {
					rr.printMajors();
					System.out.println("Аль мэргэжлийн дүнгүүдийг харах вэ? мэргэжлийн кодыг оруулна уу.");
					System.out.println("Бүх мэргэжлүүдийн дүнгүүдийг цувуулах бол all гэж бичнэ үү");
					System.out.println("Гарах бол exit гэж бичнэ үү.");
					
					String mergejil = scanner.nextLine();
					
					if (mergejil.equals("exit")) {
						break;
					} else if (mergejil.equals("all")) {
						//END ALL IIG BICHNE.
						rr.printAll();
					} else {
						try {
							
							File file = new File("Exams.txt");
							Scanner sc = new Scanner(file);
							while (sc.hasNext()) {
								String[] strArr = sc.nextLine().split("/");
								if (strArr[0].substring(0, 2).equals(mergejil)) {
									System.out.println(strArr[0] + "  " + strArr[1] + "  " + strArr[2]);
								}
								
							}
							sc.close();
							
						} catch (FileNotFoundException e) {
							System.out.println("Error: " + e.getMessage());
							System.exit(0);
						}
					}
				}
			} else if (songolt == 7) {
				break;
			} else {
				System.out.println("Buruu utga oruulsan baina.");
			}

		}
		scanner.close();
	}

}
