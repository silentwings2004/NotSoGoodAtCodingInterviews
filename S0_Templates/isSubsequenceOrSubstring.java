package S0_Templates;

public class isSubsequenceOrSubstring {
    // check if string a is a subsequence of string b
    public boolean isSubsequence(String a, String b) {
        int i = 0, j = 0;
        int m = a.length(), n = b.length();

        while (i < m && j < n) {
            if (a.charAt(i) == b.charAt(j)) i++;
            j++;
        }
        return i == m;
    }

    // check if string a is a substring of string b
    public static boolean isSubstring(String a, String b) {
        int idx = b.indexOf(a);
        System.out.println(idx);
        return idx != -1;
    }
    public static void main(String[] args) {
        String a = "abc", b = "djgklsdabcdjfkdsljfdkfjs";
        System.out.println(isSubstring(a, b));
    }
}
