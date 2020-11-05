import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This is the hashTable structure class
 * @author jerry
 *
 */
public class CourseDBStructure implements CourseDBStructureInterface {
	
	private int m_table_size;
	protected LinkedList<CourseDBElement>[] hashTable;
	
	//this constructor is to input the estimated hashTable size
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int table_size) {
		this.m_table_size = table_size;
		hashTable = new LinkedList[table_size];
		for(int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new LinkedList<CourseDBElement>();
		}
	}
	
	//this constructor is for testing purposes
	@SuppressWarnings("unchecked")
	public CourseDBStructure(String string, int table_size) {
		this.m_table_size = table_size;
		hashTable = new LinkedList[table_size];
		for(int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new LinkedList<CourseDBElement>();
		}
	}
	
	/** 
	* Use the hashCode of the CourseDatabaseElement to see if it is 
	* in the hashTable.
	* 
	* If the CourseDatabaseElement does not exist in the hashTable,
	* add it to the hashTable.
	* 
	* @param element the CDE to be added
	*/
	@Override
	public void add(CourseDBElement element) {	
		String stringCRN = Integer.toString(element.getCRN());
		int index = getHashIndex(stringCRN);
		ListIterator<CourseDBElement> iterator = hashTable[index].listIterator();
		//comparing hashCode to see if the object already existed in the list
		//if the object already existed, don't add it 
		while(iterator.hasNext()) {
			if(iterator.next().compareTo(element) == 0) {
				return;
			}
		}		
		hashTable[index].addLast(element);		
}

	
	/** 
	* 
	* If the CourseDatabaseElement is in the hashTable, return it
	* If not, throw an IOException
	* 
	* @param element the CDE to be added
	 * @throws IOException 
	*/
	@Override
	public CourseDBElement get(int crn) throws IOException {
		String stringCRN = Integer.toString(crn);
		int hashedKey = getHashIndex(stringCRN);
		ListIterator<CourseDBElement> iterator = hashTable[hashedKey].listIterator();
		CourseDBElement courseDBElement;
		while(iterator.hasNext()) {
			courseDBElement = iterator.next();
			if(courseDBElement.equals(courseDBElement)) {
				return courseDBElement;
			}
		}
		throw new IOException("Sorry, I can't get element...");
	}

	/**
	 * get the size of hashTable
	 */
	@Override
	public int getTableSize() {
		return this.m_table_size;
	}
	
	/**
	 * Get the hashIndex
	 * @param CRN  String input of CRN
	 * @return Returns hashed Index 
	 */
	//the CRN is like the key here
	public int getHashIndex(String CRN) {
		int hashIndex = CRN.hashCode() % hashTable.length;
		return hashIndex;
	}
	
	/**
	 * A method that displays added courses in a arrayList
	 * @return Return ArrayList
	 */
	public ArrayList<String> showAll(){
		ArrayList<String> list = new ArrayList<>();
		String course;
		for(int i = 0; i < hashTable.length; i++) {
			if(hashTable[i].isEmpty()) {
				return null;
			}
			ListIterator<CourseDBElement> iterator = hashTable[i].listIterator();
			while(iterator.hasNext()) {
				course = iterator.next().toString();
				list.add("\n" + course);
			}
		}
		
		return list;
	}
	
	/**
	 * prints the hashTable
	 */
	public void printHashTable() {
		for(int i = 0; i < hashTable.length; i++) {
			if(hashTable[i].isEmpty()) {
				System.out.print("Index " + i + "--> empty");
			}
			else {
				System.out.print("Index " + i + "--> ");
				ListIterator<CourseDBElement> list = hashTable[i].listIterator();
				while(list.hasNext()) {
					System.out.print(list.next().getCRN() + " --> ");
				}
			}
			System.out.println("\n");
		}
	}
	
	
	//class test
	public static void main(String[] args) {
		CourseDBElement s1 = new CourseDBElement("CMSC201", 39999, 3, "R322", "kakakak");
		CourseDBElement s2 = new CourseDBElement("CMSC202", 40000, 3, "R322", "kakakak");
		CourseDBElement s3 = new CourseDBElement("CMSC203", 40480, 3, "R322", "kakakak");
		CourseDBElement s4 = new CourseDBElement("CMSC204", 12121, 3, "R322", "kakakak");
		CourseDBElement s5 = new CourseDBElement("CMSC205", 42343, 3, "R322", "kakakak");
		CourseDBElement s6 = new CourseDBElement("CMSC206", 67655, 3, "R322", "kakakak");
		CourseDBElement s7 = new CourseDBElement("CMSC207", 34545, 3, "R322", "kakakak");
		CourseDBElement s8 = new CourseDBElement("CMSC208", 23233, 3, "R322", "kakakak");
		CourseDBElement s9 = new CourseDBElement("CMSC209", 35455, 3, "R322", "kakakak");
		CourseDBElement s10 = new CourseDBElement("CMSC210",32343, 3, "R322", "kakakak");
		CourseDBElement s11 = new CourseDBElement("CMSC211",45454, 3, "R322", "kakakak");
		CourseDBElement s12 = new CourseDBElement("CMSC212",78988, 3, "R322", "kakakak");
		CourseDBElement s13 = new CourseDBElement("CMSC213",87877, 3, "R322", "kakakak");
		CourseDBElement s14 = new CourseDBElement("CMSC214",66764, 3, "R322", "kakakak");
		CourseDBElement s15 = new CourseDBElement("CMSC214",12121, 3, "R322", "kakakak");
		CourseDBElement s16 = new CourseDBElement("CMSC214",11111, 3, "R322", "kakakak");
		CourseDBElement s17 = new CourseDBElement("CMSC214",23434, 3, "R322", "kakakak");
		CourseDBElement s18 = new CourseDBElement("CMSC214",13675, 3, "R322", "kakakak");
		CourseDBElement s19 = new CourseDBElement("CMSC214",98987, 3, "R322", "kakakak");
		CourseDBElement s20 = new CourseDBElement("CMSC214",80758, 3, "R322", "kakakak");
		
		CourseDBStructure t = new CourseDBStructure(10);
		//test hashTable size
		System.out.println("The hashTable size is: " + t.getTableSize());
		//assuming we are now adding the object, and the key needs to be a string...
		int test1 = s1.getCRN();
		int test2 = s2.getCRN();
		@SuppressWarnings("unused")
		int test3 = s3.getCRN();
		String testt1 = Integer.toString(test1);
		String testt2 = Integer.toString(test2);
		String testt3 = Integer.toString(40480);
		String testt5 = Integer.toString(42343);
		//test collision
		System.out.println("The course will be stored at index: "+ t.getHashIndex(testt1));
		System.out.println("The course will be stored at index: "+ t.getHashIndex(testt2));
		System.out.println("The course will be stored at index: "+ t.getHashIndex(testt3));
		System.out.println("The course will be stored at index: "+ t.getHashIndex(testt5));
		//test add
		System.out.println("Add s1 to s20. Note: I added s1 three times and s14 two times, so the result should not have duplicates.");
		t.add(s1);
		t.add(s1);
		t.add(s2);
		t.add(s3);
		t.add(s4);
		t.add(s5);
		t.add(s6);
		t.add(s7);
		t.add(s8);
		t.add(s14);
		t.add(s9);
		t.add(s10);
		t.add(s11);
		t.add(s12);
		t.add(s13);
		t.add(s1);
		t.add(s14);
		t.add(s15);
		t.add(s16);
		t.add(s17);
		t.add(s18);
		t.add(s19);
		t.add(s20);
		System.out.println("\nTest IOException and get method\n");
		try {
			System.out.println("Test the get method: " + t.get(1234) + "\n");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n");
		}
		try {
			System.out.println("Test the get method: " + t.get(test2) + "\n");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n");
		}
		
		System.out.println("\nBelow is the Hash Table\n");
		t.printHashTable();
		
		System.out.println(t.showAll());
	}
}
