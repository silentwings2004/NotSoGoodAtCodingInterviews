package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: GuessNumberHigherorLower
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 374. Guess Number Higher or Lower
 */
public class LC374_GuessNumberHigherorLower extends GuessGame {
    /**
     * We are playing the Guess Game. The game is as follows:
     *
     * I pick a number from 1 to n. You have to guess which number I picked.
     *
     * Every time you guess wrong, I'll tell you whether the number is higher or lower.
     *
     * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
     *
     * -1 : My number is lower   --> target < guessNumber
     *  1 : My number is higher  --> target > guessNumber
     *  0 : Congrats! You got it! --> target == guessNumber
     * Example :
     *
     * Input: n = 10, pick = 6
     * Output: 6
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public int guessNumber(int n) {
        // corner case: from 1 to n ==> n > 1
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == 0) return mid;
            if (guess(mid) < 0) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
}
