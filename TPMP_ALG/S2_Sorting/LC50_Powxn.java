package S2_Sorting;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Powxn
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 50. Pow(x, n)
 */
public class LC50_Powxn {
    /**
     * Implement pow(x, n), which calculates x raised to the power n (xn).
     *
     * Example 1:
     *
     * Input: 2.00000, 10
     * Output: 1024.00000
     *
     * Example 2:
     *
     * Input: 2.10000, 3
     * Output: 9.26100
     *
     * Example 3:
     *
     * Input: 2.00000, -2
     * Output: 0.25000
     * Explanation: 2-2 = 1/22 = 1/4 = 0.25
     * Note:
     *
     * -100.0 < x < 100.0
     * n is a 32-bit signed integer, within the range [−231, 231 − 1]
     * @param x
     * @param n
     * @return
     */
    // time = O(logn), space = O(logn)
    public double myPow(double x, int n) {
        if (n >= 0) return helper(x, n);
        else return 1 / helper(x, n);
    }

    private double helper(double x, int n) {
        if (n == 0) return 1;

        double temp = helper(x, n / 2);
        return n % 2 == 0 ? temp * temp : temp * temp * x;
    }
}
