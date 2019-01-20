import data_structure.LRUCache;
import depth_first_search.ninety_nine_cents.CoinChange;
import public_class.TreeNode;

public class Main {
    public static void main(String[] args) {
//        NumberOfIslands obj = new NumberOfIslands();
//        System.out.print(obj.numIslands2(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},
//                {'1','1','0','0','0'},{'0','0','0','0','0'}}));

//        ExpressiveWords obj = new ExpressiveWords();
//        for (ExpressiveWords.Extension extension: obj.allExtensions("heeellooo")) {
//            System.out.print(extension.start + "->" + extension.end);
//            System.out.println();
//        }

        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    public int[] sortedSquares(int[] A) {
        if (A == null) return new int[0];
        int[] res = new int[A.length];
        int l = 0, r = A.length - 1;
        int index = res.length - 1;
        while (l <= r) {
            if (Math.abs(A[l]) > Math.abs(A[r])) {
                res[index--] = A[l] * A[l];
                l++;
            } else {
                res[index--] = A[r] * A[r];
                r--;
            }
        }
        return res;
    }

    public int maxTurbulenceSize(int[] A) {
        if (A == null) return 0;
        int maxLen = 1, curLen = 1;
        int flag = 1;
        for (int i = 0; i < A.length - 1; i++) {
            int tempFlag;
            if (A[i + 1] > A[i]) tempFlag = 1;
            else if (A[i + 1] < A[i]) tempFlag = -1;
            else tempFlag = 0;
            if (i == 0 || (tempFlag != 0 && tempFlag == 0 - flag)) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = tempFlag == 0 ? 1 : 2;
            }
            flag = tempFlag;
        }
        return Math.max(curLen, maxLen);
    }

    private int steps = 0;
    public int distributeCoins(TreeNode root) {
        distributeCoinsHelper(root);
        return steps;
    }

    private int distributeCoinsHelper(TreeNode root) {
        if (root == null) return 0;
        int left = distributeCoinsHelper(root.left);
        int right = distributeCoinsHelper(root.right);
        steps += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }


}

