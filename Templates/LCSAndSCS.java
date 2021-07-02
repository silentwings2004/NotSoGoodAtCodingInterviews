package Templates;

public class LCSAndSCS {
    public int longestCommonSupersequence(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        String t = sb.reverse().toString();

        s = "#" + s;
        t = "#" + t;

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }


    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        str1 = "#" + str1;
        str2 = "#" + str2;
        
        int[][] dp = new int[m + 1][n + 1];
        
        // init
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        // return dp[m][n]

        // recover the path
        int i = m, j = n;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (str1.charAt(i) == str2.charAt(j)) {
                sb.append(str1.charAt(i));
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j] + 1) sb.append(str1.charAt(i--));
            else sb.append(str2.charAt(j--));
        }
        while (i > 0) sb.append(str1.charAt(i--));
        while (j > 0) sb.append(str2.charAt(j--));
        return sb.reverse().toString();
    }
}
