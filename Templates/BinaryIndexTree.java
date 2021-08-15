package Templates;

public class BinaryIndexTree {
    int MAX_N = 100000;
    long[] bitArray = new long[MAX_N + 1];
    long[] nums = new long[MAX_N + 1]; // Note: nums is 1-index
    long M = (long)(1e9 + 7);

    // increase nums[i] by delta  (1-index)
    private void updateDelta(int i, long delta) {
        int idx = i;
        while (idx <= MAX_N) {
            bitArray[idx] += delta;
            bitArray[idx] %= M;
            idx += idx & (-idx);
        }
    }

    // sum of a range nums[1:j] inclusively, 1-index
    private long queryPreSum(int idx) {
        long res = 0;
        while (idx != 0) {
            res += bitArray[idx];
            res %= M;
            idx -= idx & (-idx);
        }
        return res;
    }

    // sum of a range nums[i:j] inclusively
    private long sumRange(int i, int j) {
        return queryPreSum(j) - queryPreSum(i - 1);
    }
}
