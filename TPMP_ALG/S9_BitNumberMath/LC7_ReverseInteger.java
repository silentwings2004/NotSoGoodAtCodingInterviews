package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ReverseInteger
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 7. Reverse Integer
 */
public class LC7_ReverseInteger {
    /**
     * Given a 32-bit signed integer, reverse digits of an integer.
     *
     * Example 1:
     *
     * Input: 123
     * Output: 321
     * Example 2:
     *
     * Input: -123
     * Output: -321
     * Example 3:
     *
     * Input: 120
     * Output: 21
     * Note:
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer
     * range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the
     * reversed integer overflows.
     * @param x
     * @return
     */
    // S1：单独manually处理overflow情况
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            // overflow: 2^31 -1 = 2147483647   reverse后最高位为7，肯定会发生overlfow --> 只会发生在最后1次
            int max = Integer.MAX_VALUE / 10;
            int min = Integer.MIN_VALUE / 10;
            if (max < res || max == res && x % 10 >= 7 || min > res || min == res && x % 10 <= -8) return 0;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    // S2: Output用long
    public int reverse2(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        return (int)res;
    }
}
