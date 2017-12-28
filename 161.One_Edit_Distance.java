//Given two strings S and T, determine if they are both one edit distance apart.
class Solution {
  public boolean isOneEditDistance(String s, String t) {
    int len1 = s.length(), len2 = t.length();
    int len = Math.min(len1, len2);

    for (int i = 0; i < len; i++) {
      if (s.charAt(i) != t.charAt(i)) {
        if (len1 == len2) {
          return s.substring(i + 1).equals(t.substring(i + 1));
        } else if (len1 < len2) {
          return s.substring(i).equals(t.substring(i + 1));
        } else {
          return s.substring(i + 1).equals(t.substring(i));
        }
      }
    }

    return Math.abs(len1 - len2) == 1;
  }
}

//Edit distance
class Solution {
  public int minDistance(String word1, String word2) {
    // write your code here
    int len1 = word1.length();
    int len2 = word2.length();

    int[][] dp = new int[len1 + 1][len2 + 1];

    for (int i = 0; i <= len1; i++) {
      for (int j = 0; j <= len2; j++) {
            if (i == 0) {
                dp[i][j] = j;
            } else if (j == 0) {
                dp[i][j] = i;
            } else {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                    dp[i][j]++;
                }
            }
        }
    }
    return dp[len1][len2];
  }
}
