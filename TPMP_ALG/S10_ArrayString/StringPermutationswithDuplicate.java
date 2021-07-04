package S10_ArrayString;
import java.util.*;
public class StringPermutationswithDuplicate {
    /**
     * input "abbbc"  adjacent 带重复，可以不sorted
     *                            {1, 2a, 2b, 2c, 3}					n
     * 		12223 		21223 	\22123 \22213	 32221	  剪枝跳for loop	n-1 * n
     *             12223   13222       21223 22123 23221  32221 31222
     *                                           22123
     * @param s
     * @return
     */
    public List<String> permuteUnique(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        char[] array = s.toCharArray();
        permutation(array, result, 0);
        return result;
    }

    private void permutation(char[] array, List<String> result, int index) {
        if (index == array.length) {
            result.add(new String(array, 0, array.length));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = index; i < array.length; i++) {
            if (set.add(array[i])) {
                swap(array, index, i);
                permutation(array, result, index + 1);
                swap(array, index, i);
            }
        }
    }

    private void swap(char[] array, int i, int j) {
        char count = array[i];
        array[i] = array[j];
        array[j] = count;
    }
}
