package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SingleNumberIII
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 260. Single Number III
 */
public class LC260_SingleNumberIII {
    /**
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
     * exactly twice. Find the two elements that appear only once.
     *
     * Example:
     *
     * Input:  [1,2,1,3,2,5]
     * Output: [3,5]
     * diff = 3 ^ 5
     * A B : 二进制数字有且至少有一位是不相同
     *
     * 3 ： 0011
     * 5 ： 0101
     * 3 ^ 5 : 0110 --> 6
     * -6: 11111111111111111111111111111010
     * 6 & -6 : 0010
     *
     *  2 ^ 2 = 0
     *
     *  1. 使用异或运算可以帮助我们消除出现两次的数字；我们计算 bitmask ^= x，则 bitmask 留下的就是出现奇数次的位。
     *  bitmask          00000000
     *  x = 2            00000010
     *  bitmask ^ x =    00000010
     *  bitmask ^x ^ x = 00000000
     *
     *  2. x & (-x) 是保留位中最右边 1 ，且将其余的 1 设位 0 的方法。
     *  x = 7            00000111
     *  -x = -7          11111001
     *  x & (-x)         00000001  --> 最右边的1得以保留
     *
     *  x = 6            00000110
     *  -x = -6          11111010
     *  x & (-x)         00000010  --> 同样还是最右边的1得以保留
     *
     *  首先计算 bitmask ^= x，则 bitmask 不会保留出现两次数字的值，因为相同数字的异或值为 0。
     *  但是 bitmask 会保留只出现一次的两个数字（x 和 y）之间的差异。
     *
     *  bitmask                         00000000
     *  x = 1                           00000001
     *  y = 2                           00000010
     *  a = 3                           00000011
     *  a = 3                           00000011
     *  bitmask ^ x ^ y ^ a ^ a         00000011
     *
     *  我们可以直接从 bitmask 中提取 x 和 y 吗？不能，但是我们可以用 bitmask 作为标记来分离 x 和 y。
     *
     *  我们通过 bitmask & (-bitmask) 保留 bitmask 最右边的 1，这个 1 要么来自 x，要么来自 y。
     *  bitmask = bitmask ^ x ^ y ^ a ^ a      00000011
     *  -bitmask                               11111101
     *  diff = bitmask & (-bitmask)            00000001  --> 保留最右边的1
     *
     *  当我们找到了 x，那么 y = bitmask^x。
     *
     * Note:
     *
     * The order of the result is not important. So in the above example, [5, 3] is also correct.
     * Your algorithm should run in linear runtime complexity. Could you implement it using only constant
     * space complexity?
     * @param nums
     * @return
     */
    // S1: HashMap   time = O(n), space = O(n)
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1); // 只出现1次没有重复的value设为1,出现2次的就遍历后value为2
        }

        int[] res = new int[2]; // 最后输出结果一定有2个，所以先创建长度为2的数组来存放最后找出的结果
        int index = 0;
        for (Map.Entry<Integer, Integer> item: map.entrySet()) { // 遍历hashmap里的每个key-value pair
            if (item.getValue() == 1) res[index++] = item.getKey();
        }
        return res;
    }

    // S2: Bit Operations   time: O(n)   space = O(1)
    public int[] singleNumber2(int[] nums) {
        int bitmask = 0;
        for (int num : nums) bitmask ^= num; //提取出只出现1次的两个数字(x and y)

        int diff = bitmask & (-bitmask); //提取最右边的1，该1要么来自x,要么来自y

        int x = 0;
        for (int num : nums) {
            if ((num ^ diff) != 0) x ^= num; // 筛选出贡献出最右边1的那个出现单次的数，另一个数由于该位位0， 不会执行if后的语句
        } // 注意上面语句不能写作!= 1,因为你不知道最右边的那个1出现在从右到左第几个位置上，很有可能diff是00000010这种情况
        return new int[]{x, bitmask ^ x}; // 通过bitmask ^ x 把x从bitmask里去掉，留下另一个元素y
    }
}
