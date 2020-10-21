import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_StudentTest {
	BasicDoubleLinkedList<Integer> linkedInteger;
	integerComparator comparatorInteger;
	
	@Before
	public void setUp() throws Exception {
		comparatorInteger = new integerComparator();
		linkedInteger = new BasicDoubleLinkedList<Integer>();
		linkedInteger.addToEnd(50);
		linkedInteger.addToEnd(25);

	
	}

	@After
	public void tearDown() throws Exception {	
		linkedInteger = null;
		comparatorInteger = null;
	}
	
	@Test
	public void testGetSize() {
		assertEquals(2,linkedInteger.getSize());
	}
	
	
	@Test
	public void testAddToEnd() {
		assertEquals(25, linkedInteger.getLast(), 0.0001);
		linkedInteger.addToEnd(12);
		assertEquals(12, linkedInteger.getLast(), 0.0001);
		linkedInteger.addToEnd(13);
		assertEquals(4, linkedInteger.getSize());
	}
	
	
	
	@Test
	public void testAddToFront() {
		assertEquals(50, linkedInteger.getFirst(), 0.0001);
		linkedInteger.addToFront(23);
		assertEquals(23, linkedInteger.getFirst(), 0.0001);
	}
	
	@Test
	public void testGetFirst() {
		assertEquals(50, linkedInteger.getFirst(), 0.0001);
		linkedInteger.addToFront(1);
		assertEquals(1, linkedInteger.getFirst(), 0.0001);
	}

	@Test
	public void testGetLast() {
		assertEquals(25, linkedInteger.getLast(), 0.0001);
		linkedInteger.addToEnd(100);
		assertEquals(100, linkedInteger.getLast(), 0.0001);		
	}
	
	@Test
	public void testToArrayList(){
		ArrayList<Integer> list;
		linkedInteger.addToFront(20);
		linkedInteger.addToEnd(30);
		list = linkedInteger.toArrayList();
		assertEquals(20 ,list.get(0), 0.0001);
		assertEquals(50 ,list.get(1), 0.0001);
		assertEquals(25 ,list.get(2), 0.0001);
		assertEquals(30 ,list.get(3), 0.0001);
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedInteger.addToFront(20);
		linkedInteger.addToEnd(30);
		ListIterator<Integer> iterator = linkedInteger.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(20, iterator.next(), 0.0001);
		assertEquals(50, iterator.next(), 0.0001);
		assertEquals(25, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasNext());
		assertEquals(30, iterator.next(), 0.0001);
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedInteger.addToFront(20);
		linkedInteger.addToEnd(30);
		ListIterator<Integer> iterator = linkedInteger.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(20, iterator.next(), 0.0001);
		assertEquals(50, iterator.next(), 0.0001);
		assertEquals(25, iterator.next(), 0.0001);
		assertEquals(30, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(30, iterator.previous(), 0.0001);
		assertEquals(25, iterator.previous(), 0.0001);
		assertEquals(50, iterator.previous(), 0.0001);
		assertEquals(20, iterator.previous(), 0.0001);
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedInteger.addToFront(20);
		linkedInteger.addToEnd(30);
		ListIterator<Integer> iterator = linkedInteger.iterator();		
		assertEquals(true, iterator.hasNext());
		assertEquals(20, iterator.next(), 0.0001);
		assertEquals(50, iterator.next(), 0.0001);
		assertEquals(25, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasNext());
		assertEquals(30, iterator.next(), 0.0001);
		
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedInteger.addToFront(20);
		linkedInteger.addToEnd(30);
		ListIterator<Integer> iterator = linkedInteger.iterator();		
		assertEquals(true, iterator.hasNext());
		assertEquals(20, iterator.next(), 0.0001);
		assertEquals(50, iterator.next(), 0.0001);
		assertEquals(25, iterator.next(), 0.0001);
		assertEquals(30, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(30, iterator.previous(), 0.0001);
		assertEquals(25, iterator.previous(), 0.0001);
		assertEquals(50, iterator.previous(), 0.0001);
		assertEquals(20, iterator.previous(), 0.0001);
		
		try{
			iterator.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedInteger.addToFront(20);
		linkedInteger.addToEnd(30);
		ListIterator<Integer> iterator = linkedInteger.iterator();		
		assertEquals(true, iterator.hasNext());
		assertEquals(20, iterator.next(), 0.0001);
		assertEquals(50, iterator.next(), 0.0001);
		assertEquals(25, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasNext());
		assertEquals(30, iterator.next(), 0.0001);
		
		try{
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	
	@Test
	public void testRemove() {
		// remove the first
		assertEquals(50, linkedInteger.getFirst(), 0.0001);
		assertEquals(25, linkedInteger.getLast(), 0.0001);
		linkedInteger.addToFront(21);
		assertEquals(21, linkedInteger.getFirst(), 0.0001);
		linkedInteger.remove(21, comparatorInteger);
		assertEquals(50, linkedInteger.getFirst(), 0.0001);
		//remove from the end of the list
		linkedInteger.addToEnd(34);
		assertEquals(34, linkedInteger.getLast(), 0.0001);
		linkedInteger.remove(34, comparatorInteger);
		assertEquals(25, linkedInteger.getLast(), 0.0001);
		//remove from middle of list
		linkedInteger.addToFront(9);
		assertEquals(9, linkedInteger.getFirst(), 0.0001);
		assertEquals(25, linkedInteger.getLast(), 0.0001);
		linkedInteger.remove(9, comparatorInteger);
		assertEquals(50, linkedInteger.getFirst(), 0.0001);
		assertEquals(25, linkedInteger.getLast(), 0.0001);
		}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(50, linkedInteger.getFirst(), 0.0001);
		linkedInteger.addToFront(20);
		assertEquals(20, linkedInteger.getFirst(), 0.0001);
		assertEquals(20, linkedInteger.retrieveFirstElement(), 0.0001);
		assertEquals(50, linkedInteger.getFirst(), 0.0001);
		assertEquals(50, linkedInteger.retrieveFirstElement(), 0.0001);
		assertEquals(25, linkedInteger.getFirst(), 0.0001);

	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(25, linkedInteger.getLast(), 0.0001);
		linkedInteger.addToEnd(13);
		assertEquals(13, linkedInteger.getLast(), 0.0001);
		assertEquals(13, linkedInteger.retrieveLastElement(), 0.0001);
		assertEquals(25,linkedInteger.getLast(), 0.0001);
		
	}
	
	private class integerComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer arg0, Integer arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}

