package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RegularExpressionMatching
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 10. Regular Expression Matching
 */
public class LC10_RegularExpressionMatching {
    /**
     * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
     *
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     *
     * Note:
     *
     * s could be empty and contains only lowercase letters a-z.
     * p could be empty and contains only lowercase letters a-z, and characters like . or *.
     *
     * Example 1:
     *
     * Input:
     * s = "aa"
     * p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     *
     * Example 2:
     *
     * Input:
     * s = "aa"
     * p = "a*"
     * Output: true
     * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once,
     * it becomes "aa".
     *
     * Example 3:
     *
     * Input:
     * s = "ab"
     * p = ".*"
     * Output: true
     * Explanation: ".*" means "zero or more (*) of any character (.)".
     *
     * Example 4:
     *
     * Input:
     * s = "aab"
     * p = "c*a*b"
     * Output: true
     * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
     *
     * Example 5:
     *
     * Input:
     * s = "mississippi"
     * p = "mis*is*p*."
     * Output: false
     *
     * // S1
     * s的整个string要被包含在p当中 ==> s.length() <= p.length() --> p一旦走完，s一定也要走完才可能return true，否则一定false
     * 使用2个下标sp和pp分别记录String s和String p里当前匹配的位置
     * 1. 如果已经遍历完了p，则要求s也要被遍历完成；
     * 2. 如果pp的下一个字符不为'*'，则匹配pp和sp的当前字符；如果匹配成功，则recursion继续匹配pp + 1和sp + 1
     * 3. 如果pp的下一个字符为'*'，则分为两叉：
     * a. 跳过'*'符号，recursion匹配pp + 2与sp；('*' matches 0 preceding element)
     * b. 不跳过'*'符号，继续匹配pp与sp + 1. ('*' matches more of the preceding element)
     * 4. 最后还需要比较pp + 2和sp的情况，因为如果sp到头了，'.*'也是能匹配空串的。
     *
     * // S2: 回溯法
     * 如果只是两个普通字符串进行匹配，按序遍历比较即可：
     * if( s.charAt(i) == p.charAt(i) )
     *
     * 如果正则表达式字符串p只有一种"."一种特殊标记，依然是按序遍历比较即可 ：
     * if( s.charAt(i) == p.charAt(i) || p.charAt(i) == '.' )
     *
     * 上述两种情况实现时还需要判断字符串长度和字符串判空的操作。
     * 但是，"*"这个特殊字符需要特殊处理，当p的第i个元素的下一个元素是星号时会有两种情况：
     *
     * 1. i元素需要出现0次，我们就保持s不变，将p的减掉两个元素，调用isMatch。例如s：bc、p：a*bc，我们就保持s不变，减掉p的"a*"，
     * 调用isMatch(s:bc,p:bc)。
     *
     * 2. i元素需要出现一次或更多次，先比较i元素和s首元素，相等则保持p不变，s减掉首元素，调用isMatch。例如s：aabb、p：a*bb，
     * 就保持p不变，减掉s的首元素，调用isMatch(s:abb,p:a*bb)。此时存在一些需要思考的情况，例如s：abb、p：a*abb，会用两种方式处理：
     *
     * a. 按照上述第二种情况比较i元素和s首元素，发现相等就会减掉s的首字符，调用isMatch(s:bb,p:a*abb)。
     * 在按照上述第一种情况减去p的两个元素，调用isMatch(s:bb,p:abb)，最终导致false。
     *
     * b. 直接按照上述第一种情况减去p的两个元素，调用isMatch(s:abb,p:abb)，最终导致true。
     *
     * 所以说这算是一种暴力方法，会将所有的情况走一边，看看是否存在可以匹配的情况。
     *
     * // S3: DP
     *
     *  Some examples:
     *  isMatch("aa","a") → false
     *  isMatch("aa","aa") → true
     *  isMatch("aaa","aa") → false
     *  isMatch("aa", "a*") → true
     *  isMatch("aa", ".*") → true
     *  isMatch("ab", ".*") → true
     *  isMatch("aab", "c*a*b") → true
     *
     * boolean dp[i][j]的含义是s[0-i] 与 p[0-j]是否匹配。
     *
     *  c* = empty
     *
     *  1，p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]
     *  2，If p.charAt(j) == ‘.’ : dp[i][j] = dp[i-1][j-1];
     *  3，If p.charAt(j) == ‘*’:
     *      here are two sub conditions:
     *      1，if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty
     *      2，if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == ‘.’:
     *          dp[i][j] = dp[i][j-1] // in this case, a* counts as single a
     *          dp[i][j] = dp[i-1][j] //in this case, a* counts as multiple a
     *          dp[i][j] = dp[i][j-2] // in this case, a* counts as empty
     *
     * @param s
     * @param p
     * @return
     */
    // S1:
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null) return p == null;
        if (p == null) return false;
        return helper(s, p, 0, 0);
    }

    private boolean helper(String s, String p, int sp, int pp) {
        // p到头了，check下s是否也到头 ==> p.length() >= s.length()
        if (pp == p.length()) return sp == s.length();
        // 如果pp的下一个字符不是'*'的情况
        if (pp == p.length() - 1 || p.charAt(pp + 1) != '*') {
            if (sp == s.length()) return false;
            // 转到下一个字符
            return (p.charAt(pp) == '.' || s.charAt(sp) == p.charAt(pp)) && helper(s, p, sp + 1, pp + 1);
        }
        while (sp < s.length() && (p.charAt(pp) == '.' || s.charAt(sp) == p.charAt(pp))) {
            // 跳过*字符，如果匹配，返回true，否则继续比较
            if (helper(s, p, sp, pp + 2)) return true;
            sp++;
        }
        // 跳过*字符，方便检查s到头的情况
        return helper(s, p, sp, pp + 2);
    }

    // S2: 回溯法: 这种匹配思路其实就是不断地减掉s和p的可以匹配首部，直至一个或两个字符串被减为空的时候，根据最终情况来得出结论
    // time = O((m + n) * 2^(m + n/2)), space = O((m + n) * 2^(m + n/2)), m 和 n分别表示s和p的长度
    public boolean isMatch2(String s, String p) {
        // corner case
        //如果正则串p为空字符串s也为空这匹配成功，如果正则串p为空但是s不是空则说明匹配失败
        if (p == null || p.length() == 0) return s == null || s.length() == 0;

        //判断s和p的首字符是否匹配，注意要先判断s不为空
        boolean headMatched = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') { //如果p的第一个元素的下一个元素是*
            //则分别对两种情况进行判断
            return isMatch2(s, p.substring(2)) || (headMatched && isMatch2(s.substring(1), p));
        } else if (headMatched) { //否则，如果s和p的首字符相等
            return isMatch2(s.substring(1), p.substring(1));
        } else {
            return false;
        }
    }

    // S3: DP
    public boolean isMatch3(String s, String p) {
        // corner case
        if (p == null || p.length() == 0) return s == null || s.length() == 0;
        //需要分别取出s和p为空的情况，所以dp数组大小+1
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //初始化dp[0][0]=true,dp[0][1]和dp[1][0]~dp[s.length][0]默认值为false所以不需要显式初始化
        dp[0][0] = true;
        //填写第一行dp[0][2] ~ dp[0][p.length]
        for (int k = 2; k <= p.length(); k++) {
            //p字符串的第2个字符是否等于'*',此时j元素需要0个，所以s不变p减除两个字符
            dp[0][k] = p.charAt(k - 1) == '*' && dp[0][k - 2];
        }
        //填写dp数组剩余部分
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                //p第j个字符是否为*
                if (p.charAt(j) == '*') {
                    // 两种情况:1.s不变[i+1],p移除两个元素[j+1-2]。
                    // 2.比较s的i元素和p的j-1(因为此时j元素为*)元素,相等则移除首元素[i+1-1],p不变。
                    dp[i + 1][j + 1] = dp[i + 1][j - 1] || (dp[i][j + 1] && headMatched(s, p, i, j - 1));
                } else {
                    //s的i元素和p的j元素是否相等,相等则移除s的i元素[i+1-1]和p的j元素[j+1-1]
                    dp[i + 1][j + 1] = dp[i][j] && headMatched(s, p, i, j);
                }
            }
        }
        return dp[s.length()][p.length()];
    }
    //判断s第i个字符和p第j个字符是否匹配
    private boolean headMatched(String s, String p, int i, int j) {
        return s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
    }
}
