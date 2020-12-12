
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TownRoadManager_StudentTest {
	private TownGraphManagerInterface graph;
	private Graph m_newGraph;
	private String[] town;
	private File inputFile;
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  m_newGraph = new Graph();
		  town = new String[13];
		  
		  for (int i = 0; i < 13; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }
		  
		  graph.addRoad(town[0], town[1], 5, "Road_0");
		  graph.addRoad(town[1], town[2], 20, "Road_1");
		  graph.addRoad(town[1], town[3], 10, "Road_2");
		  graph.addRoad(town[1], town[4], 7, "Road_3");
		  graph.addRoad(town[2], town[7], 50, "Road_4");
		  graph.addRoad(town[2], town[8], 10, "Road_5");
		  graph.addRoad(town[3], town[5], 3, "Road_6");
		  graph.addRoad(town[3], town[12], 1, "Road_7");
		  graph.addRoad(town[5], town[0], 6, "Road_8");
		  graph.addRoad(town[5], town[6], 9, "Road_9");
		  graph.addRoad(town[0], town[11], 4, "Road_10");
		  graph.addRoad(town[9], town[8], 2, "Road_11");
		  graph.addRoad(town[9], town[10], 3, "Road_12");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
		m_newGraph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_0", roads.get(0));
		assertEquals("Road_1", roads.get(1));
		assertEquals("Road_10", roads.get(2));
		assertEquals("Road_11", roads.get(3));
		graph.addRoad(town[4], town[12], 15,"Road_13");
		roads = graph.allRoads();
		assertEquals("Road_0", roads.get(0));
		assertEquals("Road_1", roads.get(1));
		assertEquals("Road_10", roads.get(2));
		assertEquals("Road_11", roads.get(3));
		assertEquals("Road_12", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_5", graph.getRoad(town[2], town[8]));
		assertEquals("Road_6", graph.getRoad(town[3], town[5]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_13"));
		graph.addTown("Town_13");
		assertEquals(true, graph.containsTown("Town_13"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_13"));
		graph.addTown("Town_13");
		ArrayList<String> path = graph.getPath(town[0],"Town_13");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_0"));
		assertEquals(false, graph.containsTown("Town_13"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[0], town[11]));
		assertEquals(false, graph.containsRoadConnection(town[11], town[5]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_0", roads.get(0));
		assertEquals("Road_1", roads.get(1));
		assertEquals("Road_10", roads.get(2));
		assertEquals("Road_7", roads.get(10));
		assertEquals("Road_8", roads.get(11));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[2]));
		graph.deleteRoadConnection(town[1], town[2], "Road_1");
		assertEquals(false, graph.containsRoadConnection(town[1], town[2]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_5"));
		graph.deleteTown(town[5]);
		assertEquals(false, graph.containsTown("Town_5"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> towns = graph.allTowns();
		assertEquals("Town_0", towns.get(0));
		assertEquals("Town_1", towns.get(1));
		assertEquals("Town_10", towns.get(2));
		assertEquals("Town_11", towns.get(3));
		assertEquals("Town_6", towns.get(9));
	}
	
	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1],town[5]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_0 to Town_0 5 mi",path.get(0).trim());
		  assertEquals("Town_0 via Road_8 to Town_5 6 mi",path.get(1).trim());
	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[6],town[12]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_6 via Road_9 to Town_5 9 mi",path.get(0).trim());
		  assertEquals("Town_5 via Road_6 to Town_3 3 mi",path.get(1).trim());
		  assertEquals("Town_3 via Road_7 to Town_12 1 mi",path.get(2).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[11],town[10]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_11 via Road_10 to Town_0 4 mi",path.get(0).trim());
		  assertEquals("Town_0 via Road_0 to Town_1 5 mi",path.get(1).trim());
		  assertEquals("Town_1 via Road_1 to Town_2 20 mi",path.get(2).trim());
		  assertEquals("Town_2 via Road_5 to Town_8 10 mi",path.get(3).trim());
		  assertEquals("Town_8 via Road_11 to Town_9 2 mi",path.get(4).trim());
		  assertEquals("Town_9 via Road_12 to Town_10 3 mi",path.get(5).trim());

	}	
	
	@Test
	public void testInputFile() {
		// since I added the data in the file to the Graph class, I will 
		// use m_newGraph to take the input. Then, I would be able to 
		// see the results from TownRoadManager.
		Set<Town> towns = m_newGraph.vertexSet();
		try {
			 inputFile = new File("MD Towns.txt");
			graph.populateTownGraph(inputFile);
			assertEquals(true, graph.containsTown("Bethesda"));
			assertEquals(true, graph.containsTown("Boyds"));
			assertEquals(true, graph.containsTown("Clarksburg"));
			assertEquals(true, graph.containsTown("Potomac"));
			
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
	
}
