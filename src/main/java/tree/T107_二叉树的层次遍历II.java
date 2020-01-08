package tree;

import org.junit.jupiter.api.Test;
import tools.Times;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 */
public class T107_二叉树的层次遍历II {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 声明为 LinkedList 类型以便调用 addFirst方法
        // 这里要频繁往头部添加,使用LinkedList效率更高
        LinkedList<List<Integer>> lists = new LinkedList<>();
        if (root == null) {
            return lists;
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelNum = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            --levelNum;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelNum == 0) {
                levelNum = queue.size();
                // 使用addFirst添加到头部效率更高
                lists.addFirst(list);
                list = new ArrayList<>();
            }
        }
        return lists;
    }

    // 测试 add(0, list)和 addFirst(list) 的效率
    @Test
    public void testTime() {
        int num = 999999;
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        List<List<Integer>> lists1 = new ArrayList<>();
        List<List<Integer>> lists2 = new LinkedList<>();
        LinkedList<List<Integer>> lists3 = new LinkedList<>();
        Times.test("ArrayList的add(0,list)", ()->{
            for (int i = 0; i < num; i++) {
                lists1.add(0, list);
            }
        });
        Times.test("LinkedList的add(0,list)", ()->{
            for (int i = 0; i < num; i++) {
                lists2.add(0, list);
            }
        });
        Times.test("LinkedList的addFirst(list)", ()->{
            for (int i = 0; i < num; i++) {
                lists3.addFirst(list);
            }
        });
    }
}
