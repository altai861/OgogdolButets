
public class Subject {
	private String subjectCode;
	private String subjectName;
	private float credit;
	
	public Subject() {
		subjectCode = null;
		subjectName = null;
		credit = 0.0f;
	}
	
	public Subject(String ss) {
		String[] strval;
		strval = ss.split("/");
		subjectCode = strval[0];
		subjectName = strval[1];
		credit = Float.parseFloat(strval[2]);
	}
	
	public Subject(Subject ll) {
		subjectCode = ll.subjectCode;
		subjectName = ll.subjectName;
		credit = ll.credit;
	}
	public void setCode(String sC) {
		subjectCode = sC;
	}
	public void setName(String name) {
		subjectName = name;
	}
	public void setCredit(float cre) {
		credit = cre;
	}
	public String getCode() {
		return subjectCode;
	}
	public String getName() {
		return subjectName;
	}
	public float getCredit() {
		return credit;
	}
	public static void displayHead() {
		String titleTemplate = "%-20s %-50s %10s\n";
		System.out.println("***** Хичээлүүдийн жагсаалт ****");
		System.out.printf(titleTemplate, "Хичээлийн код", "Хичээлийн нэр", "Кредит");
	}
	public void display() {
		String titleTemplate = "%-20s %-50s %10s\n";
		System.out.printf(titleTemplate, subjectCode, subjectName, credit);
	}
}
