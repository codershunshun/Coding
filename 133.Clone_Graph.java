/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) {
      return null;
    }

    List<UndirectedGraphNode> oldNodes = getNodes(node);
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

    //copy all nodes
    for (UndirectedGraphNode oldNode : oldNodes) {
      map.put(oldNode, new UndirectedGraphNode(oldNode.label));
    }

    //copy all neighbors
    for (UndirectedGraphNode oldNode: oldNodes) {
      for (UndirectedGraphNode neighbor : oldNode.neighbors) {
        UndirectedGraphNode newNeighbor = map.get(neighbor);
        map.get(oldNode).neighbors.add(newNeighbor);
      }
    }

    return map.get(node);
  }

  private List<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
    HashSet<UndirectedGraphNode> set = new HashSet<>();
    Queue<UndirectedGraphNode> queue = new LinkedList<>();

    queue.add(node);
    set.add(node);

    while (!queue.isEmpty()) {
      UndirectedGraphNode head = queue.poll();

      for (UndirectedGraphNode n : head.neighbors) {
        if (set.contains(n)) {
          continue;
        }

        set.add(n);
        queue.add(n);
      }
    }

    return new ArrayList<UndirectedGraphNode>(set);
  }
}
