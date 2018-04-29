package legacy_code.legacy_code_class;

import java.util.HashMap;

/**
 * Created by stevenjia on 8/4/16.
 */
public class Problem281To290 {

    /*
    285. Inorder Successor in bst
    Given a binary search tree and a node in it, find the in-order successor of that node in the bst.

Note: If the given node has no in-order successor in the tree, return null.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.val <= p.val) return inorderSuccessor(root.right, p);
         else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }

//    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//        TreeNode res = null;
//        while(root!=null) {
//            if(root.val > p.val) {
//                res = root;
//                root = root.left;
//            }
//            else root = root.right;
//        }
//        return res;
//    }

    /*
    288. Unique Word Abbreviation
    An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example:
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
     */
    public class ValidWordAbbr {

        HashMap<String, String> map = new HashMap<>();

        public ValidWordAbbr(String[] dictionary) {
            for(String str:dictionary){
                String key = convertString(str);
                if(map.containsKey(key) && !map.get(key).equals(str)) {
                    map.put(key, "");
                } else {
                    map.put(key, str);
                }
            }
        }

        public boolean isUnique(String word) {
            String key = convertString(word);
            return !map.containsKey(key) || map.get(key).equals(word);
        }

        private String convertString(String str){
            if(str.length()<=2) return str;
            return str.charAt(0)+Integer.toString(str.length()-2)+str.charAt(str.length()-1);
        }

    }


}
