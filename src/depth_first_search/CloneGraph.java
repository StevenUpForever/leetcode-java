package depth_first_search;

import public_class.UndirectedGraphNode;

import java.util.HashMap;

public class CloneGraph {

    //TAG: Uber
    //TAG: DFS

    /**
     * 133. Clone Graph
     * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


     OJ's undirected graph serialization:
     Nodes are labeled uniquely.

     We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
     As an example, consider the serialized graph {0,1,2#1,2#2,2}.

     The graph has a total of three nodes, and therefore contains three parts as separated by #.

     First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
     Second node is labeled as 1. Connect node 1 to node 2.
     Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
     Visually, the graph looks like the following:

     1
     / \
     /   \
     0 --- 2
     / \
     \_/

     */

    /**
     * Solution:
     * Base case: when node == null or find node in map, return node/map.get(node)
     * Recursion rule: when node not get from map, copy new one newNode, recursion each orginNode's neighbors to newNode
     *
     * Time: O(n)
     * Space: O(n)
     */

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return cloneGraphHelper(node, new HashMap<>());
    }

    private UndirectedGraphNode cloneGraphHelper(UndirectedGraphNode node,
                                  HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (node == null) return node;
        if (map.containsKey(node)) return map.get(node);
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);
        for (UndirectedGraphNode nei: node.neighbors) {
            newNode.neighbors.add(cloneGraphHelper(nei, map));
        }
        return newNode;
    }


}
