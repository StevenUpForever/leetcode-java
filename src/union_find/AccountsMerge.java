package union_find;

import java.util.*;

public class AccountsMerge {

    //TAG: Facebook
    //TAG: Union find
    //Difficulty: medium

    /**
     * 721. Accounts Merge
     * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
     *
     * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
     *
     * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
     *
     * Example 1:
     * Input:
     * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
     * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
     * Explanation:
     * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
     * The second John and Mary are different people as none of their email addresses are used by other accounts.
     * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
     * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
     * Note:
     *
     * The length of accounts will be in the range [1, 1000].
     * The length of accounts[i] will be in the range [1, 10].
     * The length of accounts[i][j] will be in the range [1, 30].
     */

    /*
    Solution:
    key point is when first add list0(email0, email1), then list1(email2, email3), next list2(email1, email2),
    all of these 3 list need merge together, so cannot iteratively merge them, e.g. list0 and list1 will be two separate
    lists, and when list2c comes in, will merge to list0 but no list1

    So Union find is the solution to solve this problem
    sort the acts emails
    1. initially set parents to email itself, and associate email with owner in a map
    2. set all emails' parent to first email in current list
    3. loop the accounts again, put all emails to it's top parent key set pair in map
    4. put all sets along with owner to results from owner -> set map

    Time: O(mn^2) find may need O(n) so need O(n^2) in put one account info
    Space: O(n)
     */

    public List<List<String>> accountsMerge(List<List<String>> acts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : acts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));
                owner.put(a.get(i), a.get(0));
            }
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++)
                parents.put(find(a.get(i), parents), p);
        }
        for(List<String> a : acts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
            for (int i = 1; i < a.size(); i++)
                unions.get(p).add(a.get(i));
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList<>(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }
    //The method going to find top parent of s which when p.get(s).equals(s)
    private String find(String s, Map<String, String> p) {
        return p.get(s).equals(s) ? s : find(p.get(s), p);
    }

}
