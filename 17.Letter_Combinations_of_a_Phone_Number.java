class Solution {
  public List<String> letterCombinations(String digits) {
    String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> results = new ArrayList<>();

    if (digits.length() == 0) {
      return results;
    }

    int startIndex = 0;
    String str = "";

    dfs(results, phone, digits, 0, str);

    return results;
  }

  private void dfs(List<String> results, String[] phone, String digits, int startIndex, String str) {
    if (startIndex == digits.length()) {
      results.add(str);
      return;
    }

    int index = digits.charAt(startIndex) - '0';
    for (char c : phone[index].toCharArray()) {
      dfs(results, phone, digits, startIndex + 1, str + c);
    }
  }
}
