package S10_ArrayString;
import java.util.*;

public class RemoveSpaces {
    /**
     * input: _ _ _ you _ _ _ get _ offer_ _ _
     *
     *          y o u _get _ offer _ offer_ _ _
     *
     *                               s
     *                                        f
     *          _ y o u _ g e t _ offer ffer_ _ _
     *
     *                                  s
     *                                          f
     * @param s
     * @return
     */
    // 条件比较多的情况下，不要用if，而是以结果为导向来反推其条件是怎样的，这样一定是if else两条
    // S1: always keep the space after word
    public String removeSpaces(String s) {
        // corner case
        if (s == null || s.length() == 0) return s;

        char[] chars = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (!(chars[fast] == ' ' && (fast == 0 || chars[fast - 1] == ' '))) {
                chars[slow++] = chars[fast];
            }
        }
        if (slow == 0) return "";
        return chars[slow - 1] == ' ' ? new String(chars, 0, slow - 1) : new String(chars, 0, slow);
    }

    // S2: always keep the space before word
    public String removeSpaces2(String s) {
        // corner case
        if (s == null || s.length() == 0) return s;

        char[] chars = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (!(chars[fast] == ' ' && fast == chars.length - 1 || chars[fast + 1] == ' ')){
                chars[slow++] = chars[fast];
            }
        }
        if (slow == 0) return "";
        return chars[0] == ' ' ? new String(chars, 1, slow - 1) : new String(chars, 0, slow);
    }

    // S3: use split("\\s+") + StringBuilder
    public String removeSpaces3(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        String[] array = s.split("\\s+");
        for (String str : array) {
            if (!str.equals("")) {
                sb.append(str);
                sb.append(" ");
            }
        }
        if (sb.length() != 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
