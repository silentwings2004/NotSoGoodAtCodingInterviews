package Templates;

public class StateCompression {
    public void main() {
        int state = 0;
        int[] count = new int[1 << n]; // presum
        count[0] += 1; // 什么字符都没有，意味着所有字符出现0次，也是一种前缀
        
        long res = 0;
        for (int k = 0; k < n; k++) {
            state = state ^ (1 << k); // bit flip 0 -> 1, 1 -> 0
            res += count[state];
        }
        count[state] += 1;
    }
}
