package S0_Templates;
public class Inverse_Element {
    long N = (long)(1e6 + 7), mod = 998244353;
    // Linear method to compute inv[i]
    public void main() {
        long[] inv = new long[(int)N];
        inv[1] = 1;
        for (int i = 2; i < N; i++) {
            inv[i] = (mod - mod / i) * inv[(int)mod % i] % mod;
        }

        // res *= inv[n] % mod; // res = (res / n) % mod;
    }

    /*********************************/
    // Qucik Pow Method, based on Fermat's little theorem

    public long quickPow(long x, long y) {
        long res = 1, cur = x;
        while (y > 0) {
            if ((y & 1) != 0) res = (long) res * cur % mod;
            cur = (long)cur * cur % mod;
            y >>= 1;
        }
        return res;
    }
    
    long inv(long x) {
        return quickPow(x, mod - 2);
    }

    /*****************************/

    public long inv2(long x) {
        long s = 1;
        for (; x > 1; x = mod % x) {
            s = s * (mod - mod / x) % mod;
        }
        return s;
    }
}


/**
逆元

一般地，我们说如果a和b满足这样的性质：

x / a ≡ x * b (mod M)
从形式上来看，b好像就与1/a（在同余的意义上）等价，所以我们称b就是a的逆元，记做 b = inv(a)（反之也成立）。
对于a而言，存在逆元的充要条件是a与M互质。当然，我们做题时M一般都已经是质数。

逆元的计算方法
方法1：快速幂法
根据费马小定理，我们有

inv(a) ≡ a ^ (M-2)  (mod M)
显然，我们需要利用快速幂来计算这个数。

方法2：线性求逆元
如果我们想求1,2,3...N 每个数的逆元：

const ll N = 1e6+7, M = 998244353;
ll inv[N];
int i;
for(inv[1]=1, i=2; i<N; ++i)
    inv[i] = (M - M/i) * inv[M % i] % M
逆元的一些性质
逆元的计算有如下的性质：

x1/y1 + x2/y2 ≡ x1 * y1^-1 + x2 * y2^-1 (mod M)
x1/y1/y2 ≡ x1 * y1^-1 * y2^-1 (mod M)
 */