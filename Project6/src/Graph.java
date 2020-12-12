import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
/**
 * This Graph class uses two Linked lists to represent a graph 
 * @author jerry
 *
 */
public class Graph implements GraphInterface<Town, Road> {
	
	protected LinkedList<Town> m_graph;
	protected Map<String, Town> m_previous;
	private ArrayList<String> m_cur_shortest_Path;
	
	public Graph() {
		this.m_graph = new LinkedList<Town>();
		this.m_previous = new HashMap<String, Town>();
		m_cur_shortest_Path = new ArrayList<String>();
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for (int i = 0; i < m_graph.size(); i++) {
			if (sourceVertex.equals(m_graph.get(i))) {
				for (int j = 0; j < sourceVertex.m_adjList.size(); j++) {
					if(sourceVertex.m_adjList.get(j).getDestination().equals(destinationVertex)) {
						return sourceVertex.m_adjList.get(j);
					}
				}
			}
		}
		return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description){
		Road m_road1 = new Road(sourceVertex, destinationVertex, weight, description);
		Road m_road2 = new Road(destinationVertex, sourceVertex, weight, description);
		
		if(sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException("Town not found!!!!");
		}
		if(!m_graph.contains(sourceVertex) || !m_graph.contains(destinationVertex)) {
			throw new IllegalArgumentException("Town not found!!!!");
		}
		if(sourceVertex.m_adjList.contains(m_road1)||destinationVertex.m_adjList.contains(m_road2)){
			return null;
		}
	
		sourceVertex.m_adjList.add(m_road1);
		destinationVertex.m_adjList.add(m_road2);
		
		return m_road1;
	}

