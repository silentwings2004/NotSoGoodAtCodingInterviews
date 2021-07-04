package S10_ArrayString;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RemoveVowelsfromaString
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 1119. Remove Vowels from a String
 */
public class LC1119_RemoveVowelsfromaString {
    /**
     * Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
     *
     * Example 1:
     *
     * Input: "leetcodeisacommunityforcoders"
     * Output: "ltcdscmmntyfrcdrs"
     *
     * Example 2:
     *
     * Input: "aeiou"
     * Output: ""
     *
     *
     * Note:
     *
     * S consists of lowercase English letters only.
     * 1 <= S.length <= 1000
     * @param S
     * @return
     */
    // S1: Two pointers: slow / fast
    // time = O(n), space = O(1)
    public String removeVowels(String S) {
        // corner case
        if (S == null || S.length() == 0) {
            return S;
        }

        char[] chars = S.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (chars[fast] != 'a' && chars[fast] != 'e' && chars[fast] != 'i' && chars[fast] != 'o' && chars[fast] != 'u') {
                chars[slow++] = chars[fast];
            }
        }
        return new String(chars, 0, slow);
    }
}
