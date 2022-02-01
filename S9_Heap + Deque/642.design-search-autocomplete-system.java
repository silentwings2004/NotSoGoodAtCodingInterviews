import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=642 lang=java
 *
 * [642] Design Search Autocomplete System
 */

// @lc code=start
class AutocompleteSystem {
    // time = O(nlogn), space = O(n)
    HashMap<String, Integer> map;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        sb = new StringBuilder();
        int n = sentences.length;
        for (int i = 0; i < n; i++) {
            map.put(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        List<String> res = new LinkedList<>();
        if (c == '#') {
            String data = sb.toString();
            map.put(data, map.getOrDefault(data, 0) + 1);
            sb = new StringBuilder();
            return res;
        }

        sb.append(c);
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.freq != o2.freq ? o1.freq - o2.freq : o2.name.compareTo(o1.name));
        for (String x : map.keySet()) {
            if (match(sb.toString(), x)) {
                pq.offer(new Pair(x, map.get(x)));
            }
            if (pq.size() > 3) pq.poll();
        }        

        while (!pq.isEmpty()) res.add(0, pq.poll().name);
        return res;
    }

    private boolean match(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (i >= b.length() || a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    private class Pair {
        private String name;
        private int freq;
        public Pair(String name, int freq) {
            this.name = name;
            this.freq = freq;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
// @lc code=end

