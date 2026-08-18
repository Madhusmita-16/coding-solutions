import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();

        // Base index before the start of a valid substring
        stack.push(-1);

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // Remove the matching '('
                stack.pop();

                if (stack.isEmpty()) {
                    // Current ')' cannot be matched
                    stack.push(i);
                } else {
                    // Length of current valid substring
                    maxLength = Math.max(
                        maxLength,
                        i - stack.peek()
                    );
                }
            }
        }

        return maxLength;
    }
}