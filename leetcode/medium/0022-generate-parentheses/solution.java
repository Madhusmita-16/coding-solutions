import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack("", 0, 0, n, result);

        return result;
    }

    private void backtrack(
            String current,
            int open,
            int close,
            int n,
            List<String> result) {

        // A complete valid combination
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // We can add an opening bracket if we haven't used all n
        if (open < n) {
            backtrack(current + "(", open + 1, close, n, result);
        }

        // We can add a closing bracket only when
        // there are unmatched opening brackets
        if (close < open) {
            backtrack(current + ")", open, close + 1, n, result);
        }
    }
}