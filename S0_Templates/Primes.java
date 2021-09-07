package S0_Templates;
import java.util.*;

public class Primes {
    private boolean isPrime(int k) {
        if (k < 2) return false;
        if (k % 2 == 0) return k == 2;
        for (int i = 3; i * i <= k; i += 2) {
            if (k % i == 0) return false;
        }
        return true;
    }

    // 给定一个上限n，求出这个上限以内所有的prime numbers
    private int[] eratosthenes(int n) { 
        int[] q = new int[n + 1];
        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
            if (q[i] == 0) {
                int j = i * 2;
                while (j < n) {
                    q[j] = 1;
                    j += i;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (q[i] == 0) primes.add(i);
        }
        int[] res = new int[primes.size()];
        for (int i = 0; i < primes.size(); i++) res[i] = primes.get(i);
        return res;
    }

    // 同上一个模板，分解质因数
    private List<Integer> primeDecompose(int n) {
        List<Integer> res = new ArrayList<>();

        int factor = 2;
        while (n >= factor * factor) {
            if (n % factor == 0) {
                res.add(factor);
                n /= factor;
            } else factor += 1;
        }
        res.add(n);
        return res;
    }
}
