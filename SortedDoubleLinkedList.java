import java.util.Comparator;
import java.util.ListIterator;
/**
 * 
 * @author jerry This program sorts the double linked list in ascending order.
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	Comparator<T> comparator;
	
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
		
	public SortedDoubleLinkedList<T> add(T data) {
		
		Node<T> newNode = new Node<T>(data);
		if(head == null) {
			head = tail = newNode;
			head.m_next = null;
			head.m_prev = null;
			System.out.println("You added a newNode in the empty list: " + head.m_data);
			size++;
			return this;
		}
		
		Node<T> current = head;
		
		if(comparator.compare(head.m_data, newNode.m_data) > 0) {
			current.m_prev = newNode;
			newNode.m_next = current;
			newNode.m_prev = null;
			current.m_next = null;
			head = newNode;	
			System.out.println("You added a node in the list that has only one head: " + head.m_data);		
			size++;
			return this;
		}
		
		
		Node<T> preNode = null;
		while(current != null && comparator.compare(current.m_data, newNode.m_data) <= 0) {
			preNode = current;
			current = current.m_next;
		}
		
		if(current == null) {
			preNode.m_next = newNode;
			newNode.m_prev = preNode;
			newNode.m_next = null;
			tail = newNode;
			System.out.println("You added a node at the end of the list: " + tail.m_data);
			size++;
			return this;
		}
		
		preNode.m_next = newNode;
		newNode.m_prev = preNode;
		newNode.m_next = current;
		current.m_prev = newNode;	
		System.out.println("You added a node between two nodes: " + data);
		size++;
		return this;
	}
	
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		if(this.head == null) {
			return null;
		}
		
		Node<T> current = head;
		if(comparator.compare(current.m_data, targetData) == 0) {
			current = head.m_next;
			current.m_prev = null;
			head = current;
			size--;
			return this;
		}
		
		Node<T> preNode = current;
		current = current.m_next;
		while(current != null && comparator.compare(current.m_data, targetData) != 0) {
			preNode = current;
			current = current.m_next;
		}
		if(current == null) {
			size--;
			return this;
		}
		
		Node<T>tempNode = current.m_next;
		if(tempNode == null) {
			preNode.m_next = null;
			current.m_prev = null;
			this.tail = preNode;
			this.tail.m_next = null;
			System.out.println("Removed: " + current.m_data);
			size--;
			return this;
		}
		
		preNode.m_next = tempNode;
		tempNode.m_prev = preNode;
		current.m_next = null;
		current.m_prev = null;
		size--;
		return this;
	}

	//personal test
	public static void main(String[] args) {
		IntegerComparator compare = new IntegerComparator();
		SortedDoubleLinkedList<Integer> linkedInteger = new SortedDoubleLinkedList<>(compare);
		linkedInteger.add(1);
		linkedInteger.add(2);
		linkedInteger.add(3);
		linkedInteger.add(4);
		linkedInteger.add(3);
		linkedInteger.add(2);
		linkedInteger.display();
		System.out.println("\nLast item: " + linkedInteger.getLast() + "\n");
		linkedInteger.add(7);
		linkedInteger.display();
		System.out.println("\nLast item: " + linkedInteger.getLast() + "\n");
		linkedInteger.add(6);
		linkedInteger.display();
		System.out.println("\nLast item: " + linkedInteger.getLast() + "\n");
		linkedInteger.add(1);
//		System.out.println("\n");
		linkedInteger.display();
		System.out.println("\nRemove the number 3.");
		linkedInteger.remove(3, compare);
		System.out.println("\n");
		linkedInteger.display();
		System.out.println("\n");
		linkedInteger.add(6);
		System.out.println("\n");
		linkedInteger.add(9);
		System.out.println("\n");
		linkedInteger.remove(3, compare);
		linkedInteger.display();
		System.out.println("\nLast item: " + linkedInteger.getLast() + "\n");
		linkedInteger.remove(9, compare);
		System.out.println("\n");
		linkedInteger.display();
		System.out.println("\nLast item: " + linkedInteger.getLast() + "\n");
		
	}
}
