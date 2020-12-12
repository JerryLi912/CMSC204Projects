
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_StudentTest {

	private Town town1, town2, town3, town4, town5;

	@Before
	public void setUp() {
		town1 = new Town("Bethesda");
		town2 = new Town("Bethesda");
		town3 = new Town("Rockville");
		town4 = new Town("Potomacll");
		town5 = new Town("Rockville");
	}
	
	@After
	public void tearDown() {
		town1 = null;
		town2 = null;
		town3 = null;
		town4 = null;
		town5 = null;
	}
	
	@Test
	public void testGetTownName() {
		assertEquals("Bethesda", town1.getM_townName());
		assertEquals("Bethesda", town2.getM_townName());
		assertEquals("Rockville", town3.getM_townName());
		assertEquals("Potomacll", town4.getM_townName());
	}
	
	@Test
	public void testsetTownName() {
		town1.setM_townName("kkkk");
		town2.setM_townName("ssss");
		town3.setM_townName("cccc");
		town4.setM_townName("aaaa");
		assertEquals("kkkk", town1.getM_townName());
		assertEquals("ssss", town2.getM_townName());
		assertEquals("cccc", town3.getM_townName());
		assertEquals("aaaa", town4.getM_townName());
	}
	
	@Test
	public void testEquals() {
		assertTrue(town1.equals(town2));
		assertFalse(town3.equals(town4));
		assertFalse(town1.equals(town3));
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, town1.compareTo(town2));
		assertEquals(-1, town3.compareTo(town4));
		assertEquals(-1, town2.compareTo(town3));
	}
	
	@Test
	public void testHashCode() {
		assertEquals(town1.hashCode(), town2.hashCode());
		assertEquals(town3.hashCode(), town5.hashCode());
	}
	
	@Test
	public void testToString() {
		assertEquals("Bethesda", town1.toString());
		assertEquals("Bethesda", town2.toString());
		assertEquals("Rockville", town3.toString());
		assertEquals("Potomacll", town4.toString());
	}

}
