package S0_Templates;

public class myPow {
    public double myPow(double x, int n) {
        long k = n;
        return k >= 0 ? helper(x, k) : 1 / helper(x, -k);
    }
    
    private double helper(double x, long k) {
        if (k == 0) return 1;
        
        double y = helper(x, k / 2);
        return k % 2 == 0 ? y * y : y * y * x;
    }
}
