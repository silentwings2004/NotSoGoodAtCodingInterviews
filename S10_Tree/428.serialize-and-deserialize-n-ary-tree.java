import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.Node;

/*
 * @lc app=leetcode id=428 lang=java
 *
 * [428] Serialize and Deserialize N-ary Tree
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) return "";

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(',');

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            sb.append(cur.children.size()).append(',');
            
            for (Node child : cur.children) {
                sb.append(child.val).append(',');
                queue.offer(child);
            }
        }
        return sb.toString();
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) return null;

        String[] strs = data.split(",");
        Node root = new Node(Integer.parseInt(strs[0]));

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int idx = 1;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int size = Integer.parseInt(strs[idx++]);
            cur.children = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node child = new Node(Integer.parseInt(strs[idx++]));
                cur.children.add(child);
                queue.offer(child);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
// @lc code=end

