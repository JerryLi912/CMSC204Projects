/**
 * This Road class serves as a edge 
 * @author jerry
 *
 */
public class Road implements Comparable<Road> {

	private String m_roadName;
	private int m_weight;
	private Town m_source, m_destination;
	
	public Road(Town town1, Town town2, int miles, String roadName) {
		this.m_roadName = roadName;
		this.m_weight = miles;
		this.m_source = town1;
		this.m_destination = town2;
	}
	
	public Road(Town source, Town destination, String roadName) {
		this.m_source = source;
		this.m_destination = destination;
		this.m_roadName = roadName;
		this.m_weight = 1;
	}
	
	@Override
	public int compareTo(Road road) {
		if (this.m_roadName.compareTo(road.m_roadName)==0) 
			return 0;
		if (this.m_roadName.compareTo(road.m_roadName) == 1) {
			return 1;
		}
		return -1;
	}
	

	public String getM_roadName() {
		return m_roadName;
	}

//	public void setM_roadName(String m_roadName) {
//		this.m_roadName = m_roadName;
//	}

	public int getM_weight() {
		return this.m_weight;
	}
	
//	public void setM_weight(int m_milesBetweenTwoTowns) {
//		this.m_weight = m_milesBetweenTwoTowns;
//	}

	
	public boolean contains(Town town) {
		if (town == getSource() || town == getDestination()) {
			return true;
		}
		return false;
	}
	
	//the second contain method is for the TownRoadManager class
	public boolean containsTownName(String townName) {
		if(townName.equals(getSource().getM_townName()) || townName.equals(getDestination().getM_townName())) {
			return true;
		}
		return false;
	}
	
	
	public Town getSource() {
		return this.m_source;
	}
	
	public void setSource(Town town) {
		if(town.equals(m_destination)) {
		this.m_source = town;
		}
	}
	
	public Town getDestination() {
		return this.m_destination;
	}
	
	public void setDestination(Town town) {
		if(town.equals(m_source)) {
		this.m_destination = town;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Road road = (Road)obj;
		
		boolean checkRoad1 = (road.m_roadName.equals(this.m_roadName));
		boolean checkRoadWeight1 = (road.m_weight == this.m_weight);
		
		boolean checkTown1 = (road.m_source.equals(this.m_source));
		boolean checkTown2 = (road.m_destination.equals(this.m_destination));
		
		boolean checkTown_1 = (road.m_destination.equals(this.m_source));
		boolean checkTown_2 = (road.m_source.equals(this.m_destination));
		
		return checkRoad1 && checkRoadWeight1 && (checkTown1 && checkTown2) || (checkTown_1 && checkTown_2);
	}
	

	@Override
	public int hashCode() {
		int hash = 0;
		for(int i = 0; i < m_roadName.length(); i++) {
			hash = 31 * hash + m_roadName.charAt(i);
			if(hash < 0) {
				hash *= (-1);
			}
		}		
		return hash;
	}
	
	public String toString() {
		String result = "";
		result = getM_roadName() + "," + getM_weight() + ";" +
				 getSource() + ";" + getDestination();
		return result;
	}
}

