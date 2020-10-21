import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author jerry  This program aims to create the basic features in double linked list. 
 * 
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class BasicDoubleLinkedList<T> implements Iterable<T>{
	
	protected Node<T> head; 
	protected Node<T> tail; 
	protected int size; //number of elements in the list
	
	//initialize head, tail, size
	public BasicDoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	//the node inner class
	@SuppressWarnings("hiding")
	 class Node<T>{
		 T m_data;
		 Node<T> m_next;
		 Node<T> m_prev;
		public Node(T data) {
			this.m_data = data;
			this.m_next = null;
			this.m_prev = null;
		}
		public T getData(){
			return this.m_data;
		}
	}//end class node
	
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node<T> newNode = new Node<T>(data);
		Node<T> current = head;
		
		if(this.head == null) {
			//both head and tail point to newNode
			this.head = this.tail = newNode;
			this.head.m_prev = null;
			this.tail.m_next = null;
			size++;
			//return this.tail.m_data;
			return this;
		}
		while (current.m_next != null) {
			current = current.m_next;
		}

		current.m_next = newNode;
		newNode.m_prev = current;
		//now let the tail become the newNode:
		this.tail = newNode;
		//this.tail.m_next = null;
		size++;
		
		//return this.tail.m_data;
		return this;
	}
	
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node<T> newNode = new Node<T>(data);
		Node<T> current = head;
		if(this.head == null) {
			this.head = this.tail = newNode;
			this.head.m_prev = null;
			this.tail.m_next = null;
			size++;
			
			//return this.head.m_data;
			return this;
		}
		
		current.m_prev = newNode;
		newNode.m_next = current;
		newNode.m_prev = null;
		//now let the head become the newNode
		this.head = newNode;
	//	this.head.m_prev = null;
		size++;
		
		//return this.head.m_data;
		return this;
	}
	
	public T getFirst() {
		return this.head.m_data;
	}
	public T getLast() {
		return this.tail.m_data;
	}
	
	public int getSize() {
		return size;
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
		//	System.out.println("Removed: " + current.m_data);
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
	
	//remove and get the first element
	public T retrieveFirstElement() {
		Node<T> temp = this.head;
		if(this.head == null) {
			return null;
		}
		this.head = this.head.m_next;
		temp.m_next = null;
		this.head.m_prev = null;
		return temp.m_data;
	}
	
	//remove and get the last element
	public T retrieveLastElement() { 
		Node<T> temp = this.tail;
		if(this.head == null && this.tail == null) {
			return null;
		}
		this.tail = this.tail.m_prev;
		temp.m_prev = null;
		temp.m_next = null;
		return temp.m_data;
	}
	
	//the Iterator inner class
	public class DoubleLinkedListIterator implements ListIterator<T>{
		private Node<T> curHead; 
		private Node<T> curTail;
		public DoubleLinkedListIterator() {
			curHead = head;
			curTail = tail;
		}
		
		@Override
		public void add(Object arg0) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			if(curHead == null) {
				return false;
			}
			if(curHead == tail) {
				return true;
			}
			return (curHead.m_next != null);
		}
		
		@Override
		public boolean hasPrevious() {
			if(curTail == null) {
				return false;
			}
			
		    if(curTail == head) {
				return true;
			}
			return (curTail.m_prev != null);
		}
		@Override
		public T next() throws NoSuchElementException {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T value = curHead.m_data;
			curHead = curHead.m_next;
			return value;
		}
		
		@Override
		public int nextIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		//Not sure why previous() is wrong?
		@Override
		public T previous() throws NoSuchElementException {
			
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			
			T value = curTail.m_data;
			curTail = curTail.m_prev;
			return value;
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(Object e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}
		
	}//end inner class Iterator
	
	
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException{
		return new DoubleLinkedListIterator();
	}
	
	
	@SuppressWarnings("unused")
	public ArrayList<T> toArrayList() {
		ArrayList<T> newArrayList = new ArrayList<>();
		int count = 0;
		if(this.head == null) {
			throw new NoSuchElementException();
		}
		while(this.head != null && this.head != this.tail.m_next) {
			newArrayList.add(this.head.m_data);
			this.head = this.head.m_next;
			count++;
		}
		return newArrayList;
	}

	
	
	
	 //display() will print out the nodes of the list  
    public void display() {  
        //Node current will point to head  
        Node current = head;  
        if(head == null) {  
            System.out.println("List is empty");  
            return;  
        }  
        System.out.println("Nodes of doubly linked list: ");  
        while(current != null) {  
            //Prints each node by incrementing the pointer.  
  
            System.out.print(current.m_data + " ");  
            current = current.m_next;  
        }  
	
    }
	
	
	
	public static void main(String[] args) {
		BasicDoubleLinkedList<Integer> linkedInteger = new BasicDoubleLinkedList<>();
		linkedInteger.addToEnd(1);
		linkedInteger.addToEnd(2);
		linkedInteger.addToEnd(3);
		linkedInteger.addToFront(4);
		linkedInteger.addToFront(5);
		ListIterator<Integer> iter = linkedInteger.iterator();
		iter.hasPrevious();
		linkedInteger.display();
		System.out.println("\n\nLast item: " + linkedInteger.getLast());
		IntegerComparator comparator = new IntegerComparator();
		linkedInteger.remove(3, comparator);
		System.out.println("\nRemove last");
		linkedInteger.display();
		System.out.println("\nLast item: " + linkedInteger.getLast());
		linkedInteger.display();
		System.out.println("\n\nAdd item to the end\n");
		linkedInteger.addToEnd(123);
		linkedInteger.display();
		linkedInteger.remove(123, comparator);
		System.out.println("\n\nRemove last\n");
		linkedInteger.display();
		System.out.println("\nLast item: " + linkedInteger.getLast());
	}	
}

class IntegerComparator implements Comparator<Integer>
{

	@Override
	public int compare(Integer arg0, Integer arg1) {
		// TODO Auto-generated method stub
		return arg0.compareTo(arg1);
	}
	
}