package S9_BitNumberMath;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: FractiontoRecurringDecimal
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 166. Fraction to Recurring Decimal
 */
public class LC166_FractiontoRecurringDecimal {
    /**
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
     *
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     *
     * Example 1:
     *
     * Input: numerator = 1, denominator = 2
     * Output: "0.5"
     * Example 2:
     *
     * Input: numerator = 2, denominator = 1
     * Output: "2"
     * Example 3:
     *
     * Input: numerator = 2, denominator = 3
     * Output: "0.(6)"
     *
     * 1. 0 + - 越界
     * 2. 整数
     * 3. 小数 -> 循环
     *
     * @param numerator     分子
     * @param denominator   分母
     * @return
     */
    //需要用一个哈希表记录余数出现在小数部分的位置，当你发现已经出现的余数，
    //就可以将重复出现的小数部分用括号括起来。再出发过程中余数可能为0，
    //意味着不会出现循环小数，立刻停止程序。
    public String fractionToDecimal(int numerator, int denominator) {
        // write your code here
        // corner case
        if (numerator == 0) return "0";

        StringBuilder res = new StringBuilder();
        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) res.append("-");

        // Convert to Long or else abs(-2147483648) overflows
        // Long.valueOf  or  (long)
        long dividend = Math.abs((long)numerator);
        long divisor = Math.abs((long)denominator);
        // int/long --> String ==> String.valueOf
        res.append(String.valueOf(dividend / divisor)); // 整数部分
        long remainder = dividend % divisor; // 小数部分
        // 小数部分为0，则直接返回整数部分，将StringBuilder --> String
        // ==> toString()
        if (remainder == 0) return res.toString();

        // 接下来讨论如果小数部分不为0的情况，先加"."，再用hashmap来记录余数
        res.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) { // 出现循环时
                //insert(k, s): insert a copy of string s starting at index k of the sequence
                res.insert(map.get(remainder), "("); // 在循环小数位之前插入左括号
                res.append(")");
                break; // 因为是死循环，所以达到加上括号的目的后即可跳出while循环
            }
            // 非死循环下对于小数部分的处理 ==> 在小数点后存入余数
            map.put(remainder, res.length()); //在目前结果的末尾上添加余数
            remainder *= 10; // 为继续除下去得到新的余数，必须将小数点右移1位
            res.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return res.toString();
    }
}
