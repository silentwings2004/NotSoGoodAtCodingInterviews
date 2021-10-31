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
        int n = watchedVideos.size();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < friends.length; i++) {
            for (int f : friends[i]) {
                graph[i].add(f);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        boolean[] visited = new boolean[n];
        visited[id] = true;

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
            step++;
            if (step == level) break;
        }

        HashMap<String, Integer> map = new HashMap<>();
        while (!queue.isEmpty()) {
            int f = queue.poll();
            for (String video : watchedVideos.get(f)) {
                map.put(video, map.getOrDefault(video, 0) + 1);
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.freq != o2.freq ? o1.freq - o2.freq : o1.name.compareTo(o2.name));
        
        for (String key : map.keySet()) {
            pq.offer(new Pair(key, map.get(key)));
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll().name);
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

