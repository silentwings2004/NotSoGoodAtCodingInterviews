package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Numberof1Bits
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 191. Number of 1 Bits
 */
public class LC191_Numberof1Bits {
    /**
     * Write a function that takes an unsigned integer and return the number of '1' bits it has
     * (also known as the Hamming weight).
     *
     *
     *
     * Example 1:
     *
     * Input: 00000000000000000000000000001011
     * Output: 3
     * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
     * Example 2:
     *
     * Input: 00000000000000000000000010000000
     * Output: 1
     * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
     * Example 3:
     *
     * Input: 11111111111111111111111111111101
     * Output: 31
     * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
     *
     *
     * Note:
     *
     * Note that in some languages such as Java, there is no unsigned integer type. In this case, the input will be
     * given as signed integer type and should not affect your implementation, as the internal binary representation of
     * the integer is the same whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore,
     * in Example 3 above the input represents the signed integer -3.
     *
     *
     * Follow up:
     *
     * If this function is called many times, how would you optimize it?
     * @param n
     * @return
     */
    // you need to treat n as an unsigned value
    // S1:<< 一共左移31次，check了32个位置
    public int hammingWeight4(int n) {
        int count = 0, mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask << i) != 0) count++;
        }
        return count;
    }

    // S1.5:<< 一共左移31次，check了32个位置
    public int hammingWeight1(int n) {
        int count = 0, mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) count++;
            mask <<= 1;
        }
        return count;
    }

    // S2: very important >>>  确定loop 32次则不用care最高位补0还是1
    public int hammingWeight3(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((1 & n) != 0) count++;  // if ((1 & n >> i) != 0) count++;
            n >>>= 1;  // n >> 1 也可以，因为确保移动32位
        }
        return count;
    }

    // S3: 有提前结束的可能性，用while loop，check是否为0从而可以提前结束 → 最高位无脑补0
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((1 & n) != 0) count++;
            n >>>= 1; // 这里必须用>>>，不能用>>，因为需要判断是否为0而提前结束，所以只能无脑补0来实现
        }
        return count;
    }
}
