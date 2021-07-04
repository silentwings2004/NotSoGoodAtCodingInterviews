package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Sqrtx
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 69. Sqrt(x)
 */
public class LC69_Sqrtx {
    /**
     * Implement int sqrt(int x).
     *
     * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
     *
     * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is
     * returned.
     *
     * Example 1:
     *
     * Input: 4
     * Output: 2
     * Example 2:
     *
     * Input: 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since
     *              the decimal part is truncated, 2 is returned.
     * @param x
     * @return
     */
    //S1: Binary Search
    public int mySqrt(int x) {
        // corner case
        if (x < 0) throw new IllegalArgumentException();
        else if (x <= 1) return x;

        int left = 1, right = x;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) return mid;
            if (mid < x / mid) left = mid;
            else right = mid;
        }
        if (right <= x / right) return right;
        return left;
    }

    //S2: recursion + bit operations ==> mySqrt(x) = 2 * mySqrt(x / 4) (x >= 2)
    public int mySqrt2(int x) {
        // corner case
        if (x < 0) throw new IllegalArgumentException();
        else if (x <= 1) return x;

        int left = mySqrt(x >> 2) << 1;
        int right= left + 1;
        return (long)right * right > x ? left : right; //注意出界问题，强制转化为long来防止判断条件结果越界
    }

    // S3: 数学解——牛顿法
    public int mySqrt3(int x) {
        // corner case
        if (x < 0) throw new IllegalArgumentException();
        else if (x <= 1) return x;

        double f0 = x / 2;
        double f1 = (x / f0 + f0) / 2;

        while (Math.abs(f0 - f1) > 0.1) {
            f0 = f1;
            f1 = (x / f0 + f0) / 2;
        }
        return (int)f1;
    }
}