	@Override
	public boolean addVertex(Town town) {
		m_graph.add(town);
		if(m_graph.contains(town)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for (int i = 0; i < m_graph.size(); i++) {
			if (sourceVertex.equals(m_graph.get(i))) {
				for (int j = 0; j < sourceVertex.m_adjList.size(); j++) {
					if(sourceVertex.m_adjList.get(j).getDestination().equals(destinationVertex)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town town) {
		for (int i = 0; i < m_graph.size(); i++) {
			if (m_graph.get(i).getM_townName().equals(town.getM_townName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		//System.out.println("\n");
		Set<Road> roadSets = new HashSet<>();
		for(Town town : m_graph) {
			for (int i = 0; i < town.m_adjList.size(); i++) {
					roadSets.add(town.m_adjList.get(i));	
				}
			}
		return roadSets;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		//System.out.println("\n");
		Set<Road> roadSets = new HashSet<>();
		
		if(!m_graph.contains(vertex)) {
			throw new IllegalArgumentException("Town not found!!!!");
		}
		if(vertex == null) {
			throw new NullPointerException("Town not found!!!!");
		}
		
		for(Town town : m_graph) {
			for(int i = 0; i < town.m_adjList.size(); i++) {
				if(town.equals(vertex)) {
					roadSets.add(town.m_adjList.get(i));
				}
			}
		}
		return roadSets;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road m_road = new Road(sourceVertex, destinationVertex, weight, description); 
		if(containsEdge(sourceVertex, destinationVertex)) {
			for (Town town : m_graph) {
				for (int i = 0; i < town.m_adjList.size(); i++) {
					if (m_road.equals(town.m_adjList.get(i))) {
						town.m_adjList.remove(i);
					}
				}
			}
		}
		return m_road;
	}

	@Override
	public boolean removeVertex(Town targetTown) {
		System.out.println("\n");
		
		if(targetTown == null) {
			return false;
		}
		
		for(Town town : m_graph) {
			for (int i = 0; i < town.m_adjList.size(); i++) {
					if(town.m_adjList.get(i).contains(targetTown)) {
						town.m_adjList.remove(i);
					}
				}
			}
		
		if(m_graph.contains(targetTown)) {
			m_graph.remove(targetTown);
		}
		if(m_graph.contains(targetTown)) {
			return false;
		}
		return true;
	}

	@Override
	public Set<Town> vertexSet() {
		System.out.println("\n");
		Set<Town> townSets = new HashSet<>();
		for(Town town : m_graph) {
			townSets.add(town);
		}
		return townSets;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		//cur_shortest_Path = new ArrayList<>();
		dijkstraShortestPath(sourceVertex);
		Town destination = destinationVertex;
		
		//System.out.println(this.m_previous);
		
		while(!destination.equals(sourceVertex)) {
			//check if disjoint
			if(!this.m_previous.containsKey(destination.getM_townName()) || this.m_previous.get(destination.getM_townName()) == null) {
				System.out.print("destination not included");
				m_cur_shortest_Path.clear();
				break;
			}
			
			
			Town previous = this.m_previous.get(destination.getM_townName());
			//System.out.println(previous);
			Road road = getEdge(previous, destination);
			//System.out.println(getEdge(previous, destination));
			
			if(road != null) {
			//adding element to the front
			m_cur_shortest_Path.add(0, previous.getM_townName() + " via " + road.getM_roadName() + " to " + destination.getM_townName()
			 + " " + road.getM_weight() + " mi");	
			
			//System.out.println(m_cur_shortest_Path);
			//destination = previous;
		}
			destination = previous;
		}
		return m_cur_shortest_Path;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		//since a town needs to hold a pair of name and cost, I will use HashMap to do it. 
		//Map<String TownName, Integer cost>
		System.out.println("\n");
		
		Set<Town> visitedTown = new HashSet<>();
		ArrayList<Town> unvisitedTown = new ArrayList<>();
		Map<String, Integer> town_with_cost = new HashMap<>();
		this.m_previous.clear();
		
		
		for(Town town : m_graph) {
			unvisitedTown.add(town);
			town_with_cost.put(town.getM_townName(), Integer.MAX_VALUE);
			this.m_previous.put(town.getM_townName(), null);
		}
		
		town_with_cost.put(sourceVertex.getM_townName(), 0);
		
		//test out put
		System.out.println("Unvisited towns: "+ unvisitedTown);
		System.out.println("Town with cost: "+ town_with_cost);
		
		while(!unvisitedTown.isEmpty()) {
			int min_town_index = 0;
			
			for(int i = 1; i < unvisitedTown.size(); i++) {
				Town unvisited = unvisitedTown.get(i);
				if(town_with_cost.get(unvisited.getM_townName()) < 
				  town_with_cost.get(unvisitedTown.get(min_town_index).getM_townName())) {
					min_town_index = i;
				}
			}//end of for loop
			
			Town min_cost_town = unvisitedTown.remove(min_town_index);
			visitedTown.add(min_cost_town);
			
			if(town_with_cost.get(min_cost_town.getM_townName()) == Integer.MAX_VALUE) {
				return;
			}
			
			for(Road road : edgesOf(min_cost_town)) {
				Town neighbor = road.getDestination();
				
				if(neighbor.equals(min_cost_town)) {
					neighbor = road.getSource();
				}
				if(visitedTown.contains(neighbor)) {
					continue;
				}
				
				int adjacentCost = town_with_cost.get(min_cost_town.getM_townName()) + road.getM_weight();
				
				if(adjacentCost < town_with_cost.get(neighbor.getM_townName())) {
					town_with_cost.put(neighbor.getM_townName(), adjacentCost);
					this.m_previous.put(neighbor.getM_townName(), min_cost_town);
				}
			}//end of for loop
		}//end of while loop

	}
	
	public void print() {
		System.out.println("\n\n"+ m_graph.toString()+"\n\n");
		for(int i = 0; i < m_graph.size(); i++) {
				System.out.println(m_graph.get(i).toString() + " -> "+ m_graph.get(i).m_adjList.toString());		
		}	
	}

	public static void main(String[] args) {
		Graph g1 = new Graph();
		Town t1 = new Town("Bethesda");
		Town t2 = new Town("Rockville");
		Town t3 = new Town("G-town");
		Town t4 = new Town("C-town");
		Town t5 = new Town("D-town");
		
		
		System.out.println(g1.addVertex(t1));
		System.out.println(g1.addVertex(t2));
		System.out.println(g1.addVertex(t3));
		System.out.println(g1.addVertex(t5));
		System.out.println(g1.containsVertex(t3));
		try {
		//g1.addEdge(t4, t3, 10, "I-900");
		g1.addEdge(t5, t3, 40, "Q_001");
		
		}
		
		catch(IllegalArgumentException e) {
			
			System.out.println(e.getMessage());
		}
		

		System.out.println("\nTest add edge: \n");
		System.out.println(g1.addEdge(t1, t3, 10, "I-900"));
		
		System.out.println(g1.addEdge(t2, t3, 20, "I-900"));
		
		System.out.println(g1.addEdge(t1, t2, 30, "T-747"));
		//System.out.println(g1.addEdge(t1, t2, 30, "T-747"));
		
		System.out.println(g1.addEdge(t5, t2, 90, "N-063"));
		
		
		System.out.println("\nTest get edge: \n");
		System.out.println(g1.getEdge(t1, t3));
		System.out.println(g1.getEdge(t3, t1));
		
		System.out.println(g1.getEdge(t2, t3));
		System.out.println(g1.getEdge(t3, t2));
		
		System.out.println(g1.getEdge(t1, t2));
		
		System.out.println(g1.getEdge(t2, t5));
		
		g1.print();
		
		System.out.println(g1.edgeSet());
		
//		g1.removeEdge(t1, t3, 10, "I-900");
//		System.out.println(g1.edgeSet());
//		System.out.println(g1.removeVertex(t1));
//		System.out.println(g1.edgeSet());
//		System.out.println(g1.vertexSet());
//		System.out.println(g1.edgesOf(t2));
		
		System.out.println(g1.getEdge(t1, t5));
		System.out.println(g1.shortestPath(t5, t1));
	}
}

