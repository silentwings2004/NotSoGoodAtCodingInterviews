package S18_ProbabilityRandomization;
import java.util.*;
public class Q1_BiasedCoin {
    /**
     * Q1 Given a biased coin, face up with p, face down with (1-p)   不等概率 如何利用这两个做出等概率1/2?
     * 一次
     * 1	p
     * 0	(1-p)
     *
     * 两次
     * 1 1	p * p
     * 1 0	p * (1-p)	return 1
     * 0 1	(1-p) * p	return 0
     * 0 0	(1-p)*(1-p)
     *
     * 定性的基本想法，采用的基本方案就是all possible的概率最后的结果如果有哪些部分不想要，我采用的就是再来一轮，这个不算，直到它出来的
     * 结果落在我想要的区间当中，我就结束。就是用这种方案来保证等概率且最后结果各自为1/2的。
     *
     * @return
     */
    int yao()  // call完之后，以p的概率返回1，以1-p的概率返回0
    public int myYao() {
        int val1 = yao();
        int val2 = yao();
        if (val1 == 1 && val2 == 0) {
            return 1;
        }
        if (val1 == 0 && val2 == 1) {
            return 0;
        }
        return myYao(); // 以recursion来表示这里再来一轮的意思
    }
    // 理论上有stackoverflow的可能性，但是实际上这个概率非常非常低，几乎不可能
    // 只能call到stackoverflow才能结束，所以base case在这里就不写了
    // 如果被challenge recursion stackoverflow层数不够的话，你就改写成while loop，最多就是时间长，最终总能运算出最后的结果
    // return 0或者return 1。

}
