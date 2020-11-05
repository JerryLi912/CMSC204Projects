import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
/**
 * This is the Data Manager class. 
 * This class has add(), get(int crn), readFile(), and showAll() methods
 * @author jerry
 *
 */
public class CourseDBManager implements CourseDBManagerInterface {
		
	//set hashTable size to 10
	CourseDBStructure structure = new CourseDBStructure(20);
	CourseDBElement element;
	/**
	 * This method adds the Course Information by user input in the GUI
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		String stringCRN = Integer.toString(element.getCRN());
		int index = structure.getHashIndex(stringCRN);
		ListIterator<CourseDBElement> iterator = structure.hashTable[index].listIterator();
		//comparing hashCode to see if the object already existed in the list
		//if the object already existed, don't add it 
		while(iterator.hasNext()) {
			if(iterator.next().compareTo(element) == 0) {
				return;
			}
		}		
		structure.hashTable[index].addLast(element);		
		return;
	}

	/**
	 * This method gets the Course CRN in type integer
	 */
	@Override
	public CourseDBElement get(int crn) {
		String stringCRN = Integer.toString(crn);
		int hashedKey = structure.getHashIndex(stringCRN);
		ListIterator<CourseDBElement> iterator = (ListIterator<CourseDBElement>) structure.hashTable[hashedKey].listIterator();
		while(iterator.hasNext()) {
			element = iterator.next();
			if(element.compareTo(element) == 0) {
				return element;
			}
		}
		return null;
	}

	/**
	 * This method reads file that contains course information
	 * and adds them to hashTable
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner inputFile = new Scanner(input);
		int t = 0;
		int count_whiteSpaces = 0;
		
		while (inputFile.hasNext()) {
			String temp;
			String courseID = null;
			int course_CRN = 0;
			int course_credits = 0;
			String course_room = null;
			String course_instructor = null;
			
			String course_content = inputFile.nextLine();
			temp = course_content;
			System.out.println(course_content);
			
			//extract string content from the file
			for(int i = 0; i < course_content.length(); i++) {
				if(temp.charAt(i) == ' ') {
					count_whiteSpaces++;
					if(count_whiteSpaces == 1) {
					courseID = temp.substring(0, i);
					System.out.println("extracted courseID: " + courseID);
					}
					else if(count_whiteSpaces == 2) {
						course_CRN = Integer.parseInt(temp.substring(i-5, i));
						System.out.println("extracted courseCRN: " + course_CRN);
					}
					else if(count_whiteSpaces == 3) {
						course_credits = Integer.parseInt(temp.substring(i-1, i));
						System.out.println("extracted courseCredits: " + course_credits);
					}
					else if(count_whiteSpaces == 4) {
						t = i;
						if(temp.charAt(i-1) == 'g') {
						course_room = temp.substring(i-17, i);
						}
						else {
						course_room = temp.substring(i-5, i);
						}
						System.out.println("extracted room number: " + course_room);
					}
					if(count_whiteSpaces == 4 && temp.charAt(i+1) != ' ') {
						course_instructor = temp.substring(t+1, course_content.length());
						System.out.println("extracted instructor's name: " + course_instructor);
					}
					
				}	
			}//end for loop
			
			add(courseID, course_CRN, course_credits, course_room, course_instructor);
			count_whiteSpaces = 0;
		}//end while loop
		inputFile.close();
	}

	/**
	 * This method displays every thing added to the hashTable in ArrayList
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> list = new ArrayList<>();
		String course;
		for(int i = 0; i < structure.hashTable.length; i++) {
			ListIterator<CourseDBElement> iterator = structure.hashTable[i].listIterator();
			while(iterator.hasNext()) {
				course = iterator.next().toString();
				list.add("\n" + course);
			}
		}
		
		return list;
	}
	
	
	//test for class
	public static void main(String[] agrs) {
		File input = new File("courses.txt");
		CourseDBManager manager = new CourseDBManager();
		manager.add("M1201", 39999, 4, "R33", "weweG");
		manager.add("M1113", 40000, 4, "R13", "wsdG");
		manager.add("M1231", 50000, 4, "R35", "wgwfG");
		manager.add("M4532", 39999, 4, "R89", "wfG");
		System.out.println(manager.get(39999));
		System.out.println(manager.get(40000));
		System.out.println(manager.get(50000));
		try {
			manager.readFile(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(manager.showAll());
	}
}
