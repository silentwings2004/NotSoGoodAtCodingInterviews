package S0_Templates;

public class GetOneBit {
    public int getOneBit(int state) {
        int count = 0;
        while (state > 0) {
            count++;
            state = state & (state - 1);
        }
        return count;
    }

    // if encode is subset of state
    if (state - encode == (encode ^ state))
}