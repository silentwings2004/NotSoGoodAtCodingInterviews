package Templates;
import java.util.*;

public class UF {
    HashMap<Integer, Integer> father;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        father = new HashMap<>();
        for (int i = 0; i < n; i++) {
            father.put(i, i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    if (findFather(i) != findFather(j)) { // not belong to the same group, so have different ancestor
                        union(i, j); // merge i, j families
                    }
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(findFather(i)); // findFather(int i) is used to find the top ancestor
        } 
        // if you want 100% sure that you are calling the top ancestor, use findFather(i) only instead of father.get(i)
        return set.size();
    }

    private int findFather(int x) {
        if (father.get(x) != x) {
            father.put(x, findFather(father.get(x)));
        }
        return father.get(x);
    }

    private void union(int x, int y) {
        x = father.get(x); // already reach the top ancestor
        y = father.get(y); // same as x, now we reach the top ancestor
        // father.put(x, y);
        if (x < y) father.put(y, x);
        else father.put(x, y);
    }
}
