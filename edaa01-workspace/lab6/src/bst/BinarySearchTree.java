package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E extends Comparable<E>> {
	BinaryNode<E> root; // Används också i BSTVisaulizer
	int size; // Används också i BSTVisaulizer
	private Comparator<E> comparator;

	public static void main(String[] args) {
		getIncementedIntTree(10);

		// BinarySearchTree<Integer> bst = getRandomIntTree(10);
		BinarySearchTree<String> bst = getDefaultStringTree(10);
		BinarySearchTree<String> bst2 = getDefaultStringTree(10);
		BSTVisualizer bv = new BSTVisualizer("Binary Search Tree", 500, 500);
		bv.drawTree(bst);
		bst2.rebuild();
		BSTVisualizer bv2 = new BSTVisualizer("Binary Search Tree", 500, 500);
		bv2.drawTree(bst2);
	}

	/**
	 * 
	 * @param nbrOfElements number of elements in created tree
	 * @return a binary search tree with predefined strings
	 */
	public static BinarySearchTree<String> getDefaultStringTree(int nbrOfElements) {
		BinarySearchTree<String> tempBst = new BinarySearchTree<>();
		String[] strings = new String[] { "cat", "dog", "sun", "moon", "star", "blue", "red", "green", "tree", "bird",
				"fish", "book", "pen", "cup", "door", "rain", "snow", "cloud", "car", "road", "apple", "orange",
				"grape", "leaf", "wind", "fire", "water", "rock", "sand", "hill", "river", "lake", "sea", "city",
				"town", "map", "key", "lock", "ring", "coin", "glass", "wood", "metal", "light", "dark", "cold", "hot",
				"soft", "hard", "sky", "ball", "ship", "wave", "milk", "rose", "leaf", "cake", "jazz", "kite", "lamp",
				"desk", "frog", "snow", "rain", "gold", "rice", "bean", "corn", "lime", "pear", "kiwi", "wolf", "bear",
				"lion", "duck", "swan", "fish", "crab", "shark", "road", "path", "hill", "pond", "park", "shop", "town",
				"city", "land", "farm", "wood", "rock", "clay", "sand", "dirt", "dust", "rust", "smog", "fog", "leaf",
				"frog", "mud", "nest", "horn", "moss", "twig", "dew", "seal", "crow", "hawk", "peak", "bark", "ant",
				"bee", "wasp", "moth", "mite", "slug", "snail", "lamb", "calf", "foal", "hare", "lynx", "wolf", "bull",
				"goat", "pige", "owl", "lark", "worm", "flea", "silk", "reed", "flax", "hemp", "rice", "maiz", "oats",
				"rye", "wheat", "spelt", "yarn", "felt", "lace", "silk", "wool", "linen", "flax", "hemp", "cotton",
				"denim", "twill", "satin", "velvet", "chiffon", "organza", "tulle" };
		for (String string : strings) {
			tempBst.add(string);
			if (tempBst.size >= nbrOfElements)
				return tempBst;
		}
		return null;
	}

	/**
	 * 
	 * @param nbrOfElements number of elements in created tree
	 * @return a binary search tree with random integers
	 */
	public static BinarySearchTree<Integer> getRandomIntTree(int nbrOfElements) {
		BinarySearchTree<Integer> tempBst = new BinarySearchTree<Integer>();
		while (true) {
			tempBst.add((int) Math.round(100 * Math.random()));
			if (tempBst.size >= nbrOfElements)
				return tempBst;
		}
	}

	/**
	 * 
	 * @param nbrOfElements number of elements in created tree
	 * @return a binary search tree with incremented integers
	 */
	public static BinarySearchTree<Integer> getIncementedIntTree(int nbrOfElements) {
		BinarySearchTree<Integer> tempBst = new BinarySearchTree<Integer>();
		for (int i = 0; i < nbrOfElements; i++) {
			tempBst.add(i);
		}
		return tempBst;
	}

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		this.size = 0;
		this.comparator = Comparator.naturalOrder();
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param elem element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E elem) {
		if (root == null) {
			root = new BinaryNode<E>(elem);
			size++;
			return true;
		}
		return add(root, elem);
	}

	private boolean add(BinaryNode<E> node, E elem) {
		int comp = comparator.compare(node.element, elem);
		if (comp > 0) {
			if (node.left != null) {
				return add(node.left, elem);
			}
			node.left = new BinaryNode<E>(elem);
			size++;
			return true;
		}
		if (comp < 0) {
			if (node.right != null) {
				return add(node.right, elem);
			}
			node.right = new BinaryNode<E>(elem);
			size++;
			return true;
		}
		return false;
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> node) {
		if (node != null) {
			int r = 0;
			int l = 0;
			if (node.right != null)
				r = height(node.right);
			if (node.left != null)
				l = height(node.left);
			return Math.max(r, l) + 1;
		}
		return 0;
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		size = 0;
		root = null;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> node) {
		if (node != null) {
			printTree(node.left);
			System.out.println(node.element);
			printTree(node.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sortedList = new ArrayList<E>();
		toArray(root, sortedList);
		root = buildTree(sortedList, 0, sortedList.size() - 1);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}

	}

	/*
	 * Builds a complete tree from the elements from position first to
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (last < first) {
			return null;
		}
		int mid = (first + last) / 2;
		BinaryNode<E> newRoot = new BinaryNode<E>(sorted.get(mid));
		newRoot.left = buildTree(sorted, first, mid - 1);
		newRoot.right = buildTree(sorted, mid + 1, last);
		return newRoot;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
