package bg.leetcode.exercises.itenev.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Suppose we abstract our file system by a string in the following manner:
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 *  dir
 *     subdir1
 *     subdir2
 *         file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 *
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 *  dir
 *     subdir1
 *         file1.ext
 *         subsubdir1
 *     subdir2
 *         subsubdir2
 *             file2.ext
 * The directory dir contains two sub-directories subdir1 and subdir2.
 * subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1.
 * subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
 *
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system.
 * For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
 * and its length is 32 (not including the double quotes).
 *
 * Given a string representing the file system in the above format,
 * return the length of the longest absolute path to file in the abstracted file system.
 * If there is no file in the system, return 0.
 *
 */
public class LongestAbsoluteFilePath {

    public int lengthLongestPath(String input) {
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length+1];
        int maxLen = 0;
        for(String s:paths){
            int lev = s.lastIndexOf("\t")+1;
            int curLen = stack[lev+1] = stack[lev]+s.length()-lev+1;
            if(s.contains("."))
                maxLen = Math.max(maxLen, curLen-1);
        }
        return maxLen;
    }

    /*************************************************************************/

    public int lengthLongestPath2(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for(String s:input.split("\n")){
            int lev = s.lastIndexOf("\t")+1; // number of "\t"
            while(lev+1<stack.size())
                stack.pop(); // find parent

            int len = stack.peek()+s.length()-lev+1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if(s.contains("."))
                maxLen = Math.max(maxLen, len-1);
        }
        return maxLen;
    }

    /*************************************************************************/

    public int lengthLongestPath3(String input) {
        String[] tokens = input.split("\n");
        int result = 0;
        int curLen = 0;
        Stack<Integer> stack = new Stack<>();

        for (String s : tokens) {
            int level = countLevel(s);

            // if current directory/file depth is lower that the top directory/file on the stack, pop from stack
            while (stack.size() > level) {
                curLen -= stack.pop();
            }

            // +1 here because a "/" needs to be counted following each diretory
            int len = s.replaceAll("\t", "").length() + 1;
            curLen += len;

            // if s contains ".", we have found a file!
            if (s.contains(".")) {
                result = Math.max(curLen - 1, result);
            }
            stack.add(len);
        }
        return result;
    }

    private int countLevel(String s) {
        String cur = s.replaceAll("\t", "");
        return s.length() - cur.length();
    }
}
