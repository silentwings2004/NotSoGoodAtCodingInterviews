package Two_Pointers;
import java.util.*;

class Solution {
    // time = O(n * w * l), space = O(n * l)
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;

        int w = words[0].length();
        int n = words.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1);
        
        for (int i = 0; i <= s.length() - n * w; i++) { // O(l) 固定一端，逐个检验,注意这里是<=
            HashMap<String, Integer> copy = new HashMap<>(map); // 复制一份map
            int k = n, j = i;
            while (k > 0) { // O(n)
                String str = s.substring(j, j + w); // O(w)
                if (!copy.containsKey(str) || copy.get(str) < 1) break; // 表明str不存在
                copy.put(str, copy.get(str) - 1);
                // if (copy.get(str) == 0) copy.remove(str); // 也可以直接移除，就不用check上面的 < 1的情况了
                k--;
                j += w;
            }
            if (k == 0) res.add(i); // 表明已经全部找到
        }
        return res;
    }
}