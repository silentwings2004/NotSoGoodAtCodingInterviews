import java.util.TreeMap;

/*
 * @lc app=leetcode id=729 lang=java
 *
 * [729] My Calendar I
 */

// @lc code=start
class MyCalendar {
    // time = O(nlogn), space = O(n)
    TreeMap<Integer, Integer> map;
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer fk = map.floorKey(start);
        if (fk != null) {
            if (map.get(fk) > start) return false;
        }
        Integer hk = map.higherKey(start);
        if (hk != null) {
            if (hk < end) return false;
        }
        map.put(start, end); // 记得要把当前的[start, end]放进map里！！！
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
// @lc code=end

