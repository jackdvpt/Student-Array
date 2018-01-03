package students;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class StudentArrayService {


	/** Read student array from the keyboard. - keyboard.close() removed due to it breaking the code
	 *  Precondition: user complies with instructions and enters sensible data.
	 *                See definition of Student class for precise meaning of "sensible data".)
	 *  Postcondition: a valid full array containing the student records is returned.
	 */
	public static Student [] readList() {
		System.out.println("Please enter the number of students:");
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in); //The keyboard.close(); was removed from this due to it breaking the code later on.
		int numStudents = keyboard.nextInt();
		Student [] list = new Student[numStudents];
		Student theStudent;
		for (int i = 0; i < numStudents; i++) {
			System.out.println("Enter data for next student.");
			theStudent = new Student();
			theStudent.readStudent();
			list[i] = theStudent;
		}
		return list;
	}

	/**
	 * Display list of students' results.
	 * Precondition: the given list is assumed to be valid and fully initialized.
	 * Postcondition: the list is displayed on the console.
	 */
	public static void displayList(Student [] list) {
		for (int i = 0; i < list.length; i++) {
			System.out.printf("%-15s",  list[i].getName());
			System.out.printf("%5.1f", list[i].getScore());
			System.out.println();
		}
	}

	/**
	 * Compute and return average score for given list.
	 * Precondition: the given list is assumed to be valid and fully initialized.
	 * Postcondition: the average score is returned if list is not empty;
	 *              otherwise 0.0 is returned.
	 */
	public static double averageScore(Student [] list) {
		if (list.length > 0){
			int listLength = list.length;
			double sum = 0.0;
			for(int i = 0; i < listLength; i++){
				sum = sum + list[i].getScore(); //adds each score to the sum
			}
			double average = 0.0;
			average = sum / listLength;
			return average;
		} else {
			return 0.0; //If the list is empty then no average (this isn't actually possible though)
		}
	}

	/**
	 * Sort student list into alphabetical order on name using Insertion Sort.
	 * Precondition: the given list is assumed to be valid and fully initialized.
	 * Postcondition: the list becomes sorted with respect to name.
	 */
	public static void sortByName(Student [] list) {
		int j;
		for(int i = 1; i < list.length; i++){
			j = i;
			while ((j > 0) && list[j].getName().compareTo(list[j-1].getName()) < 0){
				Student temp = list[j - 1];
				list[j - 1] = list[j];
				list[j] = temp;
				j--;
			}
		}
	}

	/**
	 * Retrieve score for specified student in list, via binary search.
	 * If student is not in list, return -1.0.
	 * Precondition: the given list is assumed to be valid,
	 *            fully initialized and sorted by name.
	 * Postcondition: returns score for specified student (aName)
	 *             if in list; otherwise return -1.0.
	 */
	public static double retrieveScore(Student [] list, String aName) {
		int first = 0;
		int last = list.length-1;

		while (first <= last){
			int mid = (first + last)/2;
			String middle = list[mid].getName();
			if (aName.equalsIgnoreCase(middle)){
				return list[mid].getScore();
			}
			if (middle.compareToIgnoreCase(aName) > 0){ //Item is in the lower half
				last = mid -1;

			}
			if (middle.compareToIgnoreCase(aName) < 0){ //Item is in the upper half
				first = mid +1;
			}
		}
		if (first > last){
			return -1; //Not found
		}
		return list[last].getScore();//returns the score, based off its location
	}
	/**
	 * "Reverse sort" list into merit order on score using Selection Sort.
	 * Precondition: the given list is assumed to be valid and
	 *               fully initialized.
	 * Postcondition: the list becomes sorted by merit.
	 *                That is, the list becomes "reverse sorted"
	 *                with respect to score.
	 */
	public static void meritSort(Student [] list) {
		for(int i = list.length -1; i >0; i--){
			int first = 0;
			for (int j = 1; j <= i; j++){
				if (list[j].getScore() < list[first].getScore()){
					first = j; // Largest item, to be moved up.
				}
				Student temp = list[first];
				list [first] = list[i];
				list [i] = temp;
			}

		}
	}



	/**
	 * Create and return a deep copy of the given student array. 
	 * Precondition: the given list is assumed to be valid and
	 *               fully initialized.
	 * Postcondition: a deep copy of the given list is created and returned.
	 *               (Note that an array is said to be a *deep copy* of
	 *               another array if it is a copy which has no references in common
	 *               with the original.) 
	 */
	public static Student [] deepCopy(Student [] list) {
		Student [] listCopy = new Student[list.length];
		for(int i = 0; i<list.length;i++){
			listCopy[i] = list[i]; //copies each element from original list into the new one
		}
		return listCopy;
	}

	/** Read student array from named text file.
	 *  Precondition: the basic expectation is that
	 *           the named file is correctly placed within the project folder; and
	 *           that it has the correct format: namely, the number of students appears on first line;
	 *           then each subsequent line contains an individual student record,
	 *           with the fields separated by a space.
	 *           **The family name field of a data text file can be assumed to be one word only**.
	 *  Postcondition: if the file is found as expected,
	 *             an array which stores the list of student records is returned;
	 *             otherwise the exception is handled appropriately.
	 */
	@SuppressWarnings("resource")
	public static Student [] readListFromFile(String filename) {
		Scanner fileIn = null;
		try{
			fileIn = new Scanner(new FileInputStream(filename+".txt"));
			int numStud = fileIn.nextInt();
			Student [] list = new Student [numStud];
			Student currentStudent;
			for (int i = 0; i <numStud; i++){
				currentStudent = new Student();
				String name = fileIn.next(); //This is where exceptions would go to prevent the number of entries, and String and double checking would go. 
				currentStudent.setName(name);
				double score = fileIn.nextDouble();
				if (score <= 100.0){
				currentStudent.setScore(score);
				} else {
					System.out.println("Error, one of the records has a score of more than 100.0. This is not possible");
					return null;
					
				}
				list [i] = currentStudent; //Moves the student into the permanent list
			}
			//A close to the scanner is not here as it breaks other functions
			return list;
		} catch(FileNotFoundException f){
			System.out.println("Error, file not found");
			return null; //returns null so the main method knows that the file was not found, making it repeat the process.
		}
	}

	/**
	 * Sort list by name using binary insertion sort.
	 * Precondition: the given list is valid and fully initialized.
	 * Postcondition: the list becomes sorted by name.
	 */
	public static void specialSortByName(Student [] a) {
		for (int i=0;i<a.length;++i){
			Student current=a[i];
			int highest=0;
			int lowest=i;
			while (highest<lowest){
				int middle=(highest+lowest)/2;
				if (current.getName().compareToIgnoreCase(a[middle].getName() ) > 0){ // It should be positioned in the right hand side
					highest=middle+1;
				}
				else if (current.getName().compareToIgnoreCase(a[middle].getName() ) < 0) { // it should be positioned in the left hand side
					lowest=middle;
				}
			}
			for (int j=i;j>highest;--j){
				Student toBeReplaced = a[j-1];
				a[j-1]=a[j];
				a[j]=toBeReplaced;
			}
		}
	}


	/**
	 * Your main method should drive a simple interactive demonstration of the
	 * capabilities of your other methods. The kind of demo we want is
	 * briefly described in Section 2 of the Assign 2 description 
	 * document. Your main method will not be subject to JUnit testing.
	 */
	public static void main(String[] args) {
		System.out.println("Welcome.");
		boolean selection = false; //Boolean that states if the user has made a decision yet
		boolean errorCheck = false; //Boolean for error checking, if the input is unknown it will repeat, allowing multiple attempts
		Student [] myArray = null; //Array to hold the student list
		Scanner keyboard = null; //Declaring the keyboard for all statements to use.
		while(selection == false){
			while(errorCheck == false){
				System.out.print("How would you like to enter data | MANUAL or FILE : ");
				keyboard = new Scanner(System.in);
				String fileType = keyboard.next();

				if (fileType.equalsIgnoreCase("manual")){ //Manual input
					System.out.println("You have chosen manual");
					myArray = readList();
					displayList(myArray);
					errorCheck = true;
					selection = true;
				} else if (fileType.equalsIgnoreCase("file")){ //File import
					System.out.println("You have chosen file");
					System.out.print("Please enter file name : ");
					String fileName = keyboard.next();
					myArray = readListFromFile(fileName);
					if (myArray == null){ //If null, that means the file wasn't found. It then loops back to the top, repeating the process
						selection = false;
						errorCheck = false;
					} else {
						System.out.println("File found"); // file found and now loading it in
						displayList(myArray);
						errorCheck = true;
					}
				} else if (!fileType.equalsIgnoreCase("file") || !fileType.equalsIgnoreCase("manual")){ //invalid input. allows user to try entry again
					System.out.println("Error, name not recognised. Please input either 'file' or 'manual'");
					selection = false;
					errorCheck = false;
				}
			}
			System.out.print("Is this list correct ? : "); //Confirmation, allowing user to double check data was entered correctly
			keyboard = new Scanner(System.in);
			if(keyboard.hasNext()){
				String YesNo = keyboard.next();
				if (YesNo.equalsIgnoreCase("yes")){
					System.out.println("Confirmed.");
					selection = true;
				}
				if (YesNo.equalsIgnoreCase("no")){
					System.out.println("Try Again");
					myArray = null;
					selection = false;
					errorCheck = false;

				}
			}
		}


		boolean choice = false;
		System.out.println("Sorting ...");
		specialSortByName(myArray);
		System.out.println("Sorted");
		while(choice == false){
			System.out.println("Choose what you would like to do, by typing out one of the words");
			System.out.print("What would you like to do? | AVERAGE | SEARCH |  MERIT | PRINT | EXIT: "); //each keyword links to the function

			String fileType = keyboard.next();
			if (fileType.equalsIgnoreCase("average")){
				System.out.println("The average score for the class is  " + averageScore(myArray));
			} else if (fileType.equalsIgnoreCase("print")){
				displayList(myArray);
			} else if (fileType.equalsIgnoreCase("search")){
				System.out.print("What Name are you looking for? : ");
				String Name = keyboard.next();
				System.out.println(Name + " has a score of : " + retrieveScore(myArray, Name));
			} else if (fileType.equalsIgnoreCase("exit")){
				choice = true;
			}
			else if (fileType.equalsIgnoreCase("merit")){
				Student [] meritList = deepCopy(myArray);
				meritSort(meritList);
				displayList(meritList);

			}
		}
		System.out.println("Goodbye.");
	}


}
