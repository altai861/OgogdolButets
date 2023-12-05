
public class Lesson {
	public Subject learned;
	public int score;
	
	public Lesson(Subject l, int sc) {
		learned = new Subject(l);
		score = sc;
	}
	
	public float unelgeeOnoo() {
		if (score >= 96) {
			return 4.0f;
		} else if (score >= 91) {
			return 3.7f;
		} else if (score >= 88) {
			return 3.4f;
		} else if (score >= 84) {
			return 3.0f;
		} else if (score >= 81) {
			return 2.7f;
		} else if (score >= 78) {
			return 2.4f;
		} else if (score >= 74) {
			return 2.0f;
		} else if (score >= 71) {
			return 1.7f;
		} else if (score >= 68) {
			return 1.3f;
		} else if (score >= 64) {
			return 1.0f;
		} else if (score >= 60) {
			return 0.7f;
		} else {
			return 0.0f;
		}
	}
}
