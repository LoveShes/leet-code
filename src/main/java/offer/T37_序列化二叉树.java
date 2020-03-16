package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/
 */
public class T37_序列化二叉树 {
    /**
     * 前序遍历
     * 执行用时 :12 ms, 在所有 Java 提交中击败了94.68% 的用户
     * 内存消耗 :44.4 MB, 在所有 Java 提交中击败了16.80%的用户
     */
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        // 前序遍历
        if (node == null) {
            sb.append("null ");
            return;
        } else {
            sb.append(node.val).append(" ");
        }
        serialize(node.left, sb);
        serialize(node.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data.equals("")) return null;
        String[] strings = data.split(" ");
        List<String> list = new LinkedList<>(Arrays.asList(strings));
        return deserialize(list);
    }

    private TreeNode deserialize(List<String> list) {
        // list.get(0)就是根节点，有可能为null，需要判断一下
        if (list.get(0).equals("null")) {
            list.remove(0); // 访问之后就移除
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        node.left = deserialize(list);
        node.right = deserialize(list);
        return node;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        // root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(serialize(root));
        System.out.println(deserialize(serialize(root)));
    }
}
