
import public_class.TreeNode;
import string.sliding_window.MinimumWindowSubstring;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.queryString("0110", 4));
    }

    public boolean canThreePartsEqualSum(int[] A) {
        if (A == null || A.length < 3) return false;
        for (int i = 1; i < A.length; i++) {
            A[i] += A[i - 1];
        }
        int total = A[A.length - 1];
        for (int i = 0; i < A.length - 2; i++) {
            int sum = A[i];
            for (int j = i + 1; j < A.length - 1; j++) {
                if (A[j] - A[i] == sum && total - A[j] == sum) return true;
            }
        }
        return false;
    }

    public int smallestRepunitDivByK(int K) {
        //1, 3, 7, 9
        int divd = K % 10;
        if (divd != 1 && divd != 3 && divd != 7 && divd != 9) return -1;
        Set<Integer> set = new HashSet<>();
        int num = 1, len = 1;
        while (true) {
            num = num % K;
            if (num == 0) return len;
            if (!set.add(num)) return -1;
            num = num * 10 + 1;
            len++;
        }
    }

    public int maxScoreSightseeingPair(int[] A) {
        List<Integer>[] nums = new List[1001];
        for (int i = 0; i < A.length; i++) {
            if (nums[A[i]] == null) nums[A[i]] = new ArrayList<>();
            nums[A[i]].add(i);
        }
        int max = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] == null) continue;
            for (int j = i; j > 0; j--) {
                if (nums[j] == null || i + j < max) continue;
                if (j == i) {
                    List<Integer> list = nums[j];
                    if (list.size() < 2) continue;
                    int minLen = list.get(list.size() - 1) - list.get(0);
                    for (int k = 1; k < list.size(); k++) {
                        minLen = Math.min(minLen, list.get(k) - list.get(k - 1));
                    }
                    max = Math.max(max, 2 * j - minLen);
                } else {
                    List<Integer> iList = nums[i], jList = nums[j];
                    int minLen = Integer.MAX_VALUE;
                    for (int m = 0; m < iList.size(); m++) {
                        for (int n = 0; n < jList.size(); n++) {
                            minLen = Math.min(minLen, Math.abs(iList.get(m) - jList.get(n)));
                        }
                    }
                    max = Math.max(max, i + j - minLen);
                }
            }
        }
        return max;
    }

    public boolean queryString(String S, int N) {
        List<Integer> list = new ArrayList<>();
        int digit = -1, curLen = 0;
        int i = 0;
        while (S.charAt(i) == '0') i++;
        while (i < S.length()) {
            int cur = S.charAt(i) - '0';
            if (digit != cur) {
                if (digit != -1) list.add(curLen);
                digit = cur;
                curLen = 1;
            } else curLen++;
            i++;
        }
        list.add(curLen);
        List<Integer> digits = new LinkedList<>();
        digits.add(0);
//        printList(list);
        for (int num = 1; num <= N; num++) {
            addOne(digits);
            List<Integer> countList = convertList(digits);
//            printList(countList);
            if (!isSubList(list, countList)) {
                return false;
            }
        }
        return true;
    }

    private void addOne(List<Integer> digits) {
        int carry = 1;
        for (int i = digits.size() - 1; i >= 0; i--) {
            int sum = digits.get(i) + carry;
            digits.set(i, sum % 2);
            carry = sum / 2;
            if (carry == 0) break;
        }
        if (carry == 1) digits.add(0, 1);
    }

    private boolean isSubList(List<Integer> list, List<Integer> digitSub) {
        if (list.size() < digitSub.size()) return false;
        outer: for (int i = 0; i <= list.size() - digitSub.size(); i += 2) {
            int j = 0;
            for (; j < digitSub.size(); j++) {
                if (j == 0 || j == digitSub.size() - 1) {
                    if (list.get(i + j) < digitSub.get(j)) continue outer;
                } else {
                    if (!list.get(i + j).equals(digitSub.get(j))) continue outer;
                }
            }
            return true;
        }
        return false;
    }

    private List<Integer> convertList(List<Integer> list) {
        List<Integer> convert = new ArrayList<>();
        int digit = -1, len = 0;
        for (int num: list) {
            if (digit != num) {
                if (digit != -1) convert.add(len);
                digit = num;
                len = 1;
            } else len++;
        }
        convert.add(len);
        return convert;
    }

    public void printList(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        for (int num: list) {
            builder.append(num);
            builder.append(",");
        }
        System.out.println(builder);
    }


}



