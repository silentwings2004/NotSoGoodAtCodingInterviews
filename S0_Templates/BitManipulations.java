package S0_Templates;

public class BitManipulations {
    // S1
    public int getOneBit(int state) {
        int count = 0;
        while (state > 0) {
            count++;
            state = state & (state - 1);
        }
        return count;
    }

    // S2: get 1 bit
    // Integer.bitCount(state);

    // if encode is subset of state
    if (state - encode == (encode ^ state))


    // Gospers-Hack -> interate all the m-bit state where there are k 1-bits
    int state = (1 << k) - 1;
    while (state < (1 << m * n)) {
        // do something(state)
        // eg: if (check(mat, state)) return k;
        int c = state & -state;
        int r = state + c;
        state = (((r ^ state) >> 2) / c) | r; // k 必须从1开始
    }

    // iterate subset
    for (int subset = state; subset > 0; subset = (subset - 1) & state) {
        // DoSomething(subset);
    }


}