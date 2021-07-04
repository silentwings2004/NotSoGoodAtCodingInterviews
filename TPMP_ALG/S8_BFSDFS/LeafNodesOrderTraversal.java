package S8_BFSDFS;
import java.util.*;
public class LeafNodesOrderTraversal {
    /**
     * @param root
     * @return
     */
    // time = O(n^2), space = O(n)
    public List<List<Integer>> leafOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int maxHeight = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                int height = getHeight(cur);
                maxHeight = Math.max(maxHeight, height);
                if (!map.containsKey(height)) map.put(height, new ArrayList<>());
                map.get(height).add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }

        for (int i = 1; i <= maxHeight; i++) {
            if (map.containsKey(i)) res.add(map.get(i));
        }
        return res;
    }

    private int getHeight(TreeNode cur) { // O(n)
        if (cur == null) return 0;

        int leftHeight = getHeight(cur.left);
        int rightHeight = getHeight(cur.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
