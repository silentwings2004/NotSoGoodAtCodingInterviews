package S0_Templates;

public class BIT {
    int n;
    long[] bitArr; // Note: all arrays are 1-index
    long[] nums;
    long M = (long)(1e9 + 7);

    public BIT(int n) {
        this.n = n;
        bitArr = new long[n + 1];
        nums = new long[n + 1];
    }

    // increase nums[i] by delta  (1-index)
    private void updateDelta(int i, long delta) {
        int idx = i;
        while (idx <= n) {
            bitArr[idx] += delta;
            bitArr[idx] %= M;
            idx += idx & (-idx);
        }
    }

    // sum of a range nums[1:j] inclusively, 1-index
    private long queryPreSum(int idx) {
        long res = 0;
        while (idx > 0) {
            res += bitArr[idx];
            res %= M;
            idx -= idx & (-idx);
        }
        return res;
    }

    // sum of a range nums[i:j] inclusively
    private long sumRange(int i, int j) {
        return queryPreSum(j) - queryPreSum(i - 1);
    }

    public int main() {
        int n = 100000;
        BIT bit = new BIT(n);
        int[] nums = new int[n];
        
        for (int i = 1; i < n; i++) {
            bit.updateDelta(i, nums[i]);
            bit.sumRange(1, i);
        }
    }
}
