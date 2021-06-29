package Templates;

public class CreatePalindrome {
    public long createEvenPalindrome(long i) { // 10 -> 1001
        String s = String.valueOf(i);
        StringBuilder sb = new StringBuilder(s);
        return Long.valueOf(s + sb.reverse().toString());
    }

    public long createOddPalindrome(long i) { // 100 -> 10001
        String s = String.valueOf(i);
        StringBuilder sb = new StringBuilder(s.substring(0, s.length() - 1));
        return Long.valueOf(s + sb.reverse().toString());
    }

    public long constructPalin(int i, int j) {
        long y = i;
        // 构造奇数位的回文数
        if (j == 1) i /= 10;
        while (i > 0) {
            y = y * 10 + i % 10;
            i /= 10;
        }
        return y;
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
