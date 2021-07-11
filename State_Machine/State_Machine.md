# State Machine
---

状态机 -> string matching problem

```
   0 1 2 3 4 5 6 7 8
s =  x x a b a x a c
next[0][a] = 3
next[3][b] = 4
next[4][c] = 8   -> read directly from the table    跳转非常快 -> 读n次
next[8][d] = -1  没有，找不到d
2个维度，m  26 矩阵
预处理，从后往前
next[10][a] = -1
next[10][b] = -1
next[10][c] = -1
next[10][d] = -1

站在9往右看，
next[9][a] = -1
next[9][b] = 10
next[9][c] = -1
next[9][d] = -1

理论上时间复杂度 = O(26m + n  k)
状态机：从一个状态直接跳转到下一个状态
```

模板：

```java
// 第一个维度表示有多少个字符，第二个维度每个位置向右看下一个26个字母的位置在哪里
int[][] next = new int[m + 1][26]; 

for (int ch = 0; ch < 26; ch++) next[m][ch] = -1;
for (int i = m - 1; i >= 0; i--) {
    for (int ch = 0; ch < 26; ch++) {
        next[i][ch] = next[i + 1][ch];
    }
    next[i][source.charAt(i + 1) - 'a'] = i + 1;
}
```
