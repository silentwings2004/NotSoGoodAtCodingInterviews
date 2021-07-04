package S8_BFSDFS;
import java.util.*;

// Level Order Traversal from right to left

public class LevelOrderTraversalIII {
    // time = O(n), space = O(n)
    public List<List<Integer>> levelOrderRight(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.right != null) queue.offer(cur.right);
                if (cur.left != null) queue.offer(cur.left);
            }
            res.add(list);
        }
        return res;
    }
}
