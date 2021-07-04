package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PowerofTwo
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 231. Power of Two
 */
public class LC231_PowerofTwo {
    /**
     * Given an integer, write a function to determine if it is a power of two.
     *
     * Example 1:
     *
     * Input: 1
     * Output: true
     * Explanation: 20 = 1
     * Example 2:
     *
     * Input: 16
     * Output: true
     * Explanation: 24 = 16
     * Example 3:
     *
     * Input: 218
     * Output: false
     * @param n
     * @return
     */
    // S1: Recursion
    public boolean isPowerOfTwo(int n) {
        // corner case
        if (n <= 0) return false;
        // base case
        if (n == 1) return true;
        if (n % 2 != 0) return false;
        return isPowerOfTwo(n / 2);
    }

    // S2: 大 --> 小
    public boolean isPowerOfTwo2(int n) {
        // corner case
        if (n <= 0) return false;
        while (n > 1) {
            if (n % 2 != 0) return false;
            n /= 2;
        }
        return n == 1;
    }

    // S3: 小 --> 大
    public boolean isPowerOfTwo3(int n) {
        // corner case
        if (n <= 0) return false;
        long x = 1; // be careful about the overflow in the case n = 1073741825, use long instead
        while (x < n) {
            x *= 2;
        }
        return x == n;
    }

    // S4: Bit represent, we can calculate the Hamming weight
    public boolean isPowerOfTwo4(int n) {
        // corner case
        if (n <= 0) return false;
        return hammingWeight(n) == 1;
    }

    private int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((1 & (n >> i)) != 0) count++;
        }
        return count;
    }

    // S5: find the maximum power of two, then divide by the number to test
    public boolean isPowerOfTwo5(int n) {
        // The maximum power of two is 2^30, which is (Integer.MAX_VALUE + 1) / 2
        // corner case
        if (n <= 0) return false;
        return ((Integer.MAX_VALUE + 1) / 2) % (n) == 0;
    }

    //S6: Hamming weight为1的情况下(最优解)
    //    0100 1000   - 1
    //    0100 0111
    public boolean isPowerOfTwo6(int n) {
        return (n > 0) && (n & (n - 1)) == 0;
    }
}
