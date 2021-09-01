/*
 * @lc app=leetcode id=923 lang=java
 *
 * [923] 3Sum With Multiplicity
 */

// @lc code=start
class Solution {
    // time = O(n^2), space = O(1)
    public int threeSumMulti(int[] arr, int target) {
        // corner case
        if (arr == null || arr.length == 0) return 0;
        long res = 0;
        int MOD = 1000000007;
        
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length; i++) { // O(n)
            int t = target - arr[i];
            int j = i + 1, k = arr.length - 1;
            while (j < k) { // O(n)
                if (arr[j] + arr[k] < t) j++;
                else if (arr[j] + arr[k] > t) k--;
                else { // arr[j] + arr[k] == t
                    if (arr[j] != arr[k]) {
                        int left = j + 1, right = k - 1;
                        while (arr[left] == arr[j] && left < k) left++;
                        while (arr[right] == arr[k] && right > j) right--;
                        res += (left - j) * (k - right);
                        res %= MOD;
                        j = left;
                        k = right;
                    } else {
                        res += (k - j + 1) * (k - j) / 2;
                        res %= MOD;
                        j++;
                        k--;
                        break;
                    }
                }
            }
        }
        return (int) res;
    }
}
// @lc code=end

