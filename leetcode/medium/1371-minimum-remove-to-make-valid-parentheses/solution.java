class Solution {
    public String minRemoveToMakeValid(String s) {

        StringBuilder result = new StringBuilder();
        int open = 0;

        // First pass:
        // Remove invalid ')'
        for (char c : s.toCharArray()) {

            if (c == '(') {
                open++;
                result.append(c);
            } 
            else if (c == ')') {
                if (open > 0) {
                    open--;
                    result.append(c);
                }
            } 
            else {
                result.append(c);
            }
        }

        // Remove extra '(' from right to left
        for (int i = result.length() - 1; i >= 0 && open > 0; i--) {
            if (result.charAt(i) == '(') {
                result.deleteCharAt(i);
                open--;
            }
        }

        return result.toString();
    }
}