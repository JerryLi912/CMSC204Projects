import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the CourseDBManager class 
 * @author jerry
 *
 */
public class CourseDBManager_StudentTest {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("CMSC100", 30555, 5,"Distance-learning","Laddy Gaga");
			dataMgr.add("CMSC110", 11255, 4,"PS223","Laddy Gaga");
			dataMgr.add("CMSC111", 45355, 3,"R2322","Laddy Gaga");
			dataMgr.add("CMSC232", 78955, 2,"Q121","Laddy Gaga");
			dataMgr.add("CMSC454", 35754, 1,"G999","Laddy Gaga");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgr.add("CMSC203",33333,5,"SS555","RRR T. Word");
		dataMgr.add("CMSC203",22222,1,"QW222","Who is this");
		dataMgr.add("CMSC204",11111,1,"DD113","BillyBob R. Japan");
		dataMgr.add("CMSC100", 30555, 5,"Distance-learning","Laddy Gaga");
		dataMgr.add("CMSC111", 45355, 3,"R2322","Laddy Gaga");
		
		
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:CMSC111 CRN:45355 Credits:3 Room:R2322 Instructor:Laddy Gaga");
		assertEquals(list.get(1),"\nCourse:CMSC204 CRN:11111 Credits:1 Room:DD113 Instructor:BillyBob R. Japan");
		assertEquals(list.get(2),"\nCourse:CMSC100 CRN:30555 Credits:5 Room:Distance-learning Instructor:Laddy Gaga");
		assertEquals(list.get(3),"\nCourse:CMSC203 CRN:22222 Credits:1 Room:QW222 Instructor:Who is this");
		assertEquals(list.get(4),"\nCourse:CMSC203 CRN:33333 Credits:5 Room:SS555 Instructor:RRR T. Word");
	}
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC203 30504 4 SC450 Joey Bag-O-Donuts");
			inFile.println("CMSC203 30503 4 SC450 Jill B. Who-Dunit");
			inFile.println("CMSC204 11111 3 DD113 BillyBob R. Japan");
			inFile.println("CMSC205 22222 3 RR343 TBD");
			inFile.println("CMSC206 33332 2 PE343 Nobody");
			inFile.println("CMSC207 45656 2 OO122 maybe you");
			inFile.println("CMSC208 89768 3 EE234 Java");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			System.out.println(dataMgr.showAll());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}

