package legacy_code.legacy_code_class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/**
 * Created by ChengzhiJia on 5/29/16.
 */
public class Problem131To140 {

    /*
    133. Clone Graph
    Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


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

    /*
    Approach: key point is keep track of already visited original node and copy node
    so use HashMap to save original node label as key and copy node as value and return node
     */
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
        return cloneGraphHelper(node, map);
    }

    private UndirectedGraphNode cloneGraphHelper(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> map) {
        if (node == null) return null;
        if (map.containsKey(node.label)) return map.get(node.label);
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(clone.label, clone);
        for (UndirectedGraphNode neighbor: node.neighbors) {
            clone.neighbors.add(cloneGraphHelper(neighbor, map));
        }
        return clone;
    }
    /*
    138. Copy List with Random Pointer
    A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
     */
    private class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode temp = head;
        while (temp != null) {
            map.put(temp, new RandomListNode(temp.label));
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

    public RandomListNode copyRandomList2(RandomListNode head) {
        RandomListNode result = new RandomListNode(0);
        RandomListNode temp = result;
        RandomListNode tempHead = head;
        ArrayList<RandomListNode> tempList = new ArrayList<>();
        ArrayList<RandomListNode> tempList2 = new ArrayList<>();
        while (tempHead != null) {
            temp.next = new RandomListNode(tempHead.label);
            temp = temp.next;
            tempList.add(temp);
            tempList2.add(tempHead);
            tempHead = tempHead.next;
        }
        temp = result.next;
        tempHead = head;
        while (tempHead != null) {
            if (tempHead.random != null) temp.random = tempList.get(tempList2.indexOf(tempHead.random));
            temp = temp.next;
            tempHead = tempHead.next;
        }
        return result.next;
    }

    /*
    139. Word Break
    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.

Show Company Tags
Show Tags
Show Similar Problems
     */
    /*
    Approach: DP problem, which M[0] = dict.contains(first char), M[i] represent if subString(0, i + 1) can be word break
    case 1: when list contains substring to i, M[i] = true
    case 2: when for loop 0 to i, if M[0, j] is true and list contains [j + 1, i], M[i] is true
    otherwise is false
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null && wordDict == null) return true;
        else if (s.length() == 0 && wordDict.size() == 0) return true;
        else if (s.length() == 0) return false;
        else if (wordDict == null || wordDict.size() == 0) return false;
        HashMap<String, Boolean> map = new HashMap<>();
        for (String str: wordDict) {
            map.put(str, true);
        }
        int len = s.length();
        boolean[] verify = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (map.get(s.substring(0, i + 1)) != null) {
                verify[i] = true;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (verify[j] && map.get(s.substring(j + 1, i + 1)) != null) {
                    verify[i] = true;
                }
            }
        }
        return verify[len - 1];
    }
}
