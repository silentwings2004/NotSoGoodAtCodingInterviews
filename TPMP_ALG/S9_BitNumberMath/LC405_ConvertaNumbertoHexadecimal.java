package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ConvertaNumbertoHexadecimal
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 405. Convert a Number to Hexadecimal
 */
public class LC405_ConvertaNumbertoHexadecimal {
    /**
     * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method
     * is used.
     *
     * Note:
     *
     * All letters in hexadecimal (a-f) must be in lowercase.
     * The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single
     * zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
     * The given number is guaranteed to fit within the range of a 32-bit signed integer.
     * You must not use any method provided by the library which converts/formats the number to hex directly.
     * Example 1:
     *
     * Input:
     * 26
     *
     * Output:
     * "1a"
     * (0001 1010) & (0000 1111) = (0000 1010) = 10 ==> a
     * Example 2:
     *
     * Input:
     * -1
     *
     * Output:
     * "ffffffff"
     * @param num
     * @return
     */
    // time = O(1), space = O(1)
    public String toHex(int num) {
        if (num == 0) return "0";
        StringBuilder res = new StringBuilder();
        String str = "0123456789abcdef";
        char[] hex = str.toCharArray();
        while (num != 0) {
            res.append(hex[num & 15]); // 16进制取余，提取二进制中后4位
            num >>>= 4; // 无符号无脑右移4位，提取接下来的二进制中的4位
        }
        return res.reverse().toString(); // 取反然后转化为string
    }
}
