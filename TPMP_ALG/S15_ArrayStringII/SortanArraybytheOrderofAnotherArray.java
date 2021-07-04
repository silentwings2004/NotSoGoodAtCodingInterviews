package S15_ArrayStringII;
import java.util.*;
public class SortanArraybytheOrderofAnotherArray {
    /**
     * Sort an array by the order defined by another array
     * integer: comparable / comparator
     * array: 2 1 3 5 7 2 9 7 2 6 → 9 3 7 7 1  2 2 2 5 6
     * pattern: 9 3 7
     * @param array
     * @param map
     * @return
     */
    public int[] sort(Integer[] array, Map<Integer, Integer> map) { // Integer[]
        // corner case
        Arrays.sort(array, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                if (map.containsKey(a) && map.containsKey(b)) {
                    return map.get(a) - map.get(b);
                } else if (map.containsKey(a)) {
                    return -1;
                } else if (map.containsKey(b)) {
                    return 1;
                } else {
                    return a - b; // auto-unboxing --> a.compareTo(b)
                }
            }
        });
//        return array; //TODO: Integer[] --> int[] to return
    }
}
