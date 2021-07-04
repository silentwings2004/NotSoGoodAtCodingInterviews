package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ValidPalindrome
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 125. Valid Palindrome
 */
public class LC125_ValidPalindrome {
    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * Note: For the purpose of this problem, we define empty string as valid palindrome.
     *
     * Example 1:
     *
     * Input: "A man, a plan, a canal: Panama"
     * Output: true
     * Example 2:
     *
     * Input: "race a car"
     * Output: false
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isPalindrome(String s) {
        // corner case
        if (s == null || s.length() == 0) return true;

        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
