package S9_BitNumberMath;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: IntegertoEnglishWords
 * Creater: Silentwings
 * Date: Sep, 2020
 * Description: 273. Integer to English Words
 */
public class LC273_IntegertoEnglishWords {
    /**
     * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than
     * 231 - 1.
     *
     * Example 1:
     *
     * Input: 123
     * Output: "One Hundred Twenty Three"
     * Example 2:
     *
     * Input: 12345
     * Output: "Twelve Thousand Three Hundred Forty Five"
     * Example 3:
     *
     * Input: 1234567
     * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
     * Example 4:
     *
     * Input: 1234567891
     * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
     * @param num
     * @return
     */
    // time = O(n), space = O(1)
    public String numberToWords(int num) {
        // corner case
        if (num == 0) return "Zero";

        String res = intToStr(num);
        return res.substring(1);
    }

    private String intToStr(int num) {
        String res = "";
        String[] digits = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
                "Nineteen"};
        String[] tens = new String[]{"Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
                "Ninety"};

        if (num >= 1000000000) {
            return intToStr(num / 1000000000) + " Billion" + intToStr(num % 1000000000);
        } else if (num >= 1000000) {
            return intToStr(num / 1000000) + " Million" + intToStr(num % 1000000);
        } else if (num >= 1000) {
            return intToStr(num / 1000) + " Thousand" + intToStr(num % 1000);
        } else if (num >= 100) {
            return intToStr(num / 100) + " Hundred" + intToStr(num % 100);
        } else if (num >= 20) {
            return " " + tens[num / 10] + intToStr(num % 10);
        } else  if (num >= 1) {
            return " " + digits[num];
        } else return "";
    }
}
