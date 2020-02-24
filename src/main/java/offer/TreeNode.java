package offer;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(this, sb, "");
        return sb.toString();
    }

    private void toString(TreeNode node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }
        sb.append(prefix).append("【").append(node.val).append("】\n");
        toString(node.left, sb, prefix + " [L] ");
        toString(node.right, sb, prefix + " [R] ");
    }
}
