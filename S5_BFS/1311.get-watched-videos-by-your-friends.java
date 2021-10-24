import java.util.Queue;

/*
 * @lc app=leetcode id=1311 lang=java
 *
 * [1311] Get Watched Videos by Your Friends
 */

// @lc code=start
class Solution {
    // time = O(n + m + vlogv)，space = O(n + v) 其中n是人数，m是好友关系的总数，v是电影的总数
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        boolean[] visited = new boolean[n];
        visited[id] = true;

        int step = 0;
        List<Integer> persons = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                for (int f : friends[cur]) {
                    if (visited[f]) continue;
                    visited[f] = true;
                    queue.offer(f);
                    if (step + 1 == level) persons.add(f);
                }
            }
            step++;
            if (step == level) break;
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (int f : persons) {
            List<String> videos = watchedVideos.get(f);
            for (String v : videos) {
                map.put(v, map.getOrDefault(v, 0) + 1);
            }
        }

        List<Pair> list = new ArrayList<>();
        for (String key : map.keySet()) {
            list.add(new Pair(key, map.get(key)));
        }

        Collections.sort(list, (o1, o2) -> o1.freq != o2.freq ? o1.freq - o2.freq : o1.name.compareTo(o2.name));
        List<String> res = new ArrayList<>();
        for (Pair p : list) {
            res.add(p.name);
        }
        return res;
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
// @lc code=end

