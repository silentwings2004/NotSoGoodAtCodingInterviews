package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: WildcardMatching
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 44. Wildcard Matching
 */
public class LC44_WildcardMatching {
    /**
     * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
     *
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     *
     * Note:
     *
     * s could be empty and contains only lowercase letters a-z.
     * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
     * Example 1:
     *
     * Input:
     * s = "aa"
     * p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     * Example 2:
     *
     * Input:
     * s = "aa"
     * p = "*"
     * Output: true
     * Explanation: '*' matches any sequence.
     * Example 3:
     *
     * Input:
     * s = "cb"
     * p = "?a"
     * Output: false
     * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
     * Example 4:
     *
     * Input:
     * s = "adceb"
     * p = "*a*b"
     * Output: true
     * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
     * Example 5:
     *
     * Input:
     * s = "acdcb"
     * p = "a*c?b"
     * Output: false
     *
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "*") → true
     * isMatch("aa", "a*") → true
     * isMatch("ab", "?*") → true
     * isMatch("aab", "c*a*b") → false
     *
     * "bbarc" match = 3 sp = 3
     * "*c" star = 0 pp = 1
     *
     *
     * @param s
     * @param p
     * @return
     */
    // 回溯法
    // time = O(slogp), space = O(1)
    // 算法：
    //我们使用两个指针：sp 遍历输入字符串，pp 遍历字符模式。当 sp < s.length()：
    //
    //如果字符模式仍有字符 pp < p.length() 且指针下的字符匹配 p[pp] == s[sp] 或 p[pp] == '?'，则两个指针向前移动。
    //反之如果字符模式仍有字符 pp < p.length() 且 p[pp] == '*'，则首先检查匹配 0 字符的情况，即只增加模式指针 pp++。
    // 记下可能回溯的位置 star 和当前字符串的位置 match。
    //反之如果出现不匹配的情况：
    //如果字符模式中没有星号，则返回 False。
    //如果有星号，则回溯：设置 pp = star + 1 和 sp = match + 1，假设这次的星匹配多个字符。则可能的回溯为 match = sp。
    //如果字符模式的所有剩余字符都是星号，则返回 True。
    public boolean isMatch(String s, String p) {
        // 引入两个指针sp, pp分别对应于被匹配的s和匹配的p，match对应于s与p的'*'配对时的位置，而star则记录p中出现的'*'
        int sp = 0;
        int pp = 0;
        int match = 0;
        int star = -1;
        // 遍历被匹配的s，由于*的存在，s.length()与p.length()之间没有必然的大小关系，只能先固定遍历被匹配的s，
        // 然后根据p可能出现的配对可能性进行分支讨论
        while (sp < s.length()) {
            // case 1: 一对一完美匹配 'a' -- 'a' or 'a' -- '?' (注意？不能代表' '空格)
            if (pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
             // case 2: 一对一('*')非完美匹配 'a' -- '*'
            } else if (pp < p.length() && p.charAt(pp) == '*') {
                star = pp;  //pp记录此刻p中'*'的位置
                match = sp; //sp记录此刻s中与'*'匹配的位置，以'*'代表0位来算
                pp++; // 只移动pp指针至'*'的下一个位置 -->
            // case 3: 多对一('*')匹配
            } else if (star != -1) {  // 此刻sp与pp完全不match(pp不指向'?'和'*')，但是star记录着最近上一次的'*'位置，
                // 这也就意味着当前sp指向的位置不与当前pp指向的位置匹配，而是双方都可以回溯到与star所记录的上一个'*'的位置
                // 重新按照多对'*'的原则匹配
                // "abbb" -- "a*bc" --> 末尾'b' -- 'c'不匹配，双方都回溯到最近上一个'*'位置重新开始匹配 'bbb' -- '*bc'
                pp = star + 1; // 让pp回溯到上一个'*'位置之后使sp指向的位置与'*'错位匹配
                match++; // match指向的位置实现"多对*"的匹配，以'*'代表1位来算(2位， 3位......）直到之后满足先前上面的2种if语句
                sp = match; // sp也回溯到match配对最近上一个'*'位置的下一位去重新配对
            } else return false;
        }
        for (int i = pp; i < p.length(); i++) { //s遍历完之后，如果p仍未遍历完，除非剩下只有'*'，否则return false
            if (p.charAt(i) != '*') return false;
        }
        return true;
    }
}
