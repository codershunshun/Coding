public class Solution {
    public boolean isMatch(String s, String p) {
        return helper(s, p, 0, 0);
    }

    private boolean helper(String s, String p, int i, int j) {
        if (j == p.length()) {
            return i == s.length();
        }

        if (j == p.length() - 1) {
            return (i == s.length() - 1 && matchChar(s, p, i, j));
        }

        if (j < p.length() - 1 && p.charAt(j + 1) != '*') {
            if (i == s.length()) {
                return false;
            }

            if (matchChar(s, p, i, j)) {
                return helper(s, p, i + 1, j + 1);
            } else {
                return false;
            }
        }

        while (i < s.length() && j < p.length() && matchChar(s, p, i, j)) {
            if (helper(s, p, i, j + 2)) {
                return true;
            }
            i++;
        }

        return helper(s, p, i, j + 2);
    }

    private boolean matchChar(String s, String p, int i, int j) {
        return (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
    }
}
