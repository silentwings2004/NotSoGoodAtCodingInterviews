package S0_Templates;

public class SegmentTree_Basic {
    private SegTreeNode root;
    private int[] nums;
    public SegmentTree_Basic(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        root = new SegTreeNode(0, n - 1);
        init(root, 0, n - 1);
    }

    public void update(int index, int val) {
        updateSingle(root, index, val);
    }

    public int sumRange(int left, int right) {
        return queryRange(root, left, right);
    }

    private class SegTreeNode {
        private SegTreeNode left, right;
        int start, end;
        int info; // rangeSum
        public SegTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private void init(SegTreeNode node, int a, int b) { // init for range [a, b]
        // base case - single point
        if (a == b) {
            node.info = nums[a];
            return;
        }
        int mid = (a + b) / 2;
        if (node.left == null) { // 要开必须左右一起开
            node.left = new SegTreeNode(a, mid);
            node.right = new SegTreeNode(mid + 1, b);
        }
        init(node.left, a, mid);
        init(node.right, mid + 1, b);
        node.info = node.left.info + node.right.info; // write your own logic
    }

    private void updateSingle(SegTreeNode node, int idx, int val) {
        if (idx < node.start || idx > node.end) return;
        if (node.start == node.end) {
            node.info = val;
            return;
        }

        updateSingle(node.left, idx, val);
        updateSingle(node.right, idx, val);
        node.info = node.left.info + node.right.info; // 记得要更新当前node的info，左右已经更新
    }

    private int queryRange(SegTreeNode node, int a, int b) {
        if (b < node.start || a > node.end) return 0;
        if (a <= node.start && node.end <= b) return node.info;

        return queryRange(node.left, a, b) + queryRange(node.right, a, b);
    }
}
/**
 * Segment Tree
 * quary是动态的，支持单点修改
 * 线段树：本质就是二叉树，每个节点代表一段区间，从根节点开始代表一个最大的区间
 *                        [0,9]
 *            [0,4]                    [5,9]
 *       [0,2]    [3,4]          [5,7]      [8,9]
 *    [0,1] [2,2] [3,3] [4,4] [5,6][7,7] [8,8] [9,9]
 * [0,0] [1,1]              [5,5] [6,6]
 *
 * 如果区间和都知道，这样求某段区间，比如[5,7]，不需要加起来，而是已知的，直接读节点数就可以了。
 * 如果[4,9]区间和怎么算？=> 拆分[4,4] + [5,9] -> 只要求单点4的和
 * 最大特点：不需要O(n) -> only O(logn)
 * 最多logn层，每层最多只会query 2个，只会一分二 => 2*logn 有其独特的优势
 * 还支持单点修改 -> 不是O(1),也是O(logn)
 * singlePointUpdate: log(n)
 * queryRange: log(n)
 * 基础版本：模板题
 * 区间更新：lazy tag
 * BIT 比较局限，segment tree威力更大一些
 * 把模板存下来，以后直接修改模板即可 -> 换一下info，比如区间最大值，基本框架不变
 */
}
