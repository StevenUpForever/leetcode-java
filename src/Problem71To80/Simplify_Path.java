package Problem71To80;

import java.util.*;

public class Simplify_Path {

    /**
     * 71. Simplify Path
     * Given an absolute path for a file (Unix-style), simplify it.

     For example,
     path = "/home/", => "/home"
     path = "/a/./b/../../c/", => "/c"
     click to show corner cases.

     Corner Cases:
     Did you consider the case where path = "/../"?
     In this case, you should return "/".
     Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
     In this case, you should ignore redundant slashes and return "/home/foo".
     */

    /**
     * Approach:
     * Rules: when met . or .. skip, and for .. need to goto prefix path like /a/../c = /c
     */

    /**
     * Solution:
     * path is separated by /, so split path to a path array at first
     * Reserve / in path to append is complicated (may need extra / or delete extra /), so just append words in split array, and append / before or after the word
     * Key point: due to we need add path (except .. and .) or delete last paths when met ../, so we use a Deque structure, which have a good performance in pop up ../ pre path and append paths from begin to res string
     *
     * Time: O(n) split + O(n) if all paths are 1 char and split array do have n length + O(n) append final result string = O(n)
     * Space: O(n) split array + O(n) queue + O(n) final string = O(n)
     */

    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return path;
        String[] splits = path.split("/");
        //Has "" because when format is like //a there will be a array with {"", "a"}
        HashSet<String> set = new HashSet<>(Arrays.asList("..", ".", ""));
        Deque<String> queue = new LinkedList<>();
        for (int i = 0; i < splits.length; i++) {
            if (splits[i].equals("..") && !queue.isEmpty()) queue.pollLast();
            else if (!set.contains(splits[i])) queue.offerLast(splits[i]);
        }
        //StringBuilder is better than "/" + "Splits[i]", it will alloc new memory for string every time string + string
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            builder.append("/");
            builder.append(queue.pollFirst());
        }
        return builder.length() == 0 ? "/" : builder.toString();
    }

}
