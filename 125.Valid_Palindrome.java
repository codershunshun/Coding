class Solution {
  public boolean isPalindrome(String s) {
    if (s == null) {
      return false;
    }

    if (s.length() == 0) {
      return true;
    }

    s = s.trim();

    int start = 0;
    int end = s.length() - 1;

    while (start < end) {
      char c1 = s.charAt(start);
      char c2 = s.charAt(end);

      if (!validChar(c1)) {
        start++;
      } else if (!validChar(c2)) {
        end--;
      } else {
        if (matchChar(c1, c2)) {
          start++;
          end--;
        } else {
          return false;
        }
      }
    }

    return true;
  }

  private boolean validChar(char c) {
    c = Character.toLowerCase(c);
    return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
  }

  private boolean matchChar(char c1, char c2) {
    return Character.toLowerCase(c1) == Character.toLowerCase(c2);
  }
}
