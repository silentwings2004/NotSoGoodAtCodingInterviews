package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ReverseBits
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 190. Reverse Bits
 */
public class LC190_ReverseBits {
    /**
     * Reverse bits of a given 32 bits unsigned integer.
     *
     *
     *
     * Example 1:
     *
     * Input: 00000010100101000001111010011100
     * Output: 00111001011110000010100101000000
     * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
     * so return 964176192 which its binary representation is 00111001011110000010100101000000.
     * Example 2:
     *
     * Input: 11111111111111111111111111111101
     * Output: 10111111111111111111111111111111
     * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
     * so return 3221225471 which its binary representation is 10111111111111111111111111111111.
     *
     *
     * Note:
     *
     * Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output
     * will be given as signed integer type and should not affect your implementation, as the internal binary
     * representation of the integer is the same whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2
     * above the input represents the signed integer -3 and the output represents the signed integer -1073741825.
     *
     *
     * Follow up:
     *
     * If this function is called many times, how would you optimize it?
     * @param n
     * @return
     */
    // you need treat n as an unsigned value
    // S1: primitive vs reverse linkedin list or reverse string   单边访问神龙摆尾
    public int reverseBits(int n) {
        // corner case
        if (n == 0 || n == -1) return n;  // 全0全1不用check，直接返回
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 1 & (n >> i);
            res |= count << (31 - i);  //从左向右走了i ==> 从右向左走31 - i
        }
        return res;
    }

    // S2: reverse by swap vs array swap, string swap, two pointers 相向  O(1)时间随机访问
    public int reverseBits2(int n) {
        // 一个位置XOR就相当于该位取反，与~区别，~是每一位都取反
        for (int i = 0; i < 16; i++) {
            int left = 1 & (n >> (31 - i));
            int right = 1 & (n >> i);
            if (left != right) {
                n ^= 1 << (31 - i);
                n ^= 1 << i;
            }
        }
        return n;
    }

    // S3
    public int reverseBits3(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) + (n & 1);
            n >>= 1;
        }
        return res;
    }
}
