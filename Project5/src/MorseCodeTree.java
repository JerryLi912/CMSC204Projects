import java.util.ArrayList;
/**
 * This class creates a MorseCode Tree.
 * @author jerry
 *
 * @param <T>
 */
public class MorseCodeTree<T> implements LinkedConverterTreeInterface<String> {

	private TreeNode<String> root;
	
	public MorseCodeTree() {
		this.root = new TreeNode<String>("");
		buildTree();
	}
	@Override
	public TreeNode<String> getRoot() {
		return this.root;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		this.root = newNode;
	}

	@Override
	public MorseCodeTree<T> insert(String code, String letter) {
		
		addNode(getRoot(), code, letter);
		//System.out.println("Intered: " + code + " and " + letter);
		return this;
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		TreeNode<String> currentRoot = root;
		TreeNode<String> newRoot = new TreeNode<>();
		String newCode = null;
		if(code.length() == 1) {
			if(code.charAt(0) == '.'){
				currentRoot.m_leftChild = new TreeNode<String>(letter);
				currentRoot = currentRoot.m_leftChild;
			//	System.out.println("Code has a single character. The left child is: " + currentRoot.getData());
			}
			if(code.charAt(0) == '-') {
				currentRoot.m_rightChild = new TreeNode<String>(letter);
				currentRoot = currentRoot.m_rightChild;
			//	System.out.println("Code has a single character. The right child is: " + currentRoot.getData());
			}
			return;
		}
		
		if(code.length() > 1) {
			if(code.charAt(0) == '.') {
				newRoot = currentRoot.m_leftChild;	
			//	System.out.println("Left root: " + newRoot.getData());
			}
			if(code.charAt(0) == '-') {
				newRoot = currentRoot.m_rightChild;
			//	System.out.println("Right root: " + newRoot.getData());
			}
			
			newCode = code.substring(1, code.length());	
		//	System.out.println("The new code is: " + newCode);
			addNode(newRoot, newCode, letter);
		}
	}

	@Override
	public String fetch(String code) {
		return fetchNode(getRoot(), code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		
		TreeNode<String> currentRoot = root;
		TreeNode<String> newRoot = new TreeNode<>();
		String newCode = null;
		if(root == null) {
			return null;
		}
		
		if(code.charAt(0) == '.' && code.length() == 1) {
			return currentRoot.m_leftChild.getData();
		}
		if(code.charAt(0) == '-' && code.length() == 1) {
			return currentRoot.m_rightChild.getData();
		}
		if(code.length() > 1) {
			if(code.charAt(0) == '.') {
				newRoot = currentRoot.m_leftChild;
			}
			if(code.charAt(0) == '-') {
				newRoot = currentRoot.m_rightChild;
			}
			newCode = code.substring(1, code.length());
			
			return fetchNode(newRoot, newCode);
		}
	
		return null;
	}
	
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void buildTree() {
		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");	
	}

	
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<>();
 		LNRoutputTraversal(getRoot(), list);
		return list;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root == null) {
			return;
		}
		LNRoutputTraversal(root.getLeftChild(), list);
		System.out.print(root.getData() + " ");
		list.add(root.getData());
		LNRoutputTraversal(root.getRightChild(), list);
	}
	
	
	public void printInOrder(TreeNode<T> node) {
		if(node == null) {
			return;
		}
		printInOrder(node.getLeftChild());
		System.out.print(node.getData() + " ");
		printInOrder(node.getRightChild());
	}
	
	
	//personal test
	public static void main(String[] args) {
		MorseCodeTree<String> tree = new MorseCodeTree<String>();
		TreeNode<String> node = new TreeNode<>();
		ArrayList<String> list = new ArrayList<>();
		node = tree.root;
//		tree.addNode(node, ".", "e");
//		tree.addNode(node, "-", "t");
//		tree.addNode(node, "..", "i");
//		tree.addNode(node, ".-", "a");
//		tree.addNode(node, "-.", "n");
//		tree.addNode(node, "--", "m");
//		tree.insert("...", "s");
//		tree.insert("..-", "u");
//		tree.insert(".-.", "r");
//		tree.insert(".--", "w");
		System.out.println("*************************************");
		System.out.println("Print the tree in InOrder traversal: ");
		System.out.println("*************************************");
		System.out.println();
		tree.LNRoutputTraversal(node, list);
	//	System.out.println("\n\nArraylist" + list);
		System.out.print("\n\nThis is calling the toArrayList function:\n"+tree.toArrayList());
		System.out.println("\nFetched node: "+tree.fetch("-.-"));
		
		
	}

}
