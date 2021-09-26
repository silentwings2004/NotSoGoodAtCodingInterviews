/*
 * @lc app=leetcode id=1157 lang=java
 *
 * [1157] Online Majority Element In Subarray
 */

// @lc code=start
class MajorityChecker {
    HashMap<Integer, List<Integer>> map;
    int[] arr;
    SegTreeNode root;
    public MajorityChecker(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        root = new SegTreeNode(0, n - 1);
        init(root, 0, n - 1);
    }
    
    public int query(int left, int right, int threshold) {
        int[] x = queryRange(root, left, right);

        if (map.containsKey(x[0])) {
            int a = lowerBound(map.get(x[0]), left);
            int b = upperBound(map.get(x[0]), right);

            int count = b - a + 1;
            if (count >= threshold) return x[0];
        }
        return -1;
    }

    private int lowerBound(List<Integer> nums, int t) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < t) left = mid + 1;
            else right = mid;
        }
        return nums.get(left) >= t ? left : left + 1;
    }

    private int upperBound(List<Integer> nums, int t) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums.get(mid) <= t) left = mid;
            else right = mid - 1;
        }
        return nums.get(left) <= t ? left : left - 1;
    }

    private class SegTreeNode {
        private SegTreeNode left, right;
        int start, end;
        int val, freqDiff; 
        public SegTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private void init(SegTreeNode node, int a, int b) { // recursion
        // base case - single point
        if (a == b) {
            node.val = arr[a];
            node.freqDiff = 1; // 1 - 0 = 1
            return;
        }
        int mid = (a + b) / 2;
        if (node.left == null) { 
            node.left = new SegTreeNode(a, mid);
            node.right = new SegTreeNode(mid + 1, b);
        }
        init(node.left, a, mid);
        init(node.right, mid + 1, b);

        // merge 两棵子树的结果
        if (node.left.val == node.right.val) {
            node.val = node.left.val;
            node.freqDiff = node.left.freqDiff + node.right.freqDiff;
        } else if (node.left.freqDiff >= node.right.freqDiff) {
            node.val = node.left.val;
            node.freqDiff = node.left.freqDiff - node.right.freqDiff;
        } else {
            node.val = node.right.val;
            node.freqDiff = node.right.freqDiff - node.left.freqDiff;
        }
    }

    private int[] queryRange(SegTreeNode node, int a, int b) {
        if (b < node.start || a > node.end) return new int[]{0, 0}; // {val, freqDiff} 无majority
        if (a <= node.start && node.end <= b) return new int[]{node.val, node.freqDiff};

        // recursion
        int[] L = queryRange(node.left, a, b);
        int[] R = queryRange(node.right, a, b);

        // 同上面一样
        if (L[0] == R[0]) {
            return new int[]{L[0], L[1] + R[1]};
        } else if (L[1] >= R[1]) {
            return new int[]{L[0], L[1] - R[1]};
        } else {
            return new int[]{R[0], R[1] - L[1]};
        }
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
// @lc code=end

