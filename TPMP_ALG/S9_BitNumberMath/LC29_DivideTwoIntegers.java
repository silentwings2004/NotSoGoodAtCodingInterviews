package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: DivideTwoIntegers
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 29. Divide Two Integers
 */
public class LC29_DivideTwoIntegers {
    /**
     * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod
     * operator.
     *
     * Return the quotient after dividing dividend by divisor.
     *
     * The integer division should truncate toward zero.
     *
     * Example 1:
     *
     * Input: dividend = 10, divisor = 3
     * Output: 3
     * Example 2:
     *
     * Input: dividend = 7, divisor = -3
     * Output: -2
     * Note:
     *
     * Both dividend and divisor will be 32-bit signed integers.
     * The divisor will never be 0.
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer
     * range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the
     * division result overflows.
     *
     * 要注意的4个问题：
     * 1. + -
     * 2. 越界
     * 3. = 0 3/5
     * 4. 正常
     *
     * 8 / 2 = 4
     * sum = 2  multiple = 1
     * sum = 4  multiple = 2
     * sum = 8  multiple = 4
     *
     * @param dividend  被除数
     * @param divisor   除数
     * @return
     */
    // S1: 二进制bit运算 --> 看一下有多少个power of 2
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; //先考虑到正数端出界时的edge case

        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        int res = 0;

        while (ldividend >= ldivisor) {
            int shift = 0;
            while (ldividend >= (ldivisor << shift)) shift++;
            res += 1 << (shift - 1);
            ldividend -= ldivisor << (shift - 1);
        }

        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) return res;
        return -res; //考虑如果是负数要变符号位
    }

    // S2: time = O(logn)   space = O(logn)
    public int divide2(int dividend, int divisor) {
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = -1;
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        if (ldividend < ldivisor || ldividend == 0) return 0;
        long lres = divide(ldividend, ldivisor);
        int res = 0;
        if (lres > Integer.MAX_VALUE) {
            res = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//        } else res = (int)(sign * lres);
        } else res = (sign == 1) ? (int)lres : -(int)lres;
        return res;
    }

    private long divide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) return 0;
        long sum = ldivisor;
        long multiple = 1;
        while ((sum + sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        return multiple + divide(ldividend - sum, ldivisor);
    }
}
