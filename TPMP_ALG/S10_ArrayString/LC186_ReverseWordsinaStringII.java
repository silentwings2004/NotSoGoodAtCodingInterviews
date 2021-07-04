package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ReverseWordsinaStringII
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 186. Reverse Words in a String II
 */
public class LC186_ReverseWordsinaStringII {
    /**
     * Given an input string , reverse the string word by word.
     *
     * Example:
     *
     * Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
     * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
     * Note:
     *
     * A word is defined as a sequence of non-space characters.
     * The input string does not contain leading or trailing spaces.
     * The words are always separated by a single space.
     * Follow up: Could you do it in-place without allocating extra space?
     * @param s
     */
    public void reverseWords(char[] s) {
        // corner case
        if (s == null || s.length < 2) return;

        reverse(s, 0, s.length - 1);
        int slow = 0;
        int fast;
        for (fast = 1; fast < s.length; fast++) {
            if (s[fast] == ' ') {
                reverse(s, slow, fast - 1);
                slow = fast + 1;
            }
        }
        reverse(s, slow, fast - 1); // deal with the last word
    }

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char count = s[start];
            s[start++] = s[end];
            s[end--] = count;
        }
    }
}
