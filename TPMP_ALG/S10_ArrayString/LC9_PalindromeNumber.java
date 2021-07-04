package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PalindromeNumber
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 9. Palindrome Number
 */
public class LC9_PalindromeNumber {
    /**
     * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as
     * forward.
     *
     * Example 1:
     *
     * Input: 121
     * Output: true
     * Example 2:
     *
     * Input: -121
     * Output: false
     * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a
     * palindrome.
     * Example 3:
     *
     * Input: 10
     * Output: false
     * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
     * Follow up:
     *
     * Coud you solve it without converting the integer to a string?
     * @param x
     * @return
     */
    // time = O(logn), time = O(1)
    public boolean isPalindrome(int x) {
        // corner case
        // 特殊情况：当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || x % 10 == 0 && x != 0) return false;

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 rev/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，rev = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == rev || x == rev / 10;
    }
}
