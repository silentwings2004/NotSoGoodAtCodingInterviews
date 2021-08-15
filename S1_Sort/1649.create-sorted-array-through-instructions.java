/*
 * @lc app=leetcode id=1649 lang=java
 *
 * [1649] Create Sorted Array through Instructions
 */

// @lc code=start
class Solution {
    // S1: Divide & Conquer + Merge Sort
    // time = O(nlogn * logn), space = O(n)
    private long[] sorted;
    private long[] smaller; // the number of smaller numbers before nums[i]
    private long[] count;
    long M = (long)(1e9 + 7);
    public int createSortedArray(int[] instructions) {
        // corner case
        if (instructions == null || instructions.length == 0) return 0;

        int n = instructions.length;
        sorted = new long[100005];
        for (int i = 0; i < n; i++) sorted[i] = instructions[i];
        smaller = new long[100005];
        count = new long[100005];
        helper(instructions, 0, n - 1);

        long res = 0;
        for (int i = 0; i < n; i++) {
            long cost = Math.min(smaller[i], i - count[instructions[i]] - smaller[i]);
            res = (res + cost) % M;
            count[instructions[i]]++; // count更新下
        }
        return (int)res;
    }

    private void helper(int[] nums, int a, int b) {
        if (a >= b) return;

        int mid = a + (b - a) / 2;
        helper(nums, a, mid);
        helper(nums, mid + 1, b);

        for (int i = mid + 1; i <= b; i++) {
            // sorted 虽然打乱了，但是这段区间内的元素种类与nums[i]在同一段区间内是一样的，只要保证nums[i]在这段区间内即可
            int iter = lowerbound(sorted, a, mid, nums[i]);
            smaller[i] += iter - a + 1; // 注意：+ -> 对于每个z，在右半边里有几个z比它小的已经解决小问题时已经算出来了，已经更新到cost[i]里了
        }

        // merge -> make left and right halves sorted
        // Arrays.sort(sorted, a, b + 1); // O(nlogn) 粗暴点，可能TLE
        int i = a, j = mid + 1, p = 0;
        long[] temp = new long[b - a + 1];
        while (i <= mid && j <= b) {
            if (sorted[i] <= sorted[j]) temp[p++] = sorted[i++];
            else temp[p++] = sorted[j++];
        }
        while (i <= mid) temp[p++] = sorted[i++];
        while (j <= b) temp[p++] = sorted[j++];

        for (i = 0; i < b - a + 1; i++) {
            sorted[i + a] = temp[i];
        }
    }

