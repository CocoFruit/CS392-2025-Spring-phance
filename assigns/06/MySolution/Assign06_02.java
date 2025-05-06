class Invalid23TreeException extends RuntimeException {
	public Invalid23TreeException(String message) {
		super(message);
	}
}

public class Assign06_02<T> {
    private class Node<T> {
		// please declare Node for 2-3-trees
		// Note that there two kinds of nodes:
		// a 2-node (with 2 children) and a 3-node
		// (with 3 children)

		T k1, k2;
		Node<T> left, middle, right;
		
		Node(T a){ // 2 node
			k1 = a;
			k2 = null;
		}
		Node(T a, T b){ // 3 node
			k1 = a;
			k2 = b;
		}

		public boolean isTwoNode(){
			return k2 == null;
		}
		public boolean isThreeNode(){
			return k2 != null;
		}
	}

	
    public boolean is23T(Node<T> root) {
		// please give a recursive implementation
		// of [is23T] which test if a tree is indeed
		// a valid 2-3-tree.
		return check23T(root, 0) != -1;
	}

	private int check23T(Node<T> node, int depth){

	}
}
