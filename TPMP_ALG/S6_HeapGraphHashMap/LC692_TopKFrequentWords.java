package S6_HeapGraphHashMap;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: TopKFrequentWords
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 692. Top K Frequent Words
 */
public class LC692_TopKFrequentWords {
    /**
     * Given a non-empty list of words, return the k most frequent elements.
     *
     * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency,
     * then the word with the lower alphabetical order comes first.
     *
     * Example 1:
     * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * Output: ["i", "love"]
     * Explanation: "i" and "love" are the two most frequent words.
     *     Note that "i" comes before "love" due to a lower alphabetical order.
     * Example 2:
     * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     * Output: ["the", "is", "sunny", "day"]
     * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
     *     with the number of occurrence being 4, 3, 2 and 1 respectively.
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Input words contain only lowercase letters.
     * Follow up:
     * Try to solve it in O(n log k) time and O(n) extra space.
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        // corner case
        if (words == null || words.length == 0) return null;
        HashMap<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        List<String> res = new ArrayList<>(count.keySet());
        Collections.sort(res, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));
        return res.subList(0, k);
    }
}
