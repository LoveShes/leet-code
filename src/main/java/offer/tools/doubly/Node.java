package offer.tools.doubly;

public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", left=" + (left != null ? "" + left.val : "null") +
                ", right=" + (right != null ? "" + right.val : "null") +
                '}';
    }
}
