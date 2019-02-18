import public_class.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     * Build List from String like [3,2,0,-4]
     * @param arrStr the array string like [3,2,0,-4]
     * @return List contains integers
     */
    public static List<Integer> buildArrayFromStr(String arrStr) {
        List<Integer> list = new ArrayList<>();
        if (arrStr == null) return list;
        if (arrStr.startsWith("[") && arrStr.endsWith("]")) {
            String[] strs = arrStr.substring(1, arrStr.length() - 1).split(",");
            for (String str: strs) list.add(Integer.parseInt(str));
        }
        return list;
    }

    public static ListNode buildList(List<Integer> list) {
        if (list == null) return null;
        ListNode head = new ListNode(0), res = head;
        for (int num: list) {
            head.next = new ListNode(num);
            head = head.next;
        }
        return res.next;
    }

    public static ListNode buildList(String arrStr) {
        return buildList(buildArrayFromStr(arrStr));
    }

    public static ListNode buildCycledList(List<Integer> list, int cycleStart) {
        ListNode head = buildList(list), temp1 = head, temp2 = head;
        while (temp1 != null && temp1.next != null) temp1 = temp1.next;
        while (cycleStart > 0) {
            temp2 = temp2.next;
            cycleStart--;
        }
        if (temp1 != null) temp1.next = temp2;
        return head;
    }

    public static ListNode buildCycledList(String arrStr, int cycleStart) {
        return buildCycledList(buildArrayFromStr(arrStr), cycleStart);
    }

}
