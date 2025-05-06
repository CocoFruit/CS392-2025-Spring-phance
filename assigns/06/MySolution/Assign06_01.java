public class Assign06_01 {
    final int R = 0; // for red
    final int B = 1; // for black

    private class Node {
        private int color;
        private Node left;
        private Node right;
    }
    public boolean isRBT(Node root) {
	// please give a recursive implementation
	// of [isRBT] which test if a tree is indeed
	// a valid red-black-tree.
    
    if(root != null && root.color != B){
        return false;
    }
    return checkRBT(root) != -1;
    }

    private int checkRBT(Node node){
        if(node == null){
            return 1; // Null nodes are considered black with black-height = 1
        }

        // INVALID
        if(node.color != R && node.color != B){
            return -1;
        }

        // Red cant have red children
        if(node.color == R){
            if ((node.left != null && node.left.color == R) ||
            (node.right != null && node.right.color == R)) {
            return -1;
            }
        }

        int leftBH = checkRBT(node.left);
        int rightBH = checkRBT(node.right);

        if(leftBH == -1 || rightBH == -1 || leftBH != rightBH){
            return -1;
        }

        return node.color == B ? leftBH + 1 : leftBH;
    }

}
