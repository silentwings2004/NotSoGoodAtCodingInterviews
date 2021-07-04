package S10_ArrayString;
import java.util.*;
public class RemoveDuplicatesfromSortedArray {
    /**
     * 去重留k个
     */
    // S1: [)
    public String removeDup(String s, int k) {
        if (s == null || s.length() <= k) return s;

        char[] chars = s.toCharArray();
        int slow = k;
        for (int fast = k; fast < chars.length; fast++) {
            if (chars[fast] != chars[slow - k]) {
                chars[slow++] = chars[fast];
            }
        }
        return new String(chars, 0, slow);
    }

    // S2: []
    public String removeDup2(String s, int k) {
        if (s == null || s.length() <= k) return s;

        char[] chars = s.toCharArray();
        int slow = k - 1;
        for (int fast = k; fast < chars.length; fast++) {
            if (chars[fast] != chars[slow + 1 - k]) {
                chars[++slow] = chars[fast];
            }
        }
        return new String(chars, 0, slow + 1);
    }
}
