package students;

import static org.junit.Assert.*;
import org.junit.Test;

/*
 * Sample JUnit test class for StudentArrayService.
 * You may wish to add more test routines to this class.
 * The family names used in this sample test class are very simple.
 * Please be aware that your searching and sorting methods could be
 * tested using more complex names e.g. Smith-White, O'Riordan, de Witt.
 * However, for simplicity, your text file reading method will be tested using names
 * which comprise **one word only**.
 */
		
public class StudentArrayServiceTest {
	
	@Test
	public void testAverageScore() {
		Student [] myList = new Student[3];
		myList[0] = new Student("Dalton", 64.0);
		myList[1] = new Student("Lang", 14.0);
		myList[2] = new Student("Cobb", 86.0);
		assertEquals(54.67, StudentArrayService.averageScore(myList), 0.01);
	}
	
	@Test
	public void testSortByName() {
		Student [] myList = new Student[3];
		Student student0 = new Student("Dalton", 65.0);
		Student student1 = new Student("Lang", 77.0);
		Student student2 = new Student("Cobb", 87.0);
		myList[0] = student0;
		myList[1] = student1;
		myList[2] = student2;
		StudentArrayService.sortByName(myList);
		assertTrue(myList[0].equals(student2));
		assertTrue(myList[1].equals(student0));
		assertTrue(myList[2].equals(student1));
	}
	
	@Test
	public void testRetrieveScore() {
		Student [] myList = new Student[3];
		Student student0 = new Student("Dalton", 65.0);
		Student student1 = new Student("Lang", 77.0);
		Student student2 = new Student("Cobb", 87.0);
		myList[0] = student0;
		myList[1] = student1;
		myList[2] = student2;
		StudentArrayService.sortByName(myList);
		assertEquals(87.0, StudentArrayService.retrieveScore(myList, "Cobb"), 0.01);
		assertEquals(77.0, StudentArrayService.retrieveScore(myList, "Lang"), 0.01);
		assertEquals(65.0, StudentArrayService.retrieveScore(myList, "Dalton"), 0.01);
	}
	
	@Test
	public void testMeritSort() {
		Student [] meritList = new Student[4];
		Student student0 = new Student("Dalton", 65.0);
		Student student1 = new Student("Lang", 77.0);
		Student student2 = new Student("Cobb", 87.0);
		Student student3 = new Student("Steve", 100.0);
		meritList[0] = student0;
		meritList[1] = student1;
		meritList[2] = student2;
		meritList[3] = student3;
		StudentArrayService.meritSort(meritList);
		assertTrue(meritList[0].equals(student3));
		assertTrue(meritList[1].equals(student2));
		assertTrue(meritList[2].equals(student1));
		assertTrue(meritList[3].equals(student0));
	}

}