    private int lowerbound(long[] sorted, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sorted[mid] < target) left = mid + 1;
            else right = mid;
        }
        return sorted[left] < target ? left : left - 1;
    }

    // S1.2: divide & conquer + merge sort
    // time = O(nlogn), space = O(n)
    private int[] index;
    private long[] small;
    private int[] arr;
    private long[] freq;
    long Mod = (long)(1e9 + 7);
    public int createSortedArray12(int[] instructions) {
        // corner case
        if (instructions == null || instructions.length == 0) return 0;

        int n = instructions.length;
        index = new int[n];
        arr = instructions.clone(); // 注意：本题一定要用一个复制数组去做sort！！！因为下面计算count需要用到原来的instructions[i]!
        for (int i = 0; i < n; i++) index[i] = i;
        small = new long[n];
        freq = new long[100005];
        partition(arr, 0, n - 1);

        long res = 0;
        for (int i = 0; i < n; i++) {
            long cost = Math.min(small[i], i - freq[instructions[i]] - small[i]);
            res = (res + cost) % Mod;
            freq[instructions[i]]++; // count更新下
        }
        return (int)res;
    }

    private void partition(int[] nums, int a, int b) {
        if (a >= b) return;

        int mid = a + (b - a) / 2;
        partition(nums, a, mid);
        partition(nums, mid + 1, b);

        // 2种写法：既可以写在这里，也可以并入到下面merge的程序里，时间上看merge到下面更快一点。
//        int p1 = a, p2 = mid + 1;
//        while (p1 <= mid && p2 <= b) {
//            if (nums[p1] >= nums[p2]) small[index[p2++]] += p1 - a;
//            else p1++;
//        }
//        while (p2 <= b) small[index[p2++]] += p1 - a;

        int i = a, j = mid + 1, p = 0;
        int[] temp = new int[b - a + 1];
        int[] tempIdx = new int[b - a + 1];
        while (i <= mid && j <= b) {
            if (nums[i] < nums[j]) {
                temp[p] = nums[i];
                tempIdx[p++] = index[i++];
            } else {
                temp[p] = nums[j];
                small[index[i]] += i - a;
                tempIdx[p++] = index[j++];
            }
        }
        while (i <= mid) {
            temp[p] = nums[i];
            tempIdx[p++] = index[i++];
        }
        while (j <= b) {
            temp[p] = nums[j];
            small[index[i]] += i - a;
            tempIdx[p++] = index[j++];
        }

        for (i = 0; i < temp.length; i++) {
            nums[i + a] = temp[i];
            index[i + a] = tempIdx[i];
        }
    }

    // S2: BIT 树状数组
    // time = O(nlogm), space = O(m)  m: the maximum value in instructions
    private int MAX = 100000;
    long[] bitArray;
    long[] nums;
    public int createSortedArray2(int[] instructions) {
        // corner case
        if (instructions == null || instructions.length == 0) return 0;

        bitArray = new long[MAX + 1];
        nums = new long[MAX + 1];

        long res = 0;
        for (int x : instructions) {
            updateDelta(x, 1);
            long a = sumRange(1, x - 1);
            long b = sumRange(x + 1, MAX);
            res += Math.min(a, b);
            res %= M;
        }
        return (int)res;
    }

    private void updateDelta(int i, long delta) {
        int idx = i;
        while (idx <= MAX) {
            bitArray[idx] += delta;
            bitArray[idx] %= M;
            idx += idx & (-idx);
        }
    }

    private long queryPreSum(int idx) {
        long res = 0;
        while (idx != 0) {
            res += bitArray[idx];
            res %= M;
            idx -= idx & (-idx);
        }
        return res;
    }

    private long sumRange(int i, int j) {
        return queryPreSum(j) - queryPreSum(i - 1);
    }

    // S3: Segment Tree
    public int createSortedArray3(int[] instructions) {
        // corner case
        if (instructions == null || instructions.length == 0) return 0;

        TreeSet<Integer> set = new TreeSet<>(); // 注意：这里一定要用TreeSet，给数组元素排序！！！
        for (int num : instructions) set.add(num); // deduplication

        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (int x : set) map.put(x, i++);

        int n = map.size(); // 有多少个编号，有多少个叶子节点

        // build tree
        SegTreeNode root = new SegTreeNode(0, n - 1);
        init(root, 0, n - 1);

        long res = 0;
        long M = (long)(1e9 + 7);
        for (int k : instructions) {
            int idx = map.get(k);
            long count1 = queryRangeFreq(root, 0, idx - 1);
            long count2 = queryRangeFreq(root, idx + 1, n - 1);
            res += Math.min(count1, count2);
            res %= M;
            // add k to the segment tree
            updateSingleBy(root, idx, 1); // freq + 1
        }

        return (int) res;
    }

    private class SegTreeNode {
        private int start, end;
        private int info; // freq
        private SegTreeNode left, right;

        public SegTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private void init(SegTreeNode node, int a, int b) {
        if (a == b) {
            node.info = 0; // 如果是个单点的话，那么它的频次一开始都是0， 建树的时候还没往里面扔东西
            return;
        }

        int mid = (a + b) / 2;
        if (node.left == null) {
            node.left = new SegTreeNode(a, mid);
            node.right = new SegTreeNode(mid + 1, b);
        }
        init(node.left, a, mid);
        init(node.right, mid + 1, b);
        node.info = 0; // 建树的过程 -> 肯定是0
    }

    private void updateSingleBy(SegTreeNode node, int idx, int val) {
        if (idx < node.start  || idx > node.end) return;
        if (node.start == node.end) {
            node.info += val;
            return;
        }

        updateSingleBy(node.left, idx, val);
        updateSingleBy(node.right, idx, val);
        node.info = node.left.info + node.right.info;
    }

    private int queryRangeFreq(SegTreeNode node, int a, int b) {
        if (b < node.start || a > node.end) return 0;
        if (a <= node.start && node.end <= b) return node.info;

        return queryRangeFreq(node.left, a, b) + queryRangeFreq(node.right, a, b);
    }
}
/**
 * 没处理一个数，就在这个数之前取一个比它小和比它大的，找个较小的
 * [x x x x x x x x]   k
 * 有序？=> 二分
 * 每一个cost都是固定的
 * 2种做法：
 * 1. 分治 + merge sort -> LC315, LC327, LC493 只适用于某些经典题
 * 2. BIT -> 抄模板
 * 知道几个比i小，自然就知道多少比i大 -> hashmap存一下
 * 如何知道前面有多少个比i小的呢？ 非常眼熟 -> LC315
 * count smaller numbers before vs after yourself  一模一样的解法
 * x x x x x x x i
 * A: [x x x x x x x x]
 * B: [y y y y y][z z z z z]
 * A':[x x x x x x x x]  返回一个有序的
 * 小问题解决，假设这2个小问题解决了，在这个数组里面，有多少个smaller numbers before yourself
 * 如果这2个小问题解决了，大问题能解决吗？差点什么？对每个z而言，前面的y有可能比它小，所以对z而言没解决，而y解决了
 * => 数一下y里有多少个比z小
 * 很自然的想法：全部撸一遍，这个效率比较低 => 如果它是有序的，就用二分法 O(logn) 这样效率非常高
 * divide & conquer + merge sort，在解决b, c两个小问题后，要把b,c两个小问题各自保持有序
 * 归并到a之后，变成一个有序的排列
 * 在解决additional 问题时就希望是有序的，就可以用二分
 * [t t t t t t t t] 上一层可能问题更大，返回是有序的，方便该层做二分查找，所以才想到用merge sort
 * 分治法 + merge sort:
 * 1. 一个大问题拆分成2个小问题，但拆分后并不能完全解决这个大问题，还需要做额外步骤
 * 2. 如果2个小数组是有序的，那么这个additional的问题就可以高效的完成 => BS
 * 3. 最后close这个问题时再做个排序，返回上一层后带来帮助
 *
 * S2: BIT
 * 10^5 array, 存这个bin上是否有这个数
 * 对任意一个数，想知道有多少比它小/大的 => 把bin里的数字都加起来即可
 * 维护一个数组，每个bin都表示这个number出现的次数，设计一个比较高效的算法求前缀/后缀和
 * => 不是BIT，就是线段树，BIT更短，segment tree比较好的一点是离散化，开元素个数的4倍
 * 只需要了解所有的操作都在nums 整个数轴上，有多少bin的数组，一定要是1-index!nums[0]不能存你的有效信息。
 * instructions不包括0,不会取到i = 0， index = 0 空出来
 * bitArray也是个数组，是个隐含的，不用显式去操作它
 * update(i, delta) => increase nums[i] by delta
 * queryRangeSum(i, j) => sum of nums[i:j]
 *
 * S3: Segment Tree
 * [x x x x x x x x x x]   k
 * c++ 高级数据结构：pbds
 * 这个数组是提前就有的，数字可以提前看见 => 先排个序
 * 在处理k之前，有些数字已经出现了，频次不是0
 * [3 5 5 6 7 7 8]
 * 1 - 3
 * 2 - 5
 * 3 - 6
 * 4 - 7
 * 5 - 8
 * < 6 总频次
 * 1  2  3  4  5
 * 0  1  1  1  0
 * 一个区间里频次总和多少，最大最小值是多少 => 线段树
 * 这些点就是线段树的叶子节点 => 原数组里的distinct number
 * 原数组里元素出现多次 => 每个叶子节点的info上面
 * 不断更新频次，需要有一个update single(idx)
 * 区间查询(query range) (a...b)
 * 3个API: build tree, update single and query range
 * ref: LC307 唯一的差别就是"离散化"
 * 元素与结点编号要"一一对应"起来 -> 去重
 */
// @lc code=end

