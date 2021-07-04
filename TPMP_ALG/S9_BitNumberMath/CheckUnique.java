package S9_BitNumberMath;
import java.util.*;
import java.util.logging.Logger;

public class CheckUnique {
    /**
     * {a, b, +, %, d, e} check这个char[]是否是unique的，有重复1→ false，无重复0 → true
     * @param chars
     * @return
     */
    public boolean checkUnique(char[] chars){
        try {
            // corner case
            if (chars == null) {
                throw new IllegalArgumentException(("Invalid input!"));
            }
            int[] bits = new int[8];
            for (char c : chars) {
                int row = c / 32;
                int col = c % 32;
                // use left shift to avoid x modification, always use != 0 to check thus avoid potential bugs.
                if ((bits[row] & (1 << col)) != 0) return false;  // check 0
                bits[row] |= 1 << col;   // set 1
            }
            return true;
        } catch (IllegalArgumentException e) {
            // do some error handling here
            System.out.println(e.getMessage());
            e.printStackTrace();
            Logger.log(e);
        } catch (Exception e) {
            // do sth
        }
    }
}
