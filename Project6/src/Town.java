import java.util.Comparator;
import java.util.LinkedList;
/**
 * This Town class serves as a vertex
 * @author jerry
 *
 */
public class Town implements Comparable<Town> {

	private String m_townName;
	protected LinkedList<Road>m_adjList = new LinkedList<>();
	
	public Town(String townName) {
		this.m_townName = townName;
	}
	
	public Town(Town templateTown) {
		this.m_townName = templateTown.m_townName;
	}
	
	@Override
	public int compareTo(Town town) {
		if (this.m_townName.equals(town.m_townName))
			return 0;	
		return -1;
	}
	

	public String getM_townName() {
		return m_townName;
	}


	public void setM_townName(String m_townName) {
		this.m_townName = m_townName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Town town = (Town)obj;
		boolean checkName = (town.m_townName.equals(this.m_townName));
		return checkName;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		int name_length = m_townName.length();
		for(int i = 0; i < name_length; i++) {
			hash = 31 * hash + m_townName.charAt(i);
			if(hash < 0) {
				hash *= (-1);
			}
		}
		return hash;
	}

	
	
	public String toString() {
		return getM_townName();
	}
	//Personal test
	public static void main(String[] args) {
		Town t1 = new Town("Bethesda");
		Town t2 = new Town("Rockville");
		Town t3 = new Town("Olney");
		Town t4 = new Town("Geithersburg");
		Town t5 = new Town("Potomac");
		
		Road r1 = new Road(t1, t2, 14, "I-900");
		Road r2 = new Road(t2, t3, 8, "MD97");
		Road r3 = new Road(t2, t4, 7, "1270-SC");
		Road r4 = new Road(t2, t5, 6, "MD189");
		Road r5 = new Road(t5, t2, 6, "MD189");
//		t5.addAdjacentTowns(t1);
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		System.out.println(r4);
		System.out.println(r5 + "\n\n");
//		System.out.println(t5.getList());
		
		
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		System.out.println(t4);
		System.out.println(t5);
	
	System.out.println("Is the road the same road between t4 and t5?\n" + r4.equals(r5));
		
	}

}
