package legacy_code.legacy_code_class;

/**
 * Created by ChengzhiJia on 6/19/16.
 */

public class ListNode {
    public int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}