//Return all possible results -> DFS
//1. 写个基础版的DFS
// 定义个dfs函数
// "catsanddog" -> 以"c"开头 dfs("atsanddog") if "c" 在字典里
//              -> 以"ca"开头 dfs("tsanddog") if "ca" 在字典里
//              ...
// dfs 过程中用path记录所有的string
// dfs的出口 -> 当遍历到最后一位字符时(startIndex)，将path中的string合并成一个result 加入到results中 return

// 2. 动规 + DFS
// 利用动规来判断是否有解，这样在DFS的时候可以提前返回。
// 根据DFS的定义，每次是对从字符串中的第i位到length - 1位进行递归，我们只需要判断其是否有解即可
// 由此可以定义一个DP方程canBreak[i] 表示字符串从第i位到length - 1位是否可分。注意这里canBreak的size是length + 1，即canBreak[length]代表一个空字符串做为base case是可分的。 Why? 因为dp[length - 1] 的值是根据dp[length] 和 字典中是否有s.substring(length - 1, length)的值来判定的。所以必须设置base case 空字符是可分的。

class Solution {
  public List<String> wordBreak(String s, List<String> wordDict) {
    List<String> results = new ArrayList<>();

    if (s == null || s.length() == 0) {
      return results;
    }

    List<String> path = new ArrayList<>();

    dfs(s, wordDict, results, path, 0);

    return results;
  }

  private void dfs(String s, List<String> wordDict, List<String> results, List<String> path, int startIndex) {
    if (startIndex == s.length()) {
      StringBuilder sb = new StringBuilder();

      for (String str : path) {
        sb.append(str);
        sb.append(" ");
      }

      results.add(sb.toString().trim());
      return;
    }

    for (int i = startIndex; i < s.length(); i++) {
      String str = s.substring(startIndex, i + 1);
      if (wordDict.contains(str)) {
        path.add(str);
        dfs(s, wordDict, results, path, i + 1);
        path.remove(path.size() - 1);
      }
    }
  }
}


class Solution {
  public List<String> wordBreak(String s, List<String> wordDict) {
    List<String> results = new ArrayList<>();

    if (s == null || s.length() == 0) {
      return results;
    }

    List<String> path = new ArrayList<>();
    //状态方程
    boolean[] canBreak = new boolean[s.length() + 1];
    canBreak[s.length()] = true;

    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i; j < s.length(); j++) {
        String str = s.substring(i, j + 1);
        if (wordDict.contains(str) && canBreak[j + 1]) {
          canBreak[i] = true;
          break;
        }
      }
    }

    dfs(s, wordDict, results, path, 0, canBreak);

    return results;
  }

  private void dfs(String s, List<String> wordDict, List<String> results, List<String> path, int startIndex, boolean[] canBreak) {
    //剪枝
    if (!canBreak[startIndex]) {
      return;
    }

    if (startIndex == s.length()) {
      StringBuilder sb = new StringBuilder();

      for (String str : path) {
        sb.append(str);
        sb.append(" ");
      }

      results.add(sb.toString().trim());
      return;
    }

    for (int i = startIndex; i < s.length(); i++) {
      String str = s.substring(startIndex, i + 1);
      if (wordDict.contains(str)) {
        path.add(str);
        dfs(s, wordDict, results, path, i + 1, canBreak);
        path.remove(path.size() - 1);
      }
    }
  }
}
