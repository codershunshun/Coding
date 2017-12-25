/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
      return null;
    }

    Map<RandomListNode, RandomListNode> map = new HashMap<>();

    RandomListNode dummy = new RandomListNode(0);
    dummy.next = head;

    while(head != null) {
      map.put(head, new RandomListNode(head.label));
      head = head.next;
    }

    for (RandomListNode node : map.keySet()) {
      if (node.next != null) {
        map.get(node).next = map.get(node.next);
      }

      if (node.random != null) {
        map.get(node).random = map.get(node.random);
      }
    }

    return map.get(dummy.next);
  }
}
