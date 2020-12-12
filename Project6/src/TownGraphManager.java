import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
/**
 * This TownGrapgManager class allows users to input and output the towns, roads, and files
 * @author jerry
 *
 */
public class TownGraphManager implements TownGraphManagerInterface {

	private Graph m_newGraph;
	private ArrayList<String> m_cur_shortest_Path = new ArrayList<>();
	
	public TownGraphManager() {
		m_newGraph = new Graph();
		 m_cur_shortest_Path = new ArrayList<String>();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		//create town objects to compare
		Town L_town1 = new Town(town1);
		Town L_town2 = new Town(town2);
		//there is no Town in the graph, so call addVertex()
		m_newGraph.addVertex(L_town1);
		m_newGraph.addVertex(L_town2);

		m_newGraph.addEdge(L_town1, L_town2, weight, roadName);
		
		if(m_newGraph.containsEdge(L_town1, L_town2)) {
			return true;
		}
		return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		//create town objects to compare
		Town L_town1 = new Town(town1);
		Town L_town2 = new Town(town2);
		for (int i = 0; i < m_newGraph.m_graph.size(); i++) {
			
			if (L_town1.equals(m_newGraph.m_graph.get(i))) {
				
				for (int j = 0; j < m_newGraph.m_graph.get(i).m_adjList.size(); j++) {
					
					if(m_newGraph.m_graph.get(i).m_adjList.get(j).getDestination().equals(L_town2)) {
						
						return m_newGraph.m_graph.get(i).m_adjList.get(j).getM_roadName();
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean addTown(String town) {
		Town L_town = new Town(town);
		boolean town_is_added;
		town_is_added = m_newGraph.addVertex(L_town);
		return town_is_added;
	}

	@Override
	public Town getTown(String name) {
		Town L_town = new Town(name);
		if(m_newGraph.containsVertex(L_town)) {
			for (Town town : m_newGraph.m_graph) {
				if (town.equals(L_town)) {
					return town;
				}
			}
		}
		return null;
	}

	@Override
	public boolean containsTown(String town) {
		Town L_town = new Town(town);
		if(m_newGraph.containsVertex(L_town)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		if (getRoad(town1, town2) != null) {
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<String> allRoads() {
		int edges = m_newGraph.edgeSet().size();
		ArrayList<String> L_roadList = new ArrayList<String>(edges);
		
		for(Road roadName : m_newGraph.edgeSet()) {
			L_roadList.add(roadName.getM_roadName());
		}
		Collections.sort(L_roadList);
		return L_roadList;
	}

	@Override
	public ArrayList<String> allTowns() {
		int towns = m_newGraph.vertexSet().size();
		ArrayList<String> L_townList = new ArrayList<String>(towns);
		for (Town town : m_newGraph.vertexSet()) {
			L_townList.add(town.getM_townName());
		}
		Collections.sort(L_townList);
		return L_townList;
	}
	
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		System.out.println("\n");
		if(town1 == null || town2 == null || road == null) {
			return false;
		}
		//check if the road exists between two towns
		if (containsRoadConnection(town1, town2)) {
			for(Town towns : m_newGraph.m_graph) {
				for (int i = 0; i < towns.m_adjList.size(); i++) {
						if(towns.m_adjList.get(i).getM_roadName().equals(road)) {
							towns.m_adjList.remove(i);
						}
					}
				}
		}
		return true;
	}
	
	@Override
	public boolean deleteTown(String townName) {
		System.out.println("\n");
		
		if(townName == null) {
			return false;
		}
		for(Town towns : m_newGraph.m_graph) {
			for (int i = 0; i < towns.m_adjList.size(); i++) {
					if(towns.m_adjList.get(i).containsTownName(townName)) {
						towns.m_adjList.remove(i);
					}
				}
			}
		if(containsTown(townName)) {
			for(int i = 0; i < m_newGraph.m_graph.size(); i++) {
				if(m_newGraph.m_graph.get(i).getM_townName().equals(townName)) {
					m_newGraph.m_graph.remove(i);
				}
			}
		}
		if(containsTown(townName)) {
			return false;
		}
		return true;	
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2)throws NullPointerException {
		Town beginIndex = null, endIndex = null;
		Set<Town> towns = m_newGraph.vertexSet();
		Iterator<Town> iterator = towns.iterator();
		while(iterator.hasNext()) {
			Town town = iterator.next();
			if(town.getM_townName().equals(town1)) {
				beginIndex = town;
			}
			if(town.getM_townName().equals(town2)) {
				endIndex = town;
			}
		}

		m_newGraph.dijkstraShortestPath(beginIndex);
		Town destination = endIndex;
		System.out.println(this.m_newGraph.m_previous);
		int roadWeight = 0;
		while(!destination.equals(beginIndex)) {
			//check if disjoint
			if(!this.m_newGraph.m_previous.containsKey(destination.getM_townName()) || this.m_newGraph.m_previous.get(destination.getM_townName()) == null) {
				System.out.print("destination not included");
				m_cur_shortest_Path.clear();
				break;
			}
					
					
		Town previous = this.m_newGraph.m_previous.get(destination.getM_townName());
			System.out.println(previous);
			String road = getRoad(previous.getM_townName(), destination.getM_townName());
			for (Town town: m_newGraph.m_graph) {
				for (int i = 0; i < town.m_adjList.size(); i++) {
					if (town.m_adjList.get(i).getM_roadName().equals(road)) {
						roadWeight = town.m_adjList.get(i).getM_weight();
					}
				}
			}
					
					
		System.out.println(getRoad(previous.getM_townName(), destination.getM_townName()));
			if(road != null) {
			//adding element to the front
			m_cur_shortest_Path.add(0, previous.getM_townName() + " via " + road + " to " + destination.getM_townName()
			 + " "+ roadWeight +" mi");	
			System.out.println(m_cur_shortest_Path);
			//destination = previous;
			}
			destination = previous;
		}
		return m_cur_shortest_Path;
	}
	
	@Override
	public void populateTownGraph(File selectedFile) throws FileNotFoundException{
		Scanner input;
		if(selectedFile != null) {
			String[] line, roadText;
			String town1, town2;
			input = new Scanner(selectedFile);
			while (input.hasNext()) {
				line = input.nextLine().split(";");		
				town1 = line[1];
				Town town1Town = new Town(town1);
				town2 = line[2];
				Town town2Town = new Town(town2);
				roadText = line[0].split(",");
				m_newGraph.addVertex(town1Town);
				m_newGraph.addVertex(town2Town);
				m_newGraph.addEdge(town1Town, town2Town, Integer.parseInt(roadText[1]), roadText[0]);
			}	
		}
	}
	

	//Personal test
	public static void main(String[] args) {
		TownGraphManager m1 = new TownGraphManager();
		
		System.out.println(m1.addRoad("Bethesda", "Rockville", 10, "I-900"));
		System.out.println(m1.addRoad("ABC", "Rockville", 20, "L-222"));
		System.out.println(m1.addRoad("GermanTown", "Boo", 30, "W-555"));
		System.out.println(m1.addRoad("Kaath", "DC", 400, "T-887"));
		
		System.out.println(m1.getRoad("Bethesda", "Rockville"));
		System.out.println(m1.getRoad("ABC", "Rockville"));
		System.out.println(m1.getRoad("Rockville", "ABC"));
		
		System.out.println(m1.addTown("RRRRRR"));
		
		System.out.println(m1.m_newGraph.vertexSet());
		
		System.out.println(m1.containsTown("Bethesda"));
		
		System.out.println(m1.getTown("Bethesda"));
		
		System.out.println(m1.containsRoadConnection("Kaath", "DC"));
		
		System.out.println(m1.allRoads());
		
		System.out.println(m1.allTowns());
		
		System.out.println(m1.deleteTown("ABC"));
		System.out.println(m1.containsTown("ABC"));
		
		System.out.println(m1.allTowns());
		System.out.println(m1.allRoads());
		
		System.out.println(m1.deleteRoadConnection("Kaath", "DC", "T-887"));
		
		System.out.println(m1.allTowns());
		System.out.println(m1.allRoads());
		
		System.out.println(m1.getPath("GermanTown", "Boo"));
		
		File select = new File("MD Towns.txt");
		try {
			m1.populateTownGraph(select);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("added file of towns and roads: ");
		System.out.println(m1.allRoads());
		System.out.println(m1.allTowns());
		
//		TownGraphManager graph = new TownGraphManager();
//		String[] town = new String[12];
//		  
//		  for (int i = 1; i < 12; i++) {
//			  town[i] = "Town_" + i;
//			  graph.addTown(town[i]);
//		  }
//		  
//		  graph.addRoad(town[1], town[2], 2, "Road_1");
//		  graph.addRoad(town[1], town[3], 4, "Road_2");
//		  graph.addRoad(town[1], town[5], 6, "Road_3");
//		  graph.addRoad(town[3], town[7], 1, "Road_4");
//		  graph.addRoad(town[3], town[8], 2, "Road_5");
//		  graph.addRoad(town[4], town[8], 3, "Road_6");
//		  graph.addRoad(town[6], town[9], 3, "Road_7");
//		  graph.addRoad(town[9], town[10], 4, "Road_8");
//		  graph.addRoad(town[8], town[10], 2, "Road_9");
//		  graph.addRoad(town[5], town[10], 5, "Road_10");
//		  graph.addRoad(town[10], town[11], 3, "Road_11");
//		  graph.addRoad(town[2], town[11], 6, "Road_12");
//		  
//		  ArrayList<String> roads = graph.allRoads();
//		 for(int i = 0; i < roads.size(); i++) {
//			 System.out.println(roads.get(i));
//		 }
//		 System.out.println(graph.getTown("Town_12"));
//		 System.out.println(graph.allTowns());
//		 System.out.println(graph.allRoads());
//		 System.out.println();
//	
//		System.out.println(graph.getPath("Town_1", "Town_10"));
//		//System.out.println(graph.getPath("Town_1", "Town_12"));
//		System.out.println(graph.getPath("Town_2", "Town_6"));
//	}
}
}
