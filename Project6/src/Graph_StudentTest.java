
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Graph_StudentTest {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@Before
	public void setUp() throws Exception {
		  graph = new Graph();
		  town = new Town[13];
		  
		  for (int i = 0; i < 13; i++) {
			  town[i] = new Town("Town_" + i);
			  graph.addVertex(town[i]);
		  }
		  
		  graph.addEdge(town[0], town[1], 5, "Road_0");
		  graph.addEdge(town[1], town[2], 20, "Road_1");
		  graph.addEdge(town[1], town[3], 10, "Road_2");
		  graph.addEdge(town[1], town[4], 7, "Road_3");
		  graph.addEdge(town[2], town[7], 50, "Road_4");
		  graph.addEdge(town[2], town[8], 10, "Road_5");
		  graph.addEdge(town[3], town[5], 3, "Road_6");
		  graph.addEdge(town[3], town[12], 1, "Road_7");
		  graph.addEdge(town[5], town[0], 6, "Road_8");
		  graph.addEdge(town[5], town[6], 9, "Road_9");
		  graph.addEdge(town[0], town[11], 4, "Road_10");
		  graph.addEdge(town[9], town[8], 2, "Road_11");
		  graph.addEdge(town[9], town[10], 3, "Road_12");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(town[0], town[1],5, "Road_0"), graph.getEdge(town[0], town[1]));
		assertEquals(new Road(town[9], town[8],2, "Road_11"), graph.getEdge(town[9], town[8]));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[6], town[12]));
		graph.addEdge(town[6], town[12], 100, "Road_13");
		assertEquals(true, graph.containsEdge(town[6], town[12]));
	}
	
	@Test
	public void testAddVertex() {
		Town newTown = new Town("Town_13");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[1], town[4]));
		assertEquals(false, graph.containsEdge(town[6], town[12]));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Town_3")));
		assertEquals(false, graph.containsVertex(new Town("Town_13")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getM_roadName());
		Collections.sort(roadArrayList);
		assertEquals("Road_0", roadArrayList.get(0));
		assertEquals("Road_1", roadArrayList.get(1));
		assertEquals("Road_10", roadArrayList.get(2));
		assertEquals("Road_11", roadArrayList.get(3));
		assertEquals("Road_12", roadArrayList.get(4));
		assertEquals("Road_7", roadArrayList.get(10));
	}
	
	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(town[3]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getM_roadName());
		Collections.sort(roadArrayList);
		assertEquals("Road_2", roadArrayList.get(0));
		assertEquals("Road_6", roadArrayList.get(1));
		assertEquals("Road_7", roadArrayList.get(2));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[9], town[10]));
		graph.removeEdge(town[9], town[10], 3, "Road_12");
		assertEquals(false, graph.containsEdge(town[9], town[10]));
	}

	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[5]));
		graph.removeVertex(town[5]);
		assertEquals(false, graph.containsVertex(town[5]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(town[0]));
		assertEquals(true, roads.contains(town[1]));
		assertEquals(true, roads.contains(town[2]));
		assertEquals(true, roads.contains(town[6]));
		assertEquals(true, roads.contains(town[8]));
	}

	 @Test
	  public void testTown_11ToTown_5() {
		  String beginTown = "Town_11", endTown = "Town_5";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getM_townName().equals(beginTown))
				  beginIndex = town;
			  if(town.getM_townName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_11 via Road_10 to Town_0 4 mi",path.get(0).trim());
			  assertEquals("Town_0 via Road_8 to Town_5 6 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void testTown_1ToTown_6() {
		  String beginTown = "Town_1", endTown = "Town_6";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getM_townName().equals(beginTown))
				  beginIndex = town;
			  if(town.getM_townName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_1 via Road_0 to Town_0 5 mi",path.get(0).trim());
			  assertEquals("Town_0 via Road_8 to Town_5 6 mi",path.get(1).trim());
			  assertEquals("Town_5 via Road_9 to Town_6 9 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");
	  }
	  
	  @Test
	  public void testTown_2ToTown_5() {
		  String beginTown = "Town_2", endTown = "Town_5";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getM_townName().equals(beginTown))
				  beginIndex = town;
			  if(town.getM_townName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_2 via Road_1 to Town_1 20 mi",path.get(0).trim());
			  assertEquals("Town_1 via Road_0 to Town_0 5 mi",path.get(1).trim());
			  assertEquals("Town_0 via Road_8 to Town_5 6 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");
	  }
}
