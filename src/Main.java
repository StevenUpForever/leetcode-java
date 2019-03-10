import depth_first_search.matrix.Q329LongestIncreasingPathInAMatrix;
import public_class.Interval;
import public_class.TreeNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.clumsy(10));

    }

    public int largestSumAfterKNegations(int[] A, int K) {
        if (A == null || A.length == 0) return 0;
        Arrays.sort(A);
        int index = 0, sum = 0, min = Math.abs(A[0]);
        while (index < A.length && A[index] < 0 && K > 0) {
            sum += Math.abs(A[index]);
            min = Math.min(min, Math.abs(A[index]));
            index++;
            K--;
        }
        while (index < A.length) {
            sum += A[index];
            min = Math.min(min, Math.abs(A[index]));
            index++;
        }
        if (K % 2 != 0) {
            sum -= 2 * min;
        }
        return sum;
    }

    public int clumsy(int N) {
        if (N <= 0) return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(N);
        char[] oprations = {'*', '/', '+', '-'};
        int index = 0;
        for (int i = N - 1; i >= 1; i--) {
            switch (oprations[index++]) {
                case '*':
                    stack.push(stack.pop() * i);
                    break;
                case '/':
                    stack.push(stack.pop() / i);
                    break;
                case '+':
                    stack.push(i);
                    break;
                case '-':
                    stack.push(-i);
                    break;
                    default:
                        break;
            }
            if (index > 3) index = 0;
        }
        int sum = 0;
        while (!stack.isEmpty()) sum += stack.pop();
        return sum;
    }

    public int minDominoRotations(int[] A, int[] B) {
        int possible1 = A[0], possible2 = B[0], count1 = 0, count2 = 0;
        for (int i = 0; i < A.length; i++) {
            int num1 = A[i], num2 = B[i];
            if (possible1 != num1 && possible1 != num2) possible1 = -1;
            if (possible2 != num1 && possible2 != num2) possible2 = -1;
        }
        if (possible1 == -1 && possible2 == -1) return -1;
        else if (possible1 == -1) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == B[i]) continue;
                if (A[i] == possible2) count1++;
                if (B[i] == possible2) count2++;
            }
            System.out.println("count1:" + count1);
            System.out.println("count2:" + count1);
            return Math.min(count1, count2);
        } else if (possible2 == -1) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == B[i]) continue;
                if (A[i] == possible1) count1++;
                if (B[i] == possible1) count2++;
            }
            System.out.println("count1:" + count1);
            System.out.println("count2:" + count1);
            return Math.min(count1, count2);
        } else {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == B[i]) continue;
                if (A[i] == possible1) count1++;
                if (A[i] == possible2) count2++;
            }
            System.out.println("count1:" + count1);
            System.out.println("count2:" + count1);
            return Math.min(count1, count2);
        }
    }

    private int index = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorderHelper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode bstFromPreorderHelper(int[] pre, int min, int max) {
        if (index == pre.length || pre[index] <= min || pre[index] >= max) return null;
        int val = pre[index];
        TreeNode node = new TreeNode(val);
        index++;
        node.left = bstFromPreorderHelper(pre, min, val);
        node.right = bstFromPreorderHelper(pre, val, max);
        return node;
    }

}



