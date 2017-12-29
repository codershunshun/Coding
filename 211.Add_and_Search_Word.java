class TrieNode {
  char c;
  boolean hasWord;
  HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();

  TrieNode() {}

  TrieNode(char c) {
    this.c = c;
  }
}

class Trie {
  TrieNode root;

  Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode current = root;

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);

      if(!current.children.containsKey(c)) {
        current.children.put(c, new TrieNode(c));
      }

      current = current.children.get(c);

      if (i == word.length() - 1) {
        current.hasWord = true;
      }
    }
  }

  public boolean search(String word) {
    return find(root, 0, word);
  }

  private boolean find(TrieNode current, int index, String word) {
    if (index == word.length()) {
      if (current.hasWord) {
        return true;
      }
      return false;
    }

    char c = word.charAt(index);

    if (current.children.containsKey(c)) {
      return find(current.children.get(c), index + 1, word);
    } else if (c == '.') {
      for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
        if (find(entry.getValue(), index + 1, word)) {
          return true;
        }
      }
      return false;
    } else {
      return false;
    }
  }

}

public class WordDictionary {
  Trie tree;

  /** Initialize your data structure here. */
  public WordDictionary() {
    tree = new Trie();
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    tree.insert(word);
  }

  /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
  public boolean search(String word) {
    return tree.search(word);
  }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
