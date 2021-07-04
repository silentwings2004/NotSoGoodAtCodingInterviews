package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: FactorialTrailingZeros
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 172. Factorial Trailing Zeroes
 */
public class LC172_FactorialTrailingZeros {
    /**
     * Given an integer n, return the number of trailing zeroes in n!.
     *
     * Example 1:
     *
     * Input: 3
     * Output: 0
     * Explanation: 3! = 6, no trailing zero.
     * Example 2:
     *
     * Input: 5
     * Output: 1
     * Explanation: 5! = 120, one trailing zero.
     *
     * t = O(logn)
     * space = O(n)
     *
     * Note: Your solution should be in logarithmic time complexity.
     *
     * 首先末尾有多少个 0 ，只需要给当前数乘以一个 10 就可以加一个 0。
     * 2 * 5 = 10
     * 再具体对于 5!，也就是 5 * 4 * 3 * 2 * 1 = 120，我们发现结果会有一个 0，原因就是 2 和 5 相乘构成了一个 10。
     * 而对于 10 的话，其实也只有 2 * 5 可以构成，所以我们只需要找有多少对 2/5。
     *
     * 含有 2 的因子每两个出现一次，含有 5 的因子每 5 个出现一次，所有 2 出现的个数远远多于 5，换言之找到一个 5，
     * 一定能找到一个 2 与之配对。所以我们只需要找有多少个 5。直接的，我们只需要判断每个累乘的数有多少个 5 的因子即可。
     * n! = 1 * 2 * 3 * 4 * (1 * 5) * ... * (2 * 5) * ... * (3 * 5) *... * n
     * 因为每隔 5 个数出现一个 5，所以计算出现了多少个 5，我们只需要用 n/5 就可以算出来。
     *
     * ... * (1 * 5) * ... * (1 * 5 * 5) * ... * (2 * 5 * 5) * ... * (3 * 5 * 5) * ... * n
     * 每隔 25 个数字，出现的是两个 5，所以除了每隔 5 个数算作一个 5，每隔 25 个数，还需要多算一个 5。
     * 也就是我们需要再加上 n / 25 个 5。
     * 同理我们还会发现每隔 5 * 5 * 5 = 125 个数字，会出现 3 个 5，所以我们还需要再加上 n / 125 。
     * 最终 5 的个数就是 n / 5 + n / 25 + n / 125 ...
     * 写程序的话，如果直接按照上边的式子计算，分母可能会造成溢出。所以算 n / 25 的时候，我们先把 n 更新，n / 5，然后再计算 n / 5 即可。
     *
     * 总结下来：
     * 1. N!有多少个后缀0，即N!有多少个质因数5。
     * 2. N!有多少个质因数5，即N可以划分成多少组5个数字一组，加上划分成多少组25个数字一组，加上划分多少组成125个数字一组，等等。
     * 即Ans = N/5 + N/(5^2) + N/(5^3) + ... ==> N/5 + recursion(N/5)
     * 最终算法复杂度为O(logN)
     *
     * @param n
     * @return
     */
    // S1: use while loop
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            // Ans = N/5 + N/(5^2) + N/(5^3) + ...
            n /= 5;
            count += n;
        }
        return count;
    }

    // S2: recursion
    public int trailingZeroes2(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
