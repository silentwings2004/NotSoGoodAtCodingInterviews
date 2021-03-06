package S0_Templates;

public class Palindrome {
    public long constructPalin(long x, int type) {
        long y = x;
        if (type == 1) x /= 10; // type 0: 构造偶数位回文数   type 1: 构造奇数位回文数
        while (x > 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }

    public long createPalin(long i) { // construct palindrom of odd digits
        String s = String.valueOf(i);
        String s1 = s.substring(0, s.length() - 1);
        StringBuilder sb = new StringBuilder(s1);
        s1 = sb.reverse().toString();
        s += s1;
        return Long.valueOf(s);
    }

    public boolean isPalin(long x) {
        String s = String.valueOf(x);
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
