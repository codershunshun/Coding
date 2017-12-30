/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    if (root.left == null && root.right == null) {
      result.add(root.val + "");
    }

    List<String> leftPath = binaryTreePaths(root.left);
    List<String> rightPath = binaryTreePaths(root.right);

    for (String path : leftPath) {
      result.add(root.val + "->" + path);
    }

    for (String path : rightPath) {
      result.add(root.val + "->" + path);
    }

    return result;
  }
}

//dfs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    dfs(result, root.val + "", root);

    return result;
  }

  private void dfs(List<String> result, String path, TreeNode root) {
    if (root.left == null && root.right == null) {
      result.add(path);
      return;
    }

    if (root.left != null) {
      dfs(result, path + "->" + root.left.val, root.left);
    }

    if (root.right != null) {
      dfs(result, path + "->" + root.right.val, root.right);
    }
  }
}

//Stack (iterative)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;
    TreeNode lastVisit = null;
    List<TreeNode> paths = new ArrayList<>();

    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        paths.add(node);
        node = node.left;
      }

      node = stack.peek();

      if (node.left == null && node.right == null) {
        StringBuilder sb = new StringBuilder();

        for (TreeNode n : paths) {
          sb.append(n.val + "");
          sb.append("->");
        }
        sb.setLength(sb.length() - 2);

        result.add(sb.toString());
      }

      if (node.right == null || node.right == lastVisit) {
        lastVisit = node;
        stack.pop();
        paths.remove(paths.size() - 1);
        node = null;
      } else {
        node = node.right;
      }
    }

    return result;
  }
}

//BFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> results = new ArrayList<>();

    if (root == null) {
      return results;
    }

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    HashMap<TreeNode, TreeNode> map = new HashMap<>();

    queue.add(root);
    map.put(root, null);

    while (!queue.isEmpty()) {
      TreeNode head = queue.poll();

      if (head.left == null && head.right == null) {
        results.add(getPath(map, head));
      }

      if (head.left != null) {
        queue.add(head.left);
        map.put(head.left, head);
      }

      if (head.right != null) {
        queue.add(head.right);
        map.put(head.right, head);
      }
    }

    return results;
  }

  private String getPath(HashMap<TreeNode, TreeNode> map, TreeNode node) {
    String s = "";

    while (node != null) {
      s = "->" + node.val + s;
      node = map.get(node);
    }

    return s.substring(2);
  }
}

