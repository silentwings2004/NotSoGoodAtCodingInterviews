package S18_ProbabilityRandomization;
import java.util.*;
public class Q2_Random5toRandom7 {
    /**
     * random5 → random7     randomk → [0, k)
     * 等概率生成左闭右开区间从0到k这k个数字当中的任何一个，也就是其中任何一个数字的概率是1/k
     * random5 → random25
     * 0
     * 1
     * 2
     * 3
     * 4
     *
     * random5 + random5
     * 0        0
     * 1		1
     * 2		2
     * 3		3
     * 4		4
     *
     * random5 + random5 ＋ 5
     * 0        5
     * 1		6
     * 2		7
     * 3		8
     * 4		9
     *
     * random5 → random25 → random21 → random7
     * random5 * 5 + random5    // *4 → 有重复，且range不对； *6 → 有遗漏，5， 11，...丢失了
     * 0        0
     * 5		1
     * 10		2
     * 15		3
     * 20		4
     *
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24   // 21 ~24 while loop再来一轮
     * /3	%7
     * 把range比较大的等概率的21个数字映射到range比较小的等概率的7个数字，这就是hashcode的映射方案，只不过这种映射不是
     * consistent hashing，是一个general的hash方案。
     * @return
     */
    // S1: Recursion
    public int random7() {
        int val = random5() * 5 + random5();
        if (val > 20) return random7();
        return val / 3;  // or return val % 7
    }

    // S2: while loop
    public int random72() {
        while (true) {
            int val = random5() * 5 + random5();
            if (val > 20) continue;
            return val % 7;
        }
    }
}
