package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: DecodeWays
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 91. Decode Ways
 */
public class LC91_DecodeWays {
    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     *
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     *
     * Example 1:
     *
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     *
     * Example 2:
     *
     * Input: "226"
     * Output: 3
     * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     *
     * For example,
     * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
     *
     * The number of ways decoding "12" is 2.
     *
     * // S1
     * 限制条件：一位数时不能为0，两位数不能大于26，其十位上的数也不能为0
     * 需要用动态规划 Dynamci Programming 来解
     * 建立一维 dp 数组，其中 dp[i] 表示s中前i个字符组成的子串的解码方法的个数，长度比输入数组长度多1，并将 dp[0] 初始化为1
     *
     * 寻找状态转移方程：
     *
     * 226
     * i = 1 ==> s[0] = 2 只有只有一种拆分方法，就是2，注意 s[0] 一定不能为0，这样的话无法拆分
     * i = 2 ==> s[1] = 2 这样 dp[i] 至少可以有跟 dp[i-1] 一样多的拆分情况，接下来还要看其能否跟前一个数字拼起来
     * 若拼起来的两位数小于等于26，并且大于等于 10（因为两位数的高位不能是0），那么就可以在之前 dp[i-2] 的每种情况下都加上这个二位数，
     * 所以最终 dp[i] = dp[i-1] + dp[i-2] --> 斐波那契数列
     * 所以0是个很特殊的存在，若当前位置是0，则一定无法单独拆分出来，即不能加上 dp[i-1]，就只能看否跟前一个数字组成大于等于 10 且
     * 小于等于 26 的数，能的话可以加上 dp[i-2]，否则就只能保持为0了。
     *
     * 在遍历的过程中，对每个数字首先判断其是否为0，若是则将 dp[i] 赋为0，若不是，赋上 dp[i-1] 的值，然后看数组前一位是否存在，
     * 如果存在且满足前一位是1，或者和当前位一起组成的两位数不大于 26，则当前 dp[i] 值加上 dp[i - 2]。最终返回 dp 数组的最后一个值即可。
     *
     *
     * //S3: 用两个变量 c1, c2 来分别表示 s[i-1] 和 s[i-2] 的解码方法
     * 从 i=1 开始遍历，也就是字符串的第二个字符，判断如果当前字符为 '0'，说明当前字符不能单独拆分出来，只能和前一个字符一起，
     * 先将 c1 赋为0，然后看前面的字符，如果前面的字符是1或者2时，就可以更新 c1 = c1 + c2，然后 c2 = c1 - c2，
     * 其实 c2 赋值为之前的 c1，如果不满足这些条件的话，那么 c2 = c1，
     *
     * @param s
     * @return
     */
    // S1: DP - 正序扫描
    public int numDecodings(String s) {
        // corner case
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;

        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;

        for (int i = 1; i <= len; i++) {
            if (s.charAt(i - 1) == '0') dp[i] = 0;
            else dp[i] = dp[i - 1];
            if (i > 1 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }

    // S2: DP - 倒序扫描输入串
    // time = O(n), space = O(n)
    public int numDecodings2(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') dp[i] = 0;
            else dp[i] = dp[i + 1];
            if (i + 1 < s.length() && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) <= '6'))) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }

    // S3: 用两个变量 c1, c2 来分别表示 s[i-1] 和 s[i-2] 的解码方法
    // time = O(n), space = O(1)
    public int numDecodings3(String s) {
        // corner case
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;

        int c1 = 1, c2 = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') c1 = 0;
            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                c1 = c1 + c2;
                c2 = c1 - c2;
            } else c2 = c1;
        }
        return c1;
    }
}
