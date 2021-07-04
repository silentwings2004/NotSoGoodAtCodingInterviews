package S10_ArrayString;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: LongestSubstringWithoutRepeatingCharacters
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 3. Longest Substring Without Repeating Characters
 */
public class LC3_LongestSubstringWithoutRepeatingCharacters {
    /**
     * Given a string, find the length of the longest substring without repeating characters.
     *
     * Example 1:
     *
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     *
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     * @param s
     * @return
     */
    // S1: HashSet (sliding window)
    // time = O(n), space = O(min(n, k)) k是字符集的大小，
    // 比如如果都是小写字母，k = 26, 而如果n > 26的话，则space = O(k),反之，如果n < 26，则space = O(n)
    public int lengthOfLongestSubstring(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        HashSet<Character> set = new HashSet<>();
        int start = 0, end = 0;
        int max = 0;

        while (start < s.length() && end < s.length()) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end++));
                max = Math.max(max, end - start);
            } else {
                set.remove(s.charAt(start++)); // start不断前移，直至将set里与当前end指向的相同元素删除为止
            }
        }
        return max;
    }

    // S2: HashMap
    // time = O(n), space = O(min(n, k))
    public int lengthOfLongestSubstring2(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        int j = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1); // 遇到重复时，j只能向前不能倒退
            }
            map.put(s.charAt(i), i); // 不管有没有重复，后面的总是更新前面的以便计算出新的length
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    // S3: Use int[] --> 当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map
    // int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
    // int [128] 用于ASCII码
    // int [256] 用于扩展ASCII码
    // 假设字符集为 ASCII 128
    // time = O(n), space = O(k)
    public int lengthOfLongestSubstring3(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        int[] index = new int[128];
        int max = 0;
        int j = 0;

        for (int i = 0; i < s.length(); i++) {
            j = Math.max(index[s.charAt(i)], j);
            max = Math.max(max, i - j + 1);
            index[s.charAt(i)] = i + 1;
        }
        return max;
    }
}
