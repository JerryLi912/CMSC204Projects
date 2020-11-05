/** 
 * @author jerry
 *
 *CourseDBElement implements Comparable, and consists of five attributes: 
 *the Course ID (a String), the CRN (an int), the number of credits (an int), 
 *the room number (a String), and the instructor name (a String).   
 *Normally the CourseDBElement will be an object consisting of these five attributes, 
 *and is referred to as a CDE.
 */
public class CourseDBElement implements Comparable {

	private String m_courseID;
	private int m_CRN;
	private int m_credits;
	private String m_room_number;
	private String m_instructor;
	
	public CourseDBElement() {}
	
	public CourseDBElement(String courseID, int CRN, int credits, String room_number, String instructor) {
		this.m_courseID = courseID;
		this.m_CRN = CRN;
		this.m_credits = credits;
		this.m_room_number = room_number;
		this.m_instructor = instructor;
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("Testing the equals method");
		if(this == obj) {
			System.out.println("These objects are equal");
			return true;
		}
		if(obj == null || obj.getClass()!= this.getClass()) {
			System.out.println("These objects are not equal");
			return false;
		}
		CourseDBElement courseDBElement = (CourseDBElement) obj;
		
		boolean checkCourseID = (courseDBElement.m_courseID == this.m_courseID);
		boolean checkCRN = (courseDBElement.m_CRN == this.m_CRN);
		boolean checkCredits = (courseDBElement.m_credits == this.m_credits);
		boolean checkRoomNumber = (courseDBElement.m_room_number == this.m_room_number);
		boolean checkInstructor = (courseDBElement.m_instructor == this.m_instructor);
		
		return (checkCourseID && checkCRN && checkCredits && checkRoomNumber && checkInstructor);
	}
	
	@Override
	public int hashCode() {
		//CRN is an int, so I need to convert it to a string.
		int tempCRN = getCRN();
		String stringCRN = Integer.toString(tempCRN);
		
		//convert string to hashCode
		int hash = 0;
		int stringCRN_Length = stringCRN.length();
		for(int i = 0; i < stringCRN_Length; i++) {
			hash = 31 * hash + stringCRN.charAt(i);
			if(hash < 0) {
				hash *= (-1);
			}
		}
		System.out.println("HashCode is: " + hash);
		return hash;
	}
	

	public String toString() {
		String listInformation =  ("Course:" + getCourseID() + " CRN:" + getCRN() 
			+ " Credits:" + getCredits() + " Room:" + getRoomNumber() 
			+ " Instructor:" + getInstructor());
		
		return listInformation;
	}
	
	
	@Override
	public int compareTo(CourseDBElement element) {
		if(this.getCRN() > element.getCRN()) {
			return 1;
		}
		else if(this.getCRN() == element.getCRN()) {
			return 0;
		}
		else{
			return -1;
		}
	}

	public void setCRN(int CRN) {
		this.m_CRN = CRN;
	}
	public int getCRN() {
		return this.m_CRN;
	}
	public String getCourseID() {
		return this.m_courseID;
	}
	public int getCredits() {
		return this.m_credits;
	}
	public String getRoomNumber() {
		return this.m_room_number;
	}
	public String getInstructor() {
		return this.m_instructor;
	}
	
	public static void main(String[] args) {
		CourseDBElement s0 = new CourseDBElement("wewe", 99999, 3, "R232", "kabakaba");
		CourseDBElement s1 = new CourseDBElement("sddwe", 12134, 3, "R232", "kabakaba");
		CourseDBElement s2 = new CourseDBElement("wewe", 32222, 3, "R343", "pkkomsds");
		
		
		CourseDBElement s3 = new CourseDBElement("CMSC203", 40480, 3, "R322", "kakakak");
		CourseDBElement s5 = new CourseDBElement("CMSC205", 42343, 3, "R322", "kakakak");
//		CourseDBElement s3 = new CourseDBElement("wewe", 99999, 3, "R232", "kabakaba");
		System.out.println(s0.hashCode() % 20);
		System.out.println(s1.hashCode() % 20);
		System.out.println(s2.hashCode() % 20);
//		System.out.println(s3.hashCode() % 20);
		System.out.println("Compare s0 and s1: (Expected false)\n" + s0.equals(s1) + "\n");
//		System.out.println("Compare s0 and s3: (Expected true)\n" + s0.equals(s3) + "\n");
		System.out.println("Compare s1 and s2: (Expected false)\n" + s1.equals(s2) + "\n");	
		
		System.out.println("Compare s3 and s5: (Expected false)\n" + s3.equals(s5) + "\n");	
		
		//test compare
//		System.out.println(s0.compareTo(s3));
		System.out.println(s0.compareTo(s2));
		System.out.println(s1.compareTo(s2));
		System.out.println(s3.compareTo(s5));
	}
}
