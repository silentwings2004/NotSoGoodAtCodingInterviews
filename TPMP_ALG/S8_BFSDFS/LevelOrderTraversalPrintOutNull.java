package S8_BFSDFS;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversalPrintOutNull {
    /**
     * @param root
     */
    public void levelOrderTraversal(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) System.out.println("#");
            else {
                System.out.println(cur.val);
                queue.offer(root.left);  // 可以加null进queue,所以不再需要判断是否为null的语句
                queue.offer(root.right);
            }
        }
    }
}
