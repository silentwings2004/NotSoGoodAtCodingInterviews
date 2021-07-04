package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CountandSay
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 38. Count and Say
 */
public class LC38_CountandSay {
    /**
     * The count-and-say sequence is the sequence of integers with the first five terms as following:
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     *
     * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so
     * recursively, in other words from the previous member read off the digits, counting the number of digits in
     * groups of the same digit.
     *
     * Note: Each term of the sequence of integers will be represented as a string.
     *
     *
     *
     * Example 1:
     *
     * Input: 1
     * Output: "1"
     * Explanation: This is the base case.
     * Example 2:
     *
     * Input: 4
     * Output: "1211"
     * Explanation: For n = 3 the term was "21" in which we have two groups "2" and "1", "2" can be read as "12" which
     * means frequency = 1 and value = 2, the same way "1" is read as "11", so the answer is the concatenation of "12"
     * and "11" which is "1211".
     * @param n
     * @return
     */
    // S1: Recursion
    // time = O(2^n), space = O(2^(n - 1))
    public String countAndSay(int n) {
        StringBuilder sb = new StringBuilder();
        int prev = 0;
        if (n == 1) return "1";
        String s = countAndSay(n - 1);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(prev) != s.charAt(i)) {
                int count = i - prev;
                sb.append(count).append(s.charAt(prev));
                prev = i;
            }
        }
        sb.append(s.length() - prev).append(s.charAt(prev));
        return sb.toString();
    }

    // S2:
    public String countAndSay2(int n) {
        // corner case
        if (n < 0) return null;

        String res = "1";
        for (int i = 0; i <= n - 2; i++) { // i = 0 → n = 2 ⇒ i = n - 2
            res = helper(res.toCharArray());
        }
        return res;
    }

    private String helper(char[] chars) {
        StringBuilder sb = new StringBuilder();
        char c = chars[0]; // → c对应指向当前的比较点
        int count = 1; // 当前比较点的count
        int i = 1; // 从初始string开头的后一位开始遍历统计重复的个数

        while (i < chars.length) {
            if (chars[i] == c) { // 如果重复，count++；
                count++;
            } else { // 如果不重复，则把当前count和与count对应的数字(当前指向的上一位)添加到结果中
                sb.append(count).append(chars[i - 1]);
                count = 1; // 还原count = 1，给目前所在的新数字重新从1开始计数
                c = chars[i]; // 比较点c设置为当前所指的新数字
            }
            i++;
        }
        sb.append(count).append(chars[i - 1]); //到达末尾时记得做post-processing，添加最后结果
        return sb.toString();
    }
}
