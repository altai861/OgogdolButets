
public class Major {
	private String majorCode;
	private String majorName;
	
	public Major() {
		majorCode = null;
		majorName = null;
	}
	
	public Major(String ss) {
		String [] strval;
		strval = ss.split("/");
		majorCode = strval[0];
		majorName = strval[1];
	}
	
	public Major(Major mj) {
		majorCode = mj.majorCode;
		majorName = mj.majorName;
	}
	
	public void setCode(String code) {
		majorCode = code;
	}
	
	public String getCode() {
		return majorCode;
	}
	
	public void setName(String name) {
		majorName = name;
	}
	
	public String getName() {
		return majorName;
	}
	
	public static void displayHead() {
		String titleTemplate = "%-20s %-45s\n";
		System.out.println("***** Мэргэжлүүдийн жагсаалт *****");
		System.out.printf(titleTemplate, "Мэргэжлийн код", "Мэргэжлийн нэр");
		
	}
	
	public void display() {
		String titleTemplate = "%-20s %-45s\n";
		System.out.printf(titleTemplate, majorCode, majorName);

	}
}
