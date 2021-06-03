### DP - Double_Order 双序列型

[分类模板]
1. double string类
模板题：LC97_Interleaving String
```java
boolean[][] dp = new boolean[m + 1][n + 1];
dp[0][0] = true; // 千万别忘记初始化边界条件！！！在这里两个空string就是match的，所以要赋值为true
// trick: 在string前面补1位来实现dp_idx与str_idx的同步！！！
s1 = "#" + s1;

for (int i = 1; i <= m; i++) dp[i][0] = (dp[i - 1][0] && s1.charAt(i) == s3.charAt(i));
for (int j = 1; j <= n; j++) dp[0][j] = (dp[0][j - 1] && s2.charAt(j) == s3.charAt(j));

for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (dp[i -1][j] && s1.charAt(i) == s3.charAt(i + j) || dp[i][j - 1] && s2.charAt(j) == s3.charAt(i + j)) {
            dp[i][j] = true;
        }
    }
}
return dp[m][n];
```


[解题笔记]

097.Interleaving-String (H-)
```
千万别忘记初始化边界条件！！！在这里两个空string就是match的，所以要赋值为true
dp[0][0]= true;

str_idx = dp_idx - 1 => 为了消除这种问题，可以在string前补一位
s1 = "#" + s1;

```
