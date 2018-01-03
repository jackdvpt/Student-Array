package students;

/**
 * Class to store family name and exam score data for a student.
 * Do not change this class!
 * @author Scott McCallum, September 2015
 */
	
import java.util.Scanner;

public class Student {
	private String name;  // Family name
	                      // e.g. Jones, Smith-White, Chang, de Witt, 
	private double score;  // Exam score. This should be a value in the range [0.0, 100.0].
	
	/**
	 * Set name.
	 * Precondition: aName should be a valid family name
	 * Postcondition: value of name is set to aName.
	 */
	public void setName(String aName) {
		name = aName;
	}
	
	/**
	 * Set score.
	 * Precondition: aScore should be in range [0.0, 100.0].
	 * Postcondition: value of score is set to aScore.
	 */
	public void setScore(double aScore) { 
			score = aScore;
	}
	
	/**
	 * Default constructor.
	 */
	public Student() {
		setName("Smith");
		setScore(0.0);
	}
	
	/**
	 * Constructor with two parameters.
	 * Precondition: aName should be a valid family name, and
	 * aScore should be in range [0.0, 100.0].
	 * Postcondition: values of name and score are set accordingly.
	 */
	public Student(String aName, double aScore) {
		setName(aName);
		setScore(aScore);
	}

	/**
	 * Return true if this student equals aStudent, false otherwise.
	 */
	public boolean equals(Student aStudent) {
		return name.equals(aStudent.name) && score == aStudent.score; 
	}
	
	/**
	 * Read student data.
	 * Precondition: user enters sensible family name and score data:
	 *               the family name could be any valid name from
	 *               the telephone book (e.g. Jones, Smith-White,
	 *               Chang, de Witt, etc.); and the score should be
	 *               in the range [0.0, 100.0].
	 * Postcondition: the name and score data are stored in this student.
	 */
	public void readStudent() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter family name:");
		String aName = keyboard.nextLine();
		setName(aName);
		System.out.println("Enter exam score in range [0.0, 100.0]:");
		double aScore = keyboard.nextDouble();
		while (aScore < 0.0 || aScore > 100.0) {
			System.out.println("Invalid score.");
			System.out.println("Enter exam score in range [0.0, 100.0]:");
			aScore = keyboard.nextDouble();
		}
		setScore(aScore);
	}
	
	public String getName() {
		return name;
	}
	
	public double getScore() {
		return score;
	}

}
