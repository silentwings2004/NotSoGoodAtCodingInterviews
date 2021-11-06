package S0_Templates;

public class BinaryIndexTree {
    int n = 100000;
    long[] bitree = new long[n + 1];
    long[] nums = new long[n + 1]; // Note: nums is 1-index
    long M = (long)(1e9 + 7);

    // increase nums[i] by delta  (1-index)
    private void update(int i, long diff) {
        nums[i] += diff;
        while (i <= n) {
            bitree[i] += diff;
            btree[i] %= M;
            i += i & (-i);
        }
    }

    // sum of a range nums[1:j] inclusively, 1-index
    private long querySum(int i) {
        long sum = 0;
        while (i > 0) {
            sum += bitree[i];
            sum %= M;
            i -= i & (-i);
        }
        return sum;
    }

    // sum of a range nums[i:j] inclusively
    private long sumRange(int i, int j) {
        return querySum(j) - querySum(i - 1);
    }
}
