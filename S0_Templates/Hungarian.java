package S0_Templates;
import java.util.*;

public class Hungarian {
    int num_nodes = 100000;
    List<Integer>[] next;
    int[] match;
    public int minimumOperations(int[][] grid) {
        next = new List[num_nodes];
        match = new int[num_nodes];
        Arrays.fill(match, -1);
        
        // construct next[]

        boolean[] visited = new boolean[num_nodes];
        int count = 0;
        for (int i = 0; i < num_nodes; i++) {
            if (match[i] != -1) continue;
            Arrays.fill(visited, false);
            if (dfs(i, visited)) count++;
        }
        return count;
    }

    private boolean dfs(int i, boolean[] visited) {
        for (int j : next[i]) {
            if (visited[j]) continue;
            visited[j] = true;
            if (match[j] == -1 || dfs(match[j], visited)) {
                match[i] = j;
                match[j] = i;
                return true;
            }
        }
        return false;
    }
}
