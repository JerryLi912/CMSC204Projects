import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author jerry
 *
 */
public class MorseCodeTree_StudentTest {

	MorseCodeTree<String> tree;
	ArrayList<String> list;
	ArrayList<String> buildTreeList;
	String letterA, letterB, letterC;
	String codeA, codeB, codeC;
	String inOrderTree;
	@Before
	public void setUp() {
		tree = new MorseCodeTree<>();
		list = new ArrayList<>();
		buildTreeList = tree.toArrayList();
		letterA = "a";
		letterB = "b";
		letterC = "c";
		codeA = ".-";
		codeB = "-...";
		codeC = "-.-.";
		inOrderTree = "h s v i f u e l r a p w j  b d x n c k y t z g q m o";
	}
	
	@After
	public void tearDown() {
		tree = null;
		list = null;
		buildTreeList = null;
		letterA = letterB = letterC = null;
		codeA = codeB = codeC = null;
		inOrderTree = null;
	}
	
	@Test 
	public void testGetRoot() {
		assertEquals("", tree.getRoot().getData());
	}
	
	@Test
	public void testInsert() {
		//if the node is inserted, then it should return the tree
		assertEquals(this.tree, tree.insert(codeA, letterA));
		assertEquals(this.tree, tree.insert(codeB, letterB));
		assertEquals(this.tree, tree.insert(codeC, letterC));		 
	}
	
	@Test
	public void testAddNode() {
		//if the node is added, then ArrayList should contain the node data
		assertEquals(true, tree.toArrayList().contains(letterA));
		assertEquals(true, tree.toArrayList().contains(letterB));
		assertEquals(true, tree.toArrayList().contains(letterC));
		
		//add extra nodes
		tree.addNode(tree.getRoot(), ".....", "A");
		tree.addNode(tree.getRoot(), "....-", "B");
		tree.addNode(tree.getRoot(), "----", "C");
		tree.addNode(tree.getRoot(), "......", "D");
		tree.addNode(tree.getRoot(), "-....", "E");
		assertEquals(true, tree.toArrayList().contains("A"));
		assertEquals(true, tree.toArrayList().contains("B"));
		assertEquals(true, tree.toArrayList().contains("C"));
		assertEquals(true, tree.toArrayList().contains("D"));
		assertEquals(true, tree.toArrayList().contains("E"));
		
	}
	
	@Test
	public void testFetch() {
		//the fetch method return the data returned by FetchNode()
		assertEquals(letterA, tree.fetch(codeA));
		assertEquals(letterB, tree.fetch(codeB));
		assertEquals(letterC, tree.fetch(codeC));
	}
	
	@Test
	public void testFetchNode() {
		//if the node exists, then it should be fetched
		assertEquals(letterA, tree.fetchNode(tree.getRoot(), codeA));
		assertEquals(letterB, tree.fetchNode(tree.getRoot(), codeB));
		assertEquals(letterC, tree.fetchNode(tree.getRoot(), codeC));
	}
	
	@Test
	public void testDelete() {
		try {
			tree.delete(letterA);
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}catch(UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}catch(Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testUpdate() {
		try {
			tree.update();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}catch(UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}catch(Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void buildTree() {
		assertEquals(buildTreeList.get(0), "h");
		assertEquals(buildTreeList.get(1), "s");
		assertEquals(buildTreeList.get(2), "v");
		assertEquals(buildTreeList.get(3), "i");
		assertEquals(buildTreeList.get(4), "f");
	}
	
	@Test
	public void testToArrayList() {
		//test the order of the MorseCode tree ArrayList
		//should be in-order traverse of the tree
		list.add("h");
		list.add("s");
		list.add("v");
		list.add("i");
		list.add("f");
		list.add("u");
		list.add("e");
		list.add("l");
		list.add("r");
		list.add("a");
		list.add("p");
		list.add("w");
		list.add("j");
		list.add("");
		list.add("b");
		list.add("d");
		list.add("x");
		list.add("n");
		list.add("c");
		list.add("k");
		list.add("y");
		list.add("t");
		list.add("z");
		list.add("g");
		list.add("q");
		list.add("m");
		list.add("o");
		assertEquals(list, tree.toArrayList());
	}
	
	
	public void testLNRoutputTraversal() {
		//I think the best way to test this method is console output
		//I also have this tested in the MoreCodeTree class, in the main() method at the bottom. 
		System.out.println("\n\n\n");
		tree.LNRoutputTraversal(null, buildTreeList);
	}	
}
