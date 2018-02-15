import java.util.Stack;

public class LongestAbsoluteFilePath {

    //TAG: Google
    //TAG: Stack
    //Difficulty: Medium

    /**
     * 388. Longest Absolute File Path
     * Suppose we abstract our file system by a string in the following manner:

     The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

     dir
     subdir1
     subdir2
     file.ext
     The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

     The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

     dir
     subdir1
     file1.ext
     subsubdir1
     subdir2
     subsubdir2
     file2.ext
     The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

     We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

     Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

     Note:
     The name of a file contains at least a . and an extension.
     The name of a directory or sub-directory will not contain a ..
     Time complexity required: O(n) where n is the size of the input string.

     Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
     */

    /**
     * Solution:
     * Use stack to filer dir or file,
     *      if peek() is larger or equal level than current dir, pop until current dir is smallest
     *      then level is deeper, push to stack
     *          if is File update global max len
     *
     *  Time: O(n) split + O(n) loop + buildNode + average O(1) for each node as push and pop once = O(n)
     *  Space: O(n) array + O(n) Stack = O(n)
     */
    public int lengthLongestPath(String input) {
        Stack<PathNode> stack = new Stack<>();
        String[] strs = input.split("\\n");
        int max = 0, curLen = 0;
        for (int i = 0; i < strs.length; i++) {
            PathNode newNode = buildPathNode(strs[i]);
            /*
            First pop all larger or equal level nodes, so that next could push new node without more check, make code
            more lint
             */
            while (!stack.isEmpty() && stack.peek().degree >= newNode.degree) {
                PathNode pop = stack.pop();
                curLen -= pop.name.length();
            }
            stack.push(newNode);
            curLen += newNode.name.length();
            //Only if node is file, update global max
            if (newNode.isFile) max = Math.max(max, curLen);
        }
        return max;
    }

    //This builder used not only this question but could output longest path string
    private PathNode buildPathNode(String str) {
        int i = 0;
        while (i < str.length() && str.charAt(i) == '\t') {
            i++;
        }
        String name = i < str.length() ? str.substring(i) : "";
        boolean isFile = name.contains(".");
        if (!isFile) name += "|";
        return new PathNode(name, i, isFile);
    }

    class PathNode {
        String name;
        int degree;
        boolean isFile;
        public PathNode(String name, int degree, boolean isFile) {
            this.name = name;
            this.degree = degree;
            this.isFile = isFile;
        }
    }

}
