import java.util.ArrayList;
/**
 * 
 * @author jerry
 *
 * @param <T> T is data type 
 * 
 * This NotationQueue class implements the QueueInterface and uses array to implement Queue. 
 */

public class NotationQueue <T> implements QueueInterface<T>{
	private T[]arrayQueue;
	private int frontIndex;
	private int backIndex;
	private static final int DEFAULT_CAPACITY = 50;
	private int capacity; // total number of elements in queue
	private int current; //current number of elements
	
	/**
	 * default the queue size
	 */
	public NotationQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 
	 * @param capacity The desired capacity of queue
	 */
	@SuppressWarnings("unchecked")
	public NotationQueue(int capacity) {
		this.capacity = capacity;
		this.arrayQueue = (T[]) new Object[this.capacity];
		this.frontIndex = 0;
		this.backIndex  = 0;
	}
	
	/**
	 * 
	 * @param list Copy the ArrayList to arrayQueue 
	 */
	@SuppressWarnings("unchecked")
	public NotationQueue(ArrayList<T> list) {
		this.arrayQueue = (T[]) new Object[list.size()];
		this.frontIndex = 0;
		this.backIndex = 0;
		this.current = list.size();
		this.capacity = list.size();
		for(int i = 0; i < arrayQueue.length; i++) {
			arrayQueue[i] = list.get(i);
			backIndex++;	
		}
	}
	
	/**
	 * Check to see if queue is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return (frontIndex == backIndex);
	}

	/**
	 * check is the queue is full or not
	 * @return Return true if the queue is full 
	 */
	@Override
	public boolean isFull() {
		return (this.current == this.capacity);
	}

	/**
	 * 
	 * @return Return the front element of the queue
	 * @throws QueueUnderflowException
	 */
	public T getFront() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		else {
			return (arrayQueue[frontIndex % capacity]);
		}
	}
	
	/**
	 * @return Return the result after dequeue
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		T result = getFront();
		arrayQueue[frontIndex % this.capacity] = null; //for garbage collection
		frontIndex++;
		this.current--;
		return result;
	}

	/**
	 * @return Return the current size of the queue
	 */
	@Override
	public int size() {
		return this.current;
	}
	
	/**
	 * @return Return true if successfully enqueue
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException(false);
		}
		
		//the % I did to the backIndex means that when the backIndex is greater than the array capacity, 
		// it will warp-around the array, which is add the element to the first index and so on. 
		arrayQueue[backIndex % capacity] = e;
		backIndex++;
		this.current++;
		return true;
	}

	/**
	 * @return Return the elements in the queue in string format
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < size(); i++) {
			result = result + arrayQueue[i].toString();
		}
		return result;
	}
	/**
	 * @return Return the elements in the queue in a desired string format
	 */
	@Override
	public String toString(String delimiter) {
		
		String separated = "";
		
		for (int i = 0; i < size(); i++) {
			separated += arrayQueue[i].toString();
			if (i <= size()-2) {
				separated += delimiter;
			}
		}
		return separated;	
	}

	/**
	 * fill in the queue with a list of elements from ArrayList
	 */
	@Override
	public void fill(ArrayList<T> list) {
		@SuppressWarnings({ "unchecked" })
		T[]result = (T[]) new Object[list.size()];
		for(int index = 0; index < list.size(); index++) {
			result[index] = list.get(index);
			try {
				enqueue(result[index]);
			} catch (QueueOverflowException e) {
				e.printStackTrace();
			}
		}
	
   }
	
	//this is just personal test
	public static void main(String[] args) {
	    NotationQueue<Integer> q = new NotationQueue<>(10);
	    ArrayList<String> list = new ArrayList<>();
	    list.add("apple");
	    list.add("banana");
	    list.add("fruit");
	    NotationQueue<String> q1 = new NotationQueue<>(list);
	    
	   try {
		System.out.println("If successfully added an element in Queue, output true.");
	    System.out.println("Adding the 1st element:" + q.enqueue(1));
	    
	    System.out.println("Adding the 2nd element:" + q.enqueue(2));
	   
	    System.out.println("Adding the 3rd element:" + q.enqueue(3));
	    
	    System.out.println("Adding the 4th element:" + q.enqueue(4));
	    
	    System.out.println("Adding the 5th element:" + q.enqueue(5));
	   
	    System.out.println("Adding the 6th element:" + q.enqueue(6));
	  
	    System.out.println("Adding the 7th element:" + q.enqueue(7));
	   
	    System.out.println("Adding the 8th element:" + q.enqueue(8));
	    
	    System.out.println("Remove the 1st element number 1 -> " + q.dequeue());
	    System.out.println("Remove the 2nd element number 2 -> " + q.dequeue());
	    System.out.println("Remove the 3rd element number 3 -> " + q.dequeue());
	    System.out.println("Remove the 4th element number 4 -> " + q.dequeue());
	    System.out.println("Remove the 5th element number 5 -> " + q.dequeue());
	    
	    System.out.println("Adding the 9th element:" + q.enqueue(1));
	    
	    System.out.println("Adding the 10th element:" + q.enqueue(2));
	   
	    System.out.println("Adding the 11th element:" + q.enqueue(3));
	    
	    System.out.println("Adding the 12th element:" + q.enqueue(4));
	    
	    System.out.println("Adding the 13th element:" + q.enqueue(5));
	    
	   
	    System.out.println("After removing the first two elements in the array, from front to back, we get: ");
	    while (!q.isEmpty()) {
	        System.out.print(q.getFront() + " ");
	        q.dequeue();
	    }
	    
	   System.out.println("\nThe number of items added from ArrayList inside queue1 is " 
			   				+ q1.size() + "\nThe items are:  " + q1.toString(" & "));
	    
	   }catch(QueueOverflowException | QueueUnderflowException e) {
		   e.printStackTrace();
	   }
	}    
}

@SuppressWarnings("serial")
class QueueOverflowException extends Exception{
	private boolean value;
	public QueueOverflowException() {
		super("This should have caused an QueueOverflowException");
	}
	
	public QueueOverflowException(boolean value) {
		super("This should have caused an QueueOverflowException");
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
}

@SuppressWarnings("serial")
class QueueUnderflowException extends Exception{
	private boolean value;
	public QueueUnderflowException() {
		super("This should have caused an QueueUnderflowException");
	}
	public QueueUnderflowException(boolean value) {
		super("This should have caused an QueueUnderflowException");
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
}




