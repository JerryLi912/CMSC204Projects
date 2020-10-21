import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_StudentTest {

	SortedDoubleLinkedList<Integer> linkedInteger;
	integerComparator comparatorInteger;
	
	@Before
	public void setUp() throws Exception {
		comparatorInteger = new integerComparator();
		linkedInteger = new SortedDoubleLinkedList<Integer>(comparatorInteger);
	}

	@After
	public void tearDown() throws Exception {
		
		linkedInteger = null;
		comparatorInteger = null;
	}
	
	@Test
	public void testAdd() {
		linkedInteger.add(1);
		linkedInteger.add(2);
		linkedInteger.add(3);
		assertEquals(1, linkedInteger.getFirst(), 0.0001);
		assertEquals(3, linkedInteger.getLast(), 0.0001);
		linkedInteger.add(9);
		linkedInteger.add(6);
		assertEquals(1, linkedInteger.getFirst(), 0.0001);
		assertEquals(9, linkedInteger.getLast(), 0.0001);
		assertEquals(9, linkedInteger.retrieveLastElement(), 0.0001);
		assertEquals(6, linkedInteger.getLast(), 0.0001);
	}

	@Test
	public void testAddToEnd() {
		try {
			linkedInteger.addToEnd(100);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			linkedInteger.addToFront(999);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testRemoveFirstInteger() {
		linkedInteger.add(32);
		linkedInteger.add(2);
		assertEquals(2, linkedInteger.getFirst(), 0.0001);
		assertEquals(32, linkedInteger.getLast(), 0.0001);
		linkedInteger.add(5);
		assertEquals(2, linkedInteger.getFirst(), 0.0001);
		// remove the first
		linkedInteger.remove(2, comparatorInteger);
		assertEquals(5, linkedInteger.getFirst(), 0.0001);
	}
	
	@Test
	public void testRemoveEndInteger() {
		linkedInteger.add(32);
		linkedInteger.add(2);
		assertEquals(2, linkedInteger.getFirst(), 0.0001);
		assertEquals(32, linkedInteger.getLast(), 0.0001);
		linkedInteger.add(1);
		assertEquals(32, linkedInteger.getLast(), 0.0001);
		//remove from the end of the list
		linkedInteger.remove(32, comparatorInteger);
		assertEquals(32, linkedInteger.getLast(), 0.0001);
	}

	@Test
	public void testRemoveMiddleInteger() {
		linkedInteger.add(32);
		linkedInteger.add(2);
		assertEquals(2,linkedInteger.getFirst(), 0.0001);
		assertEquals(32, linkedInteger.getLast(), 0.0001);
		linkedInteger.add(4);
		assertEquals(2, linkedInteger.getFirst(), 0.0001);
		assertEquals(32, linkedInteger.getLast(), 0.0001);
		assertEquals(3,linkedInteger.getSize(), 0.0001);
		//remove from middle of list
		linkedInteger.remove(4, comparatorInteger);
		assertEquals(2, linkedInteger.getFirst(), 0.0001);
		assertEquals(32, linkedInteger.getLast(), 0.0001);
		assertEquals(2,linkedInteger.getSize(), 0.0001);
	}

	@Test
	public void testIteratorSuccessfulNext() {
		linkedInteger.add(1);
		linkedInteger.add(3);
		linkedInteger.add(4);
		linkedInteger.add(2);
		ListIterator<Integer> iterator = linkedInteger.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(1, iterator.next(), 0.0001);
		assertEquals(2, iterator.next(), 0.0001);
		assertEquals(3, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasNext());
		assertEquals(4, iterator.next(), 0.0001);	
	}
	
	@Test
	public void testIteratorSuccessfulIntegerPrevious() {
		linkedInteger.add(1);
		linkedInteger.add(3);
		linkedInteger.add(4);
		linkedInteger.add(2);
		ListIterator<Integer> iterator = linkedInteger.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(1, iterator.next(), 0.0001);
		assertEquals(2, iterator.next(), 0.0001);
		assertEquals(3, iterator.next(), 0.0001);
		assertEquals(4, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(4, iterator.previous(), 0.0001);
		assertEquals(3, iterator.previous(), 0.0001);
		assertEquals(2, iterator.previous(), 0.0001);
		assertEquals(1, iterator.previous(), 0.0001);
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		linkedInteger.add(1);
		linkedInteger.add(3);
		linkedInteger.add(4);
		linkedInteger.add(2);
		ListIterator<Integer> iterator = linkedInteger.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(1, iterator.next(), 0.0001);
		assertEquals(2, iterator.next(), 0.0001);
		assertEquals(3, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasNext());
		assertEquals(4, iterator.next(), 0.0001);
		try{
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
	public void testIteratorUnsupportedOperationExceptionInteger() {
		linkedInteger.add(1);
		linkedInteger.add(3);
		linkedInteger.add(4);
		linkedInteger.add(2);
		ListIterator<Integer> iterator = linkedInteger.iterator();
		
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
	
	
	
	private class integerComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
}
