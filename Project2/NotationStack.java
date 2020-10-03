import java.util.ArrayList;

/**
 * @author jerry
 *
 * @param <T> T is data type 
 * 
 * This NotationStack class implements the StackInterface and uses array to implement Stack. 
 */
public class NotationStack<T> implements StackInterface<T> {
	private int top;
	private int capacity;
	private final static int DEFAULT_CAPACITY = 50;
	private T[] arrayStack;

	/**
	 * default the stack size
	 */
	public NotationStack() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 
	 * @param capacityIn The desired capacity of the stack
	 */
	@SuppressWarnings("unchecked")
	public NotationStack(int capacityIn) {
		this.capacity = capacityIn;
		this.top = -1;
		this.arrayStack = (T[]) new Object[capacity];		
	}
	
	/**
	 * 
	 * @param list Copy the ArrayList to arrayStack
	 */
	@SuppressWarnings("unchecked")
	public NotationStack(ArrayList<T> list) {
		this.arrayStack = (T[]) new Object[list.size()];
		this.top = -1;
		this.capacity = list.size();
		for(int i = 0; i < arrayStack.length; i++) {
			arrayStack[i] = list.get(i);
			top++;
		}
	}
	
	/**
	 * @return Return true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return (top < 0);
	}

	/**
	 * @return Return true if stack is full
	 */
	@Override
	public boolean isFull() {
		return (size() == capacity);
	}

	/**
	 * @return Return the popped element from stack
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		else{
			T topArray = arrayStack[top];
			arrayStack[top--] = null;
			return topArray;
		}
	}

	/**
	 * @return Return the element at the top of the stack
	 */
	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();	
		}
		return (T)arrayStack[top];
	}
	
	/**
	 * @return Return the size of the stack
	 */
	@Override
	public int size() {
		return (top + 1);
	}

	/**
	 * @return Return true if the element is successfully pushed to stack
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if (size() == capacity) {
			throw new StackOverflowException(false);
		}
		arrayStack[top + 1] = e;
		top++;
		
		return true;
	}
	
	/**
	 * @return Return the elements in stack in string format
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < size(); i++) {
			result = result + arrayStack[i].toString();
		}
		return result;
	}
	
	/**
	 * @return Return the elements in stack in a desired string format
	 */
	@Override
	public String toString(String delimiter) {
		String separated = "";
		
		for (int i = 0; i < size(); i++) {
			separated += arrayStack[i].toString();
			if (i <= size()-2) {
				separated += delimiter;
			}
		}
		return separated;
	}	
	
	/**
	 * fill in the stack with a list of elements from ArrayList
	 */
	@Override
	public void fill(ArrayList<T> list) {
		@SuppressWarnings({ "unchecked" })
		T[]result = (T[]) new Object[list.size()];
		for(int index = 0; index < list.size(); index++) {
			result[index] = list.get(index);
			try {
				push(result[index]);
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}
	}

	//person test only
	public static void main(String[] args) {
	    NotationStack<Integer> q = new NotationStack<>(9);
	    
	    ArrayList<String> list = new ArrayList<>();
	    list.add("apple");
	    list.add("banana");
	    list.add("fruit");
	    NotationStack<String> q1 = new NotationStack<>(list);
	   try {
		System.out.println("If successfully added an element in Stack, output true.");
	    System.out.println("Adding the first element:" + q.push(1));
	    
	    System.out.println("Adding the second element:" + q.push(2));
	   
	    System.out.println("Adding the third element:" + q.push(3));
	    
	    System.out.println("Remove the fourth element:" + q.push(4));
	    
	    System.out.println("Remove the top element number 4 -> " + q.pop());
	    System.out.println("Remove the top element number 3 -> " + q.pop());
	  
	    System.out.println("Adding the fifth element:" + q.push(5));
	   
	    System.out.println("Adding the sixth element:" + q.push(6));
	  
	    System.out.println("Adding the seventh element:" + q.push(7));
	   
	    System.out.println("Adding the eighth element:" + q.push(8));
	    
	    System.out.println("After removing the first two elements in the array, from top to bottom, we get: ");
	    while (!q.isEmpty()) {
	        System.out.print(q.top() + "\n");
	        q.pop();
	    }
	    System.out.println("\nThe number of items inside stack after adding the elements from ArrayList is " + q1.size());
	   }catch(StackOverflowException | StackUnderflowException e) {
		   e.printStackTrace();
	   }
	}    
	
}




@SuppressWarnings("serial")
class StackOverflowException extends Exception{
	@SuppressWarnings("unused")
	private boolean value;
	
	public StackOverflowException() {
		super("This should have caused an StackOverflowException");	
	}
	
	public StackOverflowException(boolean value) {
		super("This should have caused an StackOverflowException");
		this.value = value;
	}
	
	
	 public boolean getValue(boolean value) {
		 return value;
	 }
}


@SuppressWarnings("serial")
class StackUnderflowException extends Exception{
	@SuppressWarnings("unused")
	private boolean value;
	
	public StackUnderflowException() {
		super("This should have caused an StackUnderflowException");
	}
	public StackUnderflowException(boolean value) {
		super("This should have caused an StackUnderflowException");
		this.value = value;
	}
	
	public boolean getValue(boolean value) {
		 return value;
	 }
}


