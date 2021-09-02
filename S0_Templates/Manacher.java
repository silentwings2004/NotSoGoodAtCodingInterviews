package S0_Templates;

public class Manacher {
    // time = O(n), space = O(n)
    public String longestPalindrome(String s) {
        // corner case
        if (s == null || s.length() == 0) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (char c : s.toCharArray()) {
            sb.append(c);
            sb.append("#");
        }

        int n = sb.length();
        int[] p = new int[n];
        int maxCenter = -1, maxRight = -1;

        for (int i = 0; i < n; i++) {
            int r = 0;
            if (i <= maxRight) {
                int j = maxCenter * 2 - i;
                r = Math.min(p[j], maxRight - i);
            } 
            while (i - r >= 0 && i + r < n && sb.charAt(i - r) == sb.charAt(i + r)) r++;
            p[i] = r - 1;
            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                maxCenter = i;
            }
        }

        //********************************************* */
        // below are specific to the problem, eg.LC5
        int maxLen = -1, center = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                center = i;
            }
        }
        int start = center / 2 - maxLen / 2; // 注意：这道题的一大坑点：一定要先算出start，然后end = start + maxLen
        return s.substring(start, start + maxLen); // 不能直接让end = center/2 + maxLen/2,因为由于除2，可能小数位舍去，结果可能最终差1.
    }
}
