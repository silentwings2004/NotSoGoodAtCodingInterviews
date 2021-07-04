package S10_ArrayString;
import java.util.*;

public class RemoveCharsFromString {
    /**
     * yougetoffer  remove of 'f' and 'o'
     * @param s
     * @return
     */
    public String removeChar(String s) {
        // corner case
        if (s == null || s.length() == 0) return s;

        char[] chars = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (chars[fast] != 'o' && chars[fast] != 'f') {
                chars[slow++] = chars[fast];
            }
        }
        return new String(chars, 0, slow);
    }
}
