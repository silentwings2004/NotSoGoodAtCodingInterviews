package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: UniqueCharacters
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 157. Unique Characters
 */
public class LN157_UniqueCharacters {
    /**
     * Implement an algorithm to determine if a string has all unique characters.
     *
     * Example
     * Example 1:
     *
     * Input: "abc_____"
     * Output: false
     * Example 2:
     *
     * Input:  "abc"
     * Output: true
     * Challenge
     * What if you can not use additional data structures?
     * @param str
     * @return
     */
    public boolean isUnique(String str) {
        // corner case
        if (str == null) throw new IllegalArgumentException("msg say sth");
        int[] bits = new int[8];
        char[] chars = str.toCharArray();
        for (char c : chars) {
            int row = c / 32;
            int col = c % 32;
            // use left shift to avoid x modification, always use != 0 to check thus avoid potential bugs.
            if ((bits[row] & (1 << col)) != 0) return false;
            bits[row] |= 1 << col;
        }
        return true;
    }
}
