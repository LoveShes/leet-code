package offer.tools.random;

public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = this;
        while (node != null) {
            sb.append(node.val).append("(");
            if (node.random != null) {
                sb.append(random.val);
            } else {
                sb.append("null");
            }
            sb.append(")");
            if (node.next != null) {
                sb.append(" -> ");
            }
            node = node.next;
        }
        return sb.toString();
    }
}
