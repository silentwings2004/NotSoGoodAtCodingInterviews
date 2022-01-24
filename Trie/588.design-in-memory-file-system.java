import java.util.HashMap;

/*
 * @lc app=leetcode id=588 lang=java
 *
 * [588] Design In-Memory File System
 */

// @lc code=start
class FileSystem {
    TrieNode root;
    HashMap<String, String> fc;
    public FileSystem() {
        root = new TrieNode();
        fc = new HashMap<>();
    }
    // time = O(n), space = O(n)
    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        TrieNode node = root;
        int n = path.length();
        String s = "";

        for (int i = 1; i < n; i++) {
            int j = i;
            while (j < n && path.charAt(j) != '/') j++;
            s = path.substring(i, j);
            node = node.map.get(s);
            i = j;
        }

        if (node.isFile) res.add(s);
        else {
            for (String x : node.map.keySet()) res.add(x);
        }
        return res;
    }
    // time = O(n), space = O(n)
    public void mkdir(String path) {
        TrieNode node = root;
        int n = path.length();

        for (int i = 1; i < n; i++) {
            int j = i; 
            while (j < n && path.charAt(j) != '/') j++;
            String s = path.substring(i, j);
            if (!node.map.containsKey(s)) node.map.put(s, new TrieNode());
            node = node.map.get(s);
            i = j;
        }
    }
    // time = O(n), space = O(n)
    public void addContentToFile(String filePath, String content) {
        TrieNode node = root;
        int n = filePath.length();

        for (int i = 1; i < n; i++) {
            int j = i; 
            while (j < n && filePath.charAt(j) != '/') j++;
            String s = filePath.substring(i, j);
            if (!node.map.containsKey(s)) node.map.put(s, new TrieNode());
            node = node.map.get(s);
            i = j;
        }
        node.isFile = true;
        fc.put(filePath, fc.getOrDefault(filePath, "") + content);
    }
    // time = O(1), space = O(n)
    public String readContentFromFile(String filePath) {
        return fc.get(filePath);
    }

    private class TrieNode {
        private TreeMap<String, TrieNode> map;
        private boolean isFile;
        public TrieNode() {
            this.map = new TreeMap<>();
            this.isFile = false;
        }
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
// @lc code=end

