import dataStructures.Chain;

public class Student {
	private String studentCode;
	private Major studentMajor;
	private float GPA;
	private Chain lessons;
	
	public Student() {
		studentCode = null;
		studentMajor = null;
		lessons = null;
	}
	
	public Student(String sCode, Major sMajor) {
		studentCode = sCode;
		studentMajor = new Major(sMajor);
		lessons = new Chain(); 
	}
	
	public String getCode() {
		return studentCode;
	}
	
	public float getGPA() {
		float sumCredit = 0;
		float sumQuality = 0;
		for (int i = 0; i < lessons.size(); i++) {
			sumCredit += ((Lesson)lessons.get(i)).learned.getCredit();
			sumQuality += (((Lesson)lessons.get(i)).unelgeeOnoo() * ((Lesson)lessons.get(i)).learned.getCredit());
			
		}
		GPA = sumQuality / sumCredit;
		return GPA;
	}
	
	public Major getMajor() {
		return studentMajor;
	}
	
	public Chain getLessons() {
		return lessons;
	}
	
	public void addLesson(Subject sub, int grade) {
		Lesson al = new Lesson(sub, grade);
		if (lessons.indexOf(al) == - 1) {
			lessons.add(lessons.size(), al);
		}
		
	}
	
	public static void displayHeader() {
		String titleTemplate = "%-20s %15s %10s\n";
		System.out.println("******* Оюутнуудын голч онооны жагсаалт ************");
		System.out.printf(titleTemplate, "Оюутны код", "Үзсэн хичээлийн тоо", "Голч оноо");
	}
	
	public void displayGPA() {
		String titleTemplate = "%-20s %15s %10.2f\n";
		System.out.printf(titleTemplate, this.studentCode, this.lessons.size(), this.getGPA());
	}
	
	
	
}
