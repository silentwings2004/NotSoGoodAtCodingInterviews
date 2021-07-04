package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: RotateString
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 8. Rotate String
 */
public class LN8_RotateString {
    /**
     * Given a string(Given in the way of char array) and an offset, rotate the string by offset in place. (rotate from
     * left to right).
     *
     * Example
     * Example 1:
     *
     * Input: str="abcdefg", offset = 3
     * Output: str = "efgabcd"
     * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "efgabcd".
     * Example 2:
     *
     * Input: str="abcdefg", offset = 0
     * Output: str = "abcdefg"
     * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "abcdefg".
     * Example 3:
     *
     * Input: str="abcdefg", offset = 1
     * Output: str = "gabcdef"
     * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "gabcdef".
     * Example 4:
     *
     * Input: str="abcdefg", offset =2
     * Output: str = "fgabcde"
     * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "fgabcde".
     * Example 5:
     *
     * Input: str="abcdefg", offset = 10
     * Output: str = "efgabcd"
     * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "efgabcd".
     * ==> 当offset > str.length时，rotate offset = offset % str.length
     *
     * Challenge
     * Rotate in-place with O(1) extra memory.
     *
     * Clarification
     * in place means you should change string s in the function. You don't return anything.
     *
     * Notice
     * offset >= 0
     * the length of str >= 0
     * Make changes on the original input data
     * @param str
     * @param offset
     */
    // 【算法】三次分段旋转
    // time = O(n), space = O(1)
    public void rotateString(char[] str, int offset) {
        // corner case
        if (str == null || str.length == 0) return;
        // 【算法】分先后三次旋转: abcd efg offset = 3 --> gfe dcba --> efg dcba --> efg abcd
        offset = offset % str.length; // 对size取余，题目中一旦出现offset，都要想到可能超过array或string长度而进行取余操作！
        reverse(str, 0, str.length - 1);
        reverse(str, 0, offset - 1); // 注意这里的终止位置是offset - 1，而不是offset，因为起始位置是0
        reverse(str, offset, str.length - 1);
    }
    // 【经典】通过swap操作实现array的reverse操作！
    private void reverse(char[] str, int start, int end) {
        while (start < end) {
            char count = str[start];
            str[start++] = str[end];
            str[end--]= count;
        }
    }
}
