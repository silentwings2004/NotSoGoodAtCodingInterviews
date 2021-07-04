package S8_BFSDFS;

import java.util.LinkedList;
import java.util.Queue;

public class StandardLevelOrderTraversal {
    // 标准模板
    public void levelOrderTraversal(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                System.out.println(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            System.out.println();
        }
    }
}
