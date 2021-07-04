package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: SpaceReplacement
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 212. Space Replacement
 */
public class LN212_SpaceReplacement {
    /**
     * Write a method to replace all spaces in a string with %20. The string is given in a characters array, you can
     * assume it has enough space for replacement and you are given the true length of the string.
     *
     * You code should also return the new length of the string after replacement.
     *
     * Example
     * Example 1:
     *
     * Input: string[] = "Mr John Smith" and length = 13
     * Output: string[] = "Mr%20John%20Smith" and return 17
     * Explanation:
     * The string after replacement should be "Mr%20John%20Smith", you need to change the string in-place and return
     * the new length 17.
     * Example 2:
     *
     * Input: string[] = "LintCode and Jiuzhang" and length = 21
     * Output: string[] = "LintCode%20and%20Jiuzhang" and return 25
     * Explanation:
     * The string after replacement should be "LintCode%20and%20Jiuzhang", you need to change the string in-place and
     * return the new length 25.
     * Challenge
     * Do it in-place.
     *
     * Notice
     * If you are using Java or Python，please use characters array instead of string.
     * @param string
     * @param length
     * @return
     */
    // 从右向左 —— "拉"  in-place操作(override时从右向左做)
    // time = O(n), space = O(1)
    public int replaceBlank(char[] string, int length) {
        // corner case
        if (string == null || string.length == 0) return 0;

        int count = 0;
        for (int i = 0; i < string.length; i++) {
            if (string[i] == ' ') count++; // calculate the number of space in the input string
        }

        int offset = "%20".length();
        int newLen = length + count * (offset - 1); //注意：扩容的数量是offset - 1而不是offset，因为原来的' '也占一位，要减掉
        int j = 1;
        for (int i = length - 1; i >= 0; i--) { // 防止前面把后面覆盖掉 ==> 从右向左 —— "拉"
            if (string[i] != ' ') {
                string[newLen - j] = string[i];
            } else {
                string[newLen - j] = '0';
                j++;
                string[newLen - j] = '2';
                j++;
                string[newLen - j] = '%';
            }
            j++;
        }
        return newLen;
    }
}