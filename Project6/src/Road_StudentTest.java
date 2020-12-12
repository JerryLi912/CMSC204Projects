import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_StudentTest {

	private Road road1, road2, road3, road4;
	private Town[] town;
	
	@Before
	public void setUp() {
		
		town = new Town[4];
		for (int i = 0; i < 4; i++) {
			town[i] = new Town("Town" + i);
		}
		road1 = new Road(town[0], town[1], 3, "I-900");
		road2 = new Road(town[1], town[2], 4, "L-008");
		road3 = new Road(town[2], town[3], 5, "I-333");
		road4 = new Road(town[3], town[2], 5, "I-333");
	}
	
	@After
	public void tearDown() {
		town = null;
		road1 = road2 = road3 = road4 = null;
	}
	
	@Test
	public void testCompareTo() {
		//comparing the road name
		assertEquals(-1, road2.compareTo(road3));
		assertEquals(0, road3.compareTo(road4));
		assertEquals(-1, road1.compareTo(road3));	
	}
	
	@Test
	public void testGetRoadName() {
		assertEquals("I-900", road1.getM_roadName());
		assertEquals("L-008", road2.getM_roadName());
		assertEquals("I-333", road3.getM_roadName());
		assertEquals("I-333", road4.getM_roadName());
	}
	
	@Test
	public void testGetSource_and_getDestination() {
	
		assertEquals("Town1", road1.getDestination().getM_townName());
		assertEquals("Town2", road2.getDestination().getM_townName());
		assertEquals("Town2", road3.getSource().getM_townName());
		assertEquals("Town0", road1.getSource().getM_townName());
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(3, road1.getM_weight());
		assertEquals(4, road2.getM_weight());
		assertEquals(5, road3.getM_weight());
		assertEquals(5, road4.getM_weight());
	}
	
	@Test
	public void testContainsTownName() {
		assertEquals(true, road1.containsTownName("Town0"));
		assertEquals(false, road2.containsTownName("Town5"));
		assertEquals(true, road3.containsTownName("Town3"));
		assertEquals(false, road4.containsTownName("Town5"));
	}
	
	@Test
	public void testHashCode() {
		assertEquals(road3.hashCode(), road4.hashCode());
	}
	
	@Test 
	public void testEquals() {
		assertEquals(true, road3.equals(road4));
		assertEquals(false, road3.equals(road2));
		assertEquals(false, road1.equals(road4));
	}
	
	@Test
	public void testToString() {
		assertEquals("I-333,5;Town2;Town3", road3.toString());
		assertEquals("L-008,4;Town1;Town2", road2.toString());
		assertEquals("I-900,3;Town0;Town1", road1.toString());
	}
	
}
