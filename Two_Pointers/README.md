### Two_Pointers

[分类模板]
1. Sum类
模板题：LC15_3Sum
```java
for (int i = 0; i < n; i++) {
    if (i > 0 && nums[i] == nums[i - 1]) continue; // 查重1
    int sum = -nums[i];
    int left = i + 1, right = n - 1;
    while (left < right) {
        if (nums[left] + nums[right] == sum) {
            res.add(Arrays.asList(nums[i], nums[left], nums[right]));
            while (left < right && nums[left] == nums[left + 1]) left++; // 查重2.1
            while (left < right && nums[right] == nums[right - 1]) right--; // 查重2.2
            left++;
            right--;
        } else if (nums[left] + nums[right] < sum) left++;
        else right--;
    }
}
```


[解题笔记]

18. 4Sum (E)
```
注意这里j从i + 1出发 j = i + 1
```
259. 3Sum Smaller (M)
```
if (sum < target) => left++  
left收缩，继续看是否有符合条件的解出现
```
30. Substring with Concatenation of All Words (M+)
```
w = words[0].length(),   i <= s.length() - n * w
```