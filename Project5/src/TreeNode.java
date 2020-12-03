/**
 * This is the TreeNode class.
 * @author jerry
 *
 * @param <T>
 */
public class TreeNode<T> {
	//a new tree node with left and right child set to null 
	//and data set to the dataNode
	private T m_data;
	//left tree node
	TreeNode<T> m_leftChild;
	//right tree node
	TreeNode<T> m_rightChild;
	
	public TreeNode() {}
	
	public TreeNode(T dataNode) {
		this.m_data = dataNode;
		this.m_leftChild = null;
		this.m_rightChild = null;
	}
	public TreeNode(TreeNode<T> node) {
		if(this.m_leftChild == null && this.m_rightChild == null) {
			this.m_data = node.m_data;
		}
		
		if(this.m_leftChild != null && this.m_rightChild == null)
		{
			this.m_leftChild = new TreeNode<>(node.m_leftChild);
			this.m_data = node.m_data;
		}
		
		if(this.m_rightChild != null && this.m_leftChild == null) {
			this.m_rightChild = new TreeNode<>(node.m_rightChild);
			this.m_data = node.m_data;
		}
		
		if(this.m_leftChild != null && this.m_rightChild != null) {
			this.m_leftChild = new TreeNode<>(node.m_leftChild);
			this.m_rightChild = new TreeNode<>(node.m_rightChild);
			this.m_data = node.m_data;
		}
		
		System.out.println("Copying Tree Node");		
	}
	
	public void setData(T data) {
		this.m_data = data;
	}
	public T getData() {
		return this.m_data;
	}
	public TreeNode<T> getLeftChild(){
		return this.m_leftChild;
	}
	public TreeNode<T> getRightChild(){
		return this.m_rightChild;
	}
	public void setLeftChild(TreeNode<T> leftChild) {
		this.m_leftChild = leftChild;
	}
	public void setRightChild(TreeNode<T> rightChild) {
		this.m_rightChild = rightChild;
	}
	public boolean isLeaf() {
		return ((m_leftChild == null) && (m_rightChild == null));
	}
	

	//personal class test
	public static void main(String[] agrs) {
		TreeNode<Integer> t0 = new TreeNode<Integer>(1);
		TreeNode<Integer> t1 = new TreeNode<Integer>(2);
		TreeNode<Integer> t2 = new TreeNode<Integer>(3);
		TreeNode<Integer> t3 = new TreeNode<Integer>(t2);
		t0.setLeftChild(t1);
		t0.setRightChild(t2);
		System.out.println("t0's Left child:"+ t0.getLeftChild().getData());
		System.out.println("t0's Right child:"+ t0.getRightChild().getData());
		System.out.println("Check if the node has left and right null: "+ t2.isLeaf());
		System.out.println(t0.getData());
		System.out.println(t1.getData());
		System.out.println(t2.getData());
		System.out.println(t3.getData());
		
		
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		tree.root = new TreeNode<>(1);
		tree.root.m_leftChild = new TreeNode<>(2);
		tree.root.m_rightChild = new TreeNode<>(3);
		tree.root.m_leftChild.m_rightChild = new TreeNode<>(4);
		tree.root.m_rightChild.m_rightChild = new TreeNode<>(5);
		tree.root.m_leftChild.m_leftChild = new TreeNode<>(6);
		tree.root.m_rightChild.m_leftChild = new TreeNode<>(7);
		tree.printInOrder(tree.root);
		System.out.println();
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>();
		tree1.root = new TreeNode<>(tree.root);
		tree1.printInOrder(tree1.root);
	}
	
}

//personal class test
class BinaryTree<T>{
	TreeNode<T> root;
	public BinaryTree() {
		root = null;
	}
	public BinaryTree(T rootData) {
		root = new TreeNode<>(rootData);
	}
	public void printInOrder(TreeNode<T> node) {
		if(node == null) {
			return;
		}
		printInOrder(node.getLeftChild());
		System.out.print(node.getData() + " ");
		printInOrder(node.getRightChild());
	}
}


