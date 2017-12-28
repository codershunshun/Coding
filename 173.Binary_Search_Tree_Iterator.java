/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
  Stack<TreeNode> stack;

  public BSTIterator(TreeNode root) {
    stack = new Stack<TreeNode>();
    pushAll(root);
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    //检查可行性并且准备next值
    return !stack.isEmpty();
  }

  /** @return the next smallest number */
  public int next() {
    TreeNode node = stack.pop();
    pushAll(node.right);

    return node.val;

  }

  private void pushAll(TreeNode node) {
    while (node != null) {
      stack.push(node);
      node = node.left;
    }
  }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

//用Stack实现tree 的 preorder, inorder, postorder traversal
class Solution {
  public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
    List<Integer> results = new ArrayList<>();

    if (root == null) {
        return results;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;

    while(node != null || !stack.isEmpty()) {
      while (node != null) {
        results.add(node.val);
        stack.push(node);
        node = node.left;
      }

      if (!stack.isEmpty()) {
        node = stack.pop();
        node = node.right;
      }
    }
    return results;
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> results = new ArrayList<>();

    if (root == null) {
      return results;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;

    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      node = stack.pop();
      results.add(node.val);
      node = node.right;
    }

    return results;
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> results = new ArrayList<>();

    if (root == null) {
      return results;
    }

    Stack<TreeNode> stack = new Stack<>();

    // 设置node为一个游标
    TreeNode node = root;
    // lastVisit代表当前可以输出的node
    TreeNode lastVisit = null;

    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      node = stack.peek();

      if (node.right == null || node.right == lastVisit) {
        lastVisit = node;
        stack.pop();
        results.add(node.val);
        node = null;
      } else {
        node = node.right;
      }
    }

    return results;
  }
}
