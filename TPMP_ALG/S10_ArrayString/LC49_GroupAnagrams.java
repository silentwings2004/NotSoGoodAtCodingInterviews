package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: GroupAnagrams
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 49. Group Anagrams
 */
public class LC49_GroupAnagrams {
    /**
     * Given an array of strings, group anagrams together.
     *
     * Example:
     *
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     *
     * aet
     * "eat"
     * HashMap : "aet" , 0
     * res : "ate"
     *
     * Note:
     *
     * All inputs will be in lowercase.
     * The order of your output does not matter.
     */
    // S1: Array sort
    // time = O(nklogk), space  = O(nk) worst case n个元素全部进入hashmap，str最大长度为k ==> space = O(nk)
    //将每个单词按照字母表顺序排序。比如eat，aet排序好以后都是aet。遍历原数组，取出每个单词按字母表排序后的结果。将其加入map。
    // 对于strs = {"eat", "tea", "tan", "ate", "nat", "bat"}，
    // 我们会统计出如下的map。{aet=[eat, tea, ate], abt=[bat], ant=[tan, nat]}。map的value即为我们所求。

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        // corner case
        if (strs == null || strs.length == 0) return res;

        HashMap<String, Integer> map = new HashMap<>(); // --> space O(n)
        for (String str : strs) {  // --> time  O(n)
            char[] chars = str.toCharArray();
            Arrays.sort(chars);  // --> time O(klogk) k是strs中字符串的最大长度
            String s = new String(chars);
            if (map.containsKey(s)) {
                res.get(map.get(s)).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, res.size());  // --> space O(k)
                res.add(list);
            }
        }
        return res;
    }

    // S2: count sort
    // 统计每个单词中字母出现的次数。比如eat和aet我们都会统计出a1,e1,t1。 在map中，这种会被当做统一的key，即a1e1t1。
    // 对于strs = {"eat", "tea", "tan", "ate", "nat", "bat"}，我们会统计出如下的map。
    // {a1b1t1=[bat], a1n1t1=[tan, nat], a1e1t1=[eat, tea, ate]}。map的value即为我们所求。
    // time = O(nk), space = O(nk)
    public List<List<String>> groupAnagrams2(String[] strs) {
        // corner case
        if (strs == null || strs.length == 0) return null;

        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {  // time --> O(n)
            int[] count = new int[26];
            for (Character ch : str.toCharArray()) {
                count[ch - 'a']++;
            }
            String s = "";
            for (int i = 0; i < count.length; i++) { // time --> O(k)
                if (count[i] != 0) {
                    s += String.valueOf(count[i]) + String.valueOf((char)('a' + i));
                }
            }

            if (map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
